package co.edu.uniandes.bi.exception;
/**
 * 
 */

/**
 * Excepci�n relacionada con la creaci�n de un gr�fico
 * @author Sebastian
 *
 */
public class ChartBuilderException extends Exception {

	//--------------------------------------------------------------------------------------------------
	// Constantes
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Identificaci�n de la versi�n de la clase
	 */
	private static final long serialVersionUID = -1529904341369314915L;

	//--------------------------------------------------------------------------------------------------
	// Constructores
	//--------------------------------------------------------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public ChartBuilderException() {
		super();
	}

	/**
	 * Construye una excepci�n con el mensaje dado
	 * @param msg el mensaje de la excepci�n
	 */
	public ChartBuilderException(String msg) {
		super(msg);
		
	}

	/**
	 * Construye una excepci�n a partir del objeto dado
	 * @param exc 
	 */
	public ChartBuilderException(Throwable exc) {
		super(exc);
	}

	/**
	 * @param msg el mensaje de la excepci�n
	 * @param exc 
	 */
	public ChartBuilderException(String msg, Throwable exc) {
		super(msg, exc);
	}

}
