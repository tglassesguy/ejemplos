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

package uniandes.cupi2.procesadoraCafe.mundo;

import java.util.ArrayList;

/**
 *  Clase que modela a la empresa Procesadora de Caf�
 */
public class ProcesadoraCafe
{
    //-----------------------------------------------------------------
    // Atributos
    //-------------------------------------------------
	/**
	 * Vector que modela los productos que la procesadora puede fabricar.
	 */
	private ArrayList productos;

	/**
	 * Vector que modela los proveedores de la procesadora.
	 */
	private ArrayList proveedores;
	
	/**
	 * Vector que modela los clientes de la procesadora.
	 */
	private ArrayList clientes;

	
	/**
	 * Cantidad de dinero disponible en la Procesadora de Caf�
	 */
	private double dineroEnCaja;
	
	/**
	 * Cantidad de kilos de insumo (caf� tostado) 
	 */
	private double insumoDisponible;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

	/**
     * M�todo constructor de una nueva Procesadora de Caf� <br>
     * <b>post: </b> Se crea una procesadora vac�a y se inicializan las colecciones de:
     *  proveedores, clientes y productos. El dinero en caja se inicializa con el par�metro dado
     *  y la cantidad de insumo disponible en cero.
	 * @param dinero Dinero en caja inicial. Valor real mayor a cero 
     */
    public ProcesadoraCafe(double dinero)
    {
    	
   	 proveedores = new ArrayList();
   	 clientes = new ArrayList();
   	 productos = new ArrayList();
   	 
   	 dineroEnCaja = dinero;
   	 insumoDisponible = 0;

    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * M�todo que devuelve la lista de los productos de la Procesadora <br>
     * <b>pre: </b> La lista de productos debe estar inicializada. <br>
     * @return Retorna una lista con productos.
     */
    public ArrayList darProductos() {
    	return productos;
    }
    
    /**
     * M�todo que devuelve la lista de los proveedores de la Procesadora <br>
     * <b>pre: </b> La lista de proveedores debe estar inicializada. <br>
     * @return Retorna una lista con proveedores.
     */
    public ArrayList darProveedores() {
    	return proveedores;
    }
    
    /**
     * M�todo que devuelve la lista de los clientes de la Procesadora <br>
     * <b>pre: </b> La lista de clientes debe estar inicializada. <br>
     * @return Retorna una lista con clientes.
     */
    public ArrayList darClientes() {
    	return clientes;
    }
    
    
    /**
     * M�todo que devuelve el dinero en caja de la procesadora.
     * @return Retorna el dinero en caja de la procesadora.
     */
    public double darDineroEnCaja()
    {
    	return dineroEnCaja;
    }
    
    /**
     * M�todo que devuelve la cantidad de insumo (cafe tostado) disponible en la procesadora.
     * @return Retorna la cantidad de kilogramos de insumo disponibles.
     */
    public double darInsumoDisponible()
    {
    	return insumoDisponible;
    }
    
    
    /**
     * M�todo que busca un proveedor en la lista de proveedores y lo devuelve. <br />
     * <b>pre: </b> La lista de proveedores debe estar inicializada.
     * @param cedula: C�dula del proveedor que se est� buscando.
     * @return El objeto proveedor cuya c�dula es igual a la que se pas� por par�metro o null si dicho proveedor no existe.
     */
    public Proveedor buscarProveedor(int cedula)
    {
    	boolean encontrado = false;
    	Proveedor respuesta = null;
    	
    	for (int i = 0; i < proveedores.size() && !encontrado; i++) 
    	{
			Proveedor p = (Proveedor) proveedores.get(i);
			if(p.darCedula() == cedula)
			{
				encontrado = true;
				respuesta = p;
			}
		}
    	
    	return respuesta;
    }  
    
    /**
     * M�todo que agrega un nuevo proveedor a la lista de proveedores de la procesadora, si �ste no se encontraba en la lista con anterioridad.<br>
     * <b>pre: </b> La lista de proveedores debe estar inicializada.
     * <b>post: </b> Se ha agragado un nuevo proveedor a la lista de proveedores.
     * @param nombreP - Corresponde al nombre del proveedor - nombreP != null y nombreP != ""
	 * @param cedulaP - Corresponde a la c�dula del proveedor - cedulaP > 0
	 * @param telefonoP - Corresponde al tel�fono del proveedor - telefonoP != null y telefonoP != ""
	 * @param origenP - Corresponde a la regi�n de origen del caf� vendido por el proveedor - origenP != null y origenP != ""
	 * @param precioP - Corresponde al precio por kilogramo de caf� tostado cobrado por el proveedor - precioP > 0
	 * @throws Exception - Se lanza una excepci�n si el proveedor ya exist�a en la lista de proveedores de la procesadora. 
     */
    public void agregarProveedor(String nombreP, int cedulaP, String telefonoP, String origenP, double precioP) throws Exception {
    	
    	for (int i = 0; i < proveedores.size(); i++) {
    		Proveedor tempProveedor = (Proveedor)proveedores.get(i);
    		if (tempProveedor.darCedula() == cedulaP) throw new Exception("El proveedor ya esiste.");
    	}
    	
    	Proveedor nuevoProveedor = new Proveedor(nombreP, cedulaP, telefonoP, origenP, precioP);
    	proveedores.add(nuevoProveedor);
    }
    
    /**
     * M�todo que busca un cliente. <br>
     * <b>pre: </b> La lista de clientes est� inicializada (no es null). <br>
     * @param nit Es el NIT del cliente que se est� buscando - nit != null y nit != ""
     * @return Retorna el cliente buscado. Si el NIT no pertenece a ning�n cliente retorna null
     */
    public Cliente buscarCliente(String nit) {
    	Cliente respuesta = null;
    	boolean continuar = true;
    	
    	for (int i = 0; i < clientes.size() && continuar; i++) {
    		Cliente tempCliente = (Cliente)clientes.get(i);
    		if (tempCliente.darNit().equals(nit)) {
    			respuesta = tempCliente;
    			continuar = false;
    		}
    	}
    	return respuesta;
    }

    /**
     * M�todo que agrega un nuevo cliente a la lista de clientes de la proveedora, si �ste no se encontraba en la lista con anterioridad.
     * <b>pre: </b> La lista de clientes debe estar inicializada.
     * <b>post:</b> Se ha agragado un nuevo cliente a la lista de clientes de la proveedora.
     * @param nombreP - Corresponde al nombre del cliente - nombreP != null y nombreP != ""
	 * @param nitP - Corresponde al NIT del cliente - nitP != null y nitP != ""
	 * @param telefonoP - Corresponde al tel�fono del cliente - telefonoP != null y telefonoP != ""
	 * @throws Exception - Se lanza una excepci�n si el cliente ya exist�a en la lista de clientes de la procesadora. 
     */
    public void agregarCliente(String nombreP, String nitP, String telefonoP) throws Exception {
    	for (int i = 0; i < clientes.size(); i++) {
    		Cliente tempCliente = (Cliente)clientes.get(i);
    		if (tempCliente.darNit().equals(nitP)) throw new Exception("El cliente ya existe");
    	}
    	
    	Cliente nuevoCliente = new Cliente(nombreP, nitP, telefonoP);
    	clientes.add(nuevoCliente);
    }

    /**
     * M�todo que devuelve el producto cuyo nombre coincide con el nombre pasado por par�metro. Si dicho producto no existe devuelve null.
     * @param nombreP - String que representa el nombre del producto que se est� buscando.
     * @return - Retorna el objeto Producto buscado o null si el producto no existe en la procesadora.
     */
    public Producto buscarProducto(String nombreP) {
    	Producto respuesta = null;
    	boolean continuar = true;
    	
    	for (int i = 0; i < productos.size() && continuar; i++) {
    		Producto tempProducto = (Producto)productos.get(i);
    		if (tempProducto.darNombre().equals(nombreP)) {
    			respuesta = tempProducto;
    			continuar = false;
    		}
    	}
    	
    	return respuesta;
    } 
 
    /**
     * Este m�todo agrega un nuevo producto en la Procesadora de Caf�. <br>
     * <b>pre: </b> La lista de productos est� inicializada (no es null). <br>
     * <b>post: </b> Se ha agregado un nuevo producto en la procesadora con los datos dados. <br>
     * @param nombreP - Es el nombre del nuevo producto - nombre != null y nombre != ""
	 * @param aromaP - Es el aroma del nuevo producto - aroma != null y aroma != ""
	 * @param acidezP - Es la acidez del nuevo producto - acidez != null y acidez != ""
	 * @param cuerpoP - Es el cuerpo del nuevo producto - cuerpo != null y cuerpo != ""
	 * @param precioP - Es el precio por kilo del nuevo producto - precio > 0
	 * @param conversionP - Es la cantidad de kilos insumo necesarios para obtener 1 kilo del nuevo producto - conversion > 0
	 * @throws Exception Se lanza una excepci�n si un producto con el mismo nombre ya estaba registrado a la procesadora.
     */
    public void agregarProducto(String nombreP, String aromaP, String acidezP, String cuerpoP, double precioP, double conversionP) throws Exception {
    	for (int i = 0; i < productos.size(); i++) {
    		Producto tempProducto = (Producto)productos.get(i);
    		if (tempProducto.darNombre().equals(nombreP)) throw new Exception("El producto ya existe.");
    	}
    	Producto nuevoProducto = new Producto(nombreP, aromaP, acidezP, cuerpoP, precioP, conversionP);
    	productos.add(nuevoProducto);
    } 
    
    /**
     * Este m�todo elimina producto de la Procesadora de Caf� dado su nombre. <br>
     * <b>pre: </b> La lista de productos est� inicializada (no es null) y el producto existe. <br>
     * <b>post: </b> Se ha eliminado el producto de la procesadora. <br>
     * @param nombre - Es el nombre del producto - nombre != null y nombre != ""
	 * @throws Exception Se lanza una excepci�n si un producto tiene una cantidad de kilos disponibles superior a cero
	 */
    public void eliminarProducto(String nombreP) throws Exception {
    	boolean continuar = true;
    	
    	for (int i = 0; i < productos.size() && continuar; i++){
    		Producto tempProducto = (Producto)productos.get(i);
    		
    		if (tempProducto.darNombre().equals(nombreP)) {
    			continuar = false;
    			if (tempProducto.darCantidadDisponible() == 0)
    				productos.remove(i);
    			else
    				throw new Exception("A�n hay existencias de este producto en bodega.");
    		}
    	}
    }

    /**
     * Este m�todo permite producir un producto de la Procesadora de Caf�, dado su nombre y cantidad a producir. <br>
     * <b>pre: </b> La lista de productos est� inicializada (no es null)y existe un producto con el nombre dado por par�metro. <br>
     * <b>post: </b> Se ha incrementado la cantidad disponible del producto y se ha disminuido 
     * la cantidad de insumo disponible de la procesadora dependiendo de la conversion del producto. <br>
     * @param nombre - Es el nombre del producto - nombre != null y nombre != "". Existe un producto en la procesadora con el nombre dado.
	 * @param cantidad - Es el cantidad (kilos) de producto a producir - cantidad > 0
	 * @throws Exception Se lanza una excepci�n si la procesadora no cuenta con el insumo suficiente para la producci�n del producto
	 */
    public void producir(String nombre, double cantidad) throws Exception {
    	boolean continuar = true;
    	Producto tempProducto = null;
    	
    	for (int i = 0; i < productos.size() && continuar; i++) {
    		tempProducto = (Producto)productos.get(i);
    		if (tempProducto.darNombre().equals(nombre)) {
    			continuar = false;
    			
    			double cantidadNecesaria = (tempProducto.darConversion())*cantidad;
    			if (cantidadNecesaria > insumoDisponible)
    				throw new Exception("No hay insumos suficientes para producir la cantidad solicitada.");
    			else {
    				insumoDisponible -= cantidadNecesaria;
    				tempProducto.producir(cantidad);
    			}
    		}
    	}
    }
    
    /**
     * Este m�todo permite vender cierto producto a un cliente de la Procesadora de Caf�. <br>
     * <b>pre: </b> La lista de productos y clientes est�n inicializadas (no son null) y el producto y el cliente existen en la procesadora. <br>
     * <b>post: </b> Se ha disminuido la cantidad disponible del producto, se ha incrementado el 
     * dinero de la procesadora y se ha registrado la venta al cliente. <br>
     * @param nombre - Es el nombre del producto - nombre != null y nombre != ""
	 * @param nitCliente - Es el NIT del cliente - nitCliente != null y nitCliente != ""
	 * @param cantidad - Es el cantidad (kilos) de producto a vender - cantidad > 0
	 * @throws Exception Se lanza una excepci�n si no se cuenta con una cantidad suficiente del producto para la venta
	 */
    public void vender(String nombre, String nitCliente, double cantidad) throws Exception {
    	boolean continuar = true;
    	Producto tempProducto = null;
    	Cliente tempCliente = null;
    	
    	// Ubicando el producto y verificando cantidad disponible
    	for (int i = 0; i < productos.size() && continuar; i++){
    		tempProducto = (Producto)productos.get(i);
    		if (tempProducto.darNombre().equals(nombre)) {
    			continuar = false;
    			if (tempProducto.darCantidadDisponible() < cantidad) throw new Exception("No hay existencias suficientes para completar el pedido.");
    		}
    	}
    	
    	continuar = true;
    	// Ubicando al cliente
    	for (int i = 0; i < clientes.size() && continuar; i++) {
    		tempCliente = (Cliente)clientes.get(i);
    		if (tempCliente.darNit().equals(nitCliente)) continuar = false;
    	}
    	
    	// Realizando la venta
    	tempProducto.vender(cantidad);
    	tempCliente.registarVentaCafe(cantidad);
    	dineroEnCaja += cantidad*tempProducto.darPrecio();
    }

    /**
     * Este m�todo permite comprar insumos a un proveedor de la Procesadora de Caf�. <br>
     * <b>pre: </b> La lista de proveedores est� inicializadas (no son null) y el proveedor existe. <br>
     * <b>post: </b> Se ha aunmentado la cantidad insumo disponible y se ha disminuido el 
     * dinero en caja de la procesadora. <br>
     * @param cedulaP - Es el cedula del proveedor - N�mero entero positivo
	 * @param cantidadP - Es el cantidad (kilos) de insumos a comprar - cantidad > 0
	 * @throws Exception Se lanza una excepci�n si no se cuenta con dinero suficiente para la compra
	 */
    public void comprarInsumo(int cedulaP, double cantidadP) throws Exception {
    	boolean continuar = true;
    	Proveedor tempProveedor = null;
    	
    	for (int i = 0; i < proveedores.size() && continuar; i++) {
    		tempProveedor = (Proveedor)proveedores.get(i);
    		if (tempProveedor.darCedula() == cedulaP) continuar = false;
    	}
    	
    	double dineroNecesario = (tempProveedor.darPrecio())*cantidadP;
    	if (dineroNecesario > dineroEnCaja) throw new Exception("No hay dinero suficiente para comprar el pedido.");
    		
    	dineroEnCaja -= (tempProveedor.darPrecio())*cantidadP;
    	insumoDisponible += cantidadP;
    }
    
    
    /**
     * M�todo que busca el cliente al que se le ha vendido el mayor n�mero de kilos <br>
     * <b>pre: </b> La lista de clientes est� inicializada (no es null). <br>
     * @return Retorna el nombre del cliente fiel. Si no hay clientes retorna null.
     */
    public String darClienteFiel()
    {
    	String respuesta = null;
    	double tempKilos = 0;
    	
    	for (int i = 0; i < clientes.size(); i++) {
    		Cliente tempCliente = (Cliente)clientes.get(i);
    		if (tempCliente.darCantidadVendida() > tempKilos) {
    			respuesta = tempCliente.darNombre();
    			tempKilos = tempCliente.darCantidadVendida();
    		}
    	}
    	return respuesta;
    }
   
    /**
     * M�todo que busca el proveedor que ofrece el kilogramo de caf� tostado a menor precio <br>
     * <b>pre: </b> La lista de proveedores est� inicializada (no es null). <br>
     * @return Retorna el nombre del proveedor que ofrece el kilo m�s econ�mico, si no hay proveedores retorna null
     */
    public String darProveedorMasBarato()
    {
    	String respuesta = null;
    	if (proveedores.size() != 0){
    		Proveedor tempProveedor = (Proveedor)proveedores.get(0);
        	respuesta = tempProveedor.darNombre();
        	double tempPrecio = tempProveedor.darPrecio();
        	
        	for (int i = 1; i < proveedores.size(); i++) {
        		tempProveedor = (Proveedor)proveedores.get(i);
        		if (tempProveedor.darPrecio() < tempPrecio) {
        			tempPrecio = tempProveedor.darPrecio();
        			respuesta = tempProveedor.darNombre();
        		}
        	}	
    	}
    	return respuesta;
    }
    
    /**
     * M�todo que busca el producto con mayor disponibilidad en la Procesadora <br>
     * <b>pre: </b> La lista de productos est� inicializada (no es null). <br>
     * @return Retorna el nombre del producto con disponibilidad, si no hay productos retorna null.
     */
    public String darProductoMayorDisponibilidad()
    {
     	 String respuesta = null;
     	 double tempDisponibilidad = 0;
     	 
     	 for (int i = (productos.size()-1); i >= 0; i--) {
     		 Producto tempProducto = (Producto)productos.get(i);
     		 if (tempProducto.darCantidadDisponible() >= tempDisponibilidad) {
     			 tempDisponibilidad = tempProducto.darCantidadDisponible();
     			 respuesta = tempProducto.darNombre();
     		 }
     	 }
     	 return respuesta;
    }
        
    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * M�todo para la extensi�n2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }


}