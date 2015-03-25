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

import java.awt.*;

import javax.swing.*;

/**
 * Es el di�logo donde se registra el resultado de un partido
 */
public class DialogoResultado extends JDialog
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazCampeonato ventana;

    //-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

    /**
     * Es el panel donde se seleccionan los equipos
     */
    private PanelComboEquipos panelEquipos;

    /**
     * Es el panel donde se indican los resultados del partido
     */
    private PanelResultadoPartido panelResultado;

    /**
     * Es el panel donde se muestran los botones
     */
    private PanelBotonesDialogo panelBotones;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Construye el di�logo que permite ingresar un nuevo resultado al campeonato
     * @param ic Es una referencia a la clase principal de la interfaz. ic != null.
     * @param nombreEquipos Es un arreglo con los nombres de los equipos que hay en el campeonato. nombreEquipos != null.
     */
    public DialogoResultado( InterfazCampeonato ic, String[] nombreEquipos )
    {
        super( ic, true );
        ventana = ic;
        panelEquipos = new PanelComboEquipos( nombreEquipos );
        panelResultado = new PanelResultadoPartido( );
        panelBotones = new PanelBotonesDialogo( this );
        setLayout( new BorderLayout( ) );
        add( panelEquipos, BorderLayout.NORTH );
        add( panelResultado, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.SOUTH );
        setTitle( "Registrar Resultado" );
        setSize( 230, 190 );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Inicializa todos los paneles de la ventana de di�logo
     */
    public void limpiar( )
    {
        panelEquipos.limpiar( );
        panelResultado.limpiar( );
    }

    /**
     * Este m�todo registra el partido con los datos disponibles
     */
    public void registrarPartido( )
    {
        try
        {
            int goles1 = Integer.parseInt( panelResultado.darGolesEquipo1( ) );
            int goles2 = Integer.parseInt( panelResultado.darGolesEquipo2( ) );
            int equipo1 = panelEquipos.darEquipo1( );
            int equipo2 = panelEquipos.darEquipo2( );
            ventana.registrarResultado( equipo1, equipo2, goles1, goles2 );
            setVisible( false );
        }
        catch( NumberFormatException nfe )
        {
            JOptionPane.showMessageDialog( ventana, "Debe indicar un n�mero correcto de goles" );
        }
    }
}