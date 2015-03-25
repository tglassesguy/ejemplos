/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_crucero
 * Autor: Catalina Rodr�guez - 16-sep-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.crucero.mundo;

import javax.swing.Icon;

/**
 * Representa una imagen de un destino
 */
public class Foto 
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Titulo de la imagen
	 */
	private String titulo;
	
	/**
	 * Imagen
	 */
	private Icon imagen;

	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

	/**
	 * M�todo constructor. Inicializa los atributos con los par�metros dados
	 * @param tituloP Titulo de la foto. tituloP!=null
	 * @param imagenP Imagen del destino. imagenP!=null
	 */
	public Foto(String tituloP, Icon imagenP)
	{
		titulo = tituloP;
		imagen = imagenP;
	}

	//-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

	/**
	 * Retorna el titulo de la foto
	 * @return titulo Titulo de la foto. titulo!=null
	 */
	public String darTitulo()
	{
		return titulo;
	}
	
	/**
	 * Retorna la imagen del destino
	 * @return imagen Imagen del destino. imagen!=null
	 */
	public Icon darImagen()
	{
		return imagen;
	}
}
