package uniandes.cupi2.procesadoraCafe.mundo;

/**
 * Clase que modela un proveedor de caf� tostado
 */
public class Proveedor 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre del proveedor
	 */
	private String nombre;

	/**
	 * N�mero de c�dula del proveedor
	 */
	private int cedula;
	
	/**
	 * Tel�fono de contacto del proveedor
	 */
	private String telefono;
	
	/**
	 * Regi�n de origen del caf�
	 */
	private String origen;
	
	/**
	 * Precio de compra en pesos colombianos por kilo de caf� tostado
	 */
	private double precio;
		
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	
	/**
	 * Crea un proveedor de la procesadora de cafe <br />
	 * <b>post:</b> Se cre� un proveedor con las caracter�sticas recibidas por par�metro.
	 * @param nombreP - Corresponde al nombre del proveedor - nombreP != null y nombreP != ""
	 * @param cedulaP - Corresponde a la c�dula del proveedor - cedulaP > 0
	 * @param telefonoP - Corresponde al tel�fono del proveedor - telefonoP != null y telefonoP != ""
	 * @param origenP - Corresponde a la regi�n de origen del caf� vendido por el proveedor - origenP != null y origenP != ""
	 * @param precioP - Corresponde al precio por kilogramo de caf� tostado cobrado por el proveedor - precioP > 0
	 */
	public Proveedor(String nombreP, int cedulaP, String telefonoP, String origenP, double precioP)
	{
		nombre = nombreP;
		cedula = cedulaP;
		telefono = telefonoP;
		origen = origenP;
		precio = precioP;
	}
	
	//-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
	
    /**
     * M�todo que sirve para conocer el nombre del proveedor. <br>
     * @return nombre - Nombre del proveedor - nombre != null y nombre != ""
     */
	public String darNombre()
	{
		return nombre;
	}
	
	 /**
     * M�todo que sirve para conocer la c�dula del proveedor. <br>
     * @return cedula - C�dula del proveedor - N�mero entero positivo
     */
	public int darCedula()
	{
		return cedula;
	}
	
	 /**
     * M�todo que sirve para conocer el tel�fono del proveedor. <br>
     * @return telefono - Tel�fono del proveedor - telefono != null y telefono != ""
     */
	public String darTelefono()
	{
		return telefono;
	}
	
	/**
	 * M�todo que sirve para conocer el origen del caf� vendido por el proveedor
	 * @return origen - String que contiene el origen del caf� - origen != null y origen != ""
	 */
	public String darOrigen() {
		return origen;
	}
    
	/**
	 * M�todo que sirver para conocer el precio por kilogramo de caf� tostado cobrado por este proveedor
	 * @return precio - double que representa el precio por kilogramo - precio > 0	
	 */
	public double darPrecio() {
		return precio;
	}
	
	/**
     * Retorna una cadena con el nombre del proveedor
     * @return La representaci�n del proveedor en String
     */
	public String toString()
	{
		return nombre;
	}
}
