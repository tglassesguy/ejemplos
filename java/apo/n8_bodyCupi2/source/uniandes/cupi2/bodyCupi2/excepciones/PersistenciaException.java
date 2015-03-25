package uniandes.cupi2.bodyCupi2.excepciones;

/**
 * Excepci�n en caso que no sea posible realizar el procedimiento de persistencia de la aplicaci�n
 */

public class PersistenciaException extends Exception {
	
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
	
	private String nombreArchivo;

	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	
	public PersistenciaException(String nArchivo, String mensajeError){
		super("Error de persistencia en "+nArchivo+"\n"+mensajeError);
		nombreArchivo = nArchivo;
	}
	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	
	public String darNombreArchivo() {
		return nombreArchivo;
	}
}
