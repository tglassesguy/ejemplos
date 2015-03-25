package uniandes.cupi2.bodyCupi2.excepciones;

/**
 * Excepci�n en el caso que un usuario ya haya sido registrado en la aplicaci�n
 */
public class UsuarioExisteException extends Exception
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye una excepci�n con un mensaje explicando que el usuario ya existe
     * @param nombreUsuario El nombre del usuario que ya existe
     */
    public UsuarioExisteException( String nombreUsuario )
    {
        super( "El usuario " + nombreUsuario + " ya existe" );
    }
    
}
