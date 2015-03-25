/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 
 * Ejercicio: n6_buscaminas 
 * Autor: Mario S�nchez - 15/07/2005
 * Modificado por: Milena Vela - 22/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.buscaminas.test;

import java.io.File;

import uniandes.cupi2.buscaminas.mundo.CampoMinado;
import uniandes.cupi2.buscaminas.mundo.Casilla;
import junit.framework.TestCase;

/**
 * Esta clase sirve para probar la clase CampoMinado
 */
public class CampoMinadoTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el campo minado utilizado para las pruebas
     */
    private CampoMinado campoMinado;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye el escenario 1 donde hay un campo minado de 10 x 10 con 1 bomba
     */
    private void setupEscenario1( )
    {
        try
        {
            campoMinado = new CampoMinado( new File( "./test/data/buscaminas1.prop" ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a fallar: " + e.getMessage( ) );
        }
    }

    /**
     * Construye el escenario 2 donde hay un campo minado de 10 x 10 con 2 bombas
     */
    private void setupEscenario2( )
    {
        try
        {
            campoMinado = new CampoMinado( new File( "./test/data/buscaminas2.prop" ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a fallar: " + e.getMessage( ) );
        }
    }

    /**
     * Construye el escenario 3 donde hay un campo minado de 10 x 10 con 90 bombas
     */
    private void setupEscenario3( )
    {
        try
        {
            campoMinado = new CampoMinado( new File( "./test/data/buscaminas3.prop" ) );
        }
        catch( Exception e )
        {
            fail( "No deber�a fallar: " + e.getMessage( ) );
        }
    }

    /**
     * Construye el escenario 3 donde hay un campo minado de 10 x 15 con 30 bombas
     */
    private void setupEscenario4( )
    {
        try
        {
            campoMinado = new CampoMinado( new File( "./test/data/buscaminas.prop" ) );

        }
        catch( Exception e )
        {
            fail( "No deber�a fallar: " + e.getMessage( ) );
        }
    }

    /**
     * Verifica que para un campo minado nuevo el m�todo darCasillas retorne la matriz de Casillas esperada.
     */
    public void testDarCasillas1( )
    {
        setupEscenario1( );
        // llena un nuevo arreglo con las casillas del juego
        Casilla[][] casillas = campoMinado.darCasillas( );
        // Recorre todas las casillas
        for( int i = 0; i < 10; i++ )
        {
            for( int j = 0; j < 10; j++ )
            {
                // Si la casilla no est� tapada se avisa un fallo
                assertEquals( "El estado inicial de la casilla deber�a ser tapada", Casilla.TAPADA, casillas[ i ][ j ].darEstado( ) );
            }
        }
    }

    /**
     * Verificar el m�todo darEstadoJuego
     */
    public void testDarEstadoJuego( )
    {
        setupEscenario3( );

        boolean perdio = false;
        // se realizan jugadas por todo el campo minado
        for( int i = 0; i < 10 && !perdio; i++ )
        {
            for( int j = 0; j < 10 && !perdio; j++ )
            {
                try
                {
                    int resultado = campoMinado.destapar( i, j );
                    if( resultado == CampoMinado.JUEGO_PERDIDO )
                    {
                        perdio = true;
                    }
                }
                catch( Exception e )
                {
                    fail( e.getMessage( ) );
                }
            }
        }
        // si se perdi�, el sistema deber�a afirmar que se ha perdido
        if( perdio )
            assertEquals( "El estado del juego en este punto debe ser JUEGO_PERDIDO", CampoMinado.JUEGO_PERDIDO, campoMinado.darEstadoJuego( ) );

        else
            assertEquals( "El estado del juego en este punto debe ser JUEGO_GANADO", CampoMinado.JUEGO_GANADO, campoMinado.darEstadoJuego( ) );

    }

    /**
     * Verificar los m�todos marcas y desmarcar sin ning�n caso de error
     */
    public void testMarcar1( )
    {
        setupEscenario1( );

        // se verifica que la casilla inicialmente est� tapada
        Casilla[][] casillas = campoMinado.darCasillas( );
        assertEquals( "El estado inicial debe ser TAPADA", Casilla.TAPADA, casillas[ 1 ][ 1 ].darEstado( ) );
        // se prueba que la casilla se pueda marcar y desmarcar
        try
        {
            campoMinado.marcar( 1, 1 );

            casillas = campoMinado.darCasillas( );
            assertEquals( "El estado actual de la casilla debe ser MARCADA", Casilla.MARCADA, casillas[ 1 ][ 1 ].darEstado( ) );

            campoMinado.desmarcar( 1, 1 );

            casillas = campoMinado.darCasillas( );
            assertEquals( "El estado actual de la casilla debe ser TAPADA", Casilla.TAPADA, casillas[ 1 ][ 1 ].darEstado( ) );
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Verifica la excepci�n cuando se intenta marcar una casilla ya marcada
     */
    public void testMarcar2( )
    {
        setupEscenario1( );

        try
        {
            // se trata de marcar una posici�n 2 veces
            campoMinado.marcar( 1, 1 );

            campoMinado.marcar( 1, 1 );
            fail( "Debe lanzarse una excepci�n porque la posici�n ya est� marcada" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }

    /**
     * Verifica la excepci�n cuando se intenta marcar una casilla que est� por fuera del campo minado
     */
    public void testMarcar3( )
    {
        setupEscenario1( );

        try
        {
            // se trara de marcar una posici�n invalida
            campoMinado.marcar( 10, 10 );
            fail( "Debe lanzarse una excepci�n porque la posici�n est� por fuera del campo minado" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }

    /**
     * Verifica la excepci�n cuando se intenta desmarcar una casilla sin marca
     */
    public void testDesmarcar1( )
    {
        setupEscenario1( );

        try
        {
            // se trata de desmarcar una posici�n que no esta marcada
            campoMinado.desmarcar( 1, 1 );
            fail( "Debe lanzarse una excepci�n porque la posici�n no est� marcada" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }

    /**
     * Verifica la excepci�n cuando se intenta desmarcar una casilla por fuera del campo minado
     */
    public void testDesmarcar2( )
    {
        setupEscenario1( );

        try
        {
            // se trata de desmarcar una psoicion que no existe
            campoMinado.desmarcar( 10, 10 );
            fail( "Debe lanzarse una excepci�n porque la posici�n no est� en el campo minado" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }

    /**
     * Verificar el m�todo destapar
     */
    public void testDestapar1( )
    {
        setupEscenario2( );

        try
        {
            // destapa la posici�n 1,1
            int resultado = campoMinado.destapar( 1, 1 );

            Casilla[][] casillas = campoMinado.darCasillas( );
            // se revisa que el estado del juego sea el adecuado dependiendo de si termin� o no el juego
            if( resultado == CampoMinado.CONTINUA_JUEGO )
            {

                int estadoCasilla = casillas[ 1 ][ 1 ].darEstado( );
                assertTrue( "Si no se ha terminado el juego, el estado debe ser CERCA_0 o CERCA_1", Casilla.CERCA_0 == estadoCasilla || Casilla.CERCA_1 == estadoCasilla );
            }
            else
            {
                int estadoCasilla = casillas[ 1 ][ 1 ].darEstado( );
                assertTrue( "Si se termin� el juego, el estado debe ser BOMBA_ESTALLADA o VACIA", Casilla.BOMBA_ESTALLADA == estadoCasilla || Casilla.CERCA_0 == estadoCasilla );
            }
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Verificar la excepci�n cuando se intenta destapar una casilla por fuera del campo minado
     */
    public void testDestapar2( )
    {
        setupEscenario1( );

        try
        {
            campoMinado.destapar( 10, 10 );
            fail( "Debe lanzarse una excepci�n porque la posici�n no est� en el campo minado" );
        }
        catch( Exception e )
        {
            assertTrue( "Esta excepci�n era esperada", true );
        }
    }

    /**
     * Verificar el m�todo reiniciar
     */
    public void testReiniciar( )
    {
        try
        {
            int resultado = CampoMinado.JUEGO_GANADO;

            while( resultado != CampoMinado.CONTINUA_JUEGO )
            {
                // destapa la posici�n 1,1
                setupEscenario1( );
                resultado = campoMinado.destapar( 1, 1 );
            }
        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }

        // si la casilla no est� destapada falla
        Casilla[][] casillas = campoMinado.darCasillas( );
        assertFalse( "La casilla deber�a estar destapada", casillas[ 1 ][ 1 ].darEstado( ) == Casilla.TAPADA );

        campoMinado.inicializarJuego( );
        // como se reinicio el juego la casilla deber�a estar tapada
        casillas = campoMinado.darCasillas( );
        assertTrue( "La casilla deber�a estar tapada otra vez", casillas[ 1 ][ 1 ].darEstado( ) == Casilla.TAPADA );
    }

    /**
     * Verificar el m�todo darNumeroMinasRestantes
     */
    public void testDarNumeroMinasRestantes( )
    {
        setupEscenario1( );
        try
        {
            campoMinado.marcar( 0, 0 );
            assertEquals( "El n�mero de minas restantes debe haber disminuido en 1", 0, campoMinado.darNumeroMinasRestantes( ) );

        }
        catch( Exception e )
        {
            fail( e.getMessage( ) );
        }
    }

    /**
     * Verifica que el campo minado se cree con la informaci�n del archivo
     */
    public void testPersonalizarCampo( )
    {
        setupEscenario4( );
        assertEquals( "El campo no tiene la altura especificada en el archivo", 10, campoMinado.darFilas( ) );
        assertEquals( "El campo no tiene el ancho especificada en el archivo", 15, campoMinado.darColumnas( ) );
        assertEquals( "El campo no tiene el n�mero de minas especificado en el archivo", 30, campoMinado.darNumeroMinasRestantes( ) );
    }

}
