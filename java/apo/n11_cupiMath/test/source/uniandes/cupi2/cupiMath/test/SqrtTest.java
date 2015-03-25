package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Nodo;
import uniandes.cupi2.cupiMath.mundo.Numero;
import uniandes.cupi2.cupiMath.mundo.Sqrt;


/**
 *Clase de pruba para los m�todos de la clase Sqrt
 *Es necesario correr las pruebas usando aserciones 
 */
public class SqrtTest extends TestCase
{
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Operador Ra�z cuadrada
     */
    private Sqrt operadorSqrt;
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Construye un operador sqrt con su operando
     */
    public void setupEscenario1()
    {
        operadorSqrt=new Sqrt( Sqrt.SIMBOLO );
        Nodo elNodoIzquierdo=new Numero( 4 );
        operadorSqrt.asignarNodoIzquierdo( elNodoIzquierdo );
    }
    
    /**
     * Verifica que la evaluaci�n del operador Sqrt, retorne el valor de aplicar la ra�z cuadrada a su operando izquierdo
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals( "El resultado de la ra�z cuadrada no es el esperado" ,2.0, operadorSqrt.evaluar( ));
    }

}
