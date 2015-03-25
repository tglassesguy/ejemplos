/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n9_toDoList
 * Autor: Carlos Navarrete Puentes - 24-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.toDoList.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.JDialog;

/**
 * Di�logo que se usa para editar la informaci�n de una tarea
 */
public class DialogoEditarTarea extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazToDoList principal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Panel que contiene los campos de la tarea que se quiere editar
     */
    private PanelDatosEditarTarea panel;

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo di�logo para editar la tarea dada
     * 
     * @param ventana Ventana principal de la aplicaci�n
     */
    public DialogoEditarTarea( InterfazToDoList ventana )
    {
        principal = ventana;
        setLayout( new GridBagLayout( ) );
        setTitle( "Editar Tarea" );
        setSize( 250, 350 );
        int gridx = 0;
        int gridy = 0;
        GridBagConstraints gbc = new GridBagConstraints( gridx, gridy, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );

        panel = new PanelDatosEditarTarea( this );
        add( panel, gbc );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * M�todo que se llama cuando se quiere editar una tarea
     */
    public void editarTarea( )
    {
        principal.editarTarea( this, panel.darNombre( ), panel.darDescripcion( ), panel.darCalendarioFechaInicio( ), panel.darCalendarioFechaFin( ) );
    }

    /**
     * M�todo que se llama para actualizar la informaci�n de una tarea en el panel
     * 
     * @param nombre Nombre de la tarea. nombre != null
     * @param descripcion Descripci�n de la tarea. descripcion != null
     * @param fechaInicio Fecha de inicio de la tarea. fechaInicio != null
     * @param fechaFin Fecha de finalizaci�n de la tarea. Puede ser null
     */
    public void actualizar( String nombre, String descripcion, Date fechaInicio, Date fechaFin )
    {
        panel.actualizar( nombre, descripcion, fechaInicio, fechaFin );
    }
}
