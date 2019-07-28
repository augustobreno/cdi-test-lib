package br.com.vcg.tests.dataloader;

import br.com.vcg.tests.dataloader.HibernateDataLoader;
import br.com.vcg.tests.domain.UF;

/**
 * Bean DataLoader para execução de testes unitários. Insere um
 * objeto {@link UF} com sigla "aa".
 * @author augusto
 *
 */
public class UF_aa_DataLoader extends HibernateDataLoader {

	@Override
	public void load() throws Exception {
		UF uf = new UF("aa");
		getEntityManager().persist(uf);
		getEntityManager().flush();
	}

}
