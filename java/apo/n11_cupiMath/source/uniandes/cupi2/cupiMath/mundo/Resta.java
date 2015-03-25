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
 * Representa el operador resta
 */
public class Resta extends OperadorBinario
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante con el s�mbolo de la resta
     */
    public final static String SIMBOLO = "-";

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * COnstruye el nodo de la operaci�n de la resta
     * @param elTipo El tipo del operador. elTipo != null
     */
    public Resta( String elTipo )
    {
        super( elTipo );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre el sub�rbol izquierdo y el derecho
     * @return El resultado de la operaci�n de la resta de los sub�rboles
     */
    public double evaluar( )
    {
        double evaluacion = 0;
        evaluacion = darNodoIzquierdo( ).evaluar( ) - darNodoDerecho( ).evaluar( );
        return evaluacion;
    }
}
