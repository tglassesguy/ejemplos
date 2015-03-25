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

import java.util.*;

/**
 * L�nea del veh�culo
 */
public class Linea
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /** Nombre de la l�nea */
    private String nombre;
    /** Modelos de la l�nea */
    private ArrayList modelos;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     * Crea una l�nea de veh�culo con el nombre dado.
     * @param elNombre Nombre de la l�nea.
     */
    public Linea( String elNombre )
    {
        nombre = elNombre;
        modelos = new ArrayList( );
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Retorna el nombre de la l�nea.
     * @return nombre de la l�nea.
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna los modelos de la l�nea.
     * @return modelos.
     */
    public ArrayList darModelos( )
    {
        return modelos;
    }

    /**
     * Busca una modelo de la l�nea dado su a�o.
     * @param anio A�o del modelo. anio != null.
     * @return el modelo si lo encuentra o null si no lo encuentra.
     */
    public Modelo buscarModelo( String anio )
    {
        Modelo modelo = null;
        for( int i = 0; i < modelos.size( ) && modelo == null; i++ )
        {
            Modelo modeloAux = ( Modelo )modelos.get( i );
            if( modeloAux.darAnio( ).equals( anio ) )
                modelo = modeloAux;
        }
        return modelo;
    }

    /**
     * Adiciona un modelo a la l�nea. <br>
     * <b>post: </b> se agrega un nuevo modelo a la lista de modelos de la l�nea.
     * @param unModelo Modelo a adicionar. unModelo != null.
     */
    public void adicionarModelo( Modelo unModelo )
    {
        modelos.add( unModelo );
    }
}