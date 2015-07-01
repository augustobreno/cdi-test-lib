package br.com.vocegerente.vcgerente.tests.dataloader;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vocegerente.vcgerente.tests.arquillian.ArquillianTestBase;

/**
 * Testes da classe {@link SqlDataLoader}
 * 
 * @author augusto
 */
public class DataLoaderSQLArquillianTest extends ArquillianTestBase implements DataLoaderSQLTestDef {

	@Inject
	private DataLoaderSQLTestImpl tester;

	/**
	 * Tenta executar um script existente na raiz do projeto, considerando seu
	 * caminho relativo.
	 */
	@Test
	public void loadScriptTest() throws Exception {
		tester.loadScriptTest();
	}

}
