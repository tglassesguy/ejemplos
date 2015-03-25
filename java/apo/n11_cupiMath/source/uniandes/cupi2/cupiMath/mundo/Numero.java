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

import java.util.ArrayList;

/**
 * Representa un n�mero en la expresi�n
 */
public class Numero extends Nodo
{
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea un n�mero con el valor de su primer d�gito.
     * @param elValor El primer d�gito del n�mero. elValor != null
     */
    public Numero( int elValor )
    {
        this.asignarValor( "" + elValor );
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el valor del n�mero
     * @return El valor num�rico de la expresi�n.
     */
    public double evaluar( )
    {
        return Double.parseDouble( darValor( ) );
    }

    /**
     * Un n�mero no debe tener nodos
     */
    public void verificarInvariante( )
    {
        assert this.darNodoIzquierdo( ) == null && this.darNodoDerecho( ) == null : "Un operando no debe tener nodos hijos";

    }
    
    public void buscarNumero(int contador, ArrayList dondeEstan, double numeroBuscado) {
    	contador++;
    	if (evaluar() == numeroBuscado) dondeEstan.add(contador);
    }
    
    public String elementosRama(double numeroBuscado) {
    	if (evaluar() == numeroBuscado) return darValor();
    	else return "";
    }
    
    public boolean arbolCompleto() {
    	return true;
    }
}
