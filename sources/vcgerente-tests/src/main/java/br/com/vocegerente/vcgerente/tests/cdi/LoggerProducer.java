package br.com.vocegerente.vcgerente.tests.cdi;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Producer de um {@link Logger} para utilização em ambiente de testes.
 *
 * Serão produzidos vários tipos de loggers, mas todos são direcionados para o
 * Log4j2.
 *
 * @author augusto
 *
 */
public class LoggerProducer {

    private Class<?> getInjectionClass(InjectionPoint ip) {
        return ip != null ? ip.getMember().getDeclaringClass() : null;
    }

    @Produces
    @ForTest
    public Logger createLogger(InjectionPoint caller) {
		Class<?> injectionClass = getInjectionClass(caller);
		Logger logger = null;
		if (injectionClass == null) {
			logger = Logger.getLogger("NO_CLASS");
		} else {
			logger = Logger.getLogger(injectionClass.getName());
		}
   		return logger;
    }

}
