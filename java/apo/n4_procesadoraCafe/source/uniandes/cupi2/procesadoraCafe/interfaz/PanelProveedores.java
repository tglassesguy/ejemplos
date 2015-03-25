package uniandes.cupi2.procesadoraCafe.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.procesadoraCafe.mundo.Proveedor;

/**
 * Panel con la informaci�n de los proveedores de caf� tostado de la Procesadora
 */
public class PanelProveedores extends JPanel implements ActionListener, ListSelectionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Agregar Proveedor
	 */
	private final static String AGREGAR = "Agregar";

	//-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicaci�n
	 */
	private InterfazProcesadoraCafe principal;

	//-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

	/**
	 * Scroll de productos
	 */
	private JScrollPane scrollDesplazamiento;
	
	/**
	 * Lista de proveedores
	 */
	private JList listaProveedores;
	
	/**
	 * Campo de texto del nombre del proveedor
	 */
	private JTextField txtNombre;
	
	/**
	 * Campo de texto de la c�dula del proveedor
	 */
	private JTextField txtCedula;
	
	/**
	 * Campo de texto del tel�fono del proveedor
	 */
	private JTextField txtTelefono;
	
	/**
	 * Campo de texto del departamento de origen del caf�
	 */
	private JTextField txtOrigen;
	
	/**
	 * Campo de texto del precio del caf�
	 */
	private JTextField txtPrecio;
	
	/**
	 * Bot�n Agregar Proveedor
	 */
	private JButton btnAgregar;
	
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------

    /**
     * Constructor del panel
     * @param ventana Ventana principal
     */
	public PanelProveedores(InterfazProcesadoraCafe ventana)
	{
		principal = ventana;
		
		setLayout(new GridLayout(1, 2));
		
		JPanel lista = new JPanel();
		lista.setLayout(new BorderLayout());
		lista.setBorder(new TitledBorder(" Proveedores "));
		
		scrollDesplazamiento = new JScrollPane( );
		listaProveedores = new JList( );
		listaProveedores.setSelectionMode( javax.swing.ListSelectionModel.SINGLE_SELECTION );
		listaProveedores.addListSelectionListener(this);
		scrollDesplazamiento.setVerticalScrollBarPolicy( javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDesplazamiento.setViewportView( listaProveedores );
        lista.add(scrollDesplazamiento, BorderLayout.CENTER);

		JPanel auxBotones = new JPanel();
		auxBotones.setLayout(new GridLayout(1, 1));
		
		btnAgregar = new JButton(AGREGAR);
		btnAgregar.setActionCommand(AGREGAR);
		btnAgregar.addActionListener(this);
		auxBotones.add(btnAgregar);
		
		lista.add(auxBotones, BorderLayout.SOUTH);
		add(lista);
		
		JPanel info = new JPanel();
		info.setLayout( new GridLayout(5, 2));
		info.setBorder( new TitledBorder(" Informaci�n Proveedor "));
		
		JLabel temp = new JLabel(" Nombre: ");
		temp.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp);
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		info.add(txtNombre);
		
		JLabel temp2 = new JLabel(" C�dula: ");
		temp2.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp2);
		txtCedula = new JTextField();
		txtCedula.setEditable(false);
		info.add(txtCedula);
		
		JLabel temp3 = new JLabel(" Tel�fono: ");
		temp3.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp3);
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		info.add(txtTelefono);
				
		JLabel temp4 = new JLabel(" Origen del Caf�: ");
		temp4.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp4);
		txtOrigen = new JTextField();
		txtOrigen.setEditable(false);
		info.add(txtOrigen);
		
		JLabel temp5 = new JLabel(" Precio: ");
		temp5.setHorizontalAlignment(JLabel.CENTER);
		info.add(temp5);
		txtPrecio = new JTextField(" $ ");
		txtPrecio.setEditable(false);
		info.add(txtPrecio);	
		
		add(info);
	}

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Actualiza la lista de proveedores
     * @param proveedores La nueva lista de los proveedores
     */
    public void actualizarProveedores( ArrayList proveedores )
    {
        listaProveedores.setListData( proveedores.toArray( ) );
        if(!proveedores.isEmpty())
        {
        	listaProveedores.setSelectedIndex(0);
        	actualizarInfoProveedor();
        }
        else
        {
        	txtCedula.setText("");
        	txtOrigen.setText("");
        	txtNombre.setText("");
        	txtPrecio.setText("");
        	txtTelefono.setText("");
        }
    }
    
    /**
     * Actualiza la informaci�n del proveedor
     */
    public void actualizarInfoProveedor()
    {
    	Proveedor p = (Proveedor) listaProveedores.getSelectedValue();
    	txtCedula.setText( "" + p.darCedula() );
    	txtOrigen.setText( p.darOrigen() );
    	txtTelefono.setText( p.darTelefono() );
    	txtNombre.setText( p.darNombre() );
    	txtPrecio.setText( "$ " + formatearValorReal(p.darPrecio(), 2) );
    }
	
    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
    	String comando = e.getActionCommand();
    	if( comando.equals(AGREGAR) )
    	{
    		DialogoAgregarProveedor d = new DialogoAgregarProveedor(principal);
    		d.setVisible(true);
    	}
    }

	/**
	 * Actualiza la informaci�n del nuevo producto seleccionado
	 * @param e Es el evento de cambio el �tem seleccionado en la lista
	 */
	public void valueChanged(ListSelectionEvent e) 
	{
		if(listaProveedores.getSelectedValue() != null)
		{
			actualizarInfoProveedor();	
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
