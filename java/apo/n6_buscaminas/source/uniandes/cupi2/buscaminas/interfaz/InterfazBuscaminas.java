/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 
 * Ejercicio: n6_buscaminas 
 * Autor Inicial: Mario S�nchez - 15/07/2005
 * Autor: Milena Vela - 21/03/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.buscaminas.interfaz;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.buscaminas.mundo.CampoMinado;

/**
 * Esta es la clase principal de la interfaz del buscaminas
 */
public class InterfazBuscaminas extends JFrame
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica que en el modo actual se deben marcar las casillas
     */
    public static final int MODO_MARCAR = 0;

    /**
     * Indica que en el modo actual se deben desmarcar las casillas que est�n marcadas
     */
    public static final int MODO_DESMARCAR = 1;

    /**
     * Indica que en el modo actual se deben destapar las casillas
     */
    public static final int MODO_DESTAPAR = 2;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el modo actual de juego. Seg�n el modo cambia la acci�n que se realiza cuando se hace click sobre el campo minado.
     */
    private int modoActual;

    /**
     * Es el campo minado sobre el que se est� jugando.
     */
    private CampoMinado campoMinado;

    /**
     * Es el panel donde se muestran los botones para controlar el juego
     */
    private PanelBotones panelBotones;

    /**
     * Es el panel donde se muestra el estado actual del juego
     */
    private PanelBuscaminas panelBuscaminas;

    /**
     * Es el panel donde se muestra la barra de estado del juego
     */
    private PanelEstado panelBarraEstado;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la interfaz de la aplicaci�n.
     */
    public InterfazBuscaminas( )
    {
        try
        {
            campoMinado = new CampoMinado( new File( "./data/buscaminas.properties" ) );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "Error al cargar el estado inicial " + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }

        setTitle( "Buscaminas" );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 400, 400 );
        setResizable( false );

        modoActual = MODO_DESTAPAR;

        panelBotones = new PanelBotones( this );
        panelBuscaminas = new PanelBuscaminas( this, campoMinado.darFilas(), campoMinado.darColumnas( ) );
        panelBarraEstado = new PanelEstado( );

        setSize( 400, 400 );
        getContentPane( ).add( panelBotones, BorderLayout.NORTH );
        getContentPane( ).add( panelBuscaminas, BorderLayout.CENTER );
        getContentPane( ).add( panelBarraEstado, BorderLayout.SOUTH );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Cambia el modo actual
     * @param modo El nuevo modo de juego. modo pertenece a {MODO_DESMARCAR, MODO_MARCAR, MODO_DESTAPAR}
     */
    public void cambiarModo( int modo )
    {
        modoActual = modo;
    }

    /**
     * Realiza una jugada en la posici�n indicada. La jugada que se realiza depende del modo actual.
     * @param x Coordenada x de la posici�n donde se est� jugando
     * @param y Coordenada y de la posici�n donde se est� jugando
     */
    public void jugar( int x, int y )
    {
        switch( modoActual )
        {
            case MODO_MARCAR:
                marcar( x, y );
                break;
            case MODO_DESMARCAR:
                desmarcar( x, y );
                break;
            case MODO_DESTAPAR:
                destapar( x, y );
                break;
        }
    }

    /**
     * Marca la casilla en la posici�n indicada. <br>
     * Si la casilla ya est� marcada o est� destapada o el juego ya termin� se informa al usuario.
     * @param x Coordenada x de la posici�n que se va a marcar
     * @param y Coordenada y de la posici�n que se va a marcar
     */
    private void marcar( int x, int y )
    {
        try
        {
            campoMinado.marcar( x, y );
            actualizar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Desmarca una posici�n. <br>
     * Si la casilla no est� marcada o el juego ya termin� se informa al usuario.
     * @param x Coordenada x de la posici�n que se va a desmarcar
     * @param y Coordenada y de la posici�n que se va a desmarcar
     */
    private void desmarcar( int x, int y )
    {
        try
        {
            campoMinado.desmarcar( x, y );
            actualizar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Destapa una posici�n.<br>
     * Si la casilla no esta tapada o el juego ya se termin� se informa al usuario
     * @param x Coordenada x de la posici�n que se va a destapar
     * @param y Coordenada y de la posici�n que se va a destapar
     */
    private void destapar( int x, int y )
    {
        try
        {
            int resultado = campoMinado.destapar( x, y );
            actualizar( );

            if( resultado == CampoMinado.JUEGO_GANADO )
            {
                int tiempo = campoMinado.darTiempoTotal( );
                JOptionPane.showMessageDialog( this, "Gan� en " + tiempo + " segundos" );
            }
            else if( resultado == CampoMinado.JUEGO_PERDIDO )
            {
                JOptionPane.showMessageDialog( this, "Perdi�!" );
            }
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza el panel del buscaminas y la barra de estado
     */
    private void actualizar( )
    {
        panelBuscaminas.actualizar( campoMinado );
        int numeroMinas = campoMinado.darNumeroMinasRestantes( );
        int tiempo = campoMinado.darTiempoTotal( );
       
        panelBarraEstado.actualizarMinas( numeroMinas );
        panelBarraEstado.actualizarTiempo( tiempo );

        switch( modoActual )
        {
            case MODO_MARCAR:
                panelBarraEstado.actualizarModo( "Marcar" );
                break;
            case MODO_DESMARCAR:
                panelBarraEstado.actualizarModo( "Desmarcar" );
                break;
            case MODO_DESTAPAR:
                panelBarraEstado.actualizarModo( "Destapar" );
                break;
        }
    }

    /**
     * Inicia un nuevo juego de buscaminas. El estado inicial del juego es: <br>
     * Todas las casillas est�n tapadas y sin marcas. <br>
     * Las minas se distribuyeron de manera aleatoria. <br>
     * El tiempo de inicio del juego se inicializa en cero. <br>
     * El modo de juego es destapar.
     */
    public void reiniciar( )
    {
        campoMinado.inicializarJuego( );
        modoActual = MODO_DESTAPAR;
        panelBuscaminas.actualizar( campoMinado );
        panelBuscaminas.actualizar( campoMinado );

        int numeroMinas = campoMinado.darNumeroMinas( );
        int tiempo = campoMinado.darTiempoTotal( );
        panelBarraEstado.actualizarMinas( numeroMinas );
        panelBarraEstado.actualizarTiempo( tiempo );
        panelBarraEstado.actualizarModo( "Destapar" );   

    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = campoMinado.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = campoMinado.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Ejecuci�n
    // -----------------------------------------------------------------

    /**
     * Ejecuta la aplicaci�n
     * @param args Los par�metros para ejecutar la aplicaci�n. No se requiere ninguno.
     */
    public static void main( String[] args )
    {
        InterfazBuscaminas ib = new InterfazBuscaminas( );
        ib.setVisible( true );
    }
}
