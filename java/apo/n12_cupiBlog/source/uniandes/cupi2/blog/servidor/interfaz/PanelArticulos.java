/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiBlog
 * Autor: Luis Ricardo Ruiz Rodr�guez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.blog.servidor.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 * Clase donde se muestra la lista de art�culos de un usuario seleccionado en el panel de usuarios conectados
 */
public class PanelArticulos extends JPanel
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La lista de art�culos del usuario
     */
    private JList listaArticulos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * M�todo constructor. Crea una lista donde se mostrar�n los art�culos del usuario.
     */
    public PanelArticulos( )
    {
        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension( 250, 200 ) );
        
        TitledBorder borde = BorderFactory.createTitledBorder( "Lista art�culos" );
        setBorder( borde );
        
        listaArticulos = new JList();
        
        JScrollPane scroll = new JScrollPane( listaArticulos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        add( scroll, BorderLayout.CENTER );
    }
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Actualiza la lista de art�culos de un usuario.
     * @param articulos La lista de art�culos del usuario seleccionado. articulos != null
     */
    public void actualizarListaArticulos(ArrayList articulos){
        listaArticulos.setListData( articulos.toArray( ) );
    }
}
