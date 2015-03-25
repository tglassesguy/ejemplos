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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Panel de manejo de extensiones
 */
public class PanelComandos extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ingresar al sistema
     */
    private static final String INGRESAR = "INGRESAR USUARIO";

    /**
     * Comando para registrarse en el sistema
     */
    private static final String REGISTRAR_USUARIO = "REGISTRAR USUARIO";

    /**
     * Comando para publicar un art�culo
     */
    private static final String PUBLICAR_ARTICULO = "PUBLICAR_ARTICULO";

    /**
     * Comando para buscar un art�culo por el nombre de la categor�a
     */
    private static final String BUSCAR_ARTICULO_CATEGORIA = "BUSCAR_ARTICULO_CATEGORIA";

    /**
     * Comando para buscar un art�culo por el nombre de la categor�a
     */
    private static final String LISTAR_TODOS_ARTICULOS = "LISTAR_TODOS_ARTICULOS";
    
    /**
     * Comando para mostrar las estad�sticas del usuario
     */
    private static final String ESTADISTICAS = "ESTADISTICAS";

    /**
     * Comando para cerrar la sesi�n del usuario
     */
    private static final String CERRAR_SESION = "CERRAR_SESION";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazClienteBlog principal;

    // -----------------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para iniciar sesi�n
     */
    private JButton btnIniciarSesion;

    /**
     * Bot�n para registrar un Usuario
     */
    private JButton btnRegistrarUsuario;

    /**
     * Bot�n para publicar un art�culo
     */
    private JButton btnPublicarArticulo;

    /**
     * Bot�n para buscar un art�culo por la categor�a
     */
    private JButton btnBuscarCategoria;

    /**
     * Bot�n para listar todos los art�culos
     */
    private JButton btnListarTodos;
    
    /**
     * Bot�n para mostrar las estad�sticas
     */
    private JButton btnEstadisticas;

    /**
     * Bot�n para cerrar la sesi�n del usuario
     */
    private JButton btnCerrarSesion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del panel con los comandos b�sicos del blog
     * @param ventana La interfaz del blog. ventana != null
     */
    public PanelComandos( InterfazClienteBlog ventana )
    {
        principal = ventana;

        setBorder( null );
        setLayout( new GridLayout( 7, 1 ) );

        // Bot�n para iniciar sesi�n
        btnIniciarSesion = new JButton( "Iniciar" );
        btnIniciarSesion.setActionCommand( INGRESAR );
        btnIniciarSesion.addActionListener( this );
        add( btnIniciarSesion );

        // Bot�n para registrar un Usuario
        btnRegistrarUsuario = new JButton( "Registrar" );
        btnRegistrarUsuario.setActionCommand( REGISTRAR_USUARIO );
        btnRegistrarUsuario.addActionListener( this );
        add( btnRegistrarUsuario );

        // Bot�n para publicar un art�culo
        btnPublicarArticulo = new JButton( "Publicar" );
        btnPublicarArticulo.setActionCommand( PUBLICAR_ARTICULO );
        btnPublicarArticulo.setEnabled( false );
        btnPublicarArticulo.addActionListener( this );
        add( btnPublicarArticulo );

        // Bot�n para buscar art�culos por categor�a
        btnBuscarCategoria = new JButton( "Buscar" );
        btnBuscarCategoria.setActionCommand( BUSCAR_ARTICULO_CATEGORIA );
        btnBuscarCategoria.setEnabled( false );
        btnBuscarCategoria.addActionListener( this );
        add( btnBuscarCategoria );

        // Bot�n para listar todos los art�culos
        btnListarTodos = new JButton( "Listar" );
        btnListarTodos.setActionCommand( LISTAR_TODOS_ARTICULOS );
        btnListarTodos.setEnabled( false );
        btnListarTodos.addActionListener( this );
        add( btnListarTodos );

        // Bot�n para mostrar las estad�sticas del usuario
        btnEstadisticas = new JButton( "Estadisticas" );
        btnEstadisticas.setActionCommand( ESTADISTICAS );
        btnEstadisticas.setEnabled( false );
        btnEstadisticas.addActionListener( this );
        add( btnEstadisticas );

        // Bot�n para cerrar la sesi�n del Usuario
        btnCerrarSesion = new JButton( "Cerrar" );
        btnCerrarSesion.setActionCommand( CERRAR_SESION );
        btnCerrarSesion.setEnabled( false );
        btnCerrarSesion.addActionListener( this );
        add( btnCerrarSesion );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Activa y desactiva si la sesi�n est� abierta.
     * @param abierta El estado de la sesi�n.
     */
    public void sesionAbierta( boolean abierta ){
        btnIniciarSesion.setEnabled( !abierta );
        btnRegistrarUsuario.setEnabled( !abierta );
        btnPublicarArticulo.setEnabled( abierta );
        btnBuscarCategoria.setEnabled( abierta );
        btnListarTodos.setEnabled( abierta );
        btnEstadisticas.setEnabled( abierta );
        btnCerrarSesion.setEnabled( abierta );
    }
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento. e != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if( INGRESAR.equals( e.getActionCommand( ) ) )
        {
            String nombreUsuario = JOptionPane.showInputDialog( null, "Nombre de usuario:", "Iniciar Sesi�n", JOptionPane.QUESTION_MESSAGE );
            if(nombreUsuario != null){
                principal.iniciarSesion( nombreUsuario );
            }
        }
        else if( REGISTRAR_USUARIO.equals( e.getActionCommand( ) ) )
        {
            DialogoRegistrarUsuario dialogo = new DialogoRegistrarUsuario( principal );
            dialogo.setVisible( true );
        }
        else if( PUBLICAR_ARTICULO.equals( e.getActionCommand( ) ) )
        {
            DialogoPublicarArticulo dialogo = new DialogoPublicarArticulo( principal );
            dialogo.setVisible( true );
        }
        else if( BUSCAR_ARTICULO_CATEGORIA.equals( e.getActionCommand( ) ) )
        {
            String categoria = JOptionPane.showInputDialog( null, "Nombre de la categoria:", "Buscar Articulo", JOptionPane.QUESTION_MESSAGE );
            if(categoria != null){
                principal.buscarArticuloPorCategoria( categoria );
            }
        }
        else if( LISTAR_TODOS_ARTICULOS.equals( e.getActionCommand( ) ) )
        {
            principal.listarTodosArticulos( );
        }
        else if( ESTADISTICAS.equals( e.getActionCommand( ) ) )
        {
            principal.mostrarEstadisticas( );
        }
        else if( CERRAR_SESION.equals( e.getActionCommand( ) ) )
        {
            principal.cerrarSesion( );
        }
        
    }

}
