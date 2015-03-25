package uniandes.cupi2.bodyCupi2.interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel con el menu principal de la aplicaci�n
 */
public class PanelMenu extends JPanel implements ActionListener
{

    //-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------
	
    /**
     * Comando crear usuario
     */
    private static final String CREAR_USUARIO="Crear usuario";
    
    /**
     * Comando importar usuarios
     */
    private static final String IMPORTAR_USUARIOS="Importar usuarios";
    
    /**
     * Comando exportar usuarios
     */
    private static final String EXPORTAR_USUARIOS="exportar usuarios";
    
    /**
     * Comando para exportar los registros de tiempo de un usuario
     */
    private static final String REGISTRO_TIEMPOS="registro tiempos";
    
    /**
     * Comando para exportar una lista de usuarios con suscripciones pr�ximas a vencer
     */
    private static final String SUSCRIPCIONES_PROXIMAS_A_VENCER="Suscripciones cercanas a vencer";
    
    /**
     * Comando para exportar una lista de usuarios con suscripciones vencidas
     */
    private static final String SUSCRIPCIONES_VENCIDAS="Suscripciones vencidas";
	
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	
    /**
     * La ventana principal de la aplicaci�n
     */
    private InterfazBodyCupi2 interfaz;
    
    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	
	/**
     * Bot�n crear usuario
     */
    private JButton btnCrearUsuario;
    
    /**
     * Bot�n importar usuarios
     */
    private JButton btnImportarUsuarios;
    
    /**
     * Bot�n exportar usuarios
     */
    private JButton btnExportarUsuarios;
    
    /**
     * Bot�n reporte registro tiempos
     */
    private JButton btnReporteRegistroTiempos;
    
    /**
     * Bot�n reporte suscripciones vencidas
     */
    private JButton btnReporteSuscripcionesVencidas;
	
    //-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	
    
    /**
     * Construye un nuevo panel menu
     * @param ventanaPrincipal La ventana principal de la aplicaci�n
     */
    public PanelMenu(InterfazBodyCupi2 ventanaPrincipal)
    {
    	interfaz=ventanaPrincipal;
    	setBorder(new TitledBorder( "Men�" ));
    	btnCrearUsuario=new JButton(new ImageIcon( "data/imagenes/newUser.png" ));
    	btnCrearUsuario.setActionCommand(CREAR_USUARIO);
    	btnCrearUsuario.setToolTipText( CREAR_USUARIO );
    	btnCrearUsuario.addActionListener(this);
    	
    	btnImportarUsuarios=new JButton(new ImageIcon( "data/imagenes/importUsers.png" ));
    	btnImportarUsuarios.setActionCommand(IMPORTAR_USUARIOS);
    	btnImportarUsuarios.addActionListener(this);
    	btnImportarUsuarios.setToolTipText( IMPORTAR_USUARIOS );
    	
    	btnExportarUsuarios=new JButton(new ImageIcon( "data/imagenes/exportUsers.png" ));
    	btnExportarUsuarios.setActionCommand(EXPORTAR_USUARIOS);
    	btnExportarUsuarios.addActionListener(this);
    	btnExportarUsuarios.setToolTipText( EXPORTAR_USUARIOS );
    	
    	btnReporteRegistroTiempos= new JButton(new ImageIcon( "data/imagenes/timeRegistry.png" ));
    	btnReporteRegistroTiempos.setActionCommand(REGISTRO_TIEMPOS);
    	btnReporteRegistroTiempos.addActionListener(this);
    	btnReporteRegistroTiempos.setToolTipText( REGISTRO_TIEMPOS );
    	
    	btnReporteSuscripcionesVencidas= new JButton( new ImageIcon( "data/imagenes/vencida.png" ));
    	btnReporteSuscripcionesVencidas.setActionCommand(SUSCRIPCIONES_VENCIDAS);
    	btnReporteSuscripcionesVencidas.addActionListener(this);
    	btnReporteSuscripcionesVencidas.setToolTipText( SUSCRIPCIONES_VENCIDAS );
    	
    	
    	setLayout(new GridLayout(5,1));
    	
    	add(btnCrearUsuario);
    	add(btnImportarUsuarios);
    	add(btnExportarUsuarios);
    	add(btnReporteRegistroTiempos);
    	add(btnReporteSuscripcionesVencidas);
    	
    	
    }

	
	
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Manejo de los eventos de los botones
     * @param e Acci�n que gener� el evento.
     */
    public void actionPerformed(ActionEvent e)
    {
		if(e.getActionCommand().equals(CREAR_USUARIO))
		{
			interfaz.crearUsuario();
		}
		else if(e.getActionCommand().equals(IMPORTAR_USUARIOS))
		{
		    
		    JFileChooser chooser = new JFileChooser( "./data" );
            int returnVal = chooser.showOpenDialog( interfaz );
            if( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String pathArchivo = chooser.getSelectedFile( ).getParent( );
                String nombreArchivo = chooser.getSelectedFile( ).getName( );
                interfaz.cargarUsuarios( pathArchivo + "/" + nombreArchivo );
            }
			
		}
		else if(e.getActionCommand().equals(EXPORTAR_USUARIOS))
		{
		    JFileChooser chooser = new JFileChooser( "./data" );
            int returnVal = chooser.showSaveDialog( interfaz );
            if( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String pathArchivo = chooser.getSelectedFile( ).getParent( );
                String nombreArchivo = chooser.getSelectedFile( ).getName( );
                interfaz.exportarUsuarios( pathArchivo, nombreArchivo );
                
            }
			
		}
		else if(e.getActionCommand().equals(REGISTRO_TIEMPOS))
		{
		    String idUsuario=JOptionPane.showInputDialog( "ID del usuario" );
		    int elIdUsuario=-1;
		    if(idUsuario!=null)
		    {
		        try
		        {
		            elIdUsuario=Integer.parseInt( idUsuario );
		        }
		        catch (Exception ex)
		        {
		            JOptionPane.showMessageDialog( this, "Debe ingresar un valor num�rico", "Atenci�n", JOptionPane.INFORMATION_MESSAGE );
	                return;
		        }
		        
		    }
		        
		    else
		    {
		        JOptionPane.showMessageDialog( this, "Debe ingresar un valor num�rico", "Atenci�n", JOptionPane.INFORMATION_MESSAGE );
		        return;
		    }
		    JFileChooser chooser = new JFileChooser( "./data" );
            int returnVal = chooser.showSaveDialog( interfaz );
            if( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String pathArchivo = chooser.getSelectedFile( ).getParent( );
                String nombreArchivo = chooser.getSelectedFile( ).getName( );
                interfaz.exportarRegistrosTiempoUsuario( elIdUsuario,pathArchivo, nombreArchivo );
                
            }
		}
		else if(e.getActionCommand().equals(SUSCRIPCIONES_VENCIDAS))
		{
		    JFileChooser chooser = new JFileChooser( "./data" );
            int returnVal = chooser.showSaveDialog( interfaz );
            if( returnVal == JFileChooser.APPROVE_OPTION )
            {
                String pathArchivo = chooser.getSelectedFile( ).getParent( );
                String nombreArchivo = chooser.getSelectedFile( ).getName( );
                interfaz.exportarUsuariosConSuscripcionesVencidas( pathArchivo, nombreArchivo );
                
            }
		}
	
	}
	
}
