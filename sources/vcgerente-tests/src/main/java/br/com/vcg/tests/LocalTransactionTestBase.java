package br.com.vcg.tests;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

import br.com.vcg.tests.jpa.JPAStandalone;
import br.com.vcg.tests.jpa.JpaStandaloneRepository;
import br.com.vcg.tests.util.PersistenceUnitNotReadyException;
import br.com.vcg.tests.util.QuerierUtil;

/**
 * Classe base com comportamento comum para testes unitários que utilizam um
 * contexto transacional. Gerencia o ciclo de vida do JUnit, JPA, suporta CDI, e
 * outros recursos necessários para testes.
 * 
 * Cada método de teste é isolado em uma transação, não interferindo um no outro. Ao final de cada método,
 * é realizado rollback na transação.
 * 
 * @author Augusto
 * 
 */
@Ignore
public class LocalTransactionTestBase extends TestBase {

	/**
	 * Para a acesso a todas as configurações de {@link JPAStandalone} configuradas.
	 */
	@Inject
	private JpaStandaloneRepository jpaRepository;
	
	/**
	 * Executado antes de cada método de teste. Realiza o controle do início da
	 * transação.
	 */
	@Before
	public void beforeEachTest() {
		for (JPAStandalone jpa : jpaRepository.getAll()) {
			jpa.startSession();
			jpa.startTransaction();
		}
	}

	/**
	 * Executado após de cada método de teste. Realiza o controle do término da
	 * transação.
	 */
	@After
	public void afterEachTest() {
		for (JPAStandalone jpa : jpaRepository.getAll()) {
			jpa.rollbackTransaction();
		}
	}

	/**
	 * @param puName Nome da Persistence Unit para identificação do {@link EntityManager} 
	 * @return EntityManager associado ao {@link JPAStandalone}, associando à Persitence Unit informada.
	 */
	public EntityManager getEntityManager(String puName) {
		JPAStandalone jpaStandalone = jpaRepository.get(puName);
		if (jpaStandalone != null) {
			return jpaStandalone.getEm();
		} else {
			throw new PersistenceUnitNotReadyException("Não foi encontrado uma conexão configurada para a Persistence Unit informada:" + puName);
		}
	}

	/**
	 * @param puName Nome da Persistence Unit para identificação do {@link EntityManager} 
	 * @return {@link EntityManager} associado ao {@link JPAStandalone}, associado à Persistence Unit informada.
	 */
	public JPAStandalone getJpa(String puName) {
		return jpaRepository.get(puName);
	}

	/**
	 * @param puName Nome da Persistence Unit para identificação do {@link EntityManager} 
	 * @return {@link QuerierUtil} configurada com o {@link EntityManager} associado à Persistence Unit informada. Null caso a Persistence Unit não esteja registrada.
	 */
	public QuerierUtil getQuerier(String puName) {
		JPAStandalone jpa = getJpa(puName);
		return jpa != null ? jpa.getQuerier() : null;
	}
	
	protected JpaStandaloneRepository getJpaRepository() {
		return jpaRepository;
	}
	
}