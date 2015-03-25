package uniandes.cupi2.procesadoraCafe.test;

import junit.framework.TestCase;
import uniandes.cupi2.procesadoraCafe.mundo.Cliente;

/**
 * Verifica la correcta implementaci�n de la clase Cliente
 */
public class ClienteTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase donde se van a realizar las pruebas
     */
    private Cliente cliente;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Crea un nuevo Cliente
     */
    public void setupEscenario1()
    {
        cliente = new Cliente("nombre1", "nit1", "telefono1");
    }
    
    /**
     * Prueba 1 - Prueba el m�todo constructor de la clase Cliente <br>
     * <b>M�todos a probar: </b> 
     * Cliente, darNombre, darNit, darTelefono, darCantidadVendida
     */
    public void testCliente()
    {
        setupEscenario1( );
        
        assertEquals("El nombre del cliente no es inicializado correctamente", "nombre1", cliente.darNombre( ));
        assertEquals("El NIT del cliente no es inicializado correctamente", "nit1", cliente.darNit( ));
        assertEquals("El tel�fono del cliente no es inicializado correctamente", "telefono1", cliente.darTelefono( ));
        assertEquals("La cantidad de kilos vendidos al cliente no es inicializada correctamente", 0.0, cliente.darCantidadVendida( ));
    }
    
    /**
     * Prueba 2 - Prueba el m�todo registarVentaCafe <br>
     * <b>M�todos a probar: </b> 
     * registarVentaCafe, darCantidadVendida 
     */
    public void testRegistrarVentaCafe()
    {
        setupEscenario1( );
        
        cliente.registarVentaCafe( 10.3 );
        assertEquals( "No se incrementa correctamente la cantidad vendida", 10.3, cliente.darCantidadVendida( ) );
    }
    
    /**
     * Prueba 3 - Prueba el m�todo darNombre
     * <b>M�todos a probar: </b> 
     * darNombre
     */
    public void testDarNombre()
    {
    	setupEscenario1( );
        assertEquals("El nombre del cliente no es inicializado correctamente", "nombre1", cliente.darNombre( ));
    }
        
    /**
     * Prueba 4 - Prueba el m�todo darNit
     * <b>M�todos a probar: </b> 
     * darNit
     */
    public void testDarNit()
    {
    	setupEscenario1( );
        assertEquals("El NIT del cliente no es inicializado correctamente", "nit1", cliente.darNit( ));
    }
    
    /**
     * Prueba 5 - Prueba el m�todo darTelefono
     * <b>M�todos a probar: </b> 
     * darTelefono
     */
    public void testDarTelefono()
    {
    	setupEscenario1( );
        assertEquals("El tel�fono del cliente no es inicializado correctamente", "telefono1", cliente.darTelefono( ));
    }
    
    /**
     * Prueba 6 - Prueba el m�todo darCantidadVendida
     * <b>M�todos a probar: </b> 
     * darCantidadVendida
     */
    public void testDarCantidadVendida()
    {
    	setupEscenario1( );
        assertEquals("La cantidad de kilos vendidos al cliente no es inicializada correctamente", 0.0, cliente.darCantidadVendida( ));
    }
}
