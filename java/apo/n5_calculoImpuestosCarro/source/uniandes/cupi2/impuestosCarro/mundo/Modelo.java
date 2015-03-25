/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia) 
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * 
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: Impuestos de Carros
 * Autor: Katalina Marcos - Abr 15, 2005
 * Autor: Diana Puentes - Jun 23, 2005
 * Autor: Jorge Villalobos - Jul 10, 2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.impuestosCarro.mundo;

/**
 * Modelo del veh�culo
 */
public class Modelo
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** A�o del modelo */
    private String anio;
    /** Precio del veh�culo de este modelo */
    private double precio;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un modelo con el a�o y su precio.
     * @param elAnio A�o del modelo.
     * @param elPrecio Precio de un veh�culo de este modelo.
     */
    public Modelo( String elAnio, double elPrecio )
    {
        anio = elAnio;
        precio = elPrecio;
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Retorna el a�o del modelo.
     * @return a�o.
     */
    public String darAnio( )
    {
        return anio;
    }

    /**
     * Retorna el precio del modelo.
     * @return precio.
     */
    public double darPrecio( )
    {
        return precio;
    }
}