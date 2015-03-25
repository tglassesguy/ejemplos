/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Yeisson Oviedo - 10 de Mayo, 2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.mundo.algoritmos;

/**
 * Construye un {@link AlgoritmoComplementoA2}
 *
 */
public class FabricaAlgoritmoComplemento implements IFabricaAlgoritmo{
	
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/* (non-Javadoc)
	 * @see uniandes.cupi2.centralMensajes.mundo.algoritmos.IFabricaAlgoritmo#crearAlgoritmo()
	 */
	public IAlgoritmoEncriptacion crearAlgoritmo() {
		return new AlgoritmoComplementoA2();
	}

}
