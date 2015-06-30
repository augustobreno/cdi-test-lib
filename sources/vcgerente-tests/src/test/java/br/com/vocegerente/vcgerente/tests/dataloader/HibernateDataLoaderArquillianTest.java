package br.com.vocegerente.vcgerente.tests.dataloader;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vocegerente.vcgerente.tests.arquillian.ArquillianTestBase;
import br.com.vocegerente.vcgerente.tests.dataloader.HibernateDataLoader;

/**
 * Teste para a classe {@link HibernateDataLoader}
 * @author augusto
 *
 */
public class HibernateDataLoaderArquillianTest extends ArquillianTestBase implements HibernateDataLoaderTestDef {

	@Inject
	private HibernateDataLoaderTestImpl tester;
	
	@Override
	@Test
	public void simpleDataLoaderTest() throws Exception {
		tester.simpleDataLoaderTest();		
	}
}
