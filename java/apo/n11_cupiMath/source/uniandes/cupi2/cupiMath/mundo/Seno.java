/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.cupiMath.mundo;

/**
 * Representa el operador seno
 */
public class Seno extends OperadorUnario
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante que representa la operaci�n seno
     */
    public final static String SIMBOLO = "Sen";

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye el nodo de operaci�n del seno
     * @param elTipo El tipo del operador. elTipo != null
     */
    public Seno( String elTipo )
    {
        super( elTipo );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre el sub�rbol izquierdo
     * @return el resultado de la operaci�n seno.
     */
    public double evaluar( )
    {
        return Math.sin( darNodoIzquierdo( ).evaluar( ) );
    }
}
