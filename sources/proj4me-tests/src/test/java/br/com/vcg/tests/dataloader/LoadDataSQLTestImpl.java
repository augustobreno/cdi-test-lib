package br.com.vcg.tests.dataloader;

import javax.inject.Inject;

import org.junit.Assert;

import br.com.vcg.tests.dataloader.LoadData;
import br.com.vcg.tests.util.QuerierUtil;

/**
 * Testa o uso da anotação {@link LoadData} em classes de teste para carregamento de dados com Beans.
 * @author augusto
 *
 */
public class LoadDataSQLTestImpl implements LoadDataSQLTestDef {

	@Inject
	private QuerierUtil querierUtil;
	
	@Override
	public void loadSQLDataOnTypeTest() {
		// garantindo que há apenas 1 registro na base de dados
		long count = querierUtil.executeCountQuery("select count(uf) from UF uf");
		Assert.assertEquals(1, count);
		
		// buscando o registro para confirmação
		count = querierUtil.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "aa");
		Assert.assertEquals(1, count);		
	}
	
	/* (non-Javadoc)
	 * @see br.com.vcg.tests.dataloader.cdi.LoadDataSQLTestDef#loadSQLDataInMethodTest()
	 */
	@Override
	public void loadSQLDataOnMethodTest() {
		// garantindo que há apenas 2 registro na base de dados
		long count = querierUtil.executeCountQuery("select count(uf) from UF uf");
		Assert.assertEquals(2, count);
		
		// buscando o registro para confirmação
		count = querierUtil.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "aa");
		Assert.assertEquals(1, count);
		
		count = querierUtil.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "bb");
		Assert.assertEquals(1, count);		
	}
	
}
