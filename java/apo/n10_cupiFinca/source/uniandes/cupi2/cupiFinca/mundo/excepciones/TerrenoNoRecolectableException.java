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
 * Esta excepci�n se lanza si hay un terreno con un producto que no ha terminado su etapa de producci�n
 */
public class TerrenoNoRecolectableException extends Exception
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la excepci�n
     * @param x La coordenada x del terreno. x >= 0
     * @param y La coordenada y del terreno. y >= 0
     */
    public TerrenoNoRecolectableException( int x, int y )
    {
        super( "El terreno en la posici�n: " + x + ", " + y + " no tiene productos que se puedan recoger actualmente.");
    }
}
