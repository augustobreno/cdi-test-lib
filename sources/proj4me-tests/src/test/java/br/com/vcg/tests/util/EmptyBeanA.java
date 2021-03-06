package br.com.vcg.tests.util;

import javax.inject.Inject;

/**
 * Bean para teste de injeção de dependências em ambiente de teste.
 * @author augusto
 */
public class EmptyBeanA implements EmptyBean {

	@Inject @QualifierB
	private EmptyBeanB emptyBeanB;

	public EmptyBeanB getEmptyBeanB() {
		return emptyBeanB;
	}

	public void setEmptyBeanB(EmptyBeanB emptyBeanB) {
		this.emptyBeanB = emptyBeanB;
	}
	
}
