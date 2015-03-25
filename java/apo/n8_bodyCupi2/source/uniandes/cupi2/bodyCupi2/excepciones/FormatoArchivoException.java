package uniandes.cupi2.bodyCupi2.excepciones;

/**
 * Excepci�n al tratar de cargar datos de un archivo que no cumple con el formato definido
 *
 */
public class FormatoArchivoException extends Exception
{
    // -----------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 1L;

    // ----------------------------------------------
    // M�todos
    // ----------------------------------------------

    /**
     * Constructor de la excepci�n.
     * @param mensaje Mensajes que se muestra cuando se lanza la excepci�n.
     */
    public FormatoArchivoException( String mensaje )
    {
        super( mensaje );
    }
}
