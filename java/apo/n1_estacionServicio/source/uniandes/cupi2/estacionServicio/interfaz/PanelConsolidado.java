package uniandes.cupi2.estacionServicio.interfaz;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uniandes.cupi2.estacionServicio.mundo.EstacionServicio;

/**
 * Panel que muestra la informaci�n consolidada de la estaci�n de servicio
 */
public class PanelConsolidado extends JPanel 
{
    //-----------------------------------------------------------------
    // Atributos de interfaz
    //-----------------------------------------------------------------

	/**
	 * Etiqueta consolidado n�mero de galones vendido
	 */
	private JLabel etiquetaNumGalones;
	
	/**
	 * Etiqueta consolidado dinero total recaudado
	 */
	private JLabel etiquetaDineroRecaudado;
	
	/**
	 * Etiqueta costo promedio del gal�n de acuerdo a las ventas realizadas
	 */
	private JLabel etiquetaPromedioGalon;
	
	/**
	 * Campo de texto consolidado n�mero de galones vendido
	 */
	private JTextField txtNumGalones;
	
	/**
	 * Campo de texto consolidado dinero total recaudado
	 */
	private JTextField txtDineroRecaudado;
	
	/**
	 * Campo de texto costo promedio del gal�n de acuerdo a las ventas realizadas
	 */
	private JTextField txtPromedioGalon;
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

	/**
	 * Constructor del panel
	 */
	public PanelConsolidado()
	{
		etiquetaDineroRecaudado = new JLabel(" Dinero Total Recaudado: ");
		etiquetaDineroRecaudado.setHorizontalAlignment( JLabel.LEFT );
		
		txtDineroRecaudado = new JTextField(" $ ");
		txtDineroRecaudado.setEditable(false);
		
		etiquetaNumGalones = new JLabel(" Galones Vendidos: ");
		etiquetaNumGalones.setHorizontalAlignment( JLabel.LEFT );
		
		txtNumGalones = new JTextField();
		txtNumGalones.setEditable(false);
		
		etiquetaPromedioGalon = new JLabel(" Costo Promedio por Gal�n: ");
		etiquetaPromedioGalon.setHorizontalAlignment( JLabel.LEFT );
		
		txtPromedioGalon = new JTextField(" $ 0");
		txtPromedioGalon.setEditable(false);
		
		setBorder( BorderFactory.createTitledBorder( "Consolidado Total" ) );
		setLayout( new GridLayout(2, 3) );
		
		add( etiquetaNumGalones );
		add( etiquetaDineroRecaudado );
		add( etiquetaPromedioGalon );
		add( txtNumGalones );
		add( txtDineroRecaudado );
		add( txtPromedioGalon );
	}
	
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

	/**
	 * Actualiza los campos de interfaz con los consolidados de la estaci�n
	 * @param estacion Estaci�n de servicio
	 */
	public void actualizar(EstacionServicio estacion)
	{
		txtDineroRecaudado.setText(" $ " + formatearValorReal(estacion.darTotalDineroRecaudado(), 2));
		txtNumGalones.setText(" " + formatearValorReal(estacion.darTotalGalones(), 2));
		if(estacion.darTotalGalones() > 0)
		{
			txtPromedioGalon.setText(" $ " + formatearValorReal(estacion.darCostoPromedioGalon(), 2));
		}
		else
		{
			txtPromedioGalon.setText(" $ 0");
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
}
