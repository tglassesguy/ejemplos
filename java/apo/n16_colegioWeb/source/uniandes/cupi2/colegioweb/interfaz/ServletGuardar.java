/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 26, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.interfaz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uniandes.cupi2.colegioweb.mundo.Colegio;
import uniandes.cupi2.colegioweb.mundo.DatosException;
import uniandes.cupi2.colegioweb.mundo.Estudiante;

/**
 * Servlet encargado de guardar un estudiante nuevo/modificado
 */
@SuppressWarnings("serial")
public class ServletGuardar extends ServletTemplate
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
        //
        // Si es agregar/modificar cambia el t�tulo
        String tipo = request.getParameter( "tipo" );
        if( tipo == null || "nuevo".equals( tipo ) )
        {
            return "Agregar Estudiante";
        }
        else
        {
            return "Modificar Estudiante";
        }
    }

    /**
     * Devuelve el nombre de la imagen para el t�tulo de la p�gina en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el t�tulo de la p�gina en el Header
     */
    public String darImagenTitulo( HttpServletRequest request )
    {
        return "agregar48.jpg";
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
        // Lee el estudiante
        Estudiante estudiante = leerEstudiante( request, respuesta );
        //
        // Si es agregar/modificar
        if( estudiante != null )
        {
            try
            {
                String tipo = request.getParameter( "tipo" );
                if( tipo == null )
                {
                    imprimirMensajeError( respuesta, "Petici�n Inv�lida", "La petici�n no incluye informaci�n para poder guardar el estudiante" );
                }
                else
                {
                    if( "nuevo".equals( tipo ) )
                    {
                        Colegio.darInstancia( ).agregarEstudiante( estudiante );
                        imprimirMensajeOk( respuesta, "Estudiante Agregado", "Es estudiante fue agregado al sistema" );
                    }
                    else
                    {
                        Colegio.darInstancia( ).modificarEstudiante( estudiante );
                        imprimirMensajeOk( respuesta, "Estudiante Modificado", "El estudiante fue modificado en el sistema" );
                    }
                }
            }
            catch( DatosException e )
            {
                //
                // Imprime el mensaje de la excepci�n
                imprimirMensajeError( respuesta, "Error al guardar el estudiante.", "Excepci�n generada en la operaci�n", e );
            }
        }
    }

    /**
     * Lee un estudiante del request
     * @param request Pedido del cliente
     * @param respuesta Respuesta al cliente
     * @return Estudiante le�do
     */
    private Estudiante leerEstudiante( HttpServletRequest request, PrintWriter respuesta )
    {
        String codigo = request.getParameter( "codigo" );
        String nombre = request.getParameter( "nombre" );
        String apellido = request.getParameter( "apellido" );
        String curso = request.getParameter( "curso" );
        String promedio = request.getParameter( "promedio" );
        String comentarios = request.getParameter( "comentarios" );
        if( codigo != null && nombre != null && apellido != null && promedio != null && comentarios != null && curso != null )
        {

            return new Estudiante( codigo, nombre, apellido, curso, promedio, comentarios );
        }
        else
        {
            imprimirMensajeError( respuesta, "Petici�n Inv�lida", "La petici�n no incluye informaci�n completa del estudiante" );
            return null;
        }
    }

}
