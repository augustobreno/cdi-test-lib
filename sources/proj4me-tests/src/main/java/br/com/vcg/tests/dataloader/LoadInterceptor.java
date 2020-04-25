package br.com.vcg.tests.dataloader;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * Interceptador base para tratar os casos relacionados às anotações {@link LoadData} e {@link LoadDatas}.
 */

@SuppressWarnings("serial")
public abstract class LoadInterceptor implements Serializable {

	@Inject
	private LoadDataExecutor executor;
	
	/**
	 * Método interceptador que envolve a chamada ao componente
	 */
	@AroundInvoke
	public Object aroundInvoke(InvocationContext invocation) throws Exception {
		try {
			executor.process(invocation.getMethod());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invocation.proceed();
	}

}