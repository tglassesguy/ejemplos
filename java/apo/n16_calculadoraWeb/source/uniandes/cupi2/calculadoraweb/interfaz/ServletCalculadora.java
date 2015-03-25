/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_calculadoraWeb
 * Autor: Pablo Barvo - Mayo 4/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.calculadoraweb.interfaz;

import java.io.*;
import java.text.*;

import javax.servlet.*;
import javax.servlet.http.*;

import uniandes.cupi2.calculadoraweb.mundo.*;

/**
 * Servlet que se encarga de contestar a las operaciones aritm�ticas de la calculadora
 */
@SuppressWarnings("serial")
public class ServletCalculadora extends HttpServlet
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Calculadora para realizar las operaciones
     */
    private Calculadora calculadora;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializaci�n del Servlet
     */
    public void init( ) throws ServletException
    {
        calculadora = Calculadora.getInstance( );
    }

    /**
     * Maneja un pedido GET de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
        procesarSolicitud( request, response );
    }

    /**
     * Maneja un pedido POST de un cliente
     * @param request Pedido del cliente
     * @param response Respuesta
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
    {
        // Maneja el GET y el POST de la misma manera
        procesarSolicitud( request, response );
    }

    /**
     * Procesa el pedido de igual manera para todos
     * @param request Pedido del cliente
     * @param response Respuesta
     * @throws IOException Excepci�n de error al escribir la respuesta
     */
    private void procesarSolicitud( HttpServletRequest request, HttpServletResponse response ) throws IOException
    {
        // Env�a a la respuesta el encabezado del template
        imprimirEncabezado( response );

        // Valores para el resultado
        boolean tieneResultado = false;
        double resultado = 0;	// M�todos Servlet
    	// ----------------------------------------------------

        // Toma los valores de los par�metros para hacer el c�lculo
        String operador = request.getParameter( "operacion" );
        String valor1 = request.getParameter( "valor1" );
        String valor2 = request.getParameter( "valor2" );
        if( operador == null || valor1 == null || valor2 == null )
        {
            // El pedido no contiene los par�metros necesarios
            imprimirMensajeError( response.getWriter( ), "Error en los par�metros", "No contiene los valores necesarios para realizar la operaci�n." );
        }
        else
        {
            // El pedido viene completo, valida los n�meros
            try
            {
                double v1 = Double.parseDouble( valor1 );
                double v2 = Double.parseDouble( valor2 );
                // Realiza la operaci�n
                if( operador.equals( "+" ) )
                {
                    // Realiza una suma
                    tieneResultado = true;
                    resultado = calculadora.sumar( v1, v2 );
                    imprimirResultado( "Suma (+)", v1, v2, resultado, response );
                }
                else if( operador.equals( "-" ) )
                {
                    // Realiza una resta
                    tieneResultado = true;
                    resultado = calculadora.restar( v1, v2 );
                    imprimirResultado( "Resta (-)", v1, v2, resultado, response );
                }
                else if( operador.equals( "*" ) )
                {
                    // Realiza una multiplicaci�n
                    tieneResultado = true;
                    resultado = calculadora.multiplicar( v1, v2 );
                    imprimirResultado( "Multiplicaci�n (*)", v1, v2, resultado, response );
                }
                else if( operador.equals( "/" ) )
                {
                    if( v2 != 0 )
                    {
                        // Realiza una divisi�n
                        tieneResultado = true;
                        resultado = calculadora.division( v1, v2 );
                        imprimirResultado( "Divisi�n (/)", v1, v2, resultado, response );
                    }
                    else
                    {
                        // Intento de dividir por cero
                        imprimirMensajeError( response.getWriter( ), "Valores inv�lidos", "Divisi�n por cero" );
                    }
                }
                else
                {
                    // Operaci�n Inv�lida
                    imprimirMensajeError( response.getWriter( ), "Operaci�n Inv�lida", "La operaci�n especificada no es soportada" );
                }
            }
            catch( NumberFormatException e )
            {
                imprimirMensajeError( response.getWriter( ), "Valores Inv�lidos", "Los valores para la operaci�n deben ser num�ricos" );
            }
        }
        // Env�a a la respuesta la parte inferior del template
        imprimirFooter( response, tieneResultado, resultado );

    }

    /**
     * Imprime el resultado de la operaci�n
     * @param operacion Operaci�n realizada
     * @param v1 Valor 1 de la operaci�n
     * @param v2 Valor 2 de la operaci�n
     * @param resultado Resultado de la operaci�n
     * @param response Respuesta al cliente
     * @throws IOException Excepci�n al obtener el flujo de escritura para escribir al cliente
     */
    private void imprimirResultado( String operacion, double v1, double v2, double resultado, HttpServletResponse response ) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime el resultado
        respuesta.println( "                            <table border=\"0\" width=\"543\" id=\"table6\">" );
        respuesta.println( "                                <tr>" );
        respuesta.println( "                                    <td width=\"81\">" );
        respuesta.println( "                                    <p align=\"right\"><b>Valor 1:</b></td>" );
        respuesta.println( "                                    <td width=\"452\" colspan=\"2\">" );
        respuesta.println( "                                    <h3>" + formatearValor( v1 ) + "</td>" );
        respuesta.println( "                                </tr>" );
        respuesta.println( "                                <tr>" );
        respuesta.println( "                                    <td width=\"81\">" );
        respuesta.println( "                                    <p align=\"right\"><b>Operaci�n:</b></td>" );
        respuesta.println( "                                    <td width=\"452\" colspan=\"2\">" );
        respuesta.println( "                                    <h3>" + operacion + "</td>" );
        respuesta.println( "                                </tr>" );
        respuesta.println( "                                <tr>" );
        respuesta.println( "                                    <td width=\"81\">" );
        respuesta.println( "                                    <p align=\"right\"><b>Valor 2:</b></td>" );
        respuesta.println( "                                    <td width=\"452\" colspan=\"2\">" );
        respuesta.println( "                                    <h3>" + formatearValor( v2 ) + "</td>" );
        respuesta.println( "                                </tr>" );
        respuesta.println( "                                <tr>" );
        respuesta.println( "                                    <td width=\"81\" height=\"1\">" );
        respuesta.println( "                                    </td>" );
        respuesta.println( "                                    <td width=\"158\" height=\"1\" bgcolor=#000000>" );
        respuesta.println( "                                    </td>" );
        respuesta.println( "                                    <td width=\"290\" height=\"1\">" );
        respuesta.println( "                                    </td>" );
        respuesta.println( "                                </tr>" );
        respuesta.println( "                                <tr>" );
        respuesta.println( "                                    <td width=\"81\">" );
        respuesta.println( "                                    <p align=\"right\"><b>Resultado:</b></td>" );
        respuesta.println( "                                    <td width=\"452\" colspan=\"2\">" );
        respuesta.println( "                                    <h3>" + formatearValor( resultado ) + "</td>" );
        respuesta.println( "                                </tr>" );
        respuesta.println( "                            </table>" );
    }

    /**
     * Imprime el encabezado con el dise�o de la p�gina
     * @param response Respuesta
     * @throws IOException Excepci�n al imprimir en el resultado
     */
    private void imprimirEncabezado( HttpServletResponse response ) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime el encabezado
        respuesta.println( "<html>" );
        respuesta.println( "<head>" );
        respuesta.println( "<meta http-equiv=\"Content-Language\" content=\"es-co\">" );
        respuesta.println( "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1252\">" );
        respuesta.println( "<title>Calculadora Web - Resultado</title>" );
        respuesta.println( "<link type=\"text/css\" rel=\"stylesheet\" href=\"style/calc.css\">" );
        respuesta.println( "</head>" );
        respuesta.println( "<body>" );
        respuesta.println( "<div align=center>" );
        respuesta.println( "<center>" );
        respuesta.println( "<table border=\"0\" width=\"720\" id=\"table1\">" );
        respuesta.println( "    <tr>" );
        respuesta.println( "        <td>" );
        respuesta.println( "        <p align=\"center\">" );
        respuesta.println( "        <img border=\"0\" src=\"imagenes/logo.jpg\" width=\"640\" height=\"100\"></td>" );
        respuesta.println( "    </tr>" );
        respuesta.println( "    <tr>" );
        respuesta.println( "        <td>&nbsp;</td>" );
        respuesta.println( "    </tr>" );
        respuesta.println( "    <tr>" );
        respuesta.println( "        <td>" );
        respuesta.println( "        <table border=\"1\" width=\"100%\" style=\"border-collapse: collapse\" bordercolor=\"#999999\" id=\"table2\">" );
        respuesta.println( "            <tr>" );
        respuesta.println( "                <td class=\"h2\" height=\"30\">" );
        respuesta.println( "                <h1 align=\"center\">Calculadora Simple de 2 Valores</td>" );
        respuesta.println( "            </tr>" );
        respuesta.println( "            <tr>" );
        respuesta.println( "                <td bgcolor=\"#000000\" height=\"3px\"></td>" );
        respuesta.println( "            </tr>" );
        respuesta.println( "            <tr>" );
        respuesta.println( "                <td>" );
        respuesta.println( "                <table border=\"0\" width=\"710\" id=\"table3\">" );
        respuesta.println( "                    <tr>" );
        respuesta.println( "                        <td width=\"696\" colspan=\"4\" bgcolor=\"#E2E2E2\">" );
        respuesta.println( "                        <table border=\"0\" width=\"614\" id=\"table4\">" );
        respuesta.println( "                            <tr>" );
        respuesta.println( "                                <td width=\"19\">&nbsp;</td>" );
        respuesta.println( "                                <td width=\"581\">" );
        respuesta.println( "                                <h2>Resultado de la operaci�n</td>" );
        respuesta.println( "                            </tr>" );
        respuesta.println( "                        </table>" );
        respuesta.println( "                        </td>" );
        respuesta.println( "                    </tr>" );
        respuesta.println( "                    <tr>" );
        respuesta.println( "                        <td width=\"42\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"572\" colspan=\"2\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"82\">&nbsp;</td>" );
        respuesta.println( "                    </tr>" );
        respuesta.println( "                    <tr>" );
        respuesta.println( "                        <td width=\"42\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"25\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"543\" bordercolor=\"#000000\">" );
    }

    /**
     * Imprime la parte inferior del dise�o de la p�gina
     * @param response Respuesta
     * @param tieneResultado Especifica si debe incluir el resultado
     * @param resultado Resultado de la operaci�n anterior
     * @throws IOException Excepci�n al escribir en la respuesta
     */
    private void imprimirFooter( HttpServletResponse response, boolean tieneResultado, double resultado ) throws IOException
    {
        // Obtiene el flujo de escritura de la respuesta
        PrintWriter respuesta = response.getWriter( );

        // Imprime la parte inferior del dise�o de la p�gina
        respuesta.println( "                    </td>" );
        respuesta.println( "                        <td width=\"82\">&nbsp;</td>" );
        respuesta.println( "                    </tr>" );
        respuesta.println( "                    <tr>" );
        respuesta.println( "                        <td width=\"42\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"572\" colspan=\"2\"><br>" );
        respuesta.println( "                        <a href=\"index.htm\">Volver a la calculadora</a><p>&nbsp;</td>" );
        respuesta.println( "                        <td width=\"82\">&nbsp;</td>" );
        respuesta.println( "                    </tr>" );
        respuesta.println( "                    <tr>" );
        respuesta.println( "                        <td width=\"42\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"25\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"543\">&nbsp;</td>" );
        respuesta.println( "                        <td width=\"82\">&nbsp;</td>" );
        respuesta.println( "                    </tr>" );
        respuesta.println( "                </table>" );
        respuesta.println( "                </td>" );
        respuesta.println( "            </tr>" );
        respuesta.println( "            <tr>" );
        respuesta.println( "                <td bgcolor=\"#000000\" height=\"2px\"></td>" );
        respuesta.println( "            </tr>" );
        respuesta.println( "            <tr>" );
        respuesta.println( "                <td>&nbsp; <b>Proyecto CUPI2</b><br>" );
        respuesta.println( "&nbsp; Departamento de Ingenier�a de Sistemas y Computaci�n<br>" );
        respuesta.println( "&nbsp; Universidad de los Andes<br>" );
        respuesta.println( "                <b>&nbsp; 2006</b></td>" );
        respuesta.println( "            </tr>" );
        respuesta.println( "        </table>" );
        respuesta.println( "        </td>" );
        respuesta.println( "    </tr>" );
        respuesta.println( "</table>" );
        respuesta.println( "</center>" );
        respuesta.println( "</div>" );
        respuesta.println( "</body>" );
        respuesta.println( "</html>" );
    }

    /**
     * Imprime un mensaje de error
     * @param respuesta Respuesta al cliente
     * @param titulo T�tulo del error
     * @param mensaje Mensaje del error
     */
    private void imprimirMensajeError( PrintWriter respuesta, String titulo, String mensaje )
    {
        respuesta.println( "                      <p class=\"error\"><b>Ha ocurrido un error!:<br>" );
        respuesta.println( "                      </b>" + titulo + "</p><p>" + mensaje + ". </p>" );
        respuesta.println( "                      <p>Intente la " );
        respuesta.println( "                      operaci�n nuevamente. Si el problema persiste, contacte" );
        respuesta.println( "                      al administrador del sistema.</p>" );
    }

    /**
     * Formatea un valor num�rico para presentar en la interfaz <br>
     * @param valor - El valor num�rico a ser formateado
     * @return Retorna una cadena de caracteres con el valor formateado con puntos y signos.
     */
    private String formatearValor( double valor )
    {
        DecimalFormat df = ( DecimalFormat )NumberFormat.getInstance( );
        df.applyPattern( "###,###.##" );
        df.setMinimumFractionDigits( 2 );
        return df.format( valor );
    }
}
