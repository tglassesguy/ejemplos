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
 * Representa la operaci�n de ra�z cuadrada
 */
public class Sqrt extends OperadorUnario
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Constante que representa el operador unario de la ra�z cuadrada
     */
    public final static String SIMBOLO = "Sqrt";
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------
    
    /**
     * Construye el nodo de la operaci�n ra�z cuadrada
     * @param elTipo El tipo del operador. elTipo != null
     */
    public Sqrt( String elTipo )
    {
        super( elTipo );
    }
    
    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre el sub�rbol izquierdo
     */
    public double evaluar( )
    {
        return Math.sqrt(darNodoIzquierdo().evaluar());
    }
}
