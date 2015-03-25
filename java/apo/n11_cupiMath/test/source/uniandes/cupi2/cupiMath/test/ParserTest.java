package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.CupiMath;
import uniandes.cupi2.cupiMath.mundo.Parser;
import uniandes.cupi2.cupiMath.mundo.Sqrt;
import uniandes.cupi2.cupiMath.mundo.Suma;

/**
 * Clase de pruebas para los m�todos del Parser. Debido a que cada prueba usa una expresi�n
 * diferente para validar cada m�todo, dentro de la misma prueba se define el escenario que va a usar
 * Es necesario correr las pruebas usando aserciones 
 */
public class ParserTest extends TestCase
{
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    
    /**
     * Verifica que se valide correctamente si un caracter es un d�gito
     * Usa como escenario de prueba la expresi�n "56"
     */
    public void testEsDigito()
    {
        Parser parser=new Parser("56");
        boolean esDigito=parser.esDigito( );
        assertTrue( "El primer caracter de la expresi�n es un d�gito",esDigito );
    }
    
    /**
     * Verifique que se retorne correctamente un d�gito <br>
     * Valida que una vez se retorne el d�gito se aumente en 1, la posici�n del parser en la expresi�n <br>
     * Usa como escenario de prueba la expresi�n "56"
     */
    public void testDarDigito()
    {
        Parser parser= new Parser("56");
        int posicionPreLectura=parser.darPosicion( );
        int valor=parser.darDigito( );
        int posicionPostLectura= parser.darPosicion( );
        assertTrue("El d�gito retornado por el parser no es el esperado",5==valor);
        assertTrue("La posici�n del parser no se actualiz�", posicionPostLectura==posicionPreLectura+1);
    }
    
    
    /**
     * Verifica que se se valide correctamente si un caracter es <br>
     * alguno de los operadores binarios registrados en la aplicaci�n <br>
     * Usa como escenarios de prueba,expresiones con cada uno de los operadores binarios  
     */
    public void testEsOperadorBinario()
    {
        
        for(int i=0; i<CupiMath.OPERADORES_BINARIOS.length;i++)
        {
            String operadorBinario=CupiMath.OPERADORES_BINARIOS[i];
            Parser parserTemp=new Parser(operadorBinario);
            boolean esOperadorBinario=parserTemp.esOperadorBinario( );
            assertTrue("No se reconoci� al operador "+operadorBinario, esOperadorBinario);
        }
    }
    
    
    /**
     * Verifica que se retorne correctamente un operador binario
     * Valida que una vez se retorne el operador binario se aumente en 1, la posici�n del parser en la expresi�n <br>
     * Usa como escenario de prueba una expresi� que tine como valor al s�mbolo de la suma
     */
    public void testDarOperadorBinario()
    {
       Parser parser=new Parser(Suma.SIMBOLO);
       int posicionPreLectura=parser.darPosicion( );
       String operadorBinario=parser.darOperadorBinario( );
       int posicionPostLectura=parser.darPosicion( );
       assertEquals("El operador binario no es el esperado", Suma.SIMBOLO, operadorBinario );
       assertTrue("La posici�n del parser no se actualiz�", posicionPostLectura==posicionPreLectura+1);
    }
    
    
    /**
     * Verifica que se se valide correctamente si una cadena de caracteres es un operador unarios <br>
     * registrado en la aplicaci�n.<br>
     * Usa como escenarios de prueba,expresiones con cada uno de los operadores unarios <br>
     */
    public void testEsOperadorUnario()
    {
        String operandoPrueba="[45]";
        for(int i=0; i<CupiMath.OPERADORES_UNARIOS.length;i++)
        {
            String operadorUnario=CupiMath.OPERADORES_UNARIOS[i];
            Parser parserTemp=new Parser(operadorUnario+operandoPrueba);
            boolean esOperadorUnario=parserTemp.esOperadorUnario( );
            assertTrue("No se reconoci� al operador "+operadorUnario, esOperadorUnario);
        }
    }
    
    /**
     * Verifica que se retorne correctamente un operador unario <br>
     * Valida que una vez se retorne el operador unario se aumente en la longitud del operador unario, <br>
     * la posici�n del parser en la expresi�n <br>
     */
    public void testDarOperadorUnario()
    {
        Parser parser=new Parser(Sqrt.SIMBOLO+"[100]");
        int posicionPreLectura=parser.darPosicion( );
        String operadorUnario=parser.darOperadorUnario( );
        int posicionPostLectura=parser.darPosicion( );
        assertEquals("El operador unario no es el esperado", Sqrt.SIMBOLO , operadorUnario ); 
        assertTrue("La posici�n del parser no se actualiz�", posicionPostLectura==posicionPreLectura+operadorUnario.length( ));
    }
    
    /**
     * Verifica que se valide correctamente si un caracter es un punto decimal
     */
    public void testEsPuntoDecimal()
    {
        Parser parser=new Parser(".");
        assertTrue( "No se ley� un punto decimal", parser.esPuntoDecimal( ));
    }
    
    
    /**
     * Verifica que se retorne correctamente un punto decimal
     */
    public void testDarPuntoDecimal()
    {
        Parser parser=new Parser(".");
        int posicionPreLectura=parser.darPosicion( );
        String puntoDecimal=parser.darPuntoDecimal( );
        int posicionPostLectura=parser.darPosicion( );
        assertEquals( "No se retorn� un punto decimal", ".", puntoDecimal);
        assertTrue("La posici�n del parser no se actualiz�", posicionPostLectura==posicionPreLectura+1);
        
    }
    


    
}
