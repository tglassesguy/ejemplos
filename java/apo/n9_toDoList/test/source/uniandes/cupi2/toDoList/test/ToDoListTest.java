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

import java.io.File;
import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.toDoList.mundo.ElementoExisteException;
import uniandes.cupi2.toDoList.mundo.ElementoNoExisteException;
import uniandes.cupi2.toDoList.mundo.PersistenciaException;
import uniandes.cupi2.toDoList.mundo.Categoria;
import uniandes.cupi2.toDoList.mundo.Tarea;
import uniandes.cupi2.toDoList.mundo.ToDoList;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase ToDoList est�n correctamente implementados
 */
public class ToDoListTest extends TestCase
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Ruta del archivo de pruebas
     */

    public static final String RUTA_ARCHIVO = "test/data/toDoListTest.data";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private ToDoList toDoList;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva ToDoList vac�a
     * 
     */
    private void setupEscenario1( )
    {
        try
        {
            // Borra la lista de tareas si ya existe
            File f = new File( RUTA_ARCHIVO );
            if( f.exists( ) )
            {
                f.delete( );
            }
            toDoList = new ToDoList( RUTA_ARCHIVO );
        }
        catch( PersistenciaException e )
        {
            fail( "Deber�a cargar la lista de tareas sin problemas" );
        }
    }

    /**
     * Construye una nueva ToDoList con dos categor�as y dos tareas cada una
     */
    private void setupEscenario2( )
    {
        try
        {
            // Borra la lista de tareas si ya existe
            File f = new File( RUTA_ARCHIVO );
            if( f.exists( ) )
            {
                f.delete( );
            }
            toDoList = new ToDoList( RUTA_ARCHIVO );

            try
            {
                toDoList.agregarCategoria( "Categor�a 1" );
                toDoList.agregarCategoria( "Categor�a 2" );
                // El a�o base inicia en 1900
                Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
                toDoList.agregarTarea( "Categor�a 1", "Tarea 1", "Descripci�n 1", fechaInicio, null );
                toDoList.agregarTarea( "Categor�a 1", "Tarea 2", "Descripci�n 2", fechaInicio, null );
                toDoList.agregarTarea( "Categor�a 2", "Tarea 1", "Descripci�n 1", fechaInicio, null );
                toDoList.agregarTarea( "Categor�a 2", "Tarea 2", "Descripci�n 2", fechaInicio, null );
            }
            catch( ElementoExisteException e )
            {
                fail( "No deber�a lanzar excepci�n "+e.getMessage() );
            }
            catch( ElementoNoExisteException e )
            {
                fail( "No deber�a lanzar excepci�n "+e.getMessage() );
            }
        }
        catch( PersistenciaException e )
        {
            e.printStackTrace( );
            fail( "Deber�a cargar la lista de tareas sin problemas" );
        }
    }

    /**
     * Construye una nueva ToDoList con dos categor�as y dos tareas cada una Una de las tareas est� terminada, y otra est� vencida
     */
    private void setupEscenario3( )
    {
        try
        {
            // Borra la lista de tareas si ya existe
            File f = new File( RUTA_ARCHIVO );
            if( f.exists( ) )
            {
                f.delete( );
            }
            toDoList = new ToDoList( RUTA_ARCHIVO );

            try
            {
                toDoList.agregarCategoria( "Categor�a 1" );
                toDoList.agregarCategoria( "Categor�a 2" );
                // El a�o base inicia en 1900
                Date fechaInicio = new Date( 2011 - 1900, 1, 15, 5, 30 );
                toDoList.agregarTarea( "Categor�a 1", "Tarea 1", "Descripci�n 1", fechaInicio, null );
                toDoList.agregarTarea( "Categor�a 1", "Tarea 2", "Descripci�n 2", fechaInicio, null );
                toDoList.agregarTarea( "Categor�a 2", "Tarea 1", "Descripci�n 1", fechaInicio, null );
                toDoList.agregarTarea( "Categor�a 2", "Tarea 2", "Descripci�n 2", fechaInicio, null );

                // Tarea Terminada
                toDoList.terminarTarea( "Categor�a 1", "Tarea 2" );

                // Tarea Vencida
                // El a�o base inicia en 1900
                Date fechaFin = new Date( 2011 - 1900, 1, 20, 10, 00 );
                toDoList.editarTarea( "Categor�a 2", "Tarea 2", "Descripci�n", fechaInicio, fechaFin );

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
        catch( PersistenciaException e )
        {
            fail( "Deber�a cargar la lista de tareas sin problemas" );
        }
    }

    /**
     * Prueba 1 - Prueba el m�todo darPrimeraCategoria 
     * M�todos a probar: <br>
     * darPrimeraCategoria
     */
    public void testDarPrimeraCategoria1( )
    {
        setupEscenario1( );
        assertNull( "La primera categor�a es incorrecta", toDoList.darPrimeraCategoria( ) );
    }

    /**
     * Prueba 2 - Prueba el m�todo darPrimeraCategoria M�todos a probar: <br>
     * darPrimeraCategoria
     */
    public void testDarPrimeraCategoria2( )
    {
        setupEscenario2( );
        assertEquals( "La primera categor�a es incorrecta", toDoList.darPrimeraCategoria( ).darNombre( ), "Categor�a 1" );
    }

    /**
     * Prueba 3 - Prueba el m�todo agregarCategoria M�todos a probar: <br>
     * agregarCategoria
     */
    public void testAgregarCategoria( )
    {
        setupEscenario1( );
        try
        {
            toDoList.agregarCategoria( "Categor�a 1" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No deber�a lanzar excepci�n" );
        }
    }

    /**
     * Prueba 4 - Prueba el m�todo agregarcategor�a M�todos a probar: <br>
     * agregarcategor�a
     */
    public void testAgregarcategor�aError( )
    {
        setupEscenario2( );
        try
        {
            toDoList.agregarCategoria( "Categor�a 1" );
            fail( "Deber�a lanzar excepci�n (Categor�a 1 ya existe)" );
        }
        catch( ElementoExisteException e )
        {
            // Ya existe una categor�a con ese nombre
        }
    }

    /**
     * Prueba 5 - Prueba el m�todo buscarCategoria M�todos a probar: <br>
     * buscarCategoria
     */
    public void testBuscarCategoria1( )
    {
        setupEscenario1( );
        assertNull( "Encuentra una categor�a que no existe", toDoList.buscarCategoria( "Categor�a 1" ) );
    }

    /**
     * Prueba 6 - Prueba el m�todo buscarCategoria M�todos a probar: <br>
     * buscarCategoria
     */
    public void testBuscarCategoria2( )
    {
        setupEscenario2( );
        Categoria buscada = toDoList.buscarCategoria( "Categor�a 1" );
        assertEquals( "La categor�a encontrada es incorrecta", buscada.darNombre( ), "Categor�a 1" );
    }

    /**
     * Prueba 7 - Prueba el m�todo agregarTarea M�todos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea( )
    {
        setupEscenario1( );
        try
        {
            toDoList.agregarTarea( "Categor�a 1", "Tarea 1", "Descripci�n 1", new Date( ), null );
            fail( "No debi� agregar ninguna tarea" );
        }
        catch( ElementoExisteException e )
        {
            fail( "No existe ninguna tarea con ese nombre" );
        }
        catch( ElementoNoExisteException e )
        {
            // Lanza excepci�n porque no existe ninguna categor�a con ese nombre
        }
    }

    /**
     * Prueba 8 - Prueba el m�todo agregarTarea M�todos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea2( )
    {
        setupEscenario2( );
        try
        {
            // El a�o base inicia en 1900
            Date fechaInicio = new Date( 2011 - 1900, 2, 15, 5, 30 );
            toDoList.agregarTarea( "Categor�a 1", "Tarea 1", "Descripci�n 1", fechaInicio, null );
            fail( "No debi� agregar ninguna tarea" );
        }
        catch( ElementoExisteException e )
        {
            // Lanza excepci�n porque ya existe una tarea con ese nombre
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No existe ninguna categor�a con ese nombre" );
        }
    }

    /**
     * Prueba 9 - Prueba el m�todo agregarTarea M�todos a probar: <br>
     * agregarTarea
     */
    public void testAgregarTarea3( )
    {
        setupEscenario2( );
        try
        {
            toDoList.agregarTarea( "Categor�a 1", "Tarea 3", "Descripci�n 1", new Date( ), null );
        }
        catch( ElementoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
    }

    /**
     * Prueba 10 - Prueba el m�todo editarTarea M�todos a probar: <br>
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
            toDoList.editarTarea( "Categor�a 1", "Tarea 1", "Nueva descripci�n", fechaInicio, fechaFin );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
        Categoria p = toDoList.darPrimeraCategoria( );
        Tarea tarea = p.buscarTarea( "Tarea 1" );
        assertEquals( "La nueva descripci�n no corresponde", tarea.darDescripcion( ), "Nueva descripci�n" );
        assertEquals( "La nueva fecha de inicio no corresponde", tarea.darFechaInicio( ), fechaInicio );
        assertEquals( "La nueva descripci�n no corresponde", tarea.darFechaFin( ), fechaFin );
    }

    /**
     * Prueba 11 - Prueba el m�todo editarTarea M�todos a probar: <br>
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
            toDoList.editarTarea( "Categor�a que no existe", "Tarea 1", "Nueva descripci�n", fechaInicio, fechaFin );
            fail( "Debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la categor�a dada
        }
    }

    /**
     * Prueba 12 - Prueba el m�todo editarTarea M�todos a probar: <br>
     * editarTarea
     */
    public void testEditarTareaError2( )
    {
        setupEscenario2( );
        // El a�o base inicia en 1900
        Date fechaInicio = new Date( 2011 - 1900, 2, 16, 8, 30 );
        // El a�o base inicia en 1900
        Date fechaFin = new Date( 2011 - 1900, 3, 15, 10, 00 );
        try
        {
            toDoList.editarTarea( "Categor�a 1", "Tarea que no existe", "Nueva descripci�n", fechaInicio, fechaFin );
            fail( "Debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 13 - Prueba el m�todo terminarTarea M�todos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTarea( )
    {
        setupEscenario2( );
        try
        {
            toDoList.terminarTarea( "Categor�a 1", "Tarea 1" );
            Categoria p = toDoList.darPrimeraCategoria( );
            Tarea tarea = p.buscarTarea( "Tarea 1" );
            assertTrue( "La tarea no est� terminada", tarea.estaTerminada( ) );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
    }

    /**
     * Prueba 14 - Prueba el m�todo terminarTarea M�todos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError( )
    {
        setupEscenario1( );
        try
        {
            toDoList.terminarTarea( "Categor�a 1", "Tarea 1" );
            fail( "Debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la categor�a dada
        }
    }

    /**
     * Prueba 15 - Prueba el m�todo terminarTarea M�todos a probar: <br>
     * terminarTarea
     */
    public void testTerminarTareaError2( )
    {
        setupEscenario2( );
        try
        {
            toDoList.terminarTarea( "Categor�a 1", "Tarea que no existe" );
            fail( "Debi� lanzar excepci�n" );
        }
        catch( ElementoNoExisteException e )
        {
            // No existe la tarea dada
        }
    }

    /**
     * Prueba 16 - Prueba el m�todo darTareasPendientes M�todos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasPendientes( )
    {
        setupEscenario2( );
        Tarea pendientes = toDoList.darTareasPendientes( );
        Tarea actual = pendientes;

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 1" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 2" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 1" );
        actual = actual.darSiguienteTarea( );

        assertEquals( "El nombre de la tarea es incorrecto", actual.darNombre( ), "Tarea 2" );
    }

    /**
     * Prueba 17 - Prueba el m�todo darTareasTerminadas M�todos a probar: <br>
     * darTareasTerminadas
     */
    public void testDarTareasTerminadas( )
    {
        setupEscenario2( );
        Tarea terminadas = toDoList.darTareasTerminadas( );
        assertNull( "No deber�a haber tareas terminadas", terminadas );
    }

    /**
     * Prueba 18 - Prueba el m�todo darTareasPendientes M�todos a probar: <br>
     * darTareasPendientes
     */
    public void testDarTareasTerminadas2( )
    {
        setupEscenario2( );
        try
        {
            toDoList.terminarTarea( "Categor�a 1", "Tarea 2" );
            toDoList.terminarTarea( "Categor�a 2", "Tarea 2" );
        }
        catch( ElementoNoExisteException e )
        {
            fail( "No debi� lanzar excepci�n" );
        }
        Tarea terminadas = toDoList.darTareasTerminadas( );
        Tarea actual = terminadas;
        int numeroTareas = 0;
        while( actual != null )
        {
            numeroTareas++;
            assertEquals( "El nombre de la tarea no es correcto", actual.darNombre( ), "Tarea 2" );
            actual = actual.darSiguienteTarea( );
        }
        assertEquals( "El n�mero de tareas es incorrecto", numeroTareas, 2 );
    }

    /**
     * Prueba 19 - Prueba el m�todo darTareasVencidas M�todos a probar: <br>
     * darTareasVencidas
     */
    public void testDarTareasVencidas( )
    {
        setupEscenario3( );
        Tarea vencida = toDoList.darTareasVencidas( );
        assertEquals( "La tarea vencida no es correcta", vencida.darNombre( ), "Tarea 2" );
    }

    /**
     * Prueba 20 - Prueba el m�todo eliminarTareasTerminadas M�todos a probar: <br>
     * eliminarTareasTerminadas
     */
    public void testEliminarTareasTerminadas( )
    {
        setupEscenario3( );
        toDoList.eliminarTareasTerminadas( );
        Categoria p = toDoList.darPrimeraCategoria( );
        Tarea eliminada = p.buscarTarea( "Tarea 2" );
        assertNull( "La tarea no se elimin� correctamente", eliminada );
    }

}