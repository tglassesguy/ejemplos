/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Impuestos de Carros
 * Autor: Katalina Marcos - Abr 15, 2005
 * Autor: Diana Puentes - Jun 23, 2005
 * Autor: Jorge Villalobos - Jul 10, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.interfaz;

import java.awt.*;
import java.awt.event.*;
import java.text.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * Panel activo para mostrar los resultados del c�lculo de impuestos
 */
public class PanelResultados extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /** Comando de inicializaci�n del programa */
    public final static String LIMPIAR = "limpiar";
    /** Comando de c�lculo del valor de los impuestos */
    public final static String CALCULAR = "calcular";
    /** Constante OPCION_1, usada para la opci�n de la extensi�n 1 */
    private final static String OPCION_1 = "OPCION_1";
    /** Constante OPCION_2, usada para la opci�n de la extensi�n 2 */
    private final static String OPCION_2 = "OPCION_2";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Ventana principal del programa */
    private InterfazImpuestosCarro principal;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /** Etiqueta para mostrar la palabra "Total" */
    private JLabel labTotal;
    /** Campo para mostrar el total de la liquidaci�n */
    private JTextField txtTotal;
    /** Bot�n para limpiar los campos del panel de resultados */
    private JButton butLimpiar;
    /** Bot�n para calcular el total de la liquidaci�n */
    private JButton butCalcular;
    /** Bot�n Para hacer la extensi�n1 */
    private JButton opcion1;
    /** Bot�n Para hacer la extensi�n2 */
    private JButton opcion2;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea el panel de veh�culo encadenado con la ventana principal, considerando que es un panel activo
     * @param principalP - ventana principal del programa
     */
    public PanelResultados( InterfazImpuestosCarro principalP )
    {
        //Guarda la referencia a la ventana principal
        principal = principalP;

        //Establece el layout como una grilla de 4 filas y 3 columnas
        setLayout( new GridLayout( 4, 3 ) );

        //Establece la altura del panel
        setPreferredSize( new Dimension( 0, 105 ) );

        //Adiciona un marco con titulo
        TitledBorder border = BorderFactory.createTitledBorder( "Resultados" );
        border.setTitleColor( Color.BLUE );
        setBorder( border );

        //Crea los objetos del panel
        labTotal = new JLabel( "Total a pagar" );
        txtTotal = new JTextField( "$ 0" );
        txtTotal.setEditable( false );
        txtTotal.setForeground( Color.BLUE );
        txtTotal.setBackground( Color.WHITE );

        butLimpiar = new JButton( "Limpiar" );
        butLimpiar.setActionCommand( LIMPIAR );
        butLimpiar.addActionListener( this );

        butCalcular = new JButton( "Calcular" );
        butCalcular.setActionCommand( CALCULAR );
        butCalcular.addActionListener( this );

        opcion1 = new JButton( "Opci�n 1" );
        opcion1.setActionCommand( OPCION_1 );
        opcion1.addActionListener( this );
        //opcion1.setVisible( false );

        opcion2 = new JButton( "Opci�n 2" );
        opcion2.setActionCommand( OPCION_2 );
        opcion2.addActionListener( this );
        //opcion2.setVisible( false );

        //Adiciona los objetos del panel (textos en blanco en los espacios no
        // usados)
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( butLimpiar );
        add( labTotal );
        add( txtTotal );
        add( butCalcular );

        // Localizaci�n de los botones para las extensiones opcionales
        // Usa 4 labels para lograr la separaci�n de los botones
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( new JLabel( "" ) );
        add( opcion1 );
        add( opcion2 );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Cambia el valor desplegado del pago
     * @param pago - nuevo pago a desplegar
     */
    public void refrescarPago( double pago )
    {
        //Despliega el valor del veh�culo
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        txtTotal.setText( df.format( pago ) );
    }

    /**
     * Limpia los campos del panel
     */
    public void limpiar( )
    {
        txtTotal.setText( "$ 0" );
    }

    //-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------

    /**
     * Respuesta a los eventos en los elementos de la interfaz
     * @param evento Evento generado. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( LIMPIAR ) )
        {
            principal.limpiar( );
        }
        else if( comando.equals( CALCULAR ) )
        {
            principal.calcularImpuestos( );
        }
        else if( comando.equals( OPCION_1 ) )
        {
            principal.reqFuncOpcion1( );
        }
        else if( comando.equals( OPCION_2 ) )
        {
            principal.reqFuncOpcion2( );
        }
    }
}