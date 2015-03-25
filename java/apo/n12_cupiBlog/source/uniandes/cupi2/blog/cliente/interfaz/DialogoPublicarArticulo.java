/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 *Autor: Luis Ricardo Ruiz Rodr�guez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Clase que representa el di�logo donde se hace la publicaci�n de un art�culo del blog
 */
public class DialogoPublicarArticulo extends JDialog implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    
    /**
     * Es el comando de la opci�n para publicar el art�culo
     */
    private static final String PUBLICAR_ARTICULO = "Publicar Articulo";
    
    /**
     * Es el comando de la opci�n para cancelar la publicaci�n del art�culo
     */
    private static final String CANCELAR_PUBLICAR_ARTICULO = "Cancelacion Articulo";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal de la interfaz
     */
    private InterfazClienteBlog principal;

    /**
     * Campo para el t�tulo del art�culo
     */
    private JTextField txtTituloArticulo;
    
    /**
     * Campo para la categor�a del art�culo
     */
    private JTextField txtCategoria;
    
    /**
     * Campo para el contenido del art�culo
     */
    private JTextArea txtContenido;

    /**
     * Bot�n de publicar el art�culo
     */
    private JButton btnAceptarPublicacion;
    
    /**
     * Bot�n para cancelar la publicaci�n del art�culo
     */
    private JButton btnCancelarPublicacion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el dialogo para registrar un usuario
     * @param interfaz La interfaz del blog. interfaz != null
     */
    public DialogoPublicarArticulo( InterfazClienteBlog interfaz )
    {
        principal = interfaz;

        setTitle( "Publicar Art�culo" );
        setSize( 400, 300 );
        setResizable( false );
        setLayout( new BorderLayout( ) );
        
        setLocationRelativeTo( null );

        JPanel panelCampos = new JPanel();
        panelCampos.setLayout( new GridBagLayout( ) );
        panelCampos.setBorder( new TitledBorder( "Campos" ) );
        
        GridBagConstraints gbc = new GridBagConstraints( );
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel labTituloArticulo = new JLabel( "T�tulo:" );
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelCampos.add( labTituloArticulo, gbc );

        txtTituloArticulo = new JTextField( );
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelCampos.add( txtTituloArticulo, gbc );

        JLabel labCategoria = new JLabel( "Categor�a:" );
        gbc.ipadx = 100;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelCampos.add( labCategoria, gbc );

        txtCategoria = new JTextField( );
        gbc.ipadx = 200;
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelCampos.add( txtCategoria, gbc );
        
        JLabel labContenido = new JLabel( "Contenido:" );
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 100;
        gbc.ipady = 100;
        panelCampos.add( labContenido, gbc );

        txtContenido = new JTextArea( );
        txtContenido.setWrapStyleWord( true );
        JScrollPane scroll = new JScrollPane( txtContenido, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.ipadx = 200;
        gbc.ipady = 100;
        
        panelCampos.add( scroll, gbc );
        
        add( panelCampos, BorderLayout.CENTER );
        
        //El panel con los botones de aceptar y cancelar
        JPanel panelBotones = new JPanel( );
        panelBotones.setLayout( new GridLayout( 1, 2 ) );
        
        btnAceptarPublicacion = new JButton( "Publicar" );
        btnAceptarPublicacion.setActionCommand( PUBLICAR_ARTICULO );
        btnAceptarPublicacion.addActionListener( this );
        panelBotones.add( btnAceptarPublicacion, BorderLayout.SOUTH );

        btnCancelarPublicacion = new JButton( "Cancelar" );
        btnCancelarPublicacion.setActionCommand( CANCELAR_PUBLICAR_ARTICULO );
        btnCancelarPublicacion.addActionListener( this );
        panelBotones.add( btnCancelarPublicacion, BorderLayout.SOUTH );

        add( panelBotones, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
    /**
     * Ejecuta una acci�n seg�n la opci�n del men� que haya sido seleccionada
     * @param evento El evento de click en una opci�n. evento != null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if(comando.equals( PUBLICAR_ARTICULO )){
        	principal.publicarArticulo(txtTituloArticulo.getText(), txtCategoria.getText(), txtContenido.getText());
            this.dispose( );
        }
    }
}
