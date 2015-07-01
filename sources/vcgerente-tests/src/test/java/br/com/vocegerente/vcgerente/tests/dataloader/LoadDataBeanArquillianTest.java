package br.com.vocegerente.vcgerente.tests.dataloader;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vocegerente.vcgerente.tests.arquillian.ArquillianTestBase;

/**
 * Testa o uso da anotação {@link LoadData} em classes de teste para carregamento de scripts SQL.
 * @author augusto
 *
 */
@LoadData(dataLoader=UF_aa_DataLoader.class)
public class LoadDataBeanArquillianTest extends ArquillianTestBase implements LoadDataBeanTestDef {

	@Inject
	private LoadDataBeanTestImpl tester;
	
	@Override
	@Test
	public void loadDataInTypeTest() {
		tester.loadDataInTypeTest();
	}
	
	@Override
	@LoadData(dataLoader=UF_bb_DataLoader.class)
	@Test
	public void loadDataInMethodTest() {
		tester.loadDataInMethodTest();;
	}
	
	@Override
	@LoadData(dataLoader=UF_bb_DataLoader.class, precedence=2)
	@Test
	public void precedenceTest() {
		tester.precedenceTest();
	}
}
