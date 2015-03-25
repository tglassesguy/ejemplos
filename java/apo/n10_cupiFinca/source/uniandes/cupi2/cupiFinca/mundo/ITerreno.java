/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodr�guez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.mundo;

import java.awt.Graphics2D;
import java.io.PrintWriter;

/**
 * La interfaz que representa cada uno de los terrenos del cultivo
 */
public interface ITerreno
{      
    /**
     * Devuelve el nombre del producto
     * @return El nombre del producto
     */
    public String darNombre();

    /**
     * Devuelve el costo de instalar este producto
     * @return El costo de instalaci�n del producto
     */
    public int darCosto();

    /**
     * Devuelve la ganancia por recoger este producto
     * @return La ganancia de recolecci�n del producto
     */
    public int darGanancia();
    
    /**
     * Devuelve el tiempo que lleva el producto
     * @return El tiempo que lleva el producto instalado
     */
    public int darTiempo();
    
    /**
     * Devuelve el estado en una etapa del proceso de producci�n
     * @return El estado en el tiempo de producci�n
     */
    public int darEstado();
    
    /**
     * Devuelve si el producto del terreno se puede recoger
     * @return true si el producto se puede recoger, false en caso contrario
     */
    public boolean esRecolectable();
    
    /**
     * Avanza en el ciclo de vida del terreno
     */
    public void avanzarCiclo();
    
    /**
     * Dibuja el producto en el pedazo del campo
     * @param graphics El campo de dibujo. graphics != null
     */
    public void dibujar( Graphics2D graphics );
    
    /**
     * Guarda la informaci�n del terreno en el archivo que est� de par�metro
     * @param pw El archivo donde se escribe la informaci�n del terreno. pw != null
     */
    public void guardarTerreno( PrintWriter pw );
}
