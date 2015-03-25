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

package uniandes.cupi2.blog.testCliente;

import junit.framework.TestCase;
import uniandes.cupi2.blog.cliente.mundo.Comando;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Comando est�n correctamente implementados
 */
public class ComandoTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Comando comando;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva LatinChatServidor vac�a
     *  
     */
    private void setupEscenario1( )
    {
        String nombreComando = "Comando";
        String[] parametrosComando = new String[]{"parametro1", "parametro2", "parametro3"};
        comando = new Comando( nombreComando, parametrosComando );
    }

    /**
     * M�todo que prueba que el Comando se haya creado correctamente
     */
    public void testComando( )
    {
        setupEscenario1( );
        assertEquals( "El nombre del comando debe ser el mismo", comando.darNombre( ), "Comando" );
        for(int i = 0; i < comando.darParametros( ).length; i++){
            assertEquals( "El valor del par�metro debe ser igual", comando.darParametros( )[i], "parametro"+(i+1));
        }
    }

}