package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Panel con los detalles de una suscripci�n
 *
 */
public class PanelSuscripcion extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	
	/**
	 * Constante asociada al bot�n encargado de pasar a la suscripci�n siguiente
	 */
	private final static String SUSCRIPCION_SIGUIENTE=">>";
	
	/**
	 * Constante asociada al bot�n encargado de pasar a la suscripci�n anterior
	 */
	private final static String SUSCRIPCION_ANTERIOR="<<";
	
	 /**
     * Constante asociada al bot�n encargado de crear una nueva suscripci�n
     */
    private final static String NUEVA_SUSCRIPCION="Nueva suscripci�n";
	
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	
    /**
     * El id de la suscripci�n que se muestra en el panel
     */
    private String idSuscripcionActual;
    
	/**
	 * La ventana principal de la aplicaci�n
	 */
	private InterfazBodyCupi2 interfaz;
	
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    /**
     * Bot�n para pasar a la suscripci�n anterior
     */
    private JButton btnAnterior;
    
    /**
     * Bot�n para pasar a la suscripci�n siguiente
     */
    private JButton btnSiguiente;
    
    /**
     * Bot�n para crear una nueva suscripci�n
     */
    private JButton btnNuevaSuscripcion;
	
    /**
     * Etiqueta tipo de suscripci�n
     */
    private JLabel lblTipoSuscripcion;
    
    /**
     * Campo de texto tipo de suscripci�n
     */
    private JTextField txtTipoSuscripcion;
    
    /**
     * Etiqueta con la fecha de apertura
     */
    private JTextField txtFechaApertura;
    
    /**
     * La fecha de inicio de la suscripci�n
     */
    private JLabel lblFechaInicio;
    
    /**
     * Etiqueta estado de la suscripci�n
     */
    private JLabel lblEstado;
    
    /**
     * Campo de texto estado de la suscripci�n 
     */
    private JTextField txtEstado;
    
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
   
	/**
	 * Construye un nuevo panel con los detalles de una suscripci�n
	 * Inicializa idSuscripcionActual=""
	 * @param ventanaPrincipal La ventana principal de la aplicaci�n
	 */
	public PanelSuscripcion(InterfazBodyCupi2 ventanaPrincipal)
	{
		interfaz=ventanaPrincipal;
		idSuscripcionActual="";
		setBorder(new TitledBorder("Detalles Suscripci�n"));
		btnAnterior=new JButton(SUSCRIPCION_ANTERIOR);
        btnAnterior.addActionListener(this);
        btnAnterior.setActionCommand(SUSCRIPCION_ANTERIOR);
        btnSiguiente=new JButton(SUSCRIPCION_SIGUIENTE);
        btnSiguiente.addActionListener(this);
        btnSiguiente.setActionCommand(SUSCRIPCION_SIGUIENTE);
        btnNuevaSuscripcion= new JButton( NUEVA_SUSCRIPCION );
        btnNuevaSuscripcion.addActionListener( this );
        btnNuevaSuscripcion.setActionCommand( NUEVA_SUSCRIPCION );
        lblTipoSuscripcion=new JLabel("Tipo de suscripci�n:");
        txtTipoSuscripcion=new JTextField();
        txtTipoSuscripcion.setEditable(false);
        txtFechaApertura=new JTextField();
        txtFechaApertura.setEditable(false);
        lblFechaInicio=new JLabel("Fecha de apertura");
        lblTipoSuscripcion=new JLabel("Tipo de suscripci�n:");
        lblEstado=new JLabel("Estado:");
        txtEstado=new JTextField( );
        txtEstado.setEditable( false );
        
        setLayout(new GridLayout(5,2));
        add(lblTipoSuscripcion);
        add(txtTipoSuscripcion);
        add(lblFechaInicio);
        add(txtFechaApertura);
        add(lblEstado);
        add(txtEstado);
        add(btnAnterior);
        add(btnSiguiente);
        add( btnNuevaSuscripcion );
        
        
	}
    
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------
	
	
	/**
	 * Actualiza el panel con los datos de una suscripci�n
	 * @param elIdSuscripcionActual El id de la suscripci�n de la suscripci�n
	 * @param tipoSuscripcion El tipo de la suscripci�n
	 * @param fechaInicio La fecha de inicio de la suscripci�n
	 * @param estado El estado de la suscripci�n
	 */
	public void actualizar(String elIdSuscripcionActual,String tipoSuscripcion, String fechaInicio, String estado)
	{
	    idSuscripcionActual=elIdSuscripcionActual;
	    txtFechaApertura.setText(fechaInicio);
	    txtTipoSuscripcion.setText( tipoSuscripcion );
	    txtEstado.setText( estado );
	}
	
	/**
	 * Retorna el id de la suscripci�n que se est� mostrando
	 * @return El id de la suscripci�n que se est� mostrando
	 */
	public String darIdSuscripcionActual()
	{
	    return idSuscripcionActual;
	}
	
	/**
	 * Atiende al evento disparado por el usuario
	 * @param e El evento disparado por el usuario
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(SUSCRIPCION_SIGUIENTE))
		{
			try
            {
			    
                interfaz.actualizarSuscripcionSiguienteDelUsuarioActual( interfaz.darIdUsuario( ) );
            }
            catch( Exception e1 )
            {
                
                JOptionPane.showMessageDialog( this, "El ID del usuario deber ser num�rico", "Advertencia", JOptionPane.INFORMATION_MESSAGE );
            }
		}
		else if(e.getActionCommand().equals(SUSCRIPCION_ANTERIOR))
		{
			try
            {
                interfaz.actualizarSuscripcionAnteriorDelUsuarioActual( interfaz.darIdUsuario( ) );
            }
            catch( Exception e1 )
            {
                JOptionPane.showMessageDialog( this, "El ID del usuario deber ser num�rico", "Advertencia", JOptionPane.INFORMATION_MESSAGE );
            }
		}
		else if(e.getActionCommand( ).equals( NUEVA_SUSCRIPCION ))
		{
		    interfaz.crearDialogoNuevaSuscripcion();
		}
		
	}

}
