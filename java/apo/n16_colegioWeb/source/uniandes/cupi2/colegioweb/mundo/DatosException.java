/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 25, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.mundo;

/**
 * Excepci�n generada en el manejo de los datos
 */
@SuppressWarnings("serial")
public class DatosException extends Exception
{

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor de la excepci�n
     * @param mensaje Mensaje de la excepci�n
     */
    public DatosException( String mensaje )
    {
        super( mensaje );
    }

    /**
     * Constructor de la excepci�n
     * @param mensaje Mensaje de la excepci�n
     * @param interna Excepci�n Interna
     */
    public DatosException( String mensaje, Exception interna )
    {
        super( mensaje, interna );
    }

}
