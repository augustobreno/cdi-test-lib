package br.com.vocegerente.vcgerente.tests.jpa;

import java.util.Collection;
import java.util.logging.Logger;

import javax.enterprise.util.AnnotationLiteral;

import org.junit.Assert;
import org.junit.Test;

import br.com.vocegerente.vcgerente.tests.TestBase;
import br.com.vocegerente.vcgerente.tests.cdi.CDI;
import br.com.vocegerente.vcgerente.tests.cdi.ForTest;

/**
 * Testa recursos da classe {@link JpaStandaloneRepository}
 * @author augusto
 */
public class JpaStandaloneRepositoryTest extends TestBase {

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
