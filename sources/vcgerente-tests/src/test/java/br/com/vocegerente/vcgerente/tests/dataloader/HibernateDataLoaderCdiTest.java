package br.com.vocegerente.vcgerente.tests.dataloader;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vocegerente.vcgerente.tests.LocalTransactionTestBase;
import br.com.vocegerente.vcgerente.tests.dataloader.HibernateDataLoader;

/**
 * Teste para a classe {@link HibernateDataLoader}
 * @author augusto
 *
 */
public class HibernateDataLoaderCdiTest extends LocalTransactionTestBase implements HibernateDataLoaderTestDef {

	@Inject
	private HibernateDataLoaderTestImpl tester;
	
	@Override
	@Test
	public void simpleDataLoaderTest() throws Exception {
		tester.simpleDataLoaderTest();		
	}
}
