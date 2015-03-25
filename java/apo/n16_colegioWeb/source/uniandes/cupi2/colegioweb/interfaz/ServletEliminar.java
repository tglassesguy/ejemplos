/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ServletEliminar.java,v 1.2 2008/06/16 01:04:26 jua-gome Exp $
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - 27/04/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.interfaz;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uniandes.cupi2.colegioweb.mundo.Colegio;
import uniandes.cupi2.colegioweb.mundo.DatosException;
import uniandes.cupi2.colegioweb.mundo.Estudiante;

/**
 * Servlet encargado de eliminar un estudiante
 */
@SuppressWarnings("serial")
public class ServletEliminar extends ServletTemplate
{

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Devuelve el t�tulo de la p�gina para el Header
     * @param request Pedido del cliente
     * @return T�tulo de la p�gina para el Header
     */
    public String darTituloPagina( HttpServletRequest request )
    {
        return "Eliminar Estudiante";
    }

    /**
     * Devuelve el nombre de la imagen para el t�tulo de la p�gina en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el t�tulo de la p�gina en el Header
     */
    public String darImagenTitulo( HttpServletRequest request )
    {
        return "eliminar48.jpg";
    }

    /**
     * Escribe el contenido de la p�gina
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepci�n de error al escribir la respuesta
     */
    public void escribirContenido( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        //
        // Saca el Printer
        PrintWriter respuesta = response.getWriter( );
        //
        // Busca el c�digo del estudiante a eliminar
        String codigo = request.getParameter( "estudiante" );
        if( codigo == null )
        {
            imprimirMensajeError( respuesta, "Petici�n Inv�lida", "La petici�n no incluye informaci�n del estudiante a eliminar" );
        }
        else
        {
            try
            {
                ArrayList<Estudiante> estudiantes = Colegio.darInstancia( ).darEstudiantes( "CODIGO", codigo );
                if( estudiantes.size( ) != 1 )
                {
                    //
                    // Imprime el mensaje de error de no encontrar un estudiante
                    imprimirMensajeError( respuesta, "Estudiante no encontrado!", "El estudiante con c�digo '" + codigo + "' no se encuentra en el sistema" );
                }
                else
                {
                    //
                    // Elimina el estudiante e imprime un mensaje de �xito
                    Colegio.darInstancia( ).eliminarEstudiante( codigo );
                    imprimirMensajeOk( respuesta, "Estudiante eliminado", "El estudiante fue eliminado del sistema" );
                }
            }
            catch( DatosException e )
            {
                //
                // Imprime el mensaje de la excepci�n
                imprimirMensajeError( respuesta, "Error al eliminar el estudiante con c�digo '" + codigo + "'.", "Excepci�n generada en la operaci�n", e );
            }
        }
    }

}
