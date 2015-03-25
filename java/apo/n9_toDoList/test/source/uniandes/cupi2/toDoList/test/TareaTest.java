/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.toDoList.test;

import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.toDoList.mundo.Tarea;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Tarea est�n correctamente implementados
 */
public class TareaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Tarea tarea;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Tarea
     * 
     */
    private void setupEscenario1( )
    {
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
        // El a�o base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
        tarea = new Tarea( "Nombre", "Descripci�n", fechaInicio, fechaFin );
    }

    /**
     * Construye una nueva lista de tareas con dos tareas
     */
    private void setupEscenario2( )
    {
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
        // El a�o base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
        tarea = new Tarea( "Tarea 1", "Descripci�n 1", fechaInicio, fechaFin );

        // El a�o base inicia en 1900
        Date fechaInicio2 = new Date( 2011 - 1900, 1, 16, 5, 30 );
        // El a�o base inicia en 1900
        Date fechaFin2 = new Date( 2011 - 1900, 1, 21, 10, 00 );
        Tarea tarea2 = new Tarea( "Tarea 2", "Descripci�n 2", fechaInicio2, fechaFin2 );

        tarea.cambiarSiguienteTarea( tarea2 );
    }

    /**
     * Prueba 1 - Prueba el m�todo cambiarSiguienteTarea M�todos a probar: <br>
     * cambiarSiguienteTarea
     */
    public void testCambiarSiguienteTarea( )
    {
        setupEscenario1( );
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
        Tarea nueva = new Tarea( "Tarea 2", "Descripci�n 2", fechaInicio, null );
        tarea.cambiarSiguienteTarea( nueva );
        Tarea siguiente = tarea.darSiguienteTarea( );
        assertEquals( "El nombre de la tarea siguiente no es correcto", siguiente.darNombre( ), "Tarea 2" );
        assertEquals( "La descripci�n de la tarea siguiente no es correcta", siguiente.darDescripcion( ), "Descripci�n 2" );
        assertEquals( "La fecha de inicio no es igual", siguiente.darFechaInicio( ), fechaInicio );
        assertNull( "No deber�a tener fecha de finalizaci�n", siguiente.darFechaFin( ) );
    }

    /**
     * Prueba 2 - Prueba el m�todo darCopia M�todos a probar: <br>
     * darCopia
     */
    public void testDarCopia( )
    {
        setupEscenario2( );
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
        // El a�o base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
        Tarea copia = tarea.darCopia( );

        assertEquals( "El nombre de la tarea no es correcto", copia.darNombre( ), "Tarea 1" );
        assertEquals( "La descripci�n de la tarea no es correcta", copia.darDescripcion( ), "Descripci�n 1" );
        assertEquals( "La fecha de inicio no es igual", copia.darFechaInicio( ), fechaInicio );
        assertEquals( "La fecha de finalizaci�n no es igual", copia.darFechaFin( ), fechaFin );
        assertNull( "No deber�a tener siguiente", copia.darSiguienteTarea( ) );
    }

    /**
     * Prueba 3 - Prueba el m�todo darSiguienteTarea M�todos a probar: <br>
     * darSiguienteTarea
     */
    public void testDarSiguienteTarea( )
    {
        setupEscenario2( );
        // El a�o base inicia en 1900
        Date fechaInicio2 = new Date( 2011 - 1900, 1, 16, 5, 30 );
        // El a�o base inicia en 1900
        Date fechaFin2 = new Date( 2011 - 1900, 1, 21, 10, 00 );

        Tarea siguiente = tarea.darSiguienteTarea( );
        assertEquals( "El nombre de la tarea no es correcto", siguiente.darNombre( ), "Tarea 2" );
        assertEquals( "La descripci�n de la tarea no es correcta", siguiente.darDescripcion( ), "Descripci�n 2" );
        assertEquals( "La fecha de inicio no es igual", siguiente.darFechaInicio( ), fechaInicio2 );
        assertEquals( "La fecha de finalizaci�n no es igual", siguiente.darFechaFin( ), fechaFin2 );
    }

    /**
     * Prueba 4 - Prueba el m�todo editarTarea M�todos a probar: <br>
     * editarTarea
     */
    public void testEditarTarea( )
    {
        setupEscenario2( );
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El a�o base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        tarea.editarTarea( "Nueva descripci�n", fechaInicio, fechaFin );

        assertEquals( "La nueva descripci�n no corresponde", tarea.darDescripcion( ), "Nueva descripci�n" );
        assertEquals( "La nueva fecha de inicio no corresponde", tarea.darFechaInicio( ), fechaInicio );
        assertEquals( "La nueva descripci�n no corresponde", tarea.darFechaFin( ), fechaFin );
    }

    
    /**
     * Prueba 5 - Prueba el m�todo terminarTarea M�todos a probar: <br>
     * terminarTarea, estaTerminada
     */
    public void testTerminarTarea( )
    {
        setupEscenario2( );
        tarea.terminarTarea( );
        assertTrue( "La tarea no est� terminada", tarea.estaTerminada( ) );
    }

    /**
     * Prueba 6 - Prueba el m�todo estaVencida M�todos a probar: <br>
     * estaVencida
     */
    public void testEstaVencida( )
    {
        setupEscenario2( );

        assertTrue( "La tarea deber�a ser igual", tarea.estaVencida( ) );
    }

}