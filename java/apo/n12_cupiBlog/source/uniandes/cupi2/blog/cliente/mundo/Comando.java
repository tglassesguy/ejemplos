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

package uniandes.cupi2.blog.cliente.mundo;

/**
 * Clase que representa un comando obtenido por un mensaje que llega del servidor
 */
public class Comando
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * El nombre del comando
     */
    private String nombre;

    /**
     * Los par�metros del comando
     */
    private String[] parametros;
    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea un nuevo usuario con un nombre dado
     * @param pNombre El nombre del comando. pNombre != null  
     * @param pParametros Los par�metros del comando. pParametros != null  
     */
    public Comando( String pNombre, String[] pParametros )
    {
        nombre = pNombre;
        parametros = pParametros;
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Devuelve el nombre del comando
     * @return El nombre del comando
     */
    public String darNombre(){
        return nombre;
    }
    
    /**
     * Devuelve los par�metros del comando
     * @return Los par�metros del comando
     */
    public String[] darParametros(){
        return parametros;
    }
}