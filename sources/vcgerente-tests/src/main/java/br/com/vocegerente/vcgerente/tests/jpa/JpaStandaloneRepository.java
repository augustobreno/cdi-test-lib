package br.com.vocegerente.vcgerente.tests.jpa;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import br.com.vocegerente.vcgerente.tests.cdi.ForTest;

/**
 * Repositório para armazenar todas as instâncias de JpaStandalone criadas durantes
 * os testes executados, com o propósito de promover o reuso das configuraçoes
 * lidas, evitando o carregamento da configuração de uma Persistence Unit repetidamente.
 * 
 * @author augusto
 */
@Singleton
public class JpaStandaloneRepository {

	@Inject
	@ForTest
	private Logger log;
	
    /**
     * Mapa com as Persistence Units configuradas para execução dos testes
     * unitários. A chave no mapa é representada pelo nome da PersistenceUnit
     * (equivalente ao configurado no arquivo persistence.xml).
     */
    private static Map<String, JPAStandalone> jpas = new HashMap<String, JPAStandalone>();
    
    /**
     * Armazena referências para os arquivos (persistence.xml) já carregados.
     */
    private static Set<URL> loadPesistenceFiles = new HashSet<URL>();

    /**
     * Lê o arquivo META-NF/persistence.xml na procura dos nomes das persistence units configuradas.
     */
    @PostConstruct
    public void scanForPersistenceUnits() {
    	try {
    		String persistenceFileName = "META-INF/persistence.xml";
    		if (!loaded(persistenceFileName)) {
    			
				InputStream persistenceStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(persistenceFileName);
	    		log.log(Level.FINE, persistenceFileName + persistenceStream == null ? " não encontrado." : "encontrado.");
	    		
	    		if (persistenceStream != null) {
	    			for (LineIterator iterator = IOUtils.lineIterator(persistenceStream, "UTF-8"); iterator.hasNext();) {
						
	    				String linha = iterator.next();
						if (linha.contains("persistence-unit")) {
							
							log.log(Level.FINE, "Processando linha: " + linha);
							String[] tokens = linha.split(" ");
							for (String token : tokens) {
								
								if (token.startsWith("name")) {
									String puName = token.replace("name", "")
														.replace("=", "")
														.replace("\"", "");
									register(puName);	
								}
							}
						}
					}
	    			markAsLoaded(persistenceFileName);
	    		}
	    		
    		}
		} catch (IOException e) {
			log.log(Level.WARNING, "Não foi possível ler persistence.xml para descobrir os nomes dos Persistence Units configurados", e);
		}
    }
    
    /**
     * @return true se o arquivo já foi carregado uma vez.
     */
    private boolean loaded(String persistenceFileName) {
    	URL resource = Thread.currentThread().getContextClassLoader().getResource(persistenceFileName);
		return loadPesistenceFiles.contains(resource);
	}
    
    private void markAsLoaded(String persistenceFileName) {
    	URL resource = Thread.currentThread().getContextClassLoader().getResource(persistenceFileName);
    	loadPesistenceFiles.add(resource);
	}

	/**
     * Recupera uma instância de {@link JPAStandalone}, caso exista no repositorio.
     * @param name Nome da instância para recuperar.
     * @return Instância recuperada pelo nome, ou null caso o nome não identifique nenhum objeto.
     */
    public JPAStandalone get(String name) {
    	return jpas.get(name);
    }
	
    /**
     * Registra uma instância de {@link JPAStandalone}.
     * @param standalone Instância para registrar. O nome da instância será igual ao nome da Persistence Unit associada.
     */
    public void register(JPAStandalone standalone) {
    	jpas.put(standalone.getPersistenceUnitName(), standalone);
    }
    
    /**
     * Registra uma instância de {@link JPAStandalone}.
     * @param persistenceUnitName Nome da instância a ser registrada, que deverá constar no arquivo persistence.xml.
     * @return {@link JPAStandalone} criado e registrado.
     */
    public JPAStandalone register(String persistenceUnitName) {
    	if (!jpas.containsKey(persistenceUnitName)) {
    		log.log(Level.FINE, "Persitence Unit " + persistenceUnitName + " já registrado, retornando instância pré-existente.");
    		
    		JPAStandalone jpaStandalone = createJpaStandalone(persistenceUnitName);
    		jpas.put(persistenceUnitName, jpaStandalone);
    		return jpaStandalone;
    	} else {
    		return jpas.get(persistenceUnitName);
    	}
    }
    
    /**
     * Registra, se necessário, e devolve uma instância de {@link JPAStandalone}.
     * @param persistenceUnitName Nome da instância a ser registrada e devolvida, que deverá constar no arquivo persistence.xml.
     * @return {@link JPAStandalone} registrado ou recuperado. 
     */
    public JPAStandalone getOrRegister(String persistenceUnitName) {
    	JPAStandalone jpaStandalone = get(persistenceUnitName);
    	if (jpaStandalone == null) {
    		jpaStandalone = register(persistenceUnitName);
    	}
    	return jpaStandalone;
    }
    
    /**
     * Factory Method para criação de um {@link JPAStandalone}.
     *
     * @param persistenceUnitName Nome da unidade de persistência (devidamente
     * configurada em um persistence.xml).
     * @return {@link JPAStandalone} devidamente configurada a partir das
     * configurações encontradas na persistence unit encontrada no arquivo
     * persistence.xml.
     */
    protected JPAStandalone createJpaStandalone(String persistenceUnitName) {
        return new JPAStandalone(persistenceUnitName);
    }
    
    /**
     * @return Todas as conexões configuradas.
     */
    public Collection<JPAStandalone> getAll() {
    	return jpas.values();
    }

	protected Logger getLog() {
		return log;
	}

	protected void setLog(Logger log) {
		this.log = log;
	}
    
}
