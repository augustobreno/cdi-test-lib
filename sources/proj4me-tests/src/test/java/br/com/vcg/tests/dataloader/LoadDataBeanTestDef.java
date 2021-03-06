package br.com.vcg.tests.dataloader;

import br.com.vcg.tests.dataloader.DataLoader;



public interface LoadDataBeanTestDef {

	/**
	 * Verifica o funcionamento da anotação sobre a classe.
	 */
	public abstract void loadDataInTypeTest();

	/**
	 * Verifica o funcionamento da anotação sobre um método.
	 * Neste caso, deve considerar o que há sobre a classe e sobre o método.
	 */
	public abstract void loadDataInMethodTest();

	/**
	 * Teste de precendência de execução do {@link DataLoader}
	 */
	public abstract void precedenceTest();

}