package br.com.vcg.tests.dataloader;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.junit.Assert;
import org.junit.Test;

import br.com.vcg.tests.AppLocalTransactionTestBase;
import br.com.vcg.tests.domain.UF;
import br.com.vcg.tests.util.QuerierUtil;

/**
 * Testes de aninhamento de {@link DataLoader}, ou seja, quando um dataloader
 * está anotado com outro dataloader, permitindo o reuno transparente de
 * loaders.
 * 
 * @author augusto
 */
@AdditionalClasses({DataLoaderAninhado_1.class, DataLoaderAninhado_2.class})
public class DataLoaderAninhadoTest extends AppLocalTransactionTestBase {

    @Inject
    private QuerierUtil querierUtil;
    
    /**
     * Anota este teste com o dataloader {@link DataLoaderAninhado_1}, e
     * verifica se o registro criado em {@link DataLoaderAninhado_2} tmb foi
     * cadastrado. A ordem de cadastramento tmb é verificada.
     */
    @Test
    @LoadData(dataLoader={DataLoaderAninhado_1.class})
    public void validaDataLoadersAninhadosTest() {
        List<UF> ufs = querierUtil.findAll(UF.class);
        Assert.assertEquals(2, ufs.size());
    }

}
