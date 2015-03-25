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

package uniandes.cupi2.blog.comun;

/**
 * La clase que tiene las constantes de comunicaci�n
 */
public class Comunicacion
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * La constante del separador de los componentes de un comando
     */
    public static final String SEPARADOR_COMANDO = ";;;";
    
    /**
     * La constante del separador de los par�metros
     */
    public static final String SEPARADOR_PARAMETROS = ":::";

    /**
     * La constante para el ingreso al sistemas de blog
     */
    public static final String LOGIN = "LOGIN";

    /**
     * La constante para registrar un usuario al sistemas de blog
     */
    public static final String REGISTRAR = "REGISTRAR";
    
    /**
     * La constante para notificar el inicio del env�o de la informaci�n de los art�culos
     */
    public static final String LISTA_ARTICULOS = "LISTA_ARTICULOS";

    /**
     * La constante para notificar el inicio del env�o de la informaci�n de los art�culos
     */
    public static final String ARTICULOS = "ARTICULOS";

    /**
     * La constante para notificar el env�o de la informaci�n de cada art�culo
     */
    public static final String ARTICULO = "ARTICULO";
    
    /**
     * La constante para solicitar por parte del cliente los comentarios de un art�culo
     */
    public static final String COMENTARIOS_ARTICULO = "COMENTARIOS_ARTICULO";
    
    /**
     * La constante para notificar el inicio del env�o de la informaci�n de los comentarios de un art�culo
     */
    public static final String COMENTARIOS = "COMENTARIOS";
    
    /**
     * La constante para notificar el env�o de la informaci�n de cada uno de los comentarios de un art�culo
     */
    public static final String COMENTARIO = "COMENTARIO";

    /**
     * La constante para publicar un art�culo
     */
    public static final String PUBLICAR_ARTICULO = "PUBLICAR_ARTICULO";

    /**
     * La constante para publicar un nuevo comentario
     */
    public static final String PUBLICAR_COMENTARIO = "PUBLICAR_COMENTARIO";

    /**
     * La constante para pedir las estad�sticas de publicaci�n de un usuario
     */
    public static final String ESTADISTICAS = "ESTADISTICAS";
    
    /**
     * La constante para notificar la b�squeda de art�culos por categor�a
     */
    public static final String BUSQUEDA_CATEGORIA = "BUSQUEDA_CATEGORIA";

    /**
     * La constante para notificar el cierre de sesi�n por parte de un usuario de la sala de chat
     */
    public static final String LOGOUT = "LOGOUT";
    
    /**
     * La constante en caso de error en alg�n proceso
     */
    public static final String ERROR = "ERROR";
}
