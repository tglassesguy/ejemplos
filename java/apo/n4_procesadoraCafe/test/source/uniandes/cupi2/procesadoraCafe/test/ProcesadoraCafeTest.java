/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n4_procesadoraCafe
 * Autor: Catalina Rodr�guez - 01-sep-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.procesadoraCafe.test;

import junit.framework.TestCase;
import uniandes.cupi2.procesadoraCafe.mundo.Cliente;
import uniandes.cupi2.procesadoraCafe.mundo.ProcesadoraCafe;
import uniandes.cupi2.procesadoraCafe.mundo.Producto;
import uniandes.cupi2.procesadoraCafe.mundo.Proveedor;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase ProcesadoraCafe est�n correctamente implementados
 */
public class ProcesadoraCafeTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private ProcesadoraCafe procesadoraCafe;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva ProcesadoraCafe vac�a
     */
    private void setupEscenario1( )
    {
        procesadoraCafe = new ProcesadoraCafe( 5000000.0 );
    }
    
    /**
     * Construye una nueva ProcesadoraCafe con productos, clientes y proveedores
     */
    private void setupEscenario2( )
    {
        procesadoraCafe = new ProcesadoraCafe( 5000000.0 );
        
        try 
        {
			procesadoraCafe.agregarCliente("cliente1", "nit1", "tel1");
			procesadoraCafe.agregarProveedor("proveedor1", 123456789, "telefono1", "origen", 7000.50);
			procesadoraCafe.agregarProducto("producto1", "amora1", "acidez1", "cuerpo1", 1000.50, 1.20);
		} 
        catch (Exception e) 
        {
        	fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
		
    }
    
    /**
     * Construye una nueva ProcesadoraCafe con productos, clientes, proveedores,
     * compras de insumos, producciones y ventas
     */
    private void setupEscenario3( )
    {
        procesadoraCafe = new ProcesadoraCafe( 5000000.0 );
        
        try 
        {
			procesadoraCafe.agregarCliente("cliente1", "nit1", "tel1");
			procesadoraCafe.agregarCliente("cliente2", "nit2", "tel2");
			procesadoraCafe.agregarCliente("cliente3", "nit3", "tel3");
			
			procesadoraCafe.agregarProveedor("proveedor1", 123456789, "telefono1", "origen1", 7000.50);
			procesadoraCafe.agregarProveedor("proveedor2", 12345678, "telefono2", "origen2", 6000.50);
			procesadoraCafe.agregarProveedor("proveedor3", 1234567, "telefono3", "origen3", 8000.50);
			procesadoraCafe.agregarProveedor("proveedor4", 123456, "telefono4", "origen3", 6000.49);
			
			procesadoraCafe.agregarProducto("producto1", "amora1", "acidez1", "cuerpo1", 1000.50, 1.20);
			procesadoraCafe.agregarProducto("producto2", "amora1", "acidez1", "cuerpo1", 900.50, 1);
			procesadoraCafe.agregarProducto("producto3", "amora1", "acidez1", "cuerpo1", 1200.50, 1.10);
			
			procesadoraCafe.comprarInsumo(123456789, 130);
			procesadoraCafe.comprarInsumo(12345678, 150);
			procesadoraCafe.comprarInsumo(1234567, 200);
			procesadoraCafe.comprarInsumo(123456, 180);
			
			procesadoraCafe.producir("producto1", 200);
			procesadoraCafe.producir("producto2", 300);
			procesadoraCafe.producir("producto3", 100);
			
			procesadoraCafe.vender("producto1", "nit1", 50);
			procesadoraCafe.vender("producto2", "nit3", 30);
			procesadoraCafe.vender("producto2", "nit2", 60);
			procesadoraCafe.vender("producto2", "nit2", 10);
			procesadoraCafe.vender("producto3", "nit1", 10);
			
		} 
        catch (Exception e) 
        {
        	fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
		
    }

    /**
     * Prueba 1 - Prueba el m�todo constructor de la clase ProcesadoraCafe <br>
     * <b>M�todo a probar: </b>
     * ProcesadorCafe, darProductos, darClientes, darProveedores, darDineroEnCaja, darInsumoDisponible
     */
    public void testProcesadoraCafe( )
    {
        setupEscenario1( );
        
        assertNotNull("La lista de producto no ha sido inicializada", procesadoraCafe.darProductos( ));
        assertNotNull("La lista de clientes no ha sido inicializada", procesadoraCafe.darClientes( ));
        assertNotNull("La lista de proveedores no ha sido inicializada", procesadoraCafe.darProveedores( ));
        assertEquals("La cantidad de dinero en caja no se inicializa correctamente", 5000000.0, procesadoraCafe.darDineroEnCaja( ));
        assertEquals("La cantidad de insumos no se inicializa correctamente", 0.0, procesadoraCafe.darInsumoDisponible( ));
    }

    /**
     * Prueba 2 - Prueba el m�todo agregarCliente <br>
     * <b>M�todo a probar: </b>
     * agregarCliente, buscarCliente, darClientes
     * <b>Resultado esperado: </b>
     * 1. Se agrega un nuevo cliente a la procesadora
     * 2. Si ya existen un cliente con el mismo NIT se dispara una excepci�n
     */
    public void testAgregarCliente()
    {
    	setupEscenario1();
    	
    	//Caso 1
    	try 
    	{
			procesadoraCafe.agregarCliente("cliente1", "nit1", "telefono1");
			
			assertTrue("La lista de clientes deber�a tener un elemento", procesadoraCafe.darClientes().size() == 1);
			Cliente c = procesadoraCafe.buscarCliente("nit1");
			assertNotNull("No se crea correctamente al cliente", c);
		} 
    	catch (Exception e) 
    	{
    		fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
    	
    	//Caso 2
    	try 
    	{
			procesadoraCafe.agregarCliente("cliente1", "nit1", "telefono1");
			fail("No se deber�a permitir agregar el cliente, ya se ha agregado ese NIT");
    	}
    	catch (Exception e) 
    	{
    		//Deber�a generar excepci�n
		}
    }
    
    /**
     * Prueba 3 - Prueba el m�todo agregarProveedor <br>
     * <b>M�todo a probar: </b>
     * agregarProveedor, buscarProveedor, darProveedores
     * <b>Resultado esperado: </b>
     * 1. Se agrega un nuevo proveedor a la procesadora
     * 2. Si ya existen un proveedor con la misma c�dula se dispara una excepci�n
     */
    public void testAgregarProveedor()
    {
    	setupEscenario1();
    	
    	//Caso 1
    	try 
    	{
			procesadoraCafe.agregarProveedor("proveedor1", 123456789, "telefono1", "origen", 7600.50);
			
			assertTrue("La lista de proveedores deber�a tener un elemento", procesadoraCafe.darProveedores().size() == 1);
			Proveedor p = procesadoraCafe.buscarProveedor(123456789);
			assertNotNull("No se crea correctamente al proveedor", p);
		} 
    	catch (Exception e) 
    	{
    		fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
    	
    	//Caso 2
    	try 
    	{
    		procesadoraCafe.agregarProveedor("proveedor1", 123456789, "telefono1", "origen", 7600.50);
			fail("No se deber�a permitir agregar el proveedor, ya se ha agregado esa c�dula");
    	}
    	catch (Exception e) 
    	{
    		//Deber�a generar excepci�n
		}
    }
    
    /**
     * Prueba 4 - Prueba el m�todo agregarProducto <br>
     * <b>M�todo a probar: </b>
     * agregarProducto, buscarProducto, darProductos
     * <b>Resultado esperado: </b>
     * 1. Se agrega un nuevo producto a la procesadora
     * 2. Si ya existen un producto con el mismo nombre se dispara una excepci�n
     */
    public void testAgregarProducto()
    {
    	setupEscenario1();
    	
    	//Caso 1
    	try 
    	{
			procesadoraCafe.agregarProducto("producto1", "amora1", "acidez1", "cuerpo1", 1000.90, 1.20);
			
			assertTrue("La lista de productos deber�a tener un elemento", procesadoraCafe.darProductos().size() == 1);
			Producto p = procesadoraCafe.buscarProducto("producto1");
			assertNotNull("No se crea correctamente el producto", p);
		} 
    	catch (Exception e) 
    	{
    		fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
    	
    	//Caso 2
    	try 
    	{
    		procesadoraCafe.agregarProducto("producto1", "amora1", "acidez1", "cuerpo1", 1000.90, 1.20);
    		fail("No se deber�a permitir agregar el producto, ya existe uno con el mismo nombre");
    	}
    	catch (Exception e) 
    	{
    		//Deber�a generar excepci�n
		}
    }
    
    /**
     * Prueba 5 - Prueba el m�todo comprarInsumo <br>
     * <b>M�todo a probar: </b>
     * comprarInsumo, darDineroEnCaja, darInsumoDisponible
     * <b>Resultado esperado: </b>
     * 1. Se realiza la compra del insumo
     * 2. Si el dinero en caja no es suficiente para la compra de insumos se dispara una excepci�n
     */
    public void testComprarInsumo()
    {
    	setupEscenario2();
    	
    	try 
    	{
			procesadoraCafe.comprarInsumo(123456789, 500.0);
			assertEquals("La cantidad de dinero en caja no disminuye correctamente", 1499750.0, procesadoraCafe.darDineroEnCaja( ));
			assertEquals("La cantidad de insumos no se incrementa correctamente", 500.0, procesadoraCafe.darInsumoDisponible( ));
		} 
    	catch (Exception e) 
    	{
    		fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
    	
    	try 
    	{
			procesadoraCafe.comprarInsumo(123456789, 300.0);
			fail("No se deber�an comprar los insumos, el dinero en caja no es suficiente");
		} 
    	catch (Exception e) 
    	{
    		//Deber�a generar una excepci�n
		}
    }
    
    /**
     * Prueba 6 - Prueba el m�todo producir <br>
     * <b>M�todo a probar: </b>
     * producir, comprarInsumo, darInsumoDisponible, buscarProducto
     * <b>Resultado esperado: </b>
     * 1. Se realiza la producci�n del producto de la Procesadora
     * 2. Si el insumo disponible no es suficiente para la producci�n se dispara una excepci�n
     */
    public void testProducir()
    {
    	setupEscenario2();
    	
    	try 
    	{
			procesadoraCafe.comprarInsumo(123456789, 500.0);
			procesadoraCafe.producir("producto1", 350);
			Producto p = procesadoraCafe.buscarProducto("producto1");
			assertEquals("La cantidad de kilos disponibles del producto no aumenta correctamente", 350.0, p.darCantidadDisponible());
			assertEquals("La cantidad de insumos disponibles no disminuye correctamente", 80.0, procesadoraCafe.darInsumoDisponible( ));
		} 
    	catch (Exception e) 
    	{
    		fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
    	
    	try 
    	{
    		procesadoraCafe.producir("producto1", 70);
			fail("No se deber�a producir, los insumos disponibles no son suficientes");
		} 
    	catch (Exception e) 
    	{
    		//Deber�a generar una excepci�n
		}
    }
    
    /**
     * Prueba 7 - Prueba el m�todo vender <br>
     * <b>M�todo a probar: </b>
     * vender, producir, comprarInsumo, darDineroEnCaja, buscarProducto, buscarCliente
     * <b>Resultado esperado: </b>
     * 1. Se realiza la venta del producto
     * 2. Si la cantidad disponible del producto no es suficiente se dispara una excepci�n
     */
    public void testVender()
    {
    	setupEscenario2();
    	
    	try 
    	{
			procesadoraCafe.comprarInsumo(123456789, 500.0);
			procesadoraCafe.producir("producto1", 350);
			procesadoraCafe.vender("producto1", "nit1", 300);
			
			Producto p = procesadoraCafe.buscarProducto("producto1");
			Cliente c = procesadoraCafe.buscarCliente("nit1");
			assertEquals("La cantidad de kilos disponibles del producto no disminuye correctamente", 50.0, p.darCantidadDisponible());
			assertEquals("La cantidad de kilos vendidos al cliente no aumenta correctamente", 300.0, c.darCantidadVendida());
			assertEquals("El dinero en caja no aumenta correctamente", 1799900.0, procesadoraCafe.darDineroEnCaja( ));
		} 
    	catch (Exception e) 
    	{
    		fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
    	
    	try 
    	{
    		procesadoraCafe.vender("producto1", "nit1", 51);
			fail("No se deber�a realizar la venta, la cantidad de kilos del producto no es suficiente");
		} 
    	catch (Exception e) 
    	{
    		//Deber�a generar una excepci�n
		}
    }
        
    /**
     * Prueba 8 - Prueba el m�todo eliminarProducto <br>
     * <b>M�todo a probar: </b>
     * eliminarProducto, comprarInsumo, producir, vender, buscarProducto
     * <b>Resultado esperado: </b>
     * 1. Se eliminar el producto
     * 2. Si al eliminar, la cantidad disponible del producto es mayor a cero se dispara una excepci�n
     */
    public void testEliminarProducto()
    {
    	setupEscenario2();
    	
    	try 
    	{
    		procesadoraCafe.comprarInsumo(123456789, 500.0);
			procesadoraCafe.producir("producto1", 50);
			procesadoraCafe.eliminarProducto("producto1");
			fail("No se deber�a eliminar el producto, la cantidad de kilos disponibles es mayor a cero");
		} 
    	catch (Exception e) 
    	{
    		//Deber�a generar una excepci�n
		}
    	
    	try 
    	{
			procesadoraCafe.vender("producto1", "nit1", 50);
			
			procesadoraCafe.eliminarProducto("producto1");
			Producto p = procesadoraCafe.buscarProducto("producto1");
			assertNull("El producto no deber�a existir en la Procesadora de Caf�", p);
		} 
    	catch (Exception e) 
    	{
    		fail("No se deber�a generar la excepci�n: " + e.getMessage( ));
		}
    }
    
    /**
     * Prueba 9 - Prueba el m�todo darClienteFiel <br>
     * <b>M�todo a probar: </b>
     * darClienteFiel, vender
     * <b>Resultado esperado: </b>
     * 1. En caso de no tener clientes la respuesta es null
     * 2. Si existen clientes, encuentra el cliente al que se le ha vendida la mayor cantidad de kilos
     * 3. En caso de empate, devuelve el primer cliente encontrado
     */
    public void testDarClienteFiel( )
    {
    	setupEscenario1();
    	
    	String cliente = procesadoraCafe.darClienteFiel();
        assertNull("Respuesta incorrecta: No hay clientes debe retornar null", cliente);
    	
        setupEscenario3( );
        
        cliente = procesadoraCafe.darClienteFiel();
        assertEquals("El cliente es incorrecto", "cliente2", cliente);
        
        try 
        {
			procesadoraCafe.vender("producto1", "nit1", 10);
		} 
        catch (Exception e) 
        {
			fail("No deber�a generar un excepci�n");
		}
        
        cliente = procesadoraCafe.darClienteFiel();
        assertEquals("El cliente es incorrecto", "cliente1", cliente);
    }
    
    /**
     * Prueba 10 - Prueba el m�todo darProdructoMayorDisponibilidad <br>
     * <b>M�todo a probar: </b>
     * darProdructoMayorDisponibilidad, vender
     * <b>Resultado esperado: </b>
     * 1. En caso de no tener productos la respuesta es null
     * 2. Si existen productos, encuentra el producto con mayor cantidad de kilos disponibles
     * 3. En caso de empate, devuelve el primer producto encontrado
     */
    public void testDarProductoMayorDisponibilidad( )
    {
        setupEscenario1();
    	
        String producto = procesadoraCafe.darProductoMayorDisponibilidad();
        assertNull("Respuesta incorrecta: No hay productos debe retornar null", producto);
    	
        setupEscenario3( );
        
        producto = procesadoraCafe.darProductoMayorDisponibilidad();
        assertEquals("El producto es incorrecto", "producto2", producto);
        
        try 
        {
			procesadoraCafe.vender("producto2", "nit1", 50);
		} 
        catch (Exception e) 
        {
			fail("No deber�a generar un excepci�n");
		}
        
        producto = procesadoraCafe.darProductoMayorDisponibilidad();
        assertEquals("El producto es incorrecto", "producto1", producto);
    }
    
    /**
     * Prueba 11 - Prueba el m�todo darProveedorMasBarato <br>
     * <b>M�todo a probar: </b>
     * darProveedorMasBarato, agregarProveedor
     * <b>Resultado esperado: </b>
     * 1. En caso de no tener proveedores la respuesta es null
     * 2. Si existen proveedores, encuentra al proveedor que ofrece el menor precio por kilo
     * 3. En caso de empate, devuelve el primer proveedor encontrado
     */
    public void testDarProveedorMasBarato( )
    {
        setupEscenario1();
    	
        String proveedor = procesadoraCafe.darProveedorMasBarato();
        assertNull("Respuesta incorrecta: No hay proveedores debe retornar null", proveedor);
    	
        setupEscenario3( );
        
        proveedor = procesadoraCafe.darProveedorMasBarato();
        assertEquals("El proveedor es incorrecto", "proveedor4", proveedor);
        
        try 
        {
			procesadoraCafe.agregarProveedor("proveedor5", 98765, "tel5", "origen", 6000.49);
		} 
        catch (Exception e) 
        {
			fail("No deber�a generar un excepci�n");
		}
        
        proveedor = procesadoraCafe.darProveedorMasBarato();
        assertEquals("El proveedor es incorrecto", "proveedor4", proveedor);
    }
    
    /**
     * Prueba 12 - Prueba el m�todo buscarProveedor <br>
     * <b>M�todos a probar: </b>
     * buscarProveedor
     * <b>Resultado esperado: </b>
     * 1. B�squeda exitosa del proveedor
     * 2. En caso de no encontrar un proveedor con ese n�mero de c�dula retorna null
    */
    public void testBuscarProveedor()
    {
    	setupEscenario2();
    	
    	Proveedor p = procesadoraCafe.buscarProveedor(123456789);
    	assertNotNull("El resultado de la b�squeda no deber�a ser nulo", p);
    	assertEquals( "Proveedor incorrecto", "proveedor1", p.darNombre( ));
        assertEquals( "Proveedor incorrecto", 123456789, p.darCedula( ));
        
    	p = procesadoraCafe.buscarProveedor(12345678);
    	assertNull("El resultado de la b�squeda deber�a ser nulo", p);	
    }
    
    /**
     * Prueba 13 - Prueba el m�todo buscarCliente <br>
     * <b>M�todos a probar: </b>
     * buscarCliente
     * <b>Resultado esperado: </b>
     * 1. B�squeda exitosa del cliente
     * 2. En caso de no encontrar un cliente con ese NIT retorna null
    */
    public void testBuscarCliente()
    {
    	setupEscenario2();
    	
    	Cliente c = procesadoraCafe.buscarCliente("nit1");
    	assertNotNull("El resultado de la b�squeda no deber�a ser nulo", c);
    	assertEquals( "Cliente incorrecto", "cliente1", c.darNombre( ));
        assertEquals( "Cliente incorrecto", "nit1", c.darNit( ));
        
        c = procesadoraCafe.buscarCliente("nit2");
    	assertNull("El resultado de la b�squeda deber�a ser nulo", c);	
    }
    
    /**
     * Prueba 14 - Prueba el m�todo buscarProducto <br>
     * <b>M�todos a probar: </b>
     * buscarProducto
     * <b>Resultado esperado: </b>
     * 1. B�squeda exitosa del producto
     * 2. En caso de no encontrar un producto con ese nombre retorna null
    */
    public void testBuscarProducto()
    {
    	setupEscenario2();
    	
    	Producto p = procesadoraCafe.buscarProducto("producto1");
    	assertNotNull("El resultado de la b�squeda no deber�a ser nulo", p);
    	assertEquals( "Producto incorrecto", "producto1", p.darNombre( ));
        
        p = procesadoraCafe.buscarProducto("producto2");
    	assertNull("El resultado de la b�squeda deber�a ser nulo", p);	
    }
}