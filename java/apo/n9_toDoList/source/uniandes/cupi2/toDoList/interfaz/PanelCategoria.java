/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.toDoList.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel que muestra la informaci�n de una categor�a y permite la navegaci�n entre las diferentes categor�as
 */
public class PanelCategoria extends JPanel implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando Anterior
     */
    public static final String ANTERIOR = "Anterior";

    /**
     * Comando Agregar
     */
    public static final String AGREGAR = "Agregar";

    /**
     * Comando Siguiente
     */
    public static final String SIGUIENTE = "Siguiente";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazToDoList principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Label para el nombre de la categor�a
     */
    private JLabel labelNombre;

    /**
     * Bot�n anterior
     */
    private JButton btnAnterior;

    /**
     * Bot�n agregar
     */
    private JButton btnAgregar;

    /**
     * Bot�n Siguiente
     */
    private JButton btnSiguiente;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo panel que muestra la informaci�n de una categor�a
     * 
     * @param i Ventana principal de la aplicaci�n
     */
    public PanelCategoria( InterfazToDoList i )
    {
        principal = i;

        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbc = new GridBagConstraints( 0, 0, 4, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
        setBorder( BorderFactory.createTitledBorder( " Categor�a " ) );
        labelNombre = new JLabel( );
        add( labelNombre, gbc );

        gbc.gridy++;
        gbc.gridwidth = 1;

        btnAnterior = new JButton( "<" );
        btnAnterior.addActionListener( this );
        btnAnterior.setActionCommand( ANTERIOR );
        add( btnAnterior, gbc );
        gbc.gridx++;

        btnAgregar = new JButton( "Agregar" );
        btnAgregar.addActionListener( this );
        btnAgregar.setActionCommand( AGREGAR );
        add( btnAgregar, gbc );
        gbc.gridx++;

        btnSiguiente = new JButton( ">" );
        btnSiguiente.addActionListener( this );
        btnSiguiente.setActionCommand( SIGUIENTE );
        add( btnSiguiente, gbc );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre de la categor�a
     * 
     * @return nombre de la categor�a
     */
    public String darNombreCategoria( )
    {
        return labelNombre.getText( );
    }

    /**
     * Actualiza la informaci�n de la categor�a
     * 
     * @param nombre Nombre de la categor�a. nombre != null y nombre != ""
     */
    public void actualizar( String nombre )
    {
        labelNombre.setText( nombre );
    }

    /**
     * Limpia la informaci�n de la categor�a
     */
    public void limpiar( )
    {
        labelNombre.setText( "" );
    }

    /**
     * Manejo de los eventos de los botones
     * 
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );

        if( comando.equals( ANTERIOR ) )
        {
            principal.anteriorCategoria( );
        }
        else if( comando.equals( AGREGAR ) )
        {
            principal.agregarCategoria( );
        }
        else if( comando.equals( SIGUIENTE ) )
        {
            principal.siguienteCategoria( );
        }
    }
}
