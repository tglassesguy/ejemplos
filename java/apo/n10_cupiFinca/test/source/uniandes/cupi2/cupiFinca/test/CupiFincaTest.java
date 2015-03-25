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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import uniandes.cupi2.cupiFinca.mundo.Casa;
import uniandes.cupi2.cupiFinca.mundo.Cebolla;
import uniandes.cupi2.cupiFinca.mundo.Cerca;
import uniandes.cupi2.cupiFinca.mundo.Cultivable;
import uniandes.cupi2.cupiFinca.mundo.CupiFinca;
import uniandes.cupi2.cupiFinca.mundo.ITerreno;
import uniandes.cupi2.cupiFinca.mundo.Zanahoria;
import uniandes.cupi2.cupiFinca.mundo.excepciones.PersistenciaException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.ProductoNoExisteException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.TerrenoNoExisteException;
import uniandes.cupi2.cupiFinca.mundo.excepciones.TerrenoNoRecolectableException;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Finca est�n correctamente implementados
 */
public class CupiFincaTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private CupiFinca finca;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Finca vac�a
     * 
     */
    private void setupEscenario1( )
    {
        finca = new CupiFinca( 20, 20, 5, 5, 20 );
    }

    /**
     * Construye una nueva Finca con varios terrenos
     * 
     */
    private void setupEscenario2( )
    {
        finca = new CupiFinca( 20, 20, 5, 5, 20 );
        try
        {
            finca.producirTerreno( Cebolla.NOMBRE_CULTIVO, 0, 1 );
            finca.producirTerreno( Cebolla.NOMBRE_CULTIVO, 3, 3 );
            finca.producirTerreno( Cebolla.NOMBRE_CULTIVO, 2, 0 );
        }
        catch( Exception e )
        {
            fail( "No pudo llenar la finca: " + e.getMessage( ) );
        }
    }

    /**
     * Prueba del constructor
     */
    public void testCupiFinca( )
    {
        setupEscenario1( );

        // C�digo de la prueba de valores iniciales
        assertEquals( "La finca debe iniciar con la cantidad de dinero definida por defecto", finca.darDinero( ), CupiFinca.DINERO_INICIAL );

        // Debe verificar que los terrenos est�n vac�os
        for( int i = 0; i < 5; i++ )
        {
            for( int j = 0; j < 5; j++ )
            {
                ITerreno terreno = finca.darTerrenoPosicion( i, j );
                assertNull( "El terreno debe estar vac�o", terreno );
            }
        }
    }

    /**
     * Prueba que los elementos de la finca se reinician
     */
    public void testReiniciar( )
    {
        setupEscenario2( );

        // debe verificar que se agregaron elementos
        int activos = 0;
        for( int i = 0; i < 5; i++ )
        {
            for( int j = 0; j < 5; j++ )
            {
                ITerreno terreno = finca.darTerrenoPosicion( i, j );
                if( terreno != null )
                {
                    activos++;
                }
            }
        }
        assertNotSame( "La finca debe tener terrenos activos.", activos, 0 );
        assertNotSame( "No debe tener el saldo inicial.", finca.darDinero( ), CupiFinca.DINERO_INICIAL );

        // Reinicia el estado de la finca
        finca.reiniciar( );

        // Debe verificar que los terrenos est�n vac�os
        for( int i = 0; i < 5; i++ )
        {
            for( int j = 0; j < 5; j++ )
            {
                ITerreno terreno = finca.darTerrenoPosicion( i, j );
                assertEquals( "La finca debe iniciar con la cantidad de dinero definida por defecto", finca.darDinero( ), CupiFinca.DINERO_INICIAL );
                assertNull( "El terreno debe estar vac�o", terreno );
            }
        }
    }

    /**
     * Prueba el m�todo de producir en un terreno, agregue correctamente a la matr�z
     */
    public void testProducirEnTerreno1( )
    {
        setupEscenario1( );

        try
        {
            finca.producirTerreno( Cebolla.NOMBRE_CULTIVO, 0, 1 );

            ITerreno terreno = finca.darTerrenoPosicion( 0, 1 );
            if( terreno == null )
            {
                fail( "No agrego el terreno a la matr�z de los terrenos de la finca" );
            }
        }
        catch( Exception e )
        {
            fail( "No deber�a lanzar la excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Prueba el m�todo de producir en un terreno, lanza excepci�n al no poder producir. 1. En caso de intentar colocar un nuevo producto en un terreno ya ocupado
     */
    public void testProducirEnTerreno2( )
    {
        setupEscenario1( );

        // Agrega un primer elemento al terreno
        try
        {
            finca.producirTerreno( Cebolla.NOMBRE_CULTIVO, 0, 1 );
        }
        catch( Exception e )
        {
            fail( "No deber�a lanzar la excepci�n: " + e.getMessage( ) );
        }

        // 1. Prueba que no se puede agregar
        try
        {
            finca.producirTerreno( Cebolla.NOMBRE_CULTIVO, 0, 1 );
            fail( "No debe poder agregar un producto en un terreno ya ocupado." );
        }
        catch( Exception e )
        {
            // Debe lanzar excepci�n
        }
    }

    /**
     * Prueba que actualiza el estado de los terrenos sobre los terrenos activos. 1. En caso de que aumente el tiempo y no cambie de estado 2. En caso de que aumente el tiempo
     * y tenga que cambiar de estado
     */
    public void testActualizarEstadosTerrenos( )
    {
        setupEscenario2( );

        // 1. En el caso de que no sea requerido cambiar del estado
        ITerreno terreno = finca.darTerrenoPosicion( 0, 1 );
        int estadoAnterior = terreno.darEstado( );
        int tiempoAnterior = terreno.darTiempo( );

        finca.actualizarEstadoTerrenos( );
        assertNotSame( "El tiempo debi� cambiar", tiempoAnterior, terreno.darTiempo( ) );
        assertEquals( "El estado no se debi� modificar", estadoAnterior, terreno.darEstado( ) );

        // 2. en caso de que se requiera el cambio de estado
        for( int i = 1; i < Cultivable.TIEMPO_PREPARACION; i++ )
        {
            finca.actualizarEstadoTerrenos( );
        }
        estadoAnterior = terreno.darEstado( );
        tiempoAnterior = terreno.darTiempo( );

        finca.actualizarEstadoTerrenos( );
        assertNotSame( "El tiempo debi� cambiar", tiempoAnterior, terreno.darTiempo( ) );
        assertNotSame( "El estado no se debi� modificar", estadoAnterior, terreno.darEstado( ) );
    }

    /**
     * Prueba que se recolecta un producto de un terreno. 1. En caso de que hayan productos en el terreno.
     */
    public void testRecolectarProducto1( )
    {
        setupEscenario2( );

        // 1. En caso de recolectar
        try
        {
            ITerreno terreno = finca.darTerrenoPosicion( 0, 1 );
            for( int i = 0; i <= Cultivable.TIEMPO_PREPARACION + Cultivable.TIEMPO_SIEMBRA + Cultivable.TIEMPO_CRECIMIENTO; i++ )
            {
                finca.actualizarEstadoTerrenos( );
            }
            finca.recolectarProducto( 0, 1 );
            ITerreno nuevo = finca.darTerrenoPosicion( 0, 1 );
            assertNotSame( "El terreno no deber�a ser el mismo.", nuevo, terreno );
            assertNull( "El terreno deber�a ser null.", nuevo );
        }
        catch( Exception e )
        {
            fail( "No deber�a lanzar excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Prueba que se recolecta un producto de un terreno. 1. En caso de recolectar un terreno que no exista. 2. En caso de recoger un producto que no est� en estado para
     * recoger.
     */
    public void testRecolectarProducto2( )
    {
        // 1. En caso de recolectar un terreno que no existe
        setupEscenario1( );
        try
        {
            finca.recolectarProducto( 0, 1 );
            fail( "No deber�a seguir" );
        }
        catch( TerrenoNoExisteException e )
        {
            // No debe lanzar excepci�n
        }
        catch( TerrenoNoRecolectableException e )
        {
            fail( "No debe existir el terreno para recolectar" );
        }

        // 2. En caso de recolectar uno que no est� preparado
        setupEscenario2( );
        try
        {
            finca.recolectarProducto( 0, 1 );
            fail( "No deber�a seguir" );
        }
        catch( TerrenoNoExisteException e )
        {
            fail( "No debe existir el terreno para recolectar" );
        }
        catch( TerrenoNoRecolectableException e )
        {
            // No debe lanzar excepci�n
        }
    }

    /**
     * Prueba que se elimina un terreno correctamente. 1. En caso de que hayan productos en el terreno.
     */
    public void testEliminarTerreno1( )
    {
        setupEscenario2( );

        // 1. En caso de recolectar
        try
        {
            ITerreno terreno = finca.darTerrenoPosicion( 0, 1 );
            finca.eliminarTerreno( 0, 1 );
            ITerreno nuevo = finca.darTerrenoPosicion( 0, 1 );
            assertNotSame( "El terreno no deber�a ser el mismo.", nuevo, terreno );
            assertNull( "El terreno deber�a ser null.", nuevo );
        }
        catch( Exception e )
        {
            fail( "No deber�a lanzar excepci�n: " + e.getMessage( ) );
        }
    }

    /**
     * Prueba donde se elimina un producto de un terreno. 1. En caso de eliminar un terreno que no exista.
     */
    public void testEliminarProducto2( )
    {
        // 1. En caso de recolectar un terreno que no existe
        setupEscenario1( );
        try
        {
            finca.recolectarProducto( 0, 1 );
            fail( "No deber�a seguir" );
        }
        catch( TerrenoNoExisteException e )
        {
            // No debe lanzar excepci�n
        }
        catch( TerrenoNoRecolectableException e )
        {
            fail( "No debe existir el terreno para recolectar" );
        }
    }

    /**
     * Prueba que el archivo se est� guardando correctamente
     */
    public void testGuardarArchivo( )
    {
        setupEscenario2( );

        File archivo = new File( "./test/data/test.dat" );
        try
        {
            // Guarda el archivo
            finca.guardarArchivo( archivo );

        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a error leyendo el archivo: " + e.getMessage( ) );
        }

        // Abre el archivo para verificar
        BufferedReader reader;
        try
        {
            reader = new BufferedReader( new FileReader( archivo ) );

            String sDinero = reader.readLine( );
            int dinero = Integer.parseInt( sDinero );
            assertEquals( "La cantidad de dinero debe ser igual al del escenario", 0, dinero );

            String sCantidad = reader.readLine( );
            int cantidad = Integer.parseInt( sCantidad );
            assertEquals( "La cantidad de terrenos usados debe ser igual", 3, cantidad );

            String linea = reader.readLine( );
            while( cantidad > 0 && linea != null )
            {
                String[] info = linea.split( ";" );
                String nombre = info[ 0 ];
                int pX = Integer.parseInt( info[ 1 ] );
                int pY = Integer.parseInt( info[ 2 ] );

                ITerreno terreno = finca.darTerrenoPosicion( pX, pY );
                if( terreno != null )
                {
                    assertEquals( "El nombre del producto en el terreno debe ser el mismo", terreno.darNombre( ), nombre );
                }
                else
                {
                    fail( "Deber�a haber un terreno en la posici�n: " + pX + ", " + pY );
                }

                cantidad--;
                linea = reader.readLine( );
            }
            reader.close( );
        }
        catch( Exception e )
        {
            fail( "No deber�a haber error al intentar leer el archivo: " + e.getMessage( ) );
        }
        archivo.deleteOnExit( );
    }

    /**
     * Prueba que al abrir el archivo crea un escenario v�lido y similar al que se guardo en el archivo
     */
    public void testAbrirArchivo1( )
    {
        File archivo = new File( "./test/data/test1.dat" );
        setupEscenario2( );
        CupiFinca fincaTest = finca;

        // Guarda un archivo v�lido
        try
        {
            finca.guardarArchivo( archivo );
        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a error guardando el archivo: " + e.getMessage( ) );
        }

        // Abre el archivo en una nueva configuraci�n
        setupEscenario1( );
        try
        {
            finca.abrirArchivo( archivo );

            // Revisa la cantidad de dinero
            assertEquals( "La cantidad de dinero de la finca debe ser la misma", fincaTest.darDinero( ), finca.darDinero( ) );

            // Revisa que todos los terrenos sean iguales
            for( int i = 0; i < 5; i++ )
            {
                for( int j = 0; j < 5; j++ )
                {
                    ITerreno original = fincaTest.darTerrenoPosicion( i, j );
                    ITerreno cargado = finca.darTerrenoPosicion( i, j );
                    if( ( original == null && cargado != null ) || ( original != null && cargado == null ) )
                    {
                        fail( "Los escenarios de comparaci�n no son los mismos: " + i + ", " + j );
                    }
                    if( original != null )
                    {
                        assertEquals( "El nombre del producto en el terreno no es el mismo", original.darNombre( ), cargado.darNombre( ) );
                        assertEquals( "El tiempo del producto en el terreno no es el mismo", original.darTiempo( ), cargado.darTiempo( ) );
                    }
                }
            }
        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a error abriendo el archivo: " + e.getMessage( ) );
        }
        catch( ProductoNoExisteException e )
        {
            fail( "No deber�a error con los productos del archivo: " + e.getMessage( ) );
        }
        archivo.deleteOnExit( );
    }
    
    /**
     * Prueba que al abrir el archivo lance las excepciones correspondientes
     * 1. En caso de que el archivo no exista
     * 2. En caso de que un producto del archivo no exista
     */
    public void testAbrirArchivo2( )
    {
        File archivo = new File( "./test/data/test3.dat" );
        
        //1. Abre un archivo que no existe
        setupEscenario1( );
        try
        {
            finca.abrirArchivo( archivo );
        }
        catch( PersistenciaException e )
        {
            //Debe lanzar error por el archivo inexistente
        }
        catch( ProductoNoExisteException e )
        {
            fail( "No deber�a error con los productos del archivo: " + e.getMessage( ) );
        }

        //2. Abre un archivo con un producto que no existe
        archivo = new File( "./test/data/test2.dat" );
        setupEscenario1( );
        try
        {
            finca.abrirArchivo( archivo );
        }
        catch( PersistenciaException e )
        {
            fail( "No deber�a error con abrir el archivo: " + e.getMessage( ) );
        }
        catch( ProductoNoExisteException e )
        {
            //Debe lanzar error por el producto inexistente
        }
    }
}