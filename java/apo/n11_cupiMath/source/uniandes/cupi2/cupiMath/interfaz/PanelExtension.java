/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiMath.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel de manejo de extensiones
 */
public class PanelExtension extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /**
     * Comando Opci�n 1
     */
    private static final String OPCION_1 = "OPCION_1";

    /**
     * Comando Opci�n 2
     */
    private static final String OPCION_2 = "OPCION_2";
    
    /**
     * Evaluar una expresi�n aritm�ticas
     */
    private static final String EVALUAR="EVALUAR";
    
    /**
     * Expresar una expresi�n en notaci�n polaca
     */
    private static final String NOTACION_POLACA="POLACA";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCupiMath principal;

    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

    /**
     * Bot�n Opci�n 1
     */
    private JButton btnOpcion1;

    /**
     * Bot�n Opci�n 2
     */
    private JButton btnOpcion2;
    
    /**
     * Bot�n para evaluar una expresi�n aritm�tica
     */
    private JButton btnEvaluar;
    
    /**
     * Bot�n para convertir la expresi�n en notaci�n polaca
     */
    private JButton btnNotacionPolaca;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor del panel de extensi�n
     * @param ventana La ventana principal de la aplicaci�n. ventana != null
     */
    public PanelExtension( InterfazCupiMath ventana )
    {
        principal = ventana;

        setBorder( new TitledBorder( "Opciones" ) );
        setLayout( new GridLayout( 1, 2 ) );

        //Bot�n evaluar
        btnEvaluar=new JButton("EXEC");
        btnEvaluar.setActionCommand( EVALUAR );
        btnEvaluar.addActionListener( this );
        add(btnEvaluar);
        
        //Bot�n notaci�n polaca
        btnNotacionPolaca=new JButton("Notaci�n polaca" );
        btnNotacionPolaca.setActionCommand( NOTACION_POLACA );
        btnNotacionPolaca.addActionListener( this );
        add( btnNotacionPolaca );
        
        
        //Bot�n opci�n 1
        btnOpcion1 = new JButton("Opci�n 1");
        btnOpcion1.setActionCommand( OPCION_1 );
        btnOpcion1.addActionListener( this );
        add(btnOpcion1);
        
        //Bot�n opci�n 2
        btnOpcion2 = new JButton("Opci�n 2");
        btnOpcion2.setActionCommand( OPCION_2 );
        btnOpcion2.addActionListener( this );
        add(btnOpcion2);
        
        
        
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento. e != null
     */
    public void actionPerformed( ActionEvent e )
    {
        if(OPCION_1.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion1();
        }
        else if(OPCION_2.equals( e.getActionCommand() ))
        {
            principal.reqFuncOpcion2();
        }
        else if(EVALUAR.equals(e.getActionCommand( )))
        {
            principal.evaluarExpresionAritmetica();
        }
        else if(NOTACION_POLACA.equals( e.getActionCommand( ) ))
        {
            principal.convertirEnNotacionPolaca( );
        }
    }

}
