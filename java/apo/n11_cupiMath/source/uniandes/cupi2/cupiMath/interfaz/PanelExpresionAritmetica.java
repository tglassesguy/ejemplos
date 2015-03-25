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

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;


/**
 * Panel para mostrar la expresi�n aritm�tica ingresada por el usuario
 */
public class PanelExpresionAritmetica extends JPanel
{

    //-----------------------------------------------------------------
    // Atributos de la interfaz
    //-----------------------------------------------------------------
	
    /**
     * �rea de texto que contiene la expresi�n aritm�tica
     */
    private JTextArea txtAreaExpresion;
    
    /**
     * Panel con scroll para agregar el �rea de texto de la expresi�n aritm�tica
     */
    private JScrollPane scrollExpresion;
    
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Construye un nuevo panel para mostrar una expresi�n aritm�tica 
     */
    public PanelExpresionAritmetica()
    {
        setBorder( new TitledBorder( "Expresi�n Aritm�tica" ) );
        txtAreaExpresion=new JTextArea( );
        txtAreaExpresion.setFont( new Font(Font.SANS_SERIF, Font.BOLD, 25 ) );
        txtAreaExpresion.setEditable( false );
        scrollExpresion=new JScrollPane(txtAreaExpresion );
        scrollExpresion.setPreferredSize( new Dimension(510,50) );
        add(scrollExpresion);
    }
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    
    /**
     * Actualiza una expresi�n aritm�tica con el �tem que llega por par�metro
     * @param item El nuevo �tem para agregar a la expresi�n aritm�tica. item != null
     */
    public void actualizarExpresionAritmetica(String item)
    {
    	txtAreaExpresion.setText(txtAreaExpresion.getText()+item);
    }

    /**
     * Borra el �ltimo �tem de la expresi�n aritm�tica
     */
	public void borrarItem()
	{
		String expr=txtAreaExpresion.getText();
		if(expr!=null&&expr.length()>0)
		{
			String subExpr=expr.substring(0, expr.length()-1);
			txtAreaExpresion.setText(subExpr);
		}
		
		
	}
	
	/**
	 * Retorna el valor de la expresi�n aritm�tica
	 * @return EL valor de la expresi�n aritm�tica
	 */
	public String darExpresionAritmetica()
	{
	    String expresion=txtAreaExpresion.getText( );
	    return expresion;
	}

	
    /**
     * Borra toda la expresi�n aritm�tica del �rea de texto
     */
    public void borrarExpresion( )
    {
       txtAreaExpresion.setText( "" ); 
    }
    
    
}
