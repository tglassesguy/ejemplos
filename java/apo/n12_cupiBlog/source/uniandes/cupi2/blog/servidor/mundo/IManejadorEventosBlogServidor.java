/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodr�guez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.servidor.mundo;

/**
 * Interfaz que modela los eventos de actualizaci�n dados los eventos del mundo.
 */
public interface IManejadorEventosBlogServidor
{
    /**
     * Cuando un nuevo usuario se conecta al blog.
     */
    public void cambiosUsuariosConectados( );

    /**
     * Cuando un usuario publica un nuevo art�culo en el blog.
     */
    public void nuevoArticuloPublicado( );
}
