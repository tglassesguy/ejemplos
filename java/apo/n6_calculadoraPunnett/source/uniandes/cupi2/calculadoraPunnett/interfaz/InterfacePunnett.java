package uniandes.cupi2.calculadoraPunnett.interfaz;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.calculadoraPunnett.mundo.Calculadora;

public class InterfacePunnett extends JFrame {
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
	private Calculadora calculadora;
	//-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	private PanelImagen panelImagen;
	private PanelCalculadora panelCalculadora;
	private PanelOpciones panelOpciones;
	//-----------------------------------------------------------------
    // Constructor
    //-----------------------------------------------------------------
	public InterfacePunnett() {
		// Configura la ventana
		setTitle("Calculadora de Punnett");
		setSize(530,600);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		// Carga el archivo de propiedades
		cargarPropiedades();		
		
		// Genera los p�neles
		panelImagen = new PanelImagen();
		add(panelImagen, BorderLayout.NORTH);
		panelCalculadora = new PanelCalculadora(this);
		add(panelCalculadora, BorderLayout.CENTER);
		panelOpciones = new PanelOpciones(this);
		add(panelOpciones, BorderLayout.SOUTH);
	}
	//-----------------------------------------------------------------
    // Requerimientos Funcionales
    //-----------------------------------------------------------------
	/**
	 * M�todo que crea el dialogo para cargar el archivo de propiedades e inicializar el mundo del problema
	 */
	public void cargarPropiedades() {
		JFileChooser fc = new JFileChooser( "./data" );
        fc.setDialogTitle( "Cargar propiedades calculadora" );
        int resultado = fc.showOpenDialog( this );
        if( resultado == JFileChooser.APPROVE_OPTION ) {
            File propiedadesCalculadora = fc.getSelectedFile( );
            try {
            	calculadora = new Calculadora(propiedadesCalculadora);
            } catch(Exception e) {
            	JOptionPane.showMessageDialog( this, "No fue posible cargar las propiedades de la calculadora: \n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            	e.printStackTrace( );
            }
        } else {
        	JOptionPane.showMessageDialog( this, "No ha seleccionado ning�n archivo de propiedades \n El programa terminar� ahora", "Calculadora de Punnett", JOptionPane.ERROR_MESSAGE );
        	dispose();
        	System.exit(0);
        }
	}
	/**
	 * M�todo que genera el Cuadro de Punnett con los genotipos de los padres seleccionados.
	 */
	public void generarPunnett(String genotipoPadre, String genotipoMadre) throws Exception {
		String[] genotiposPadres = {genotipoPadre, genotipoMadre};
		// Generando el Cuadro de Punnett en el mundo del problema
		calculadora.generarPunnett(genotiposPadres);
		// Recuperando las combinaciones posibles de los genes de los padres
		String[][] combinacionesPadres = calculadora.darCombinacionesPosiblesPadres();
		panelCalculadora.actualizarCombinacionesPadres(combinacionesPadres[0], combinacionesPadres[1]);
		// Recuperando los genotipos posibles para el hijo
		String[][] genotiposHijo = calculadora.darGenotiposPosiblesHijo();
		panelCalculadora.actualizarGenotiposHijo(genotiposHijo);
		// Habilitando los botones
		panelCalculadora.habilitarOpciones(true);
		panelOpciones.habilitarOpciones(true);
	}
	/**
	 * M�todo que limpia el cuadro de punnett
	 */
	public void limpiar() {
		panelCalculadora.limpiarCuadro();
		panelCalculadora.habilitarOpciones(false);
		panelOpciones.habilitarOpciones(false);
	}
	/**
	 * M�todo que solicita al mundo los fenotipos para calcular las probabilidades
	 */
	public String[][] solicitarFenotipos() {
		return calculadora.darFenotipos();
	}
	/**
	 * M�todo que solicita al mundo calcular la probabilidad de un genotipo espec�fico.
	 */
	public void calcularProbabilidades(String[] fenotipos) {
		float probabilidad = calculadora.calcularProbabilidad(fenotipos);
		probabilidad = 100*probabilidad;
		String strFenotipos = "";
		for (int i = 0; i < fenotipos.length; i++) strFenotipos += fenotipos[i]+" ";
		JOptionPane.showMessageDialog( this, strFenotipos +"= "+probabilidad+"%", "Calcular Probabilidad", JOptionPane.INFORMATION_MESSAGE);
	}
	/**
	 * M�todo que solicita al mundo el n�mero de combinaciones posibles para un gentipo.
	 */
	public int solicitarCombinacionesPosibles() {
		return calculadora.darCombinacionesPosibles();
	}
	/**
	 * M�todo que retorna un arreglo de Strings con los genotipos posibles para los padres
	 */
	public String[] genotiposPadres() {
		return calculadora.darGenotiposPosibles();
	}
	/**
	 * M�todo para la opci�n 1
	 */
	public float opcion1(String genP) {
		float frecuenciaGen = 0;
		try {
			frecuenciaGen = calculadora.metodo1(genP);
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, e.getMessage(), "Frecuencia Gen", JOptionPane.ERROR_MESSAGE );
			e.printStackTrace();
		}
		return frecuenciaGen;
	}
	/**
	 * M�todo para la opci�n 2
	 */
	public void opcion2() {
		String mensaje = calculadora.metodo2();
		JOptionPane.showMessageDialog(this, mensaje);
	}

	//-----------------------------------------------------------------
    // Programa Principal
    //-----------------------------------------------------------------
	public static void main(String[] args) {
		try {
			InterfacePunnett interfaz = new InterfacePunnett();
			interfaz.setVisible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
