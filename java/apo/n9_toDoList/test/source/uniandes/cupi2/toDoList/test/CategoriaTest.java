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

import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.toDoList.mundo.ElementoExisteException;
import uniandes.cupi2.toDoList.mundo.ElementoNoExisteException;
import uniandes.cupi2.toDoList.mundo.Categoria;
import uniandes.cupi2.toDoList.mundo.Tarea;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Categoria est�n correctamente implementados
 */
public class CategoriaTest extends TestCase
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Categoria categoria;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Categor�a vac�a
     * 
     */
    private void setupEscenario1( )
    {
        categoria = new Categoria( "Categor�a" );
    }

    /**
     * Construye una nueva categor�a con cuatro tareas Una de las tareas est� terminada, y otra est� vencida
     */
    private void setupEscenario2( )
    {
        try
        {
            categoria = new Categoria( "Categor�a" );
            // El a�o base inicia en 1900
            Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
            categoria.agregarTarea( "Tarea 1", "Descripci�n 1", fechaInicio, null );
            categoria.agregarTarea( "Tarea 2", "Descripci�n 2", fechaInicio, null );
            categoria.agregarTarea( "Tarea 3", "Descripci�n 3", fechaInicio, null );
            categoria.agregarTarea( "Tarea 4", "Descripci�n 4", fechaInicio, null );

            // Tarea Terminada
            categoria.terminarTarea( "Tarea 2" );

            // Tarea Vencida
            // El a�o base inicia en 1900
            Date fechaFin = new Date( 2011 - 1900, 2, 20, 10, 00 ); // cambiando 1 por 2
            categoria.editarTarea( "Tarea 4", "Descripci�n", fechaInicio, fechaFin );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No deber�a lanzar excepci�n" );
        }

    }

    /**
     * Prueba 1 - Prueba el m�todo agregarTarea M�todos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea1( )
    {
        setupEscenario2( );
        try
        {
            // El a�o base inicia en 1900
            Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
            categoria.agregarTarea( "Tarea 1", "Descripci�n 1", fechaInicio, null );
            fail( "No debi� agregar ninguna tarea" );
        }
        catch( ElementoExisteException e )
        {
            // Lanza excepci�n porque ya existe una tarea con ese nombre
        }
    }

    /**
     * Prueba 2 - Prueba el m�todo agregarTarea M�todos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea2( )
    {
        setupEscenario2( );
        try
        {
            categoria.agregarTarea( "Tarea 5", "Descripci�n 5", new Date( ), null );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
    }

    /**
     * Prueba 3 - Prueba el m�todo buscarTarea M�todos a probar: <br>
     * buscarTarea
     */
    public void testBuscarTarea( )
    {
        setupEscenario2( );
        Tarea buscada = categoria.buscarTarea( "Tarea 3" );
        assertNotNull( "No encuentra la tarea buscada ", buscada );
        assertEquals( "La descripci�n no corresponde", buscada.darDescripcion( ), "Descripci�n 3" );
    }

    /**
     * Prueba 4 - Prueba el m�todo buscarTarea M�todos a probar: <br>
     * buscarTarea
     */
    public void testBuscarTarea2( )
    {
        setupEscenario2( );
        Tarea buscada = categoria.buscarTarea( "Tarea no existente" );
        assertNull( "No encuentra la tarea buscada ", buscada );
    }

    /**
     * Prueba 5 - Prueba el m�todo editarTarea M�todos a probar: <br>
     * editarTarea
     */
    public void testEditarTarea( )
    {
        setupEscenario2( );
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El a�o base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        try
        {
            categoria.editarTarea( "Tarea 1", "Nueva descripci�n", fechaInicio, fechaFin );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
        Tarea tarea = categoria.buscarTarea( "Tarea 1" );
        assertEquals( "La nueva descripci�n no corresponde", tarea.darDescripcion( ), "Nueva descripci�n" );
        assertEquals( "La nueva fecha de inicio no corresponde", tarea.darFechaInicio( ), fechaInicio );
        assertEquals( "La nueva descripci�n no corresponde", tarea.darFechaFin( ), fechaFin );
    }

    /**
     * Prueba 6 - Prueba el m�todo editarTarea M�todos a probar: <br>
     * editarTarea
     */
    public void testEditarTareaError( )
    {
        setupEscenario2( );
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El a�o base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        try
        {
            categoria.editarTarea( "Tarea que no existe", "Nueva descripci�n", fechaInicio, fechaFin );
            fail( "Debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 7 - Prueba el m�todo terminarTarea M�todos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTarea( )
    {
        setupEscenario2( );
        try
        {
            categoria.terminarTarea( "Tarea 1" );
            Tarea tarea = categoria.buscarTarea( "Tarea 1" );
            assertTrue( "La tarea no est� terminada", tarea.estaTerminada( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
    }

    /**
     * Prueba 8 - Prueba el m�todo terminarTarea M�todos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError( )
    {
        setupEscenario1( );
        try
        {
            categoria.terminarTarea( "Tarea 1" );
            fail( "Debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 9 - Prueba el m�todo terminarTarea M�todos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError2( )
    {
        setupEscenario2( );
        try
        {
            categoria.terminarTarea( "Tarea que no existe" );
            fail( "Debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 10 - Prueba el m�todo darTareasPendientes M�todos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasPendientes( )
    {
        setupEscenario2( );
        Tarea pendientes = categoria.darTareasPendientes( );
        Tarea actual = pendientes;

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 1" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 3" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 4" );
        actual = actual.darSiguienteTarea( );

        assertNull( "No deber�a haber m�s tareas", actual );

    }

    /**
     * Prueba 11 - Prueba el m�todo darTareasTerminadas M�todos a probar: <br>
     * darTareasTerminadas
     */
    public void testDarTareasTerminadas( )
    {
        setupEscenario1( );
        Tarea terminadas = categoria.darTareasTerminadas( );
        assertNull( "No debe haber tareas terminadas", terminadas );
    }

    /**
     * Prueba 12 - Prueba el m�todo darTareasPendientes M�todos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasTerminadas2( )
    {
        setupEscenario2( );
        Tarea terminadas = categoria.darTareasTerminadas( );
        assertEquals( "El nombre de la tarea es incorrecto", terminadas.darNombre( ), "Tarea 2" );

        terminadas = terminadas.darSiguienteTarea( );
        assertNull( "No deber�a haber m�s tareas", terminadas );
    }

    /**
     * Prueba 13 - Prueba el m�todo darTareasVencidas M�todos a probar: <br>
     * darTareasVencidas
     */
    public void testDarTareasVencidas( )
    {
        setupEscenario2( );
        Tarea vencida = categoria.darTareasVencidas( );
        assertEquals( "La tarea vencida no es correcta", vencida.darNombre( ), "Tarea 4" );
    }

    /**
     * Prueba 14 - Prueba el m�todo eliminarTareasTerminadas M�todos a probar: <br>
     * eliminarTareasTerminadas
     */
    public void testEliminarTareasTerminadas( )
    {
        setupEscenario2( );
        categoria.eliminarTareasTerminadas( );
        Tarea eliminada = categoria.buscarTarea( "Tarea 2" );
        assertNull( "La tarea no se elimin� correctamente", eliminada );
    }

}