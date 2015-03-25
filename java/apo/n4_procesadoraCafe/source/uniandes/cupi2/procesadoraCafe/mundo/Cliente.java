package uniandes.cupi2.procesadoraCafe.mundo;

/**
 * Clase que modela un cliente de la Procesadora
 */
public class Cliente 
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre del cliente.
	 */
	private String nombre;

	/**
	 * Nit del cliente.
	 */
	private String nit;
	
	/**
	 * Tel�fono del cliente.
	 */
	private String telefono;
	
	/**
	 * Registra el n�mero de kilos vendidos al cliente.
	 */
	private double kilosVendidos;
	
	//-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
	
	/**
	 * Crea un cliente de la Procesadora de Caf�. <br>
	 * <b>post: </b> Se cre� un cliente con las caracter�sticas recibidas por par�metro 
	 * y la cantidad de kilos vendidos en cero <br>
	 * @param nombreP - Corresponde al nombre del cliente - nombreP != null y nombreP != ""
	 * @param nitP - Corresponde al NIT del cliente - nitP != null y nitP != ""
	 * @param telefonoP - Corresponde al tel�fono del cliente - telefonoP != null y telefonoP != ""
	 */

	 public Cliente(String nombreP, String nitP, String telefonoP) {
		 nombre = nombreP;
		 nit = nitP;
		 telefono = telefonoP;
		 
		 kilosVendidos = 0;
	 }
	
	
	
	//-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
	
    /**
     * M�todo que sirve para conocer el nombre del cliente. <br>
     * @return nombre - Nombre del cliente - nombre != null y nombre != ""
     */
	public String darNombre()
	{
		return nombre;
	}
	
	/**
     * M�todo que sirve para conocer el NIT del cliente. <br>
     * @return nit - NIT del cliente - nit != null y nit != ""
     */
	public String darNit()
	{
		return nit;
	}
	
	/**
     * M�todo que sirve para conocer el tel�fono del cliente. <br>
     * @return telefono - Tel�fono del cliente - telefono != null y telefono != ""
     */
	public String darTelefono()
	{
		return telefono;
	}
	
	/**
     * M�todo que sirve para conocer la cantidad de kilos vendidos al cliente. <br>
     * @return kilosVendidos - Kilos vendidos al cliente - kilosVendidos >= 0
     */
	public double darCantidadVendida()
	{
		return kilosVendidos;
	}
	
	
	/**
	 * M�todo que registra la venta de determinados kilos de cafe al cliente.
	 * <b>post:</b> Se aument� el n�mero de kilos vendidos al cliente en la cantidad recibida por par�metro.
	 * @param kilos - kilos > 0
	 */
	public void registarVentaCafe(double kilos)
	{
		kilosVendidos += kilos;
	}
	
	/**
     * Retorna una cadena con el nombre del cliente
     * @return La representaci�n del cliente en String
     */
	public String toString()
	{
		return nombre;
	}
}
