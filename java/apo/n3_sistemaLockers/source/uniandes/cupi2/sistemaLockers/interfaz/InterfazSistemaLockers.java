/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n3_sistemaLockers
 * Autor: Catalina Rodriguez - 23-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.sistemaLockers.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.sistemaLockers.mundo.Casillero;
import uniandes.cupi2.sistemaLockers.mundo.SistemaLockers;


/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazSistemaLockers extends JFrame
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private SistemaLockers sistemaLockers;
    
    /**
     * N�mero de la locaci�n actual
     */
    private int locacionActual;

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
     * Panel con la informaci�n de la locaci�n
     */
    private PanelLocacion panelLocacion;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * M�todo constructor de la ventana. <br>
     * <b>post: </b> Se inicializan los paneles con la informaci�n del sistema 
     */
    public InterfazSistemaLockers()
    {
        // Crea la clase principal
        sistemaLockers = new SistemaLockers();
        locacionActual = 0;
        
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 640, 460 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle("Sistemas de Pr�stamo de Casilleros");
        
        //Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen();
        add( panelImagen, BorderLayout.NORTH );
        
        panelExtension = new PanelExtension( this );
        add( panelExtension, BorderLayout.SOUTH );
        
        JPanel aux = new JPanel();
        aux.setLayout( new GridLayout(2,1) );
        
        panelLocacion = new PanelLocacion(this, sistemaLockers.darLocacion(locacionActual));
        
        add(panelLocacion, BorderLayout.CENTER);
        
        //Centrar la ventana
        setLocationRelativeTo(null);        
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
   /**
    * Actualiza la interfaz con la informaci�n de la siguiente locaci�n en el sistema
    * @return Booleano que indica si la locaci�n actual es la �ltima 
    */
    public boolean siguienteLocacion()
    {
    	locacionActual++;
    	panelLocacion.actualizarLocacion(sistemaLockers.darLocacion(locacionActual));
    	
    	if(locacionActual == SistemaLockers.NUM_LOCACIONES-1 )
    		return true;
    	else
    		return false;
    			
    }
    
    /**
     * Actualiza la interfaz con la informaci�n de la locaci�n anterior en el sistema
     * @return Booleano que indica si la locaci�n actual es la primera 
     */
     public boolean locacionAnterior()
     {
     	locacionActual--;
     	panelLocacion.actualizarLocacion(sistemaLockers.darLocacion(locacionActual));
     	
     	if(locacionActual == 0 )
     		return true;
     	else
     		return false;
     			
     }
    
     /**
      * Modifica como asignado al casillero cuyo id y locaci�n entran por par�metro
      * @param locacion Locaci�n en la que se encuentra el casillero. locacion != null y locacion != ""
      * @param id Identificador del casillero a asignar. id != null y id != ""
      */
     public void asignarCasillero(String locacion, String id) 
     {
 		sistemaLockers.asignarCasillero(locacion, id);
     }
     
     /**
      * Modifica como des-asignado al casillero suyo id y locaci�n entran por par�metro
      * @param locacion Locaci�n en la que se encuentra el casillero. locacion != null y locacion != ""
      * @param id Identificador del casillero a asignar. id != null y id != ""
      */
     public void desasignarCasillero(String locacion, String id) 
     {
 		sistemaLockers.desasignarCasillero(locacion, id);
     }
     
     /**
      * Muestra el porcentaje de asignaci�n de la locaci�n que entra por par�metro.
      * @param locacion Nombre de la locaci�n. locacion != null y locacion != ""
      */
     public void darPorcentajeAsignado(String locacion)
     {
    	 double porcentaje = sistemaLockers.calcularPorcentajeAsignado(locacion);
    	 JOptionPane.showMessageDialog(this, "Se han asignado el " + porcentaje + " % de los casilleros", "Porcentaje Asigando", JOptionPane.INFORMATION_MESSAGE);
     }
     
     /**
      * Muestra el porcentaje total de asignaci�n del sistema de casilleros
      */
     public void darPorcentajeTotalAsignado()
     {
    	 double porcentaje = sistemaLockers.calcularPorcentajeTotalAsignado();
    	 JOptionPane.showMessageDialog(this, "Se han asignado el " + porcentaje + " % de los casilleros", "Porcentaje Asigando", JOptionPane.INFORMATION_MESSAGE);
     }
     
     /**
      * Muestra el tipo de casillero m�s popular en la locaci�n que entra por par�metro
      * @param locacion Nombre de la locaci�n. locacion != null y locacion != "" 
      */
     public void darTipoPopular(String locacion)
     {
    	 String tipo = sistemaLockers.consultarTipoPopular(locacion);
    	 JOptionPane.showMessageDialog(this, "El tipo de casillero " + tipo.toLowerCase() + " es el m�s popular", "Casillero Popular", JOptionPane.INFORMATION_MESSAGE);

     }
     
     /**
      * Muestra la locaci�n m�s popular del sistemas de casilleros
      */
     public void darLocacionPopular()
     {
    	 String locacion = sistemaLockers.consultarLocacionPopular();
    	 JOptionPane.showMessageDialog(this, "La locaci�n m�s popular es " + locacion, "Locaci�n Popular", JOptionPane.INFORMATION_MESSAGE);

     }
     
     /**
      * Muestra una lista con los id de los casilleros de cierto tipo de la locaci�n que entra por par�metro
      * @param locacion Nombre de la locaci�n. locacion != null y locacion != ""
      */
     public void buscarCasilleros(String locacion)
     {
    	 String[] tipos = new String[]{Casillero.TIPO_1, Casillero.TIPO_2, Casillero.TIPO_3};
    	 String tipo = (String)JOptionPane.showInputDialog(this, "Tipo de casillero: ", "Crear Casilleros", JOptionPane.QUESTION_MESSAGE, null, tipos, Casillero.TIPO_1);
    	 
    	 if(tipo!=null && !tipo.equals(""))
    	 {
        	 ArrayList respuesta = sistemaLockers.buscarCasilleros(locacion, tipo);
        	 DialogoResultadosBusqueda dia = new DialogoResultadosBusqueda(respuesta);
        	 dia.setVisible(true);
    	 }
     }
     
     /**
      * Permite crear casilleros en la locaci�n que entra por par�metro
      * @param locacion Nombre de la locaci�n. locacion != null y locacion != ""
      * @return Booleano que indica si fue posible o no crear los casilleros
      */
     public boolean crearCasilleros(String locacion)
     {
    	 String[] tipos = new String[]{Casillero.TIPO_1, Casillero.TIPO_2, Casillero.TIPO_3};
    	 String tipo = (String)JOptionPane.showInputDialog(this, "Tipo de casillero: ", "Crear Casilleros", JOptionPane.QUESTION_MESSAGE, null, tipos, Casillero.TIPO_1);
    	 
    	 if(tipo!=null && !tipo.equals(""))
    	 {
    		 String num = JOptionPane.showInputDialog(this, "Cantidad de casilleros: ", "Crear Casilleros", JOptionPane.QUESTION_MESSAGE );
        	 	
    		 if(num!=null && !num.equals(""))
    		 {
    			 try
            	 {
            		 int numero = Integer.parseInt(num);		 
            		 sistemaLockers.crearCasilleros(locacion, tipo, numero);
            		 return true;
            	 }
            	 catch (NumberFormatException e) 
            	 {
        			JOptionPane.showMessageDialog(this, "La cantidad debe ser una valor num�rico", "Error", JOptionPane.ERROR_MESSAGE);
        			return false;
            	 }  
    		 }
    	 }
    	 return false;
     }
     
     /**
      * Muestra la locaci�n con mayor n�mero de casilleros desocupados de un tipo dado
      */
     public void darLocacionConMasDesocupados() 
     {
    	 String[] tipos = new String[]{Casillero.TIPO_1, Casillero.TIPO_2, Casillero.TIPO_3};
    	 String tipo = (String)JOptionPane.showInputDialog(this, "Tipo de casillero: ", "Buscar Locaci�n Desocupada", JOptionPane.QUESTION_MESSAGE, null, tipos, Casillero.TIPO_1);
    	 
    	 if(tipo!=null && !tipo.equals(""))
    	 {	 
	    	 String locacion = sistemaLockers.darLocacionConMasCasillerosDesocupadosTipo(tipo);
	    	 JOptionPane.showMessageDialog(this, "La locaci�n con mayor n�mero de casilleros de tipo " + tipo + " es " + locacion, "Buscar Locaci�n Desocupada", JOptionPane.INFORMATION_MESSAGE);
    	 }
     }

     /**
      * Muestra en n�mero de locaciones con todo los tipos de casilleros
      */
     public void darNumLocacionesTodoTipo() 
     {
    	 int numLocaciones = sistemaLockers.darNumLocacionesTodoTipo();
    	 JOptionPane.showMessageDialog(this, numLocaciones + " locaciones tienen casilleros de todos los tipos", "Locaciones con todos los tipos", JOptionPane.INFORMATION_MESSAGE);

     }
     
    //-----------------------------------------------------------------
    // Puntos de Extensi�n
    //-----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = sistemaLockers.metodo1();
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = sistemaLockers.metodo2();
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

        InterfazSistemaLockers interfaz = new InterfazSistemaLockers();
        interfaz.setVisible( true );
    }
}