/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodr�guez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.mundo.excepciones;

/**
 * Esta excepci�n se lanza si hay un terreno con un producto en la finca
 */
public class TerrenoNoVacioException extends Exception
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n
     * @param x La coordenada x del terreno. x >= 0
     * @param y La coordenada y del terreno. y >= 0
     */
    public TerrenoNoVacioException( int x, int y )
    {
        super( "Hay un terreno en la posici�n: " + x + ", " + y );
    }
}
