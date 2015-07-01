package br.com.vocegerente.vcgerente.tests.dataloader;

import javax.inject.Inject;

import org.junit.Test;

import br.com.vocegerente.vcgerente.tests.LocalTransactionTestBase;

/**
 * Testa o uso da anotação {@link LoadDatas}, misturando as opções de script e Bean.
 * @author augusto
 *
 */
@LoadDatas ({
	@LoadData(sql="dataloader/uf_aa.sql"),
	@LoadData(dataLoader=UF_bb_DataLoader.class, precedence=3)
})	
public class LoadDatasMixCdiTest extends LocalTransactionTestBase implements LoadDatasMixTestDef {

	@Inject
	private LoadDatasMixTestImpl tester;
	
	@Override
	@Test
	public void loadDatasOnTypeTest() {
		tester.loadDatasOnTypeTest();
	}

	@Override
	@LoadDatas ({
		@LoadData(sql="dataloader/uf_cc.sql"),
		@LoadData(dataLoader=UF_dd_DataLoader.class)
	})
	@Test
	public void loadDatasOnMethodTest() {
		tester.loadDatasOnMethodTest();
	}

}
