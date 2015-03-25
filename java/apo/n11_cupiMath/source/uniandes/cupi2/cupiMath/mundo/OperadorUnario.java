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
 * Representa un operador unario
 */
public abstract class OperadorUnario extends Operador
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo operador unario con el tipo recibido por par�metro
     * @param elTipo El tipo del operador unario. elTipo != null
     */
    public OperadorUnario( String elTipo )
    {
        super( elTipo );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Evaluar la operaci�n del operador
     * @return Devuelve el resultado de la operaci�n
     */
    public abstract double evaluar( );

    /**
     * Un operador unario s�lo tiene un operando, su hijo izquierdo. <br>
     * El nodo derecho debe ser null.
     */
    public void verificarInvariante( )
    {
        assert this.darNodoIzquierdo( ) != null : "El nodo izquierdo de un operador unario no puede ser null";
        assert this.darNodoDerecho( ) == null : "El nodo derecho de un operador unario debe ser null";
    }
}
