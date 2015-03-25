package uniandes.cupi2.procesadoraCafe.mundo;

/**
 * Clase que modela el producto ofrecido por la Procesadora
 */
public class Producto 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre del producto
	 */
	private String nombre;

	/**
	 * Propiedad de aroma del producto
	 */
	private String aroma;
	
	/**
	 * Propiedad de nivel de acidez del producto
	 */
	private String acidez;
	
	/**
	 * Propiedad de cuerpo del producto
	 */
	private String cuerpo;
	
	/**
	 * Cantidad de kilos disponibles del producto
	 */
	private double disponible;
	
	/**
	 * Precio de venta en pesos colombianos por kilo del producto
	 */
	private double precio;
	
	/**
	 * Cantidad de kilos de caf� tostado necesarios para obtener 1 kilo del producto
	 */
	private double conversion;
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	/**
	 * Crea un producto de la Procesadora de Caf�. <br>
	 * <b>post: </b> Se cre� un producto con las caracter�sticas recibidas por par�metro 
	 * y la cantidad de kilos disponibles en cero <br>
	 * @param nombreP - Corresponde al nombre del producto - nombreP != null y nombreP != ""
	 * @param aromaP - Corresponde al aroma del producto - aromaP != null y aromaP != ""
	 * @param acidezP - Corresponde a la acidez del producto - acidezP != null y acidezP != ""
	 * @param cuerpoP - Corresponde al cuerpo del producto - cuerpoP != null y cuerpoP != ""
	 * @param precioP - Corresponde al precio por kilo del producto - precioP > 0
	 * @param conversionP - Corresponde a la cantidad de kilos insumo necesarios para obtener 1 kilo del producto - conversionP > 0
	 */
	public Producto(String nombreP, String aromaP, String acidezP, String cuerpoP, double precioP, double conversionP) {
		nombre = nombreP;
		aroma = aromaP;
		acidez = acidezP;
		cuerpo = cuerpoP;
		precio = precioP;
		conversion = conversionP;
		
		disponible = 0;
	}
	
	//-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
	
    /**
     * M�todo que sirve para conocer el nombre del producto. <br>
     * @return nombre - Nombre del producto - nombre != null y nombre != ""
     */
	public String darNombre()
	{
		return nombre;
	}
	
	 /**
     * M�todo que sirve para conocer el aroma del producto. <br>
     * @return aroma - Aroma del producto - aroma != null y aroma != ""
     */
	public String darAroma()
	{
		return aroma;
	}
	
	/**
     * M�todo que sirve para conocer la acidez del producto. <br>
     * @return acidez - Acidez del producto - acidez != null y acidez != ""
     */
	public String darAcidez()
	{
		return acidez;
	}
	
	 /**
     * M�todo que sirve para conocer el cuerpo del producto. <br>
     * @return cuerpo - Cuerpo del producto - cuerpo != null y cuerpo != ""
     */
	public String darCuerpo()
	{
		return cuerpo;
	}
	
	/**
     * M�todo que sirve para conocer la cantidad disponible del producto. <br>
     * @return disponible - Cantidad disponible del producto - disponible >= 0
     */
	public double darCantidadDisponible()
	{
		return disponible;
	}
	
	/**
     * M�todo que sirve para conocer el precio de venta por kilo del producto. <br>
     * @return precio - Precio del producto - precio > 0
     */
	public double darPrecio()
	{
		return precio;
	}
	
	/**
     * M�todo que sirve para conocer la cantidad de kilos de caf� tostado
     *  necesarios para obtener 1 kilo del producto por kilo del producto. <br>
     * @return conversion - Conversi�n del producto - conversion > 0
     */
	public double darConversion()
	{
		return conversion;
	}
		
    /**
     * M�todo que sirve para incrementar los kilos disponibles del producto. <br>
     * <b>post: </b> Se increment� la cantidad disponible del producto en los kilos recibidos por par�metro <br>
     * @param kilos - Cantidad de kilos a incrementar - kilos > 0
     */
	public void producir(double kilos) {
		disponible += kilos;
	}

	
	 /**
     * M�todo que sirve para vender una cantidad de kilos disponibles del producto. <br>
     * <b>post: </b> Se disminuy� la cantidad disponible del producto en el n�mero de kilos vendidos<br>
     * @param kilos - Cantidad de kilos a vender - kilos > 0
     * @throws Exception - Se lanza una excepci�n si la cantidad de kilos a vender es superior que la cantidad disponible.
     */
	public void vender(double kilos) throws Exception {
		if (kilos > disponible)
			throw new Exception("Cantidad disponible insuficiente para completar el pedido.");
		else
			disponible -= kilos;
	}
	
	
	/**
     * Retorna una cadena con el nombre del producto
     * @return La representaci�n del producto en String
     */
	public String toString()
	{
		return nombre;
	}
}
