package uniandes.cupi2.procesadoraCafe.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Dialogo que permite agregar un nuevo proveedor
 */
public class DialogoAgregarProveedor extends JDialog implements ActionListener
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Comando Agregar proveedor
	 */
	private static final String AGREGAR = "Agregar";
	
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Ventana principal de la aplicaci�n
	 */
	private InterfazProcesadoraCafe principal;
	
	//-----------------------------------------------------------------
    // Atributos de la Interfaz
    //-----------------------------------------------------------------

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
	 * Combo box con los departamentos de origen del caf�
	 */
	private JComboBox comboOrigen;
	
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
     * Constructor del dialogo
     * @param ventana Ventana principal
     */
	public DialogoAgregarProveedor(InterfazProcesadoraCafe ventana)
	{
		principal = ventana;
		
		setSize(250, 220);
		setLayout(new GridLayout(6, 2));
		setTitle("Agregar Proveedor");
		setModal(true);
		setLocationRelativeTo(principal);
		
		JLabel temp = new JLabel(" Nombre: ");
		add(temp);
		txtNombre = new JTextField();
		add(txtNombre);
		
		JLabel temp2 = new JLabel(" C�dula: ");
		add(temp2);
		txtCedula = new JTextField();
		add(txtCedula);
		
		JLabel temp3 = new JLabel(" Tel�fono: ");
		add(temp3);
		txtTelefono = new JTextField();
		add(txtTelefono);
		
		JLabel temp4 = new JLabel(" Origen del Caf�: ");
		add(temp4);
		String[] origen = new String[]{"Antioquia", "Caldas", "Cundinamarca", "Huila", "Nari�o", "Norte de Santander", "Quindio", "Risaralda", "Tolima", "Valle del Cauca"};
		comboOrigen = new JComboBox(origen);
		comboOrigen.setSelectedIndex(0);
		add(comboOrigen);
		
		JLabel temp5 = new JLabel(" Precio: ");
		add(temp5);
		txtPrecio = new JTextField();
		add(txtPrecio);
					
		add(new JLabel());
		btnAgregar = new JButton(AGREGAR);
		btnAgregar.setActionCommand(AGREGAR);
		btnAgregar.addActionListener(this);
		add(btnAgregar);
	}
	
	//-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed( ActionEvent e )
    {
    	String comando = e.getActionCommand();
    	if( comando.equals(AGREGAR) )
    	{
    		try
    		{
	    		String nombre = txtNombre.getText();
	    		if(nombre != null && !nombre.equals(""))
	    		{
	    			String cedulaS = txtCedula.getText();
	    			if(cedulaS != null && !cedulaS.equals(""))
	    			{
	    				int cedula = Integer.parseInt(cedulaS);
	    				String telefono = txtTelefono.getText();
	    					
	    				if(telefono != null && !telefono.equals(""))
	    				{
	    					String origenCafe = (String) comboOrigen.getSelectedItem();
	    					String precioS = txtPrecio.getText();
	    					double precioKilo = Double.parseDouble(precioS);
	    					
	    					if(precioKilo > 0)
	    					{
	    						principal.agregarProveedor(nombre, cedula, telefono, origenCafe, precioKilo);
	    						dispose();	    						
	    					}
	    					else
	    					{
	    						JOptionPane.showMessageDialog(this, "El precio debe ser mayor a cero", "Agregar Proveedor", JOptionPane.ERROR_MESSAGE);
	    					}
	    				}
	    				else
	    				{
	    					JOptionPane.showMessageDialog(this, "Ingrese el tel�fono del proveedor", "Agregar Proveedor", JOptionPane.ERROR_MESSAGE);
	    				}
	    			}
	    			else
	    			{
	    				JOptionPane.showMessageDialog(this, "Ingrese la c�dula del proveedor", "Agregar Proveedor", JOptionPane.ERROR_MESSAGE);    					
	    			}
	    		}
	    		else
	    		{
	    			JOptionPane.showMessageDialog(this, "Ingrese el nombre del proveedor", "Agregar Proveedor", JOptionPane.ERROR_MESSAGE);
	    		} 
    		}
    		catch (NumberFormatException ne) 
    		{
    			JOptionPane.showMessageDialog(this, "El precio y la c�dula deben ser un valores num�ricos", "Agregar Proveedor", JOptionPane.ERROR_MESSAGE);
			}    		
    	}
    }
}