package br.com.vcg.tests.dataloader;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vcg.tests.AppLocalTransactionTestBase;

/**
 * Teste para a classe {@link HibernateDataLoader}
 * @author augusto
 *
 */
public class HibernateDataLoaderCdiTest extends AppLocalTransactionTestBase implements HibernateDataLoaderTestDef {

	@Inject
	private HibernateDataLoaderTestImpl tester;
	
	@Override
	@Test
	public void simpleDataLoaderTest() throws Exception {
		tester.simpleDataLoaderTest();		
	}
}
