package br.com.vcg.tests.dataloader;


public interface LoadDataSQLTestDef {

	/**
	 * Verifica o funcionamento da anotação sobre a classe.
	 */
	public abstract void loadSQLDataOnTypeTest();

	/**
	 * Verifica o funcionamento da anotação sobre um método.
	 * Neste caso, deve considerar o que há sobre a classe e sobre o método.
	 */
	public abstract void loadSQLDataOnMethodTest();

}