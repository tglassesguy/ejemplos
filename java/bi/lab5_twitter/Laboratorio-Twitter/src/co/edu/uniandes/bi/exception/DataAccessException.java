package co.edu.uniandes.bi.exception;
/**
 * 
 */

/**
 * Excepci�n relacionada con el acceso a un servicio de datos
 * @author Sebastian
 *
 */
public class DataAccessException extends Exception {

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
	public DataAccessException() {
		super();
	}

	/**
	 * Construye una excepci�n con el mensaje dado
	 * @param msg el mensaje de la excepci�n
	 */
	public DataAccessException(String msg) {
		super(msg);
		
	}

	/**
	 * Construye una excepci�n a partir del objeto dado
	 * @param exc 
	 */
	public DataAccessException(Throwable exc) {
		super(exc);
	}

	/**
	 * @param msg el mensaje de la excepci�n
	 * @param exc 
	 */
	public DataAccessException(String msg, Throwable exc) {
		super(msg, exc);
	}

}
