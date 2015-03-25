/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_bancoSangre
 * Autor: Catalina Rodr�guez - 11-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bancoSangre.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.bancoSangre.mundo.BancoSangre;
import uniandes.cupi2.bancoSangre.mundo.TipoSangre;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazBancoSangre extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private BancoSangre bancoSangre;

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
     * Panel con la informaci�n del tipo de sangre 1
     */
    private PanelTipoSangre panelTipo1;
    
    /**
     * Panel con la informaci�n del tipo de sangre 2
     */
    private PanelTipoSangre panelTipo2;
    
    /**
     * Panel con la informaci�n del tipo de sangre 3
     */
    private PanelTipoSangre panelTipo3;
    
    /**
     * Panel con la informaci�n del tipo de sangre 4
     */
    private PanelTipoSangre panelTipo4;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Constructor de la aplicaci�n <br>
     */
    public InterfazBancoSangre()
    {
        // Crea la clase principal
        bancoSangre = new BancoSangre();
        
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 530, 600 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Banco de Sangre CUPI2");
        
        //Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen();
        add( panelImagen, BorderLayout.NORTH );
        
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );
        
        JPanel temp = new JPanel();
        temp.setLayout(new GridLayout(2,2));
        
        panelTipo1 = new PanelTipoSangre(this, TipoSangre.TIPO_A, TipoSangre.RH_N);
        temp.add(panelTipo1);
        
        panelTipo2 = new PanelTipoSangre(this, TipoSangre.TIPO_B, TipoSangre.RH_N);
    	temp.add(panelTipo2);
    	
        panelTipo3 = new PanelTipoSangre(this, TipoSangre.TIPO_O, TipoSangre.RH_P);
    	temp.add(panelTipo3);
    	
        panelTipo4 = new PanelTipoSangre(this, TipoSangre.TIPO_O, TipoSangre.RH_N);
        temp.add(panelTipo4);
        
        add(temp, BorderLayout.CENTER);
        actualizar();
        
        //Centrar la ventana
        setLocationRelativeTo(null);
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Suministra bolsas de sangre del banco.
     * @param tipoSangre tipo de sangre que se desea suministrar. 
     * @param rh Factor rh
     */
	public void suministrar(String tipoSangre, String rh) 
	{
		String cantidadS = JOptionPane.showInputDialog(this, "N�mero de bolsas a suministrar: ", "Suministrar Sangre", JOptionPane.QUESTION_MESSAGE);
		
		if(cantidadS != null)
		{
			try
			{
				int cantidad = Integer.parseInt(cantidadS);
				if(cantidad > 0)
				{
					String respuesta = bancoSangre.suministrarSangre(tipoSangre, rh, cantidad);
					actualizar();
					JOptionPane.showMessageDialog(this, respuesta, "Suministrar Sangre", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "La cantidad de bolsas debe ser mayor a cero", "Error", JOptionPane.ERROR_MESSAGE);
				}		
			}
			catch (NumberFormatException e) 
			{
				JOptionPane.showMessageDialog(this, "Ingrese valores num�ricos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Registra la donaci�n de una bolsa de sangre
	 * @param edad Edad del donador
	 * @param sexo Sexo del donador
	 * @param peso Peso del donador
	 * @param tipoSangre Tipo de sangre del donador
	 * @param rh Factor RH del donador
	 * @param padeceEnfermedad Indica si el donador padece o no de enfermedades
	 */
	public void registrarDonacion(int edad, String sexo, double peso, String tipoSangre, String rh, boolean padeceEnfermedad)
	{
		String respuesta = bancoSangre.registarDonacion(edad, sexo, peso, tipoSangre, rh, padeceEnfermedad);
		actualizar();
		JOptionPane.showMessageDialog(this, respuesta, "Donaci�n", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Consulta el tipo de sangre con mayor disponibilidad
	 */
	public void obtenerTipoMayorDisponibilidad()
	{
		String respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
		JOptionPane.showMessageDialog(this, respuesta, "Resultado", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Consulta el tipo de sangre con menor disponibilidad
	 */
	public void obtenerTipoMenorDisponibilidad()
	{
		String respuesta = bancoSangre.obtenerTipoSangreMenosDisponible();
		JOptionPane.showMessageDialog(this, respuesta, "Resultado", JOptionPane.INFORMATION_MESSAGE);

	}
	
	/**
	 * Consulta el tipo de sangre con mayor n�mero de bolsas suministradas
	 */
	public void obtenerTipoMasSuministrado()
	{
		String respuesta = bancoSangre.obtenerTipoSangreMasSuministrado();
		JOptionPane.showMessageDialog(this, respuesta, "Resultado", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Consulta si el donador padece o no enfermedades que impidan la donaci�n
     * @param enferm1 Indica si el donador padece o no de la enfermedad Fiebre reum�tica 
     * @param enferm2 Indica si el donador padece o no de la enfermedad Epilepsia 
     * @param enferm3 Indica si el donador padece o no de la enfermedad Hepatitis 
     * @param enferm4 Indica si el donador padece o no de la enfermedad Ictericia 
     * @param enferm5 Indica si el donador padece o no de la enfermedad S�filis u otras enfermedades ven�reas
     * @param enferm6 Indica si el donador padece o no de la enfermedad C�ncer 
     * @param enferm7 Indica si el donador padece o no de la enfermedad Problemas card�acos
     * @return Booleano indicando si el donador padece o no enfermedades
	 */
	public boolean donadorPadeceEnfermedad(boolean enferm1, boolean enferm2, boolean enferm3, boolean enferm4, boolean enferm5, boolean enferm6, boolean enferm7)
	{
		return bancoSangre.donadorPadeceEnfermedad(enferm1, enferm2, enferm3, enferm4, enferm5, enferm6, enferm7);
	}
	
	/**
	 * Actualiza los campos en la interfaz con la informaci�n de los tipos de sangre
	 */
	public void actualizar()
	{
		panelTipo1.actualizar(bancoSangre.darTipo1());
		panelTipo2.actualizar(bancoSangre.darTipo2());
		panelTipo3.actualizar(bancoSangre.darTipo3());
		panelTipo4.actualizar(bancoSangre.darTipo4());
	}
    
    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = bancoSangre.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = bancoSangre.metodo2();
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

        InterfazBancoSangre interfaz = new InterfazBancoSangre();
        interfaz.setVisible( true );
    }
}