package br.com.vcg.tests.dataloader;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vcg.tests.AppLocalTransactionTestBase;


/**
 * Testes da classe {@link SqlDataLoader}
 * @author augusto
 */
public class DataLoaderSQLCdiTest extends AppLocalTransactionTestBase implements DataLoaderSQLTestDef {

	@Inject
	private DataLoaderSQLTestImpl tester;
	
	/**
	 * Tenta executar um script existente na raiz do projeto, considerando seu caminho relativo.
	 */
	@Test
	public void loadScriptTest() throws Exception {
		tester.loadScriptTest();
	}
	
}
