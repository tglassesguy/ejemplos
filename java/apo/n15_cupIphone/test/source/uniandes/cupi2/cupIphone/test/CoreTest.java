/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: CoreTest.java 1082 2010-02-26 20:51:59Z y-oviedo $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n15_cupIphone
 * Autor: Yeisson Oviedo - 23-feb-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupIphone.test;

import junit.framework.TestCase;
import uniandes.cupi2.cupIphone.core.Core;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Core est�n correctamente implementados
 */
public class CoreTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Core core;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Core vac�a
     *  
     */
    private void setupEscenario1( )
    {
        core = new Core( );
    }

    /**
     * Prueba 1
     */
    public void testCore( )
    {
        setupEscenario1( );
        
        //
        //C�digo de la prueba
    }

}