package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Operador;
import uniandes.cupi2.cupiMath.mundo.Resta;

/**
 * Clase de prueba de los m�todos de la clase Operador
 * Es necesario correr las pruebas usando aserciones 
 */
public class OperadorTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Operador resta
     */
    private Operador resta;
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Construye un operado resta
     */
    public void setupEscenario1()
    {
        resta=new Resta( Resta.SIMBOLO );
    }
    
    
    /**
     * Valida que a un operador se le asigne el s�mbolo correctamente
     */
    public void testOperador()
    {
        setupEscenario1( );
        assertEquals("El operador no fue creado correctamente",Resta.SIMBOLO,resta.darValor( ));
    }
    
    
}
