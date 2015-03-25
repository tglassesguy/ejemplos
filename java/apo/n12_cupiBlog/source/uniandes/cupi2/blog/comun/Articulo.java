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
 * El usuario autor del art�culo debe existir.<br>
 * El t�tulo del art�culo debe existir.<br>
 * La categor�a del art�culo debe existir.<br>
 * El contenido del art�culo debe existir.<br>
 * La fecha de publicaci�n del art�culo debe existir.<br>
 */
public class Articulo
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El usuario que public� del art�culo
     */
    private String usuario;

    /**
     * El t�tulo del art�culo
     */
    private String titulo;

    /**
     * La categor�a del art�culo
     */
    private String categoria;

    /**
     * El texto del contenido del art�culo
     */
    private String contenido;
    
    /**
     * La fecha de publicaci�n del art�culo
     */
    private Date fechaPublicacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor del art�culo, con el t�tulo, la categor�a y el contenido del art�culo.
     * @param nUsuario El usuario autor del art�culo. nUsuario != null
     * @param nTitulo El t�tulo del art�culo. nT�tulo != null
     * @param nCategoria La categor�a del art�culo. nCategoria != null
     * @param nContenido El texto con el contenido del art�culo. nContenido != null
     * @param nFechaPublicacion La fecha de publicaci�n del art�culo. nFechaPublicacion != null
     */
    public Articulo( String nUsuario, String nTitulo, String nCategoria, String nContenido, Date nFechaPublicacion )
    {
        usuario = nUsuario;
        titulo = nTitulo;
        categoria = nCategoria;
        contenido = nContenido;
        fechaPublicacion = nFechaPublicacion;
        
        verificarInvariante( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    
    /**
     * Devuelve el usuario autor del art�culo
     * @return El usuario autor del art�culo
     */
    public String darUsuario( )
    {
        return usuario;
    }

    /**
     * Devuelve el t�tulo del art�culo
     * @return El t�tulo del art�culo
     */
    public String darTitulo( )
    {
        return titulo;
    }

    /**
     * Devuelve la categor�a del art�culo
     * @return La categoria del art�culo
     */
    public String darCategoria( )
    {
        return categoria;
    }

    /**
     * Devuelve el texto del contenido del art�culo
     * @return El contenido del art�culo
     */
    public String darContenido( )
    {
        return contenido;
    }
    
    /**
     * Devuelve la fecha de publicaci�n del art�culo
     * @return La fecha de publicaci�n del art�culo
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
    /**
     * Modifica el t�tulo del art�culo
     * @param nTitulo El nuevo t�tulo del art�culo. nTitulo != null
     */
    public void modificarTitulo( String nTitulo )
    {
        titulo = nTitulo;
        verificarInvariante( );
    }

    /**
     * Modifica la categor�a del art�culo
     * @param nCategoria la nueva categor�a del art�culo. nCategoria != null
     */
    public void modificarCategoria( String nCategoria )
    {
        categoria = nCategoria;
        verificarInvariante( );
    }

    /**
     * Modifica el texto de contenido del art�culo
     * @param nContenido El nuevo contenido del art�culo. nContenido != null
     */
    public void modificarApellidos( String nContenido )
    {
        contenido = nContenido;
        verificarInvariante( );
    }
    
    /**
     * Devuelve la descripci�n del art�culo
     * @return La descripci�n del art�culo
     */
    public String toString(){
        return titulo + " ( " + usuario + " )";
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    /**
     * Revisa la invariante de la clase.<br>
     * <b>inv:</b>
     * usuario != null<br>
     * titulo != null<br>
     * categoria != null<br>
     * contenido != null<br>
     * fechaPublicacion != null
     */
    private void verificarInvariante( )
    {
        assert usuario != null: "El usuario autor del art�culo debe tener un valor no nulo"; 
        assert titulo != null: "El t�tulo del art�culo debe tener un valor no nulo"; 
        assert categoria != null: "La categor�a del art�culo debe tener un valor no nulo"; 
        assert contenido != null: "El contenido del art�culo debe tener un valor no nulo"; 
        assert fechaPublicacion != null: "La fecha de publicaci�n del art�culo debe tener un valor no nulo"; 
    }
}