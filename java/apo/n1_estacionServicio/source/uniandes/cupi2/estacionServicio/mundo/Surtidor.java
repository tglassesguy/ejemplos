package uniandes.cupi2.estacionServicio.mundo;

/**
 * Clase que representa un Surtido de combustible
 */
public class Surtidor 
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Tipo de combustibles
	 */
	private String tipo;
	
	/**
	 * Costos del gal�n de combustible
	 */
	private double precioGalon;
	
    /**
     * N�mero de galones vendidos
     */
    private double numeroGalones;

    /**
     * Dinero total recaudado por la ventas de combustible
     */
    private double dineroRecaudado;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Inicializa el surtidor de combustible <br>
     * <b>post: </b> El surtidor se inicializ� con numero de galones vendidos y dinero recaudado en cero
     * @param nTipo Tipo de combustible (Corriente, Extra o Diesel)
     * @param nPrecioGalon Precio de un gal�n de combustible
     */
    public void inicializar(String nTipo, double nPrecioGalon )
    {
        //
        //Inicializa los valores
        tipo = nTipo;
        precioGalon = nPrecioGalon;
        
        numeroGalones = 0;
        dineroRecaudado = 0;
    }

    /**
     * Reinicia el surtidor, dejando el n�mero de galones vendidos y dinero recaudado en cero.
     */
    public void reiniciar( )
    {
    	numeroGalones = 0;
    	dineroRecaudado = 0;
    }

    /**
     * Devuelve el dinero total recaudado por las ventas realizadas
     * @return Dinero total recaudado
     */
    public double darDineroRecaudado( )
    {
    	return dineroRecaudado;
    }

    /**
     * Devuelve el n�mero de galones vendidos
     * @return N�mero de galones vendidos por el surtidor
     */
    public double darNumeroGalonesVendidos( )
    {
        return numeroGalones;
    }

    /**
     * Devuelve el tipo de combustible ofrecido por el distribuidor
     * @return Tipo de combustible
     */
    public String darTipoCombustible( )
    {
        return tipo;
    }

    /**
     * Devuelve el precio del gal�n de combustible
     * @return Costo del gal�n
     */
    public double darCostoGalon()
    {
    	return precioGalon;
    }
    
    /**
     * Registra una nueva venta de combustible para un veh�culo particular <br>
     * <b>post: </b> Se incremento numeroGalones de acuerdo al dinero de carga, 
     * 	dineroRecaudado aumento en dinero
     * @param dinero Cantidad de dinero. dinero >0.
     * @return Numero de galones vendidos
     */
    public double registarVentaParticular( double dinero )
    {
    	// Inicializando
    	double galonesVenta = 0;
    	// Calculando el n�mero de galones vendidos.
    	galonesVenta = dinero/precioGalon;
    	// Actualizando atributos.
    	numeroGalones = numeroGalones + galonesVenta; 
    	dineroRecaudado = dineroRecaudado + dinero;
    	// Devolviendo n�mero de galones vendidos.
    	return galonesVenta;
    }
    
    /**
     * Registrar una nueva venta de combustible para un veh�culo de servicio p�blico teniendo en cuenta la politica de descuentos<br>
     * <b>post: </b> Se incremento numeroGalones de acuerdo al dinero de carga,
     * 	dineroRecaudado aumento en dinero
     * @param dinero Cantidad de dinero. dinero>0.
     * @return Numero de galones vendidos
     */
    public double registrarVentaServicioPublico( double dinero )
    {
    	// Inicializando
    	double galonesVenta = 0;    	
    	// Calculando el n�mero de galones vendidos.
    	galonesVenta = dinero/(precioGalon*0.95);
    	// Actualizando atributos.
    	numeroGalones = numeroGalones + galonesVenta;
    	dineroRecaudado = dineroRecaudado + dinero;
    	// Devolviendo n�mero de galones vendidos.
    	return galonesVenta;
    }
    
}
