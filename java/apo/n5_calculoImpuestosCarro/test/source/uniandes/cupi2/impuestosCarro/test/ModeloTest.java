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

import junit.framework.TestCase;
import uniandes.cupi2.impuestosCarro.mundo.Modelo;

/**
 * Clase de prueba para el modelo de un veh�culo
 */
public class ModeloTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * A�o de un modelo
     */
    private String anio;
    /**
     * Precio del modelo
     */
    private double precio;
    /**
     * Modelo de veh�culo
     */
    private Modelo modelo;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Escenario con la creaci�n v�lida de un modelo de veh�culo
     */
    private void setupEscenario1( )
    {
        anio = "2005";
        precio = 43000000;
        modelo = new Modelo( anio, precio );
    }

    /**
     * Prueba la obtenci�n v�lida del a�o de un modelo
     */
    public void testDarAnho( )
    {
        //Prepara el escenario
        setupEscenario1( );

        //Valida los datos
        assertEquals( anio, modelo.darAnio( ) );
    }

    /**
     * Prueba la obtenci�n v�lida del precio de un modelo
     */
    public void testDarPrecio( )
    {
        //Prepara el escenario
        setupEscenario1( );

        //Valida los datos
        assertEquals( precio, modelo.darPrecio( ), 0 );
    }
}