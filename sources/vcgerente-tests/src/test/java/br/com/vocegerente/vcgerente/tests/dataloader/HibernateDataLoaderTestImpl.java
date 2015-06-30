package br.com.vocegerente.vcgerente.tests.dataloader;

import javax.inject.Inject;

import org.junit.Assert;

import br.com.vocegerente.vcgerente.tests.dataloader.HibernateDataLoader;
import br.com.vocegerente.vcgerente.tests.util.QuerierUtil;

/**
 * Teste para a classe {@link HibernateDataLoader}
 * @author augusto
 *
 */
public class HibernateDataLoaderTestImpl implements HibernateDataLoaderTestDef {

	@Inject
	private UF_aa_DataLoader ufDataLoader;
	
	@Inject
	private QuerierUtil querierUtil;
	
	/* (non-Javadoc)
	 * @see br.com.vocegerente.vcgerente.tests.dataloader.cdi.HibernateDataLoaderTestDef#simpleDataLoaderTest()
	 */
	@Override
	public void simpleDataLoaderTest() throws Exception {
		// garante que não há nenhuma uf com sigla "aa"
		long count = querierUtil.executeCountQuery("select count(uf) from UF uf where uf.sigla=?", "aa");
		Assert.assertEquals(0, count);
		
		ufDataLoader.load();
		
		// buscando o registro para confirmação
		count = querierUtil.executeCountQuery("select count(uf) from UF uf where uf.sigla=?", "aa");
		Assert.assertEquals(1, count);		
	}
}
