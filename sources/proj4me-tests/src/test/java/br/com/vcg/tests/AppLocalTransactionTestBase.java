package br.com.vcg.tests;

import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.deltaspike.SupportDeltaspikeCore;
import org.junit.runner.RunWith;

import br.com.vcg.tests.alternatives.AlternativeLocalEntityManagerProducer;
import br.com.vcg.tests.cdi.LoggerForTestProducer;
import br.com.vcg.tests.dataloader.LoadDataInterceptor;
import br.com.vcg.tests.dataloader.LoadDatasInterceptor;
import br.com.vcg.tests.jpa.JpaStandaloneProducer;

/**
 * Configura as dependências necessárias para utilização de {@link LocalTransactionTestBase}
 * no contexto da aplicação local. 
 * @author Augusto
 */
@AdditionalClasses({LoggerForTestProducer.class, JpaStandaloneProducer.class, LoadDataInterceptor.class, LoadDatasInterceptor.class})
@ActivatedAlternatives({AlternativeLocalEntityManagerProducer.class})
@SupportDeltaspikeCore
@RunWith(CdiRunner.class)
public class AppLocalTransactionTestBase extends LocalTransactionTestBase {

}
