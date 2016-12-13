package br.com.vcg.tests.jpa;

import java.util.Collection;
import java.util.logging.Logger;

import javax.enterprise.util.AnnotationLiteral;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeCore;
import org.junit.Assert;
import org.junit.Test;

import br.com.vcg.tests.CdiTestBase;
import br.com.vcg.tests.cdi.CDI;
import br.com.vcg.tests.cdi.ForTest;
import br.com.vcg.tests.cdi.LoggerProducer;

/**
 * Testa recursos da classe {@link JpaStandaloneRepository}
 * @author augusto
 */
@SupportDeltaspikeCore
@AdditionalClasses({LoggerProducer.class})
public class JpaStandaloneRepositoryTest extends CdiTestBase {

	/**
	 * Testa execução do método {@link JpaStandaloneRepository#scanForPersistenceUnits()}
	 */
	@SuppressWarnings("serial")
	@Test
	public void testPersistenceUnitsScanner() {
		JpaStandaloneRepository repository = new JpaStandaloneRepository();
		repository.setLog(CDI.lookup(Logger.class, new AnnotationLiteral<ForTest>() {}));
		repository.scanForPersistenceUnits();
		
		
		Collection<JPAStandalone> allPU = repository.getAll();
		Assert.assertEquals(1, allPU.size());
		Assert.assertEquals("default_pu", allPU.iterator().next().getPersistenceUnitName());
	}

}
