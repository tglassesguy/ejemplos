package uniandes.cupi2.cupIphone.componentes;

import javax.swing.JPanel;

import uniandes.cupi2.cupIphone.core.ICore;

/**
 * Interfaz que define el punto de entrada de una aplicaci�n que se
 * instala en el CupIphone
 * @author Yeisson Oviedo
 */
public interface IAplicacion{

	/**
	 * Retorna el panel principal de la aplicaci�n
	 * @return Panel principal 
	 */
	public JPanel darPanelPrincipal();

	/**
	 * M�todo que es invocado al momento de inicializar la aplicaci�n
	 * @param c Referencia al n�cleo de la aplicaci�n
	 */
	public void cambiarCore(ICore c);
	
	/**
	 * Le indica a la aplicaci�n que su ejecuci�n ha comenzado
	 */
	public void iniciarEjecucion();
	
	/**
	 * M�todo invocado justo antes de cerrar el componente
	 */
	public void terminarEjecucion();
	
	/**
	 * Retorna la instancia del modelo de esta aplicaci�n
	 * @return La instancia del modelo. La clase a la que se debe 
	 * hacer cast depende de cada componente
	 */
	public Object darInstanciaModelo();
		
}
