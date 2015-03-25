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

package uniandes.cupi2.blog.servidor.interfaz;

import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.blog.comun.InfoUsuario;
import uniandes.cupi2.blog.excepciones.CupiBlogPersistenciaException;
import uniandes.cupi2.blog.servidor.mundo.BlogServidor;
import uniandes.cupi2.blog.servidor.mundo.IManejadorEventosBlogServidor;


/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazServidorBlog extends JFrame implements IManejadorEventosBlogServidor
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private BlogServidor servidor;
    
    /**
     * El actual usuario seleccionado
     */
    private InfoUsuario usuario;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;
    
    /**
     * Panel con la lista de usuarios conectados
     */
    private PanelUsuariosConectados panelUsuariosConectados;
    
    /**
     * Panel con la lista de usuarios conectados
     */
    private PanelArticulos panelArticulos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * La interfaz principal del cliente del blog.<br>
     */
    public InterfazServidorBlog()
    {
        // Crea la clase principal
        try
        {
            servidor = new BlogServidor( this );
            // Construye la forma
            setTitle( "Servidor Blog" );
            setLayout( new BorderLayout( ) );
            setSize( 530, 430 );
            setResizable( false );
            setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
            
            //Creaci�n de los paneles aqu�
            panelUsuariosConectados = new PanelUsuariosConectados( this );
            add( panelUsuariosConectados, BorderLayout.CENTER );
            
            panelArticulos = new PanelArticulos( );
            add( panelArticulos, BorderLayout.EAST );
            
            panelExtension = new PanelExtension( this );
            add( panelExtension, BorderLayout.SOUTH );
            
            //Centrar la ventana
            setLocationRelativeTo(null);
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( null, "El servidor no se pudo iniciar: " + e.getMessage( ), "Error de conexi�n", JOptionPane.ERROR_MESSAGE );
        }  
    }
    
    public void dispose(){
        try{
            servidor.desconectar( );
            System.exit( 0 );
        }catch( Exception e ){
            JOptionPane.showMessageDialog( null, "El servidor no se pudo iniciar: " + e.getMessage( ), "Error de conexi�n", JOptionPane.ERROR_MESSAGE );
        }
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Devuelve el servidor del blog.
     * @return El servidor del blog.
     */
    public BlogServidor darServidor(){
        return servidor;
    }
    
    
    /**
     * Actualiza el usuario seleccionado actualmente.
     * @param pUsuario El nuevo usuario seleccionado.
     */
    public void actualizarUsuario(InfoUsuario pUsuario){
        usuario = pUsuario;
        actualizarListaArticulos();
    }
    
    /**
     * Actualiza la lista de art�culos dependiendo el usuario seleccionado.<br>
     * <b>pos: </b>La lista de art�culos se actualiza.<br>
     * En caso de error muestra una ventana con el mensaje de error.
     */
    public void actualizarListaArticulos(){
        ArrayList articulos = new ArrayList();
        try
        {
            if(usuario != null){
                articulos = servidor.darArticulosUsuario( usuario );
            }
            panelArticulos.actualizarListaArticulos( articulos );
        }
        catch( CupiBlogPersistenciaException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error de persistencia", JOptionPane.ERROR_MESSAGE );
        }
    }
    
    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = servidor.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = servidor.metodo2();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    //-----------------------------------------------------------------
    // M�todos de los Observadores
    //-----------------------------------------------------------------
    
    
    /**
     * Cuando un usuario se conecta al servidor
     */
    public void cambiosUsuariosConectados( )
    {
        panelUsuariosConectados.actualizarListaUsuariosConectados( servidor.darUsuariosConectados( ) );
        actualizarListaArticulos( );
    }
    
    /**
     * Cuando un art�culo nuevo es publicado.
     */
    public void nuevoArticuloPublicado( )
    {
       actualizarListaArticulos( );
    }

    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Los argumentos de ejecuci�n de la aplicaci�n
     */
    public static void main( String[] args )
    {
        try
        {
            InterfazServidorBlog interfaz = new InterfazServidorBlog();
            interfaz.setVisible( true );
            interfaz.darServidor( ).esperarConexiones( );
        } catch( IOException e ) {
        	System.out.println("Error de entrada/salida");
        	//e.printStackTrace();
            JOptionPane.showMessageDialog( null, "El servidor no se pudo iniciar: " + e.getMessage( ), "Error de conexi�n", JOptionPane.ERROR_MESSAGE );
        } catch (SQLException e) {
        	System.out.println("Error SQL");
			e.printStackTrace();
		}
    }
}