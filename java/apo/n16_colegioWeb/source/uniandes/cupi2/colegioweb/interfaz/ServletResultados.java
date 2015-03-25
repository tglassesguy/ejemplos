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
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uniandes.cupi2.colegioweb.mundo.Colegio;
import uniandes.cupi2.colegioweb.mundo.DatosException;
import uniandes.cupi2.colegioweb.mundo.Estudiante;

/**
 * Servlet de muestra de resultados de una b�squeda
 */
@SuppressWarnings("serial")
public class ServletResultados extends ServletTemplate
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
        return "Resultados de B�squeda";
    }

    /**
     * Devuelve el nombre de la imagen para el t�tulo de la p�gina en el Header
     * @param request Pedido del cliente
     * @return Nombre de la imagen para el t�tulo de la p�gina en el Header
     */
    public String darImagenTitulo( HttpServletRequest request )
    {
        return "buscar48.jpg";
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
        try
        {
            //
            // Saca los par�metros de la b�squeda
            String campo = request.getParameter( "campo" );
            String valor = request.getParameter( "valor" );
            ArrayList<Estudiante> estudiantes;
            if( "TODOS".equals( campo ) )
            {
                estudiantes = Colegio.darInstancia( ).darEstudiantes( );
            }
            else
            {
                estudiantes = Colegio.darInstancia( ).darEstudiantes( campo, valor );
            }
            imrpimirTabla( respuesta, estudiantes );
        }
        catch( DatosException e )
        {
            //
            // Imprime el mensaje de la excepci�n
            imprimirMensajeError( respuesta, "Error al buscar estudiantes.", "Excepci�n generada en la operaci�n", e );
        }
    }

    /**
     * Imprime la tabla con los estudiantes
     * @param respuesta Respuesta al cliente
     * @param estudiantes Estudiantes a imprimir
     */
    private void imrpimirTabla( PrintWriter respuesta, ArrayList<Estudiante> estudiantes )
    {
        if( estudiantes.size( ) == 0 )
        {
            respuesta.write( "                      La b�squeda no devolvi� resultados.<p>\r\n" );
            respuesta.write( "                      <a href=\"index.htm\">Volver a la p�gina principal</a>\r\n" );
        }
        else
        {
            //
            // Imprime el t�tulo de la tabla
            respuesta.write( "                      <table border=\"1\" width=\"543\" id=\"table5\" style=\"border-collapse: collapse\">\r\n" );
            respuesta.write( "                          <tr>\r\n" );
            respuesta.write( "                              <td width=\"106\" align=\"center\" bgcolor=\"#707070\">\r\n" );
            respuesta.write( "                              <font color=\"#FFFFFF\"><b>C�digo</b></font></td>\r\n" );
            respuesta.write( "                              <td align=\"center\" bgcolor=\"#707070\" width=\"135\">\r\n" );
            respuesta.write( "                              <font color=\"#FFFFFF\"><b>Nombre</b></font></td>\r\n" );
            respuesta.write( "                              <td align=\"center\" bgcolor=\"#707070\" width=\"141\">\r\n" );
            respuesta.write( "                              <font color=\"#FFFFFF\"><b>Apellido</b></font></td>\r\n" );
            respuesta.write( "                              <td align=\"center\" bgcolor=\"#707070\" width=\"71\">\r\n" );
            respuesta.write( "                              <b><font color=\"#FFFFFF\">Promedio</font></b></td>\r\n" );
            respuesta.write( "                              <td width=\"25\" align=\"center\" bgcolor=\"#707070\">\r\n" );
            respuesta.write( "                              <font color=\"#FFFFFF\"><b>M</b></font></td>\r\n" );
            respuesta.write( "                              <td width=\"25\" align=\"center\" bgcolor=\"#707070\">\r\n" );
            respuesta.write( "                              <font color=\"#FFFFFF\"><b>E</b></font></td>\r\n" );
            respuesta.write( "                          </tr>\r\n" );
            //
            // Imprime los resultados
            for( int i = 0; i < estudiantes.size( ); i++ )
            {
                Estudiante estudiante = estudiantes.get( i );
                respuesta.write( "                          <tr>\r\n" );
                respuesta.write( "                              <td width=\"106\" align=\"center\">" + estudiante.darCodigo( ) + "</td>\r\n" );
                respuesta.write( "                              <td width=\"135\" align=\"center\">" + estudiante.darNombre( ) + "</td>\r\n" );
                respuesta.write( "                              <td width=\"141\" align=\"center\">" + estudiante.darApellido( ) + "</td>\r\n" );
                respuesta.write( "                              <td width=\"71\" align=\"center\">" + estudiante.darPromedio( ) + "</td>\r\n" );
                respuesta.write( "                              <td width=\"25\" align=\"center\">\r\n" );
                respuesta.write( "                              <a href=\"estudiante.htm?estudiante=" + estudiante.darCodigo( ) + "\">\r\n" );
                respuesta.write( "                              <img border=\"0\" src=\"imagenes/editar24.jpg\" width=\"24\" height=\"24\"></a></td>\r\n" );
                respuesta.write( "                              <td width=\"25\" align=\"center\">\r\n" );
                respuesta.write( "                              <a href=\"eliminar.htm?estudiante=" + estudiante.darCodigo( ) + "\">\r\n" );
                respuesta.write( "                              <img border=\"0\" src=\"imagenes/eliminar24.jpg\" width=\"24\" height=\"24\"></a></td>\r\n" );
                respuesta.write( "                          </tr>\r\n" );
            }
            //
            // Imprime el final de la tabla
            respuesta.write( "                      </table>\r\n" );
        }
    }

}
