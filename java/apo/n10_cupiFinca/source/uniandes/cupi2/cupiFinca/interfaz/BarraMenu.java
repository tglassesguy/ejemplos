/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodr�guez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.interfaz;

import java.awt.event.*;

import javax.swing.*;

/**
 * Esta es la barra que contiene los men�s de la aplicaci�n
 */
public class BarraMenu extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El comando para abrir un nuevo juego
     */
    private static final String NUEVO = "Nuevo";
    /**
     * El comando para abrir un juego guardado
     */
    private static final String ABRIR = "Abrir";
    /**
     * El comando para salvar un juego
     */
    private static final String SALVAR = "Salvar";
    /**
     * El comando para salvar como un juego
     */
    private static final String SALVAR_COMO = "SalvarComo";
    /**
     * El comando para salir del juego
     */
    private static final String SALIR = "Salir";
    
    /**
     * El comando para la opci�n 1
     */
    private static final String OPCION1 = "Opt1";
    /**
     * El comando para la opci�n 2
     */
    private static final String OPCION2 = "Opt2";
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la clase principal de la interfaz
     */
    private InterfazCupiFinca principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * El men� Archivo
     */
    private JMenu menuArchivo;
    
    /**
     * El men� Opciones
     */
    private JMenu menuOpciones;
    
    /**
     * La opci�n Nuevo del men� Archivo
     */
    private JMenuItem itemNuevo;

    private JMenuItem itemAbrir;
    private JMenuItem itemSalvar;
    private JMenuItem itemSalvarComo;
    private JMenuItem itemSalir;
    private JMenuItem itemOpcion1;
    private JMenuItem itemOpcion2;
    
    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la barra de men� 
     * @param ventana Es la referencia a la clase principal de la interfaz. ventana != null
     */
    public BarraMenu( InterfazCupiFinca ventana )
    {
        principal = ventana;

        menuArchivo = new JMenu( "Archivo" );
        add( menuArchivo );

        itemNuevo = new JMenuItem( "Nuevo" );
        itemNuevo.setActionCommand( NUEVO );
        itemNuevo.addActionListener( this );
        menuArchivo.add( itemNuevo );

        itemAbrir = new JMenuItem("Abrir");
        itemAbrir.setActionCommand( ABRIR );
        itemAbrir.addActionListener( this );
        menuArchivo.add( itemAbrir);
        
        itemSalvar = new JMenuItem("Salvar");
        itemSalvar.setActionCommand( SALVAR );
        itemSalvar.addActionListener( this );
        menuArchivo.add( itemSalvar);
        
        itemSalvarComo= new JMenuItem("Salvar como...");
        itemSalvarComo.setActionCommand( SALVAR_COMO );
        itemSalvarComo.addActionListener( this );
        menuArchivo.add( itemSalvarComo);
        
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuArchivo.add( itemSalir);
        
        menuOpciones = new JMenu("Opciones");
        add (menuOpciones);
        
        itemOpcion1 = new JMenuItem("Opci�n 1");
        itemOpcion1.setActionCommand(OPCION1);
        itemOpcion1.addActionListener(this);
        menuOpciones.add(itemOpcion1);
        
        itemOpcion2 = new JMenuItem("Opci�n 2");
        itemOpcion2.setActionCommand(OPCION2);
        itemOpcion2.addActionListener(this);
        menuOpciones.add(itemOpcion2);
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Ejecuta la acci�n que corresponde a la opci�n del men� que fue seleccionada
     * @param evento Es el evento de seleccionar una opci�n del men� - evento != null
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );

        if( NUEVO.equals(comando)) 				principal.reiniciar( );
        else if (ABRIR.equals(comando))			principal.abrir();
        else if (SALVAR.equals(comando))		principal.guardar();
        else if (SALVAR_COMO.equals(comando))	principal.guardarComo();
        else if (SALIR.equals(comando))			principal.dispose();
        else if (OPCION1.equals(comando))		principal.reqFuncOpcion1();
        else if (OPCION2.equals(comando))		principal.reqFuncOpcion2();
    }

}