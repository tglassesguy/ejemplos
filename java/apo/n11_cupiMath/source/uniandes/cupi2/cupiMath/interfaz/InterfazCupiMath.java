/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n11_cupiMath
 * Autor: cupi2 - 15-mar-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiMath.interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import uniandes.cupi2.cupiMath.mundo.CupiMath;

/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazCupiMath extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private CupiMath cupiMath;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con las extensiones
     */
    private PanelExtension panelExtension;

    /**
     * Panel con la imagen del encabezado
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los n�meros de la calculadora
     */
    private PanelDigitosCalculadora panelNumeros;

    /**
     * Panel con los operadores de la calculadora
     */
    private PanelOperadores panelOperadores;

    /**
     * Panel con la expresi�n aritm�tica
     */
    private PanelExpresionAritmetica panelExpresion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye la ventana principal de la aplicaci�n
     */
    public InterfazCupiMath( )
    {
        // Crea la clase principal
        cupiMath = new CupiMath( );
        // Construye la forma
        setLayout( new BorderLayout( ) );
        setSize( 540, 530 );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setTitle( "Calculadora Expresiones Aritm�ticas" );
        setResizable( false );

        // Creaci�n de los paneles aqu�
        panelImagen = new PanelImagen( );
        panelExtension = new PanelExtension( this );
        panelExpresion = new PanelExpresionAritmetica( );

        // Panel que contiene al panel con el banner
        //y al banner con la expresi�n aritm�tica
        JPanel panelTemp = new JPanel( );
        panelTemp.setLayout( new BorderLayout( ) );
        panelTemp.add( panelImagen, BorderLayout.NORTH );
        panelTemp.add( panelExpresion, BorderLayout.CENTER );

        add( panelTemp, BorderLayout.NORTH );

        panelNumeros = new PanelDigitosCalculadora( this );
        add( panelNumeros, BorderLayout.CENTER );

        panelOperadores = new PanelOperadores( this );
        
        //Panel que contiene a los panales de los operadores y de las extensiones
        JPanel panelTemp2 = new JPanel( );
        panelTemp2.setLayout( new BorderLayout( ) );
        panelTemp2.add( panelOperadores, BorderLayout.NORTH );
        panelTemp2.add( panelExtension, BorderLayout.CENTER );
        add( panelTemp2, BorderLayout.SOUTH );
        // Centrar la ventana
        setLocationRelativeTo( null );

    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Verifica que s� hay par�ntesis en la expresi�n, �nicamente est�n anidados, valida que no est�n al mismo nivel.
     * Invoca al m�todo principal del mundo para que evalu� la expresi�n.
     */
    public void evaluarExpresionAritmetica( )
    {
        try
        {
            String expresion = panelExpresion.darExpresionAritmetica( );
            // Validar que no hayan parentesis que no est�n anidados
            ArrayList posicionesParentesisCerrar = new ArrayList( );
            for( int i = 0; i < expresion.length( ); i++ )
            {
                if( expresion.charAt( i ) == ')' )
                    posicionesParentesisCerrar.add( new Integer( i ) );
            }

            for( int i = 0; i < expresion.length( ); i++ )
            {
                if( expresion.charAt( i ) == '(' )
                {

                    for( int j = 0; j < posicionesParentesisCerrar.size( ); j++ )
                    {
                        if( new Integer( i ) > ( Integer )posicionesParentesisCerrar.get( j ) )
                        {
                            JOptionPane.showMessageDialog( this, "No deben haber parentesis que no est�n anidados", "ERROR", JOptionPane.INFORMATION_MESSAGE );
                            return;
                        }
                    }
                }
            }
            
            double valorExpresion = 0;
            cupiMath.construirArbol( expresion );
            valorExpresion = cupiMath.evaluarExpresion( expresion );
            JOptionPane.showMessageDialog( this, "Resultado evaluaci�n: " + panelExpresion.darExpresionAritmetica( ) + " = " + valorExpresion, "RESPUESTA", JOptionPane.INFORMATION_MESSAGE );

        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Presenta un mensaje al usuario con la expresi�n aritm�tica expresada en notaci�n polaca.
     */
    public void convertirEnNotacionPolaca( )
    {
        String notacionPolaca;
        try
        {
            notacionPolaca = cupiMath.convertirEnNotacionPolaca(panelExpresion.darExpresionAritmetica( ) );
            JOptionPane.showMessageDialog( this, notacionPolaca, "NOTACI�N POLACA", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "ERROR", JOptionPane.ERROR_MESSAGE );
        }
        

    }

    /**
     * Actualiza el panel de la expresi�n aritm�tica, visualizando al comando que oprime el usuario.
     * @param item El �tem que agrega el usuario a la expresi�n. item != null
     */
    public void actualizarExpresionAritmetica( String item )
    {
        panelExpresion.actualizarExpresionAritmetica( item );
    }

    /**
     * Borra el �ltimo �tem de la expresi�n aritm�tica del panel expresi�n
     */
    public void borrarItemExpresionAritmetica( )
    {
        panelExpresion.borrarItem( );
    }

    /**
     * Borra toda la expresi�n aritm�tica del panel expresi�n
     */
    public void borrarExpresionAritmetica( )
    {
        panelExpresion.borrarExpresion( );

    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
    	try {
    		String expresion = panelExpresion.darExpresionAritmetica( );
			cupiMath.construirArbol( expresion );
			String tempNumero = JOptionPane.showInputDialog(this, "Ingrese el n�mero a buscar");
	    	double numero = Double.parseDouble(tempNumero);
	        String resultado = cupiMath.buscarNumero(numero);
	        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
		}
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
    	try {
    		String expresion = panelExpresion.darExpresionAritmetica( );
			cupiMath.construirArbol( expresion );
			String tempNumero = JOptionPane.showInputDialog(this, "Ingrese el n�mero a buscar");
	    	double numero = Double.parseDouble(tempNumero);
	        String resultado = cupiMath.elementosRama(numero);
	        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception e) {
			JOptionPane.showMessageDialog( this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE );
		}
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args Los argumentos de ejecuci�n de la aplicaci�n. args != null
     */
    public static void main( String[] args )
    {
        InterfazCupiMath interfaz = new InterfazCupiMath( );
        interfaz.setVisible( true );
    }

}