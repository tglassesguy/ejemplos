/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Impuestos de Carros
 * Autor: Katalina Marcos.
 * Modificaci�n: Diana Puentes - Jun 23, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.impuestosCarro.mundo.Linea;
import uniandes.cupi2.impuestosCarro.mundo.Marca;
import uniandes.cupi2.impuestosCarro.mundo.Modelo;

/**
 * Clase de prueba para la marca de un veh�culo
 */
public class MarcaTest extends TestCase
{

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Nombre de la marca de prueba
     */
    private String nombre;
    /**
     * Marca de prueba
     */
    private Marca marca;
    /**
     * L�nea de prueba
     */
    private Linea linea;
    /**
     * Modelo de prueba
     */
    private Modelo modelo;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Escenario con una Marca sin l�neas
     */
    private void setupEscenario1( )
    {
        nombre = "mazda";
        marca = new Marca( nombre );
    }

    /**
     * Escenario de una marca con una l�nea sin modelos
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        linea = new Linea( "allegro" );
        marca.adicionarLinea( linea );
    }

    /**
     * Escenario de una marca con una l�nea con un modelo
     */
    private void setupEscenario3( )
    {
        setupEscenario2( );
        modelo = new Modelo( "2005", 43000000 );
        linea.adicionarModelo( modelo );
    }

    /**
     * Prueba la obtenci�n v�lida del nombre de la marca
     */
    public void testDarNombre( )
    {
        //Configura el escenario de prueba
        setupEscenario1( );

        //Valida que el nombre sea el adecuado
        assertEquals( nombre, marca.darNombre( ) );
    }

    /**
     * Prueba la correcta adici�n de una l�nea
     */
    public void testAdicionarLinea( )
    {
        ArrayList lineas;
        Linea nuevaLinea;
        int antes;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Prueba la correcta adici�n de l�neas
        lineas = marca.darLineas( );
        antes = lineas.size( );
        nuevaLinea = new Linea( "mazda3" );
        marca.adicionarLinea( nuevaLinea );
        assertEquals( antes + 1, lineas.size( ) );
        assertEquals( nuevaLinea, lineas.get( antes ) );
    }

    /**
     * Prueba la obtenci�n de las l�neas en una marca sin l�neas
     */
    public void testDarLineasVacio( )
    {
        ArrayList lineas;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Verifica que no hayan l�neas
        lineas = marca.darLineas( );
        assertEquals( 0, lineas.size( ) );
    }

    /**
     * Prueba la obtenci�n de los l�neas en una marca con l�neas
     */
    public void testDarLineas( )
    {
        ArrayList lineas;
        Linea unaLinea;
        Modelo unModelo;

        //Configura el escenario de prueba
        setupEscenario3( );

        //Verifica que est� la l�nea de prueba
        lineas = marca.darLineas( );
        assertEquals( 1, lineas.size( ) );
        unaLinea = ( Linea )lineas.get( 0 );
        assertEquals( linea, unaLinea );
        unModelo = unaLinea.buscarModelo( modelo.darAnio( ) );
        assertEquals( modelo, unModelo );
    }

    /**
     * Prueba la b�squeda de una l�nea que existe
     */
    public void testBuscarLineaExiste( )
    {
        Linea lineaEncontrada;

        //Configura el escenario de prueba
        setupEscenario2( );

        //Verifica que se encuentre una l�nea que est� en la marca
        lineaEncontrada = marca.buscarLinea( linea.darNombre( ) );
        assertEquals( linea, lineaEncontrada );
    }

    /**
     * Prueba la b�squeda de una l�nea que no existe
     */
    public void testBuscarLineaNoExiste( )
    {
        Linea lineaEncontrada;

        //Configura el escenario de prueba
        setupEscenario1( );

        //Verifica que no encuentre un modelo que no est� en la l�nea
        lineaEncontrada = marca.buscarLinea( "laLinea" );
        assertNull( lineaEncontrada );
    }
}