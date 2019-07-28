package br.com.vcg.tests.dataloader;

import br.com.vcg.tests.dataloader.HibernateDataLoader;
import br.com.vcg.tests.domain.UF;

/**
 * Data loader que insere uma UF com nome "A1". Porém define um aninhamento com o Data loader {@link DataLoaderAninhado_2}.
 * @author augusto
 */
@LoadData(dataLoader={DataLoaderAninhado_2.class})
public class DataLoaderAninhado_1 extends HibernateDataLoader {

	@Override
	public void load() throws Exception {
		UF uf = new UF("A1");
		getEntityManager().persist(uf);
		getEntityManager().flush();
	}

}
