package uniandes.cupi2.cupIphone.componentes.excepciones;

/**
 * Excepci�n producida cuando se trata de instalar una aplicaci�n
 * @author y-oviedo
 */
public class InstalacionException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param mensaje Mensaje 
	 */
	public InstalacionException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor
	 * @param mensaje
	 * @param causa
	 */
	public InstalacionException(String mensaje, Exception causa) {
		super(mensaje, causa);
	}
}
