/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiCourier
 * Autor: Luis Ricardo Ruiz Rodr�guez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.cliente.mundo;

import java.util.ArrayList;

/**
 * Interfaz que representa los elementos que escuchar los eventos que llegan del servidor
 */
public interface IEscucharEventos
{

    /**
     * M�todo que notifica una actualizaci�n de la lista de los art�culos
     * @param articulos La lista de art�culos obtenida. articulos != null
     */
    public void actualizarListaArticulos( ArrayList articulos );
    
    /**
     * M�todo que notifica una actualizaci�n de la lista de los comentarios.
     */
    public void actualizarComentarios( );
    
    /**
     * Notifica el inicio de sesi�n del usuario
     * @param estadoSesion El estado de sesi�n del usuario. estadoSesion != null
     */
    public void cambiarEstadoSesion( boolean estadoSesion );

    /**
     * M�todo que notifica una actualizaci�n de un mensaje
     * @param mensaje El mensaje del cual se debe notificar. mensaje != null
     */
    public void notificarMensaje( String mensaje );

    /**
     * M�todo que notifica una excepci�n
     * @param e La excepci�n que llega en el mensaje
     */
    public void notificarExcepcion( Exception e );
}
