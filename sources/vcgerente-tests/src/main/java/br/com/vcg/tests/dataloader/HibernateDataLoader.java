package br.com.vcg.tests.dataloader;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import br.com.vcg.tests.cdi.ForTest;
import br.com.vcg.tests.util.QuerierUtil;

/**
 * Data loader que utiliza o EntityManager para carregamento dos dados.
 * @author augusto
 *
 */
public abstract class HibernateDataLoader implements DataLoader {

	private Logger logger;
	
	/** Para acesso à conexão com a base de dados. */
	private EntityManager entityManager;

    private QuerierUtil querier;
	
	protected Session getSession() {
		return  entityManager != null ? (Session) entityManager.getDelegate() : null;
	}
	
	// getters and setters

	protected Logger getLogger() {
		return logger;
	}

	@Inject
	protected void setLogger(@ForTest Logger logger) {
		this.logger = logger;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Inject
	protected void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
    public QuerierUtil getQuerier() {
        if (this.querier == null) {
            this.querier = new QuerierUtil(getEntityManager());
        }
        return querier;
    }	
}
