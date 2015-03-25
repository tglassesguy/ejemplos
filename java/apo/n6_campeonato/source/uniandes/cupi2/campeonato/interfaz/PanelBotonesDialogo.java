/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n6_campeonato 
 * Autor: Mario S�nchez - 21/07/2005 
 * Autor: J. Villalobos - 28/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.campeonato.interfaz;

import java.awt.event.*;

import javax.swing.*;

/**
 * Es el panel donde se muestran los botones para registrar un partido
 */
public class PanelBotonesDialogo extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    private static final String OK = "ok";
    private static final String CANCELAR = "Cancelar";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es una referencia al di�logo que contiene este panel
     */
    private DialogoResultado dialogo;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Es el bot�n para registrar el resultado
     */
    private JButton botonOk;

    /**
     * Es el bot�n para cancelar el registro del resultado
     */
    private JButton botonCancelar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el panel para lo botones de OK y Cancelar
     * @param dr Es el di�logo en el que se va a mostrar el panel. dr != null.
     */
    public PanelBotonesDialogo( DialogoResultado dr )
    {
        dialogo = dr;
        botonOk = new JButton( "Ok" );
        botonOk.setActionCommand( OK );
        botonOk.addActionListener( this );
        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        add( botonOk );
        add( botonCancelar );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Este m�todo se ejecuta cuando se hace click sobre un bot�n
     * @param evento El evento del click sobre un bot�n. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( OK.equals( comando ) )
        {
            dialogo.registrarPartido( );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.setVisible( false );
        }
    }
}