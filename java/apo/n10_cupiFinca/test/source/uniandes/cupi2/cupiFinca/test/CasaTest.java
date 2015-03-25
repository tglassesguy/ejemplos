/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodr�guez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupiFinca.mundo.Casa;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Casa esten bien implementados
 */
public class CasaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Casa casa;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Casa
     * 
     */
    private void setupEscenario1( )
    {
        casa = new Casa( 0, 0, 30 );
    }

    /**
     * Prueba del constructor de una casa
     */
    public void testCasa( )
    {
        setupEscenario1( );

        // C�digo de la prueba de valores iniciales
        assertEquals( "La finca debe tener el nombre definido por defecto para el terreno", casa.darNombre( ), Casa.NOMBRE_CONSTRUCCION );
        assertEquals( "La finca debe tener el costo definido por defecto para el terreno", casa.darCosto( ), Casa.COSTO_CONSTRUCCION );
        assertEquals( "El tiempo inicial es el definido por par�metro", casa.darTiempo( ), 0 );
        assertEquals( "El tiempo inicial es el definido por par�metro", casa.darGanancia( ), 0 );
        assertFalse( "El estado de recolecci�n del producto que se cultiva en el terreno debe ser verdadero", casa.esRecolectable( ) );
    }
}