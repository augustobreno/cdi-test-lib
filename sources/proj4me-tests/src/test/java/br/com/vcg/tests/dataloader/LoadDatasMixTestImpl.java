package br.com.vcg.tests.dataloader;

import javax.inject.Inject;

import org.junit.Assert;

import br.com.vcg.tests.dataloader.LoadDatas;
import br.com.vcg.tests.util.QuerierUtil;

/**
 * Testa o uso da anotação {@link LoadDatas}, misturando as opções de script e Bean.
 * @author augusto
 *
 */
public class LoadDatasMixTestImpl implements LoadDatasMixTestDef {

	@Inject
	private QuerierUtil querier;
	
	@Override
	public void loadDatasOnTypeTest() {
		// garantindo que há apenas 2 registros na base de dados
		long count = querier.executeCountQuery("select count(uf) from UF uf");
		Assert.assertEquals(2, count);
		
		// buscando o registro para confirmação
		count = querier.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "aa");
		Assert.assertEquals(1, count);
		
		count = querier.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "bb");
		Assert.assertEquals(1, count);		
	}

	@Override
	public void loadDatasOnMethodTest() {
		// garantindo que há 4 registros na base de dados
		long count = querier.executeCountQuery("select count(uf) from UF uf");
		Assert.assertEquals(4, count);
		
		// buscando o registro para confirmação
		count = querier.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "aa");
		Assert.assertEquals(1, count);
		
		count = querier.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "bb");
		Assert.assertEquals(1, count);
		
		count = querier.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "cc");
		Assert.assertEquals(1, count);
		
		count = querier.executeCountQuery("select count(uf) from UF uf where uf.sigla=?0", "dd");
		Assert.assertEquals(1, count);
	}

}
