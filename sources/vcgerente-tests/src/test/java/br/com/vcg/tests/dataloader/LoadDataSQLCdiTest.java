package br.com.vcg.tests.dataloader;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalPackages;
import org.junit.Test;

import br.com.vcg.tests.AppLocalTransactionTestBase;

/**
 * Testa o uso da anotação {@link LoadData} em classes de teste para carregamento de dados com Beans.
 * @author augusto
 *
 */
@LoadData(sql="dataloader/uf_aa.sql")
@AdditionalPackages({LoadDataSQLCdiTest.class})
public class LoadDataSQLCdiTest extends AppLocalTransactionTestBase implements LoadDataSQLTestDef {

	@Inject
	private LoadDataSQLTestImpl tester;
	
	@Override
	@Test
	public void loadSQLDataOnTypeTest() {
		tester.loadSQLDataOnTypeTest();
	}
	
	@Override
	@LoadData(sql="dataloader/uf_bb.sql")
	@Test
	public void loadSQLDataOnMethodTest() {
		tester.loadSQLDataOnMethodTest();
	}
	
}
