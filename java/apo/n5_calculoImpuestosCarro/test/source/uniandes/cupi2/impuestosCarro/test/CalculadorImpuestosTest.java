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
import uniandes.cupi2.impuestosCarro.mundo.CalculadorImpuestos;

/**
 * Clase de prueba para el calculador de impuestos de veh�culos
 */
public class CalculadorImpuestosTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Calculador de impuestos
     */
    private CalculadorImpuestos calculador;
    /**
     * Marca de prueba
     */
    private String marca;
    /**
     * L�nea de prueba
     */
    private String linea;
    /**
     * Modelo de prueba
     */
    private String modelo;
    /**
     * Precio de prueba
     */
    private double precio;
    /**
     * Valor de prueba
     */
    private double pago;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Prepara un escenario con el calculador
     */
    private void setupEscenario1( )
    {
        try
        {
            //Crea y prepara al calculador de impuestos
            calculador = new CalculadorImpuestos( );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Prepara un escenario con la informaci�n de un veh�culo que no existe
     */
    private void setupEscenario2( )
    {
        setupEscenario1( );
        marca = "una marca";
        linea = "una linea";
        modelo = "1800";
        precio = -1;
        pago = -1;
    }

    /**
     * Prepara un escenario con la informaci�n de un veh�culo que existe
     */
    private void setupEscenario3( )
    {
        setupEscenario1( );
        marca = "Mazda";
        linea = "Allegro";
        modelo = "2005";
        precio = 43000000;
        pago = 860000;
    }

    /**
     * Prueba la b�squeda de un aval�o que no existe
     */
    public void testBuscarAvaluoVehiculoNoExiste( )
    {
        double precioObtenido;

        //Configura el escenario de prueba
        setupEscenario2( );

        try
        {
            precioObtenido = calculador.buscarAvaluoVehiculo( marca, linea, modelo );
            fail( "Debi� levantar excepci�n porque el veh�culo no existe. El precio dado fue" + precioObtenido );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }

    }

    /**
     * Prueba la b�squeda de un aval�o que existe
     */
    public void testBuscarAvaluoVehiculoExiste( )
    {
        double precioObtenido = -1;

        //Configura el escenario de prueba
        setupEscenario3( );

        try
        {
            precioObtenido = calculador.buscarAvaluoVehiculo( marca, linea, modelo );
            assertEquals( "Igualdad de los precios", precio, precioObtenido, 0 );
        }
        catch( Exception e )
        {
            fail( "Error buscando el veh�culo " + marca + "-" + linea + "-" + modelo );
        }

    }

    /**
     * Verifica el c�lculo del pago de un veh�culo que no existe
     */
    public void testCalcularPagoVehiculoNoExiste( )
    {
        double pagoObtenido = -1;

        //Configura el ambiente de prueba
        setupEscenario2( );

        try
        {
            pagoObtenido = calculador.calcularPago( marca, linea, modelo, false, false, false );
            fail( "Debi� levantar excepci�n porque el veh�culo no existe. El pago dado fue" + pagoObtenido );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Verifica el c�lculo del pago de un veh�culo que existe sin aplicar descuentos
     */
    public void testCalcularPagoExisteSinDescuentos( )
    {
        double pagoObtenido = -1;

        //Configura el ambiente de prueba
        setupEscenario3( );

        try
        {
            pagoObtenido = calculador.calcularPago( marca, linea, modelo, false, false, false );
            assertEquals( "Igualdad de pagos", pago, pagoObtenido, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Prueba el c�lculo del pago de un veh�culo que existe con el descuento de pronto pago;
     */
    public void testCalcularPagoExisteProntoPago( )
    {
        double pagoObtenido = -1;

        //Configura el ambiente de prueba
        setupEscenario3( );

        pago -= pago * CalculadorImpuestos.PORC_DESC_PRONTO_PAGO / 100;

        try
        {
            pagoObtenido = calculador.calcularPago( marca, linea, modelo, true, false, false );
            assertEquals( "Igualdad de pagos", pago, pagoObtenido, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Prueba el calculo del pago de un veh�culo que existe con el descuento de servicio p�blico;
     */
    public void testCalcularPagoExisteSPublico( )
    {
        double pagoObtenido = -1;

        //Configura el ambiente de prueba
        setupEscenario3( );

        pago -= CalculadorImpuestos.VALOR_DESC_SERVICIO_PUBLICO;

        try
        {
            pagoObtenido = calculador.calcularPago( marca, linea, modelo, false, true, false );
            assertEquals( "Igualdad de pagos", pago, pagoObtenido, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Prueba el c�lculo del pago de un veh�culo que existe con el descuento de traslado de cuenta;
     */
    public void testCalcularPagoExisteTrasladoCuenta( )
    {
        double pagoObtenido = -1;

        //Configura el ambiente de prueba
        setupEscenario3( );

        pago -= pago * CalculadorImpuestos.PORC_DESC_TRASLADO_CUENTA / 100;

        try
        {
            pagoObtenido = calculador.calcularPago( marca, linea, modelo, false, false, true );
            assertEquals( "Igualdad de pagos", pago, pagoObtenido, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }

    /**
     * Prueba el c�lculo del pago de un veh�culo que existe con todos los descuentos;
     */
    public void testCalcularPagoExisteTodosDescuentos( )
    {
        double pagoObtenido = -1;

        //Configura el ambiente de prueba
        setupEscenario3( );

        pago -= pago * CalculadorImpuestos.PORC_DESC_PRONTO_PAGO / 100;
        pago -= CalculadorImpuestos.VALOR_DESC_SERVICIO_PUBLICO;
        pago -= pago * CalculadorImpuestos.PORC_DESC_TRASLADO_CUENTA / 100;

        try
        {
            pagoObtenido = calculador.calcularPago( marca, linea, modelo, true, true, true );
            assertEquals( "Igualdad de pagos", pago, pagoObtenido, 0 );
        }
        catch( Exception e )
        {
            assertTrue( true );
        }
    }
}