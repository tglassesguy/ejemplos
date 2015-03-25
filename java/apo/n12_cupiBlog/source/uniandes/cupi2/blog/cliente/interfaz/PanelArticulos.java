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

package uniandes.cupi2.blog.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import uniandes.cupi2.blog.comun.Articulo;

/**
 * Clase donde se muestra la lista de art�culos publicados
 */
public class PanelArticulos extends JPanel implements ListSelectionListener
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * La interfaz principal de la aplicaci�n cliente
     */
    private InterfazClienteBlog principal;

    /**
     * La lista de art�culos publicados
     */
    private JList listaArticulos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * M�todo constructor. Crea una lista donde se mostrar�n los art�culos al usuario.
     * @param interfaz La interfaz principal de la aplicaci�n. interfaz != null
     */
    public PanelArticulos( InterfazClienteBlog interfaz )
    {
        principal = interfaz;

        setLayout( new BorderLayout( ) );
        setPreferredSize( new Dimension( 200, 200 ) );

        TitledBorder borde = BorderFactory.createTitledBorder( "Lista art�culos" );
        setBorder( borde );

        listaArticulos = new JList( );
        listaArticulos.addListSelectionListener( this );

        JScrollPane scroll = new JScrollPane( listaArticulos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        add( scroll, BorderLayout.CENTER );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Limpia todos los elementos de la lista
     */
    public void limpiarLista(){
        listaArticulos.setListData( new Object[0] );
    }
    
    /**
     * Actualiza la lista de art�culos publicados.
     * @param articulos La nueva lista de art�culos. articulos != null
     */
    public void actualizarListaArticulos( ArrayList articulos )
    {
        listaArticulos.setListData( articulos.toArray( ) );
    }

    // -----------------------------------------------------------------
    // M�todos del manejo de eventos
    // -----------------------------------------------------------------

    /**
     * Evento que se lanza cuando se selecciona un nuevo elemento en la lista de art�culos.
     * @param e El evento que se lanz�. e != null
     */
    public void valueChanged( ListSelectionEvent e )
    {
        Articulo articulo = ( Articulo )listaArticulos.getSelectedValue( );
        if( articulo != null )
        {
            principal.actualizarArticulo( articulo );
        }
    }

}
