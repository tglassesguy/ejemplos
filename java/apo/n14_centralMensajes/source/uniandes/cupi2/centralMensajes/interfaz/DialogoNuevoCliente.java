/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n14_centralMensajes
 * Autor: Pablo Marquez - 13 Jun, 2007
 * Autor: Juan Erasmo G�mez - 6 Ago, 2008
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.centralMensajes.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Di�logo para ingresar un nuevo cliente.
 */
public class DialogoNuevoCliente extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante de serializaci�n.
     */
    private static final long serialVersionUID = 7268749893033053711L;

    /**
     * Constante para el bot�n Aceptar.
     */
    public static final String ACEPTAR = "Aceptar";

    /**
     * Constante para el bot�n Cancelar.
     */
    public static final String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Interfaz principal de la aplicaci�n.
     */
    private InterfazCentral principal;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta "Identificador:".
     */
    private JLabel lblIdentificador;

    /**
     * Cuadro de texto donde se ingresa el identificador del cliente.
     */
    private JTextField txtIdentificador;

    /**
     * Bot�n Aceptar.
     */
    private JButton btnAceptar;

    /**
     * Bot�n Cancelar.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Di�logo para ingresar un nuevo cliente.
     * @param nPrincipal Interfaz principal de la aplicaci�n.
     */
    public DialogoNuevoCliente( InterfazCentral nPrincipal )
    {
        super( nPrincipal );
        setLayout( new BorderLayout( ) );

        JPanel panel = new JPanel( );
        principal = nPrincipal;
        GridLayout layout = new GridLayout( 3, 1 );
        layout.setHgap( 4 );
        layout.setVgap( 4 );
        panel.setLayout( layout );
        setTitle( "Nuevo Cliente" );
        setModal( true );
        setLocationRelativeTo( principal );
        panel.setBorder( BorderFactory.createEmptyBorder( 6, 6, 6, 6 ) );

        lblIdentificador = new JLabel( "Identificador:" );
        panel.add( lblIdentificador );

        txtIdentificador = new JTextField( );
        txtIdentificador.setPreferredSize( new Dimension( 250, 20 ) );
        panel.add( txtIdentificador );

        JPanel panelSur = new JPanel( );
        GridLayout layoutSur = new GridLayout( 1, 2 );
        layout.setHgap( 4 );
        layout.setVgap( 4 );
        panelSur.setLayout( layoutSur );
        btnCancelar = new JButton( CANCELAR );
        btnCancelar.setActionCommand( CANCELAR );
        btnCancelar.addActionListener( this );
        panelSur.add( btnCancelar );

        btnAceptar = new JButton( ACEPTAR );
        btnAceptar.setActionCommand( ACEPTAR );
        btnAceptar.addActionListener( this );
        panelSur.add( btnAceptar );

        panel.add( panelSur );

        add( panel, BorderLayout.CENTER );
        pack( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        if( e.getActionCommand( ).equals( ACEPTAR ) )
            principal.nuevoCliente( txtIdentificador.getText( ) );
        setVisible( false );
    }
}
