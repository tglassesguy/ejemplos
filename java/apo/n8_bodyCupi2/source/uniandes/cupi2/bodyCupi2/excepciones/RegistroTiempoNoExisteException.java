package uniandes.cupi2.bodyCupi2.excepciones;

import uniandes.cupi2.bodyCupi2.mundo.Fecha;

/**
 * Excepci�n en caso que el registro de tiempo no haya sido registado en la aplicaci�n
 */

public class RegistroTiempoNoExisteException extends Exception {
    
	// -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
	
	public RegistroTiempoNoExisteException(Fecha fechaRegistro) {
		super("El registro de tiempo para la fecha "+fechaRegistro.darFechaConFormato()+" no existe.");
	}
}