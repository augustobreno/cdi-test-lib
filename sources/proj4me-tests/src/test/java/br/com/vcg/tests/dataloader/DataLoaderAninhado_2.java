package br.com.vcg.tests.dataloader;

import br.com.vcg.tests.dataloader.HibernateDataLoader;
import br.com.vcg.tests.domain.UF;

/**
 * Data loader que insere uma UF com nome "A1". Porém define um aninhamento com o
 * @author augusto
 */
public class DataLoaderAninhado_2 extends HibernateDataLoader {

	@Override
	public void load() throws Exception {
		UF uf = new UF("A2");
		getEntityManager().persist(uf);
		getEntityManager().flush();
	}

}
