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
 * Panel para mostrar la informaci�n de los veh�culos
 */
public class PanelVehiculo extends JPanel implements ActionListener
{
    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

    /** Comando de b�squeda */
    public final static String BUSCAR = "buscar";

    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Ventana principal del programa */
    private InterfazImpuestosCarro principal;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /** Campo de texto para visualizar la marca del veh�culo */
    private JTextField txtMarca;
    /** Campo de texto para escribir y visualizar la l�nea del veh�culo */
    private JTextField txtLinea;
    /** Campo de texto para escribir y visualizar el modelo del veh�culo */
    private JTextField txtModelo;
    /** Campo de texto para visualizar el valor del veh�culo */
    private JTextField txtValor;
    /** Etiqueta para ubicar escribir y la palabra Marca antes del campo de Marca */
    private JLabel labMarca;
    /** Etiqueta para ubicar la palabra L�nea antes del campo de L�nea */
    private JLabel labLinea;
    /** Etiqueta para ubicar la palabra Modelo antes del campo de Modelo */
    private JLabel labModelo;
    /** Etiqueta para ubicar la palabra Valor antes del campo de Valor */
    private JLabel labValor;
    /** Bot�n para buscar el precio del veh�culo */
    private JButton butBuscar;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    /**
     * Crea el panel de veh�culo con el frame ra�z.
     * @param principalP Ventana principal. principalP != null.
     */
    public PanelVehiculo( InterfazImpuestosCarro principalP )
    {
        //Guarda referencia a la ventana principal
        principal = principalP;

        //Establece el layout como una grilla de 5 filas y 2 columnas
        setLayout( new GridLayout( 5, 2 ) );

        //Establece el tama�o del layout
        setPreferredSize( new Dimension( 0, 130 ) );

        //Adiciona un marco con titulo
        TitledBorder border = BorderFactory.createTitledBorder( "Datos del veh�culo" );
        border.setTitleColor( Color.BLUE );
        setBorder( border );

        //Crea e inicializa los objetos del panel
        labMarca = new JLabel( "Marca" );
        labLinea = new JLabel( "L�nea" );
        labModelo = new JLabel( "Modelo" );
        labValor = new JLabel( "Valor" );
        txtMarca = new JTextField( );
        txtLinea = new JTextField( );
        txtModelo = new JTextField( );

        txtValor = new JTextField( "$ 0" );
        txtValor.setEditable( false );
        txtValor.setForeground( Color.BLUE );
        txtValor.setBackground( Color.WHITE );

        butBuscar = new JButton( "Buscar" );
        butBuscar.setActionCommand( BUSCAR );
        butBuscar.addActionListener( this );

        //Adiciona los objetos al panel
        add( labMarca );
        add( txtMarca );
        add( labLinea );
        add( txtLinea );
        add( labModelo );
        add( txtModelo );
        add( labValor );
        add( txtValor );
        add( new JLabel( "" ) );
        add( butBuscar );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Cambia el valor desplegado del precio.
     * @param precio Nuevo precio a desplegar.
     */
    public void refrescarPrecio( double precio )
    {
        //Despliega el valor del veh�culo
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "$ ###,###.##" );
        txtValor.setText( df.format( precio ) );
    }

    /**
     * Retorna la marca dada en el campo correspondiente.
     * @return marca.
     */
    public String darMarca( )
    {
        return txtMarca.getText( );
    }

    /**
     * Retorna la l�nea dada en el campo correspondiente.
     * @return l�nea.
     */
    public String darLinea( )
    {
        return txtLinea.getText( );
    }

    /**
     * Retorna el modelo dad en el campo correspondiente.
     * @return modelo.
     */
    public String darModelo( )
    {
        return txtModelo.getText( );
    }

    /**
     * Limpia los campos del panel.
     */
    public void limpiar( )
    {
        txtMarca.setText( "" );
        txtLinea.setText( "" );
        txtModelo.setText( "" );
        txtValor.setText( "$ 0" );
    }

    //-----------------------------------------------------------------
    // Manejo de eventos
    //-----------------------------------------------------------------

    /**
     * Respuesta a los eventos en los elementos de la interfaz.
     * @param evento Evento generado. evento != null.
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( BUSCAR ) )
        {
            principal.calcularPrecioVehiculo( );
        }
    }
}