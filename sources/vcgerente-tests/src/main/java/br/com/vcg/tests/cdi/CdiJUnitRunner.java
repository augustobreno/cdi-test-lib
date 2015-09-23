package br.com.vcg.tests.cdi;

import org.jboss.weld.context.RequestContext;
import org.jboss.weld.context.unbound.UnboundLiteral;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * Junit Runner que inicializa um container CDI (Weld) para permitir a execução de testes
 * com os recursos de Injeção de Dependência.
 * @author Augusto
 *
 */
public class CdiJUnitRunner extends BlockJUnit4ClassRunner {

    private final Class<?> clazz;
    private Weld weld;
    private WeldContainer container;
    
    public CdiJUnitRunner(final Class<?> clazz) throws InitializationError {
        super(clazz);
        this.clazz = clazz;
        this.weld = new Weld();
        this.container = weld.initialize();
        
        startScopes();
    }

    /**
     * Inicia os escopos necessários para a execução dos testes unitários. É importantes, principalmente,
     * para a disponibilização de escopos nativos do ambiente web (request, session, etc).
     */
    protected void startScopes() {
        startRequestScope();
        
    }

    private void startRequestScope() {
        RequestContext requestContext= container.instance().select(RequestContext.class, UnboundLiteral.INSTANCE).get();
        requestContext.activate();
    }

    @Override
    protected Object createTest() throws Exception {
    	/*
    	 * O objeto de teste é criado junto ao container CDI, assim já será possível
    	 * utilizar a injeção de dependência dentro da própria classe de teste.
    	 */
		final Object test = container.instance().select(clazz).get();
        return test;
    }

}
