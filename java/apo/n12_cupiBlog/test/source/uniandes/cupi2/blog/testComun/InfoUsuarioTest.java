/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodr�guez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.testComun;

import junit.framework.TestCase;
import uniandes.cupi2.blog.comun.InfoUsuario;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase InfoUsuario est�n correctamente implementados
 */
public class InfoUsuarioTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private InfoUsuario infoUsuario;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva InfoUsuario vac�o
     *  
     */
    private void setupEscenario1( )
    {
        infoUsuario = new InfoUsuario( "Usuario", "Nombres", "Apellidos" );
    }

    /**
     * M�todo que prueba que los valores de InfoUsuario se ha inicializado
     */
    public void testInfoUsuario( )
    {
        setupEscenario1( );
        
        assertEquals("El nombre de usuario debe ser el mismo", infoUsuario.darNombreUsuario( ), "Usuario");
        assertEquals("Los nombres deben ser los mismos", infoUsuario.darNombre( ), "Nombres");
        assertEquals("Los apellidos deben ser los mismos", infoUsuario.darApellidos( ), "Apellidos");
    }

}