package br.com.vcg.tests;

import org.jglue.cdiunit.CdiRunner;
import org.junit.runner.RunWith;

/**
 * Integra a classe a um Container CDI (o
 * que significa que Injeção de Dependências e todos os seus recursos são
 * suportados desde a classe de testes).
 * 
 * Utiliza cdi-unit na implementação padrão
 * @author Augusto
 *
 */
@RunWith(CdiRunner.class)
public class CdiTestBase extends TestBase {

}
