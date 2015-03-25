package uniandes.cupi2.cupIphone.core;

import java.io.File;
import java.io.Serializable;

import uniandes.cupi2.cupIphone.componentes.excepciones.EjecucionException;

/**
 * Interfaz que define la interacci�n que pueden tener los componentes
 * con el n�cleo del sistema
 * @author Yeisson Oviedo
 *
 */
public interface ICore extends Serializable {

	/**
	 * Retorna la instancia de modelo de la aplicaci�n
	 * correspondiente al id suministrado
	 * @param idAplicacion Id de la aplicaci�n cuya instancia de modelo
	 * se quiere obtener
	 * @return Instancia del modelo. La clase a la que se debe hacer cast
	 * depende de cada componente
	 * @throws EjecucionException Si no se encuentra la aplicaci�n con el ID indicado
	 */
	public Object darInstanciaModelo(String idAplicacion) throws EjecucionException;

	/**
	 * Le indica a la aplicaci�n el directorio de datos que le corresponde
	 * @return Instancia de File apuntando al directorio de datos que le corresponde
	 */
	public File darDirectorioDatos();
}
