package uniandes.cupi2.bodyCupi2.excepciones;
/**
 * Excepci�n en caso que el usuario no haya sido registrado en la aplicaci�n
 */
public class UsuarioNoExisteException extends Exception {

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye una excepci�n con un mensaje explicando que el usuario no existe
     * @param id El id del usuario opr el cual se est� preguntando
     */
    public UsuarioNoExisteException( int id ) {
        super( "El usuario con id " + id + " no existe" );
    }
    
}
