package br.com.vocegerente.vcgerente.tests.jpa;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 * Producer para o bean {@link JPAStandalone}
 *
 * @author augusto
 *
 */
public class JpaStandaloneProducer {

    @Inject
    private JpaStandaloneRepository repository;
    
    /**
     * Recuper o primeiro {@link JPAStandalone} encontrado em {@link JpaStandaloneRepository}.
     * Este Producer é indicado para ser usado em aplicações que utilizam apenas um Persistence Unit configurado. Nos demais casos
     * é sugerido criar outros producers com qualifiers que atendam a cada Persistence Unit existente.
     *
     * @return {@link JPAStandalone} recuperado do Repositório.
     */
    @Produces
    @Default
    public JPAStandalone createJpaStandAlone(InjectionPoint iPoint) {
        JPAStandalone jpaStandalone = repository.getAll().iterator().next();
        return jpaStandalone;
    }

}
