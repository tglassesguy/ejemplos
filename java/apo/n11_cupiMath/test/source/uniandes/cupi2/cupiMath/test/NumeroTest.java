package uniandes.cupi2.cupiMath.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiMath.mundo.Numero;


/**
 * Clase de prueba de los m�todos de la clase Numero
 * Es necesario correr las pruebas usando aserciones 
 */
public class NumeroTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * Representa un n�mero
     */
    private Numero numero;
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Construye un n�mero y le asigna como valor "100"
     */
    public void setupEscenario1()
    {
        numero=new Numero( 1 );
        numero.asignarValor( numero.darValor( )+"00" );
    }
    
    
    /**
     * Valida que a un n�mero se le asigne el valor correctamente
     */
    public void testNumero()
    {
        setupEscenario1( );
        assertEquals( "El operando no fue creado correctamente","100", numero.darValor( ) );
    }
    
    
    /**
     * Valida que la evaluaci�n del n�mero sea el valor n�merico que se le asign�
     */
    public void testEvaluar()
    {
        setupEscenario1( );
        assertEquals("La evaluaci�n del n�mero no es la esperada", 100.0, numero.evaluar( ));
    }
    
    /**
     * Valida que el n�mero no tenga hijos
     */
    public void testVerificarInvariante()
    {
        setupEscenario1( );
        try
        {
            numero.verificarInvariante( );
        }
        catch(AssertionError ae)
        {
            fail("Un n�mero debe tener ambos hijos en null");
        }
        
        
    }
    
    
    
}
