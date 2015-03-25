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

package uniandes.cupi2.blog.cliente.interfaz;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.blog.cliente.mundo.ClienteBlog;
import uniandes.cupi2.blog.cliente.mundo.IEscucharEventos;
import uniandes.cupi2.blog.comun.Articulo;

/**
 * Esta es la ventana principal de la aplicaci�n cliente del blog.
 */
public class InterfazClienteBlog extends JFrame implements IEscucharEventos
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private ClienteBlog blog;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con la lista de art�culos
     */
    private PanelArticulos panelArticulos;

    /**
     * Panel con la informaci�n del art�culo a mostrar
     */
    private PanelArticulo panelArticulo;
    
    /**
     * Panel con los comandos del blog
     */
    private PanelComandos panelComandos;

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la interfaz del cliente del blog. <br>
     * <b>post: </b> La Interfaz es creada.
     */
    public InterfazClienteBlog( )
    {
        // Crea la clase principal
        String ipServidor = (String) JOptionPane.showInputDialog( this, "La direcci�n IP del servidor", "localhost" );
        if(ipServidor == null)
            this.dispose( );
        if(ipServidor.length( ) == 0)
            JOptionPane.showMessageDialog( this, "Debe ingresar la ip del Servidor al cual conectar", "Direcci�n ip del servidor", JOptionPane.INFORMATION_MESSAGE );

        blog = new ClienteBlog( this, ipServidor );

        // Construye la forma
        setTitle("CupiBlog");
        setLayout( new BorderLayout( ) );
        setSize( 950, 700 );
        setResizable( false );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen( );
        add( panelImagen, BorderLayout.NORTH );

        panelArticulos = new PanelArticulos( this );
        add( panelArticulos, BorderLayout.WEST );

        panelArticulo = new PanelArticulo( this );
        add( panelArticulo, BorderLayout.CENTER );

        panelComandos = new PanelComandos( this );
        add( panelComandos, BorderLayout.EAST );

        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );

        // Centrar la ventana
        setLocationRelativeTo( null );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Solicita al servidor el inicio de sesi�n del usuario.
     * @param nombreUsuario El nombre del usuario a ingresar. nombreUsuario != null
     */
    public void iniciarSesion( String nombreUsuario )
    {
        try
        {
            blog.iniciarSesion( nombreUsuario );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Env�a al servidor la solicitud de registrar un nuevo usuario.
     * @param nombreUsuario El nombre del usuario a ingresar. nombreUsuario != null
     * @param nombre El nombre de pila del usuario. nombre != null
     * @param apellidos Los apellidos del usuario. apellidos != null
     */
    public void registrarUsuario( String nombreUsuario, String nombre, String apellidos )
    {
        try
        {
            blog.registrarUsuario( nombreUsuario, nombre, apellidos );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza la informaci�n a mostrar del art�culo.
     * @param articulo El art�culo a mostrar en el panel. articulo != null
     */
    public void actualizarArticulo( Articulo articulo )
    {
        try
        {
            blog.modificarArticuloActual( articulo );
            blog.solicitarComentariosArticuloActual( );
            ArrayList comentarios = blog.darListaComentariosArticuloActual( );
            panelArticulo.actualizarArticulo( articulo, comentarios );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Publica un art�culo en el blog.
     * @param titulo El t�tulo del art�culo. titulo != null
     * @param categoria La categor�a del art�culo. categoria != null
     * @param contenido El contenido del art�culo. contenido != null
     */
    public void publicarArticulo( String titulo, String categoria, String contenido ) {
       try {
    	   blog.publicarArticulo(titulo, categoria, contenido);
       } catch( Exception e ) {
           JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
           e.printStackTrace( );
       }
    }

    /**
     * Publica un comentario de un art�culo en el blog.
     * @param contenido El contenido del comentario. contenido != null
     */
    public void publicarComentario( String contenido ){
        try
        {
            blog.publicarComentario( contenido );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Busca los art�culos que pertenezcan a la categor�a introducida.
     * @param categoria La categor�a a buscar. categoria != null
     */
    public void buscarArticuloPorCategoria( String categoria ){
        try
        {
            blog.buscarArticulosCategoria( categoria );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Solicita al servidor la lista de todos los art�culos.
     */
    public void listarTodosArticulos(){
        try
        {
            blog.listarArticulos( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Solicita al servidor las estad�sticas del usuario.
     */
    public void mostrarEstadisticas(){
        try
        {
            blog.solicitarEstadisticas( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * Cierra la sesi�n del usuario
     */
    public void cerrarSesion(){
        try
        {
            blog.cerrarSesion( );
        }
        catch( IOException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }
    
    /**
     * El m�todo que se llama al cerrar la aplicaci�n
     */
    public void dispose(){
        cerrarSesion( );
        super.dispose( );
    }

    // -----------------------------------------------------------------
    // M�todos de observador
    // -----------------------------------------------------------------

    /**
     * Actualiza los eventos que suceden en el mundo.
     * @param articulos La lista de art�culos. articulos != null
     */
    public void actualizarListaArticulos( ArrayList articulos )
    {
        panelArticulos.actualizarListaArticulos( articulos );
    }

    /**
     * Actualiza los eventos que suceden en el mundo.
     */
    public void actualizarComentarios( )
    {
        panelArticulo.actualizarComentarios( blog.darListaComentariosArticuloActual( ) );
    }

    /**
     * Notifica al usuario la notificaci�n de un usuarios
     * @param mensaje El mensaje que se va a notificar. mensaje != null
     */
    public void notificarMensaje( String mensaje )
    {
        JOptionPane.showMessageDialog( this, mensaje, "Mensaje", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Muestra la informaci�n de la excepci�n.
     * @param e La excepci�n de la aplicaci�n. e != null
     */
    public void notificarExcepcion( Exception e )
    {
        JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
    }
    
    /**
     * Notifica el inicio de sesi�n del usuario
     * @param estadoSesion El estado de sesi�n del usuario. estadoSesion != null
     */
    public void cambiarEstadoSesion( boolean estadoSesion ){
        panelComandos.sesionAbierta( estadoSesion );
        if( !estadoSesion ){
            panelArticulo.desactivar( );
            panelArticulos.limpiarLista( );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = blog.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = blog.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Los argumentos de ejecuci�n de la aplicaci�n
     */
    public static void main( String[] args )
    {

        InterfazClienteBlog interfaz = new InterfazClienteBlog( );
        interfaz.setVisible( true );
    }
}