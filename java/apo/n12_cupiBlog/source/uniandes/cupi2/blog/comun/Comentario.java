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

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * La clase que representa un art�culo del blog.<br>
 * <b>inv:</b><br>
 * El usuario del comentario debe existir.<br>
 * El art�culo del comentario debe existir.<br>
 * El contenido del comentario debe existir.<br>
 * El comentario debe tener una fecha de publicaci�n.<br>
 */
public class Comentario
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El texto del contenido del art�culo
     */
    private String contenido;

    /**
     * El usuario que realiza el comentario
     */
    private String usuario;
    
    /**
     * El art�culo que se est� comentando
     */
    private Articulo articulo;
    
    /**
     * La fecha de publicaci�n del comentario
     */
    private Date fechaPublicacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor del comentario que se crea con el contenido del comentario, el usuario que lo public� y el art�culo al que pertenece.
     * @param nArticulo El art�culo que se ha comentado. nArticulo != null
     * @param nUsuario El usuario que ha publicado el comentario. nUsuario != null
     * @param nContenido El texto con el contenido del comentario. nContenido != null
     * @param nFechaPublicacion La fecha de publicaci�n del comentario. nFechaPublicacion != null
     */
    public Comentario( String nUsuario, Articulo nArticulo, String nContenido, Date nFechaPublicacion )
    {
        usuario = nUsuario;
        articulo = nArticulo;
        contenido = nContenido;
        fechaPublicacion = nFechaPublicacion;
        
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el usuario que realiz� el comentario
     * @return El usuario que realiz� el comentario
     */
    public String darUsuario( )
    {
        return usuario;
    }

    /**
     * Devuelve el art�culo comentado
     * @return El art�culo comentado
     */
    public Articulo darArticulo( )
    {
        return articulo;
    }

    /**
     * Devuelve el texto del contenido del comentario
     * @return El contenido del comentario
     */
    public String darContenido( )
    {
        return contenido;
    }
    
    
    /**
     * Devuelve la fecha de publicaci�n del comentario
     * @return La fecha de publicaci�n del comentario
     */
    public Date darFechaPublicacion( )
    {
        return fechaPublicacion;
    }
    /**
     * Devuelve la fecha de publicaci�n en el formato del protocolo
     */
    public String darFechaPublicacionP() {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm"); 
		return sdf.format(fechaPublicacion);
    }
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Revisa la invariante de la clase.<br>
     * <b>inv:</b>
     * usuario != null<br>
     * articulo != null<br>
     * contenido != null<br>
     * fechaPublicacion != null
     */
    private void verificarInvariante( )
    {
        assert usuario != null: "El usuario due�o del comentario debe tener un valor no nulo"; 
        assert articulo != null: "El art�culo comentado debe tener un valor no nulo"; 
        assert contenido != null: "El contenido del comentario debe tener un valor no nulo"; 
        assert fechaPublicacion != null: "La fecha de publicaci�n del comentario debe tener un valor no nulo"; 
    }
}