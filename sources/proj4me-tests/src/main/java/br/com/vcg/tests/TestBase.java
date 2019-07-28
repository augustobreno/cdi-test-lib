package br.com.vcg.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

/**
 * Classe base com comportamento comum para testes unitários simples (pojo).
 * Gerencia o ciclo de vida do JUnit.
 * 
 * @author Augusto
 * 
 */
@Ignore
public abstract class TestBase {

    /**
     * Executado antes de cada método de teste.
     */
    @Before
    public void beforeEachTest() throws Exception {
    };

    /**
     * Executado após de cada método de teste.
     */
    @After
    public void afterEachTest() throws Exception  {
    };

    /**
     * Verifica se ambas as listas possuem os mesmos objetos. A ordem não é
     * verificada.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void assertContentEqual(Collection expected, Collection actual, Comparator comparator) {
        // verifica se há algum elemento da lista atual que não se encontra na
        // lista esperada
        TreeSet<Object> expectedSet = new TreeSet<>(comparator);
        expectedSet.addAll(expected);
        
        TreeSet<Object> actualSet = new TreeSet<>(comparator);
        actualSet.addAll(actual);        
        
        for (Object actualItem : actualSet) {
            assertTrue(
                    "A primeira lista (expected) não contem todos os objetos da segunda lista (actual): " + actualItem,
                    expectedSet.contains(actualItem));
        }

        // verifica se há algum elemento da lista esperada que não se encontra na
        // lista atual
        for (Object expectedItem : expectedSet) {
            assertTrue(
                    "A segunda lista (actual) não contem todos os objetos da primeira lista (expected)" + expectedItem,
                    actualSet.contains(expectedItem));
        }
    }

    public <T> void assertSize(Collection<T> expected, Collection<T> actual) {
        assertTrue("A lista atual não tem o mesmo número de elementos da lista esperada. Atual = " + actual.size()
                + " esperada = " + expected.size(), expected.size() == actual.size());
    }
    
    public <T> void assertSize(int expected, Collection<T> list) {
        assertTrue("A lista atual não tem o tamanho esperado. Atual = " + list.size()
                + " esperada = " + expected, expected == list.size());
    }

    /**
     * Verifica se ambas as listas possuem os mesmos objetos. A ordem não é
     * verificada.
     */
    public <T> void assertContentEqual(Collection<T> expected, Collection<T> actual) {

        boolean expectedContainsAllActual = expected.containsAll(actual);
        if (!expectedContainsAllActual) {
            List<T> diff = new ArrayList<>();
            actual.forEach(item -> {
                if (!expected.contains(item)) {
                    diff.add(item);
                }
            });
            Assert.fail("Os seguintes estão segunda lista e não estão na primeira: " + diff);
        }
        
        boolean actualContainsAllExpected = actual.containsAll(expected);
        if (!actualContainsAllExpected) {
            List<T> diff = new ArrayList<>();
            expected.forEach(item -> {
                if (!actual.contains(item)) {
                    diff.add(item);
                }
            });
            Assert.fail("Os seguintes estão primeira lista e não estão na segunda: " + diff);            
        }
    }
    
    /**
     * Verifica se ambas as listas possuem os mesmos objetos na mesma ordem.
     */
    public <T> void assertContentAndOrderEquals(List<T> expected, List<T> actual) {
        
        assertSize(expected, actual);
        
        for(int i = 0; i < actual.size(); i++){
            assertEquals("Foi identificado um elemento em ordem diferente",expected.get(i), actual.get(i)); 
        }

    }

    /**
     * Verifica se ambas as listas não possuem os mesmos objetos. A ordem não é
     * verificada.
     */
    public void assertContentNotEqual(List<?> expected, List<?> actual) {
        boolean hqlContemQBE = expected.containsAll(actual);
        boolean qbeContemHQL = actual.containsAll(expected);
        assertFalse("As listas NÃO deveriam conter dados equivalentes.",
                hqlContemQBE && qbeContemHQL);
    }

    /**
     * Verifica se a coleção é diferente de null e não vazia.
     * 
     * @param collection
     *            Para validação.
     */
    protected void assertNotEmpty(Collection<?> collection) {
        assertTrue(collection != null && !collection.isEmpty());
    }

    /**
     * Verifica se a coleção é diferente de null e vazia.
     * 
     * @param collection
     *            Para validação.
     */
    protected void assertEmpty(Collection<?> collection) {
        assertTrue(collection != null && collection.isEmpty());
    }
    
    /**
     * Verifica se a string é null ou empty.
     */
    protected void assertNullOrEmpty(String string) {
        assertTrue(string == null || string.trim().isEmpty());
    }    

    /**
     * Verifica se a coleção é null ou vazia.
     * 
     * @param collection
     *            Para validação.
     */
    protected void assertNullOrEmpty(Collection<?> collection) {
        assertTrue(collection == null || collection.isEmpty());
    }

}
