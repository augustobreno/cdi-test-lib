package br.com.vcg.tests.cdiunit;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeCore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.vcg.tests.cdi.CDI;
import br.com.vcg.tests.domain.UF;

@RunWith(CdiRunner.class)
@SupportDeltaspikeCore
public class CdiUnitTest {

    @Inject
    private UF uf;
    
    @Test
    public void UfTest() {
        Assert.assertNotNull(uf);
        uf = CDI.lookup(UF.class);
        Assert.assertNotNull(uf);
    }
}
