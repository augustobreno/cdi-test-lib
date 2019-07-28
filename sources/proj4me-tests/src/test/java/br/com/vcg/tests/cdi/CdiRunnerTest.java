package br.com.vcg.tests.cdi;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.vcg.tests.util.EmptyBeanA;

@RunWith(CdiRunner.class)
public class CdiRunnerTest {

	@Inject
	private EmptyBeanA emptyBeanA;
	
	/**
	 * Verifica se a injeção de dependência está sendo habilitada para a classe de teste
	 * que executa com {@link CdiJUnitRunner}
	 */
	@Test
	public void cdiBeanInjectionTest() {
		Assert.assertNotNull(emptyBeanA);
	}
	
	/**
	 * Verifica se a injeção de pripriedades aninhadas está funcionando para a classe de teste
	 * que executa com {@link CdiJUnitRunner}
	 */
	@Test
	public void cdiBeanNestedInjectionTest() {
		Assert.assertNotNull(emptyBeanA.getEmptyBeanB());
	}
	
}
