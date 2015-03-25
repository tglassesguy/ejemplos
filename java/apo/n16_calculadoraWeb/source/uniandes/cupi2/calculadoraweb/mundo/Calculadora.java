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
 * Autor: Jorge Villalobos - Noviembre 13/2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package uniandes.cupi2.calculadoraweb.mundo;

/**
 * Representa una calculadora simple
 */
public class Calculadora
{
    // -----------------------------------------------------------------
    // Implementaci�n del patr�n Singleton
    // -----------------------------------------------------------------

    /**
     * Guarda una referencia a la �nica instancia de la clase
     */
    private static Calculadora instancia = null;

    /**
     * Define el �nico constructor de la clase como privado, para evitar que se creen nuevas instancias
     */
    private Calculadora( )
    {
    }

    /**
     * Este m�todo permite localizar la �nica instancia existente de la clase. Esto permite que todos compartan el mismo objeto.
     */
    public static Calculadora getInstance( )
    {
        if( instancia == null )
            instancia = new Calculadora( );
        return instancia;
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Realiza la suma de los dos par�metros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Suma de los dos valores recibidos como par�metro
     */
    public double sumar( double v1, double v2 )
    {
        return v1 + v2;
    }

    /**
     * Realiza la resta de los dos par�metros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Resta de los dos valores recibidos como par�metro
     */
    public double restar( double v1, double v2 )
    {
        return v1 - v2;
    }

    /**
     * Realiza la multiplicaci�n de los dos par�metros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Multiplicaci�n de los dos valores recibidos como par�metro
     */
    public double multiplicar( double v1, double v2 )
    {
        return v1 * v2;
    }

    /**
     * Realiza la divisi�n de los dos par�metros
     * @param v1 Valor 1
     * @param v2 Valor 2
     * @return Resultado de la divisi�n v1 / v2
     */
    public double division( double v1, double v2 )
    {
        return v1 / v2;
    }
}
