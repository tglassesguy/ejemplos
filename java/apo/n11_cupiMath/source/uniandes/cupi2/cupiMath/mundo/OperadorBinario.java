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
 * Representa un operador binario
 */
public abstract class OperadorBinario extends Operador
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo operador unario con el tipo recibido por par�metro
     * @param elTipo El tipo del operador unario. elTipo != null
     */
    public OperadorBinario( String elTipo )
    {
        super( elTipo );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor de aplicar el operador sobre sus operandos
     */
    public abstract double evaluar( );

    /**
     * Un operador binario debe tener los dos nodos inicializados
     */
    public void verificarInvariante( )
    {
        assert this.darNodoIzquierdo( ) != null && this.darNodoDerecho( ) != null : "Un operador binario debe tener ambos hijos";
    }
}
