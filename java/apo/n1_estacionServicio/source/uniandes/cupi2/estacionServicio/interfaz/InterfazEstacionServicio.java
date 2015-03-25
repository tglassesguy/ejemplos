/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n1_estacionServicio
 * Autor: Catalina Rodriguez - 02-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.estacionServicio.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.estacionServicio.mundo.EstacionServicio;


/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazEstacionServicio extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private EstacionServicio estacionServicio;

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;
    
    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;
    
    /**
     * Panel con la informaci�n consolidada de ventas
     */
    private PanelConsolidado panelConsolidado;
    
    /**
     * Panel del surtidor 1
     */
    private PanelSurtidor panelSurtidor1;
    
    /**
     * Panel del surtidor 2
     */
    private PanelSurtidor panelSurtidor2;
    
    /**
     * Panel del surtidor 3
     */
    private PanelSurtidor panelSurtidor3;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor de la aplicaci�n <br>
     */
    public InterfazEstacionServicio()
    {
        // Crea la clase principal
        estacionServicio = new EstacionServicio();
        estacionServicio.inicializar();
        
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 650, 580 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle(" Estaci�n de Servicio ");
        
        //Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen();
        add( panelImagen, BorderLayout.NORTH );
        
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );
        
        JPanel centro = new JPanel();
        centro.setLayout( new BorderLayout() );
        
        JPanel surtidores = new JPanel();
        surtidores.setLayout( new GridLayout(1, 3) );
        
        panelConsolidado = new PanelConsolidado();
        centro.add(panelConsolidado, BorderLayout.SOUTH);
        
        panelSurtidor1 = new PanelSurtidor(this, 1);
        surtidores.add( panelSurtidor1);
        
        panelSurtidor2 = new PanelSurtidor(this, 2);
        surtidores.add( panelSurtidor2);
        
        panelSurtidor3 = new PanelSurtidor(this, 3);
        surtidores.add( panelSurtidor3);
        
        centro.add(surtidores, BorderLayout.CENTER);
        add(centro, BorderLayout.CENTER);
        
        //Centrar la ventana
        setLocationRelativeTo(null);
        
        //Actualizar los valores de la estaci�n y surtidores
        actualizar();
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Actualiza los campos de la interfaz con la informaci�n de la 
     * estaci�n de servicio y sus surtidores
     */
    private void actualizar()
    {
    	panelConsolidado.actualizar(estacionServicio);
    	panelSurtidor1.actualizar(estacionServicio.darSurtidor1());
    	panelSurtidor2.actualizar(estacionServicio.darSurtidor2());
    	panelSurtidor3.actualizar(estacionServicio.darSurtidor3());
    }
    
    /**
     * Reinicia las ventas de la estaci�n
     */
    public void reiniciar()
    {
    	estacionServicio.reiniciar();
    	actualizar();
    }
    
    /**
     * Registra la venta de combustible
     * @param numSurtidor N�mero del surtidor al que se le asocia la venta
     */
    public void registarVenta(int numSurtidor)
    {
    	String dineroS = JOptionPane.showInputDialog(this, "Ingrese el valor de la venta", "Registrar Venta Surtidor " + numSurtidor, JOptionPane.INFORMATION_MESSAGE);
    	try
    	{
    		if(dineroS!=null)
    		{
    			double dinero = Double.parseDouble(dineroS);
    			
    			if(dinero>0)
    			{
    				Object[] tipoV = { "Particular", "Servicio P�blico" };
    				String tipo = ( String )JOptionPane.showInputDialog( this, "Tipo de veh�culo:", "Tipo", JOptionPane.QUESTION_MESSAGE, null, tipoV, "Particular" );
    	            
    				if(tipo!=null)
    				{
    					double numGalones = 0;
    					if(tipo.equals("Particular"))
        				{
        					if(numSurtidor == 1)
                			{
                				numGalones = estacionServicio.registrarVentaParticularSurtidor1(dinero);
                			}
                			else if(numSurtidor == 2)
                			{
                				numGalones = estacionServicio.registrarVentaParticularSurtidor2(dinero);
                			}
                			else if(numSurtidor == 3)
                			{
                				numGalones = estacionServicio.registrarVentaParticularSurtidor3(dinero);
                			}
        				}
        				else
        				{
        					if(numSurtidor == 1)
                			{
        						numGalones = estacionServicio.registrarVentaPublicoSurtidor1(dinero);
                			}
                			else if(numSurtidor == 2)
                			{
                				numGalones = estacionServicio.registrarVentaPublicoSurtidor2(dinero);
                			}
                			else if(numSurtidor == 3)
                			{
                				numGalones = estacionServicio.registrarVentaPublicoSurtidor3(dinero);
                			}
        				}
            			actualizar();
        				JOptionPane.showMessageDialog(this, formatearValorReal(numGalones,23) + " galones vendidos", "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);        				
    				}
    				
    			}
    			else
    			{
    				JOptionPane.showMessageDialog(this, "La cantidad de dinero de la venta debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
    			}
    		}
    	}
    	catch( NumberFormatException e)
    	{
    		JOptionPane.showMessageDialog(this, "Ingrese valores num�ricos", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
	/**
     * Formatea un valor num�rico real para presentar en la interfaz <br>
     * @param valor El valor num�rico a ser formateado
     * @param numDigitos El n�mero de decimales deseados
     * @return Cadena con el valor formateado con puntos y signos.
     */
    private String formatearValorReal( double valor, int numDigitos )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( " ###,###.##" );
        df.setMinimumFractionDigits( 0 );
        return df.format( valor );
    }
    
    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = estacionServicio.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = estacionServicio.metodo2();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }


    //-----------------------------------------------------------------
    // Main
    //-----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {

        InterfazEstacionServicio interfaz = new InterfazEstacionServicio();
        interfaz.setVisible( true );
    }

}