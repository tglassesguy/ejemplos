package uniandes.cupi2.bodyCupi2.excepciones;

import uniandes.cupi2.bodyCupi2.mundo.Fecha;

/**
 * Excepci�n en el caso que un usuario ya haya ingresado al gimnasio en una fecha dada
 */
public class RegistroTiempoExisteException extends Exception
{
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye una excepci�n con un mensaje explicando que el registro de tiempo ya existe
     * @param fechaRegistro La fecha del registro que ya existe
     */
    public RegistroTiempoExisteException( Fecha fechaRegistro)
    {
        super( "El usuario ya ingres� en la fecha" + fechaRegistro.darFechaConFormato( ));
    }

}
