/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n18_colegioWeb
 * Autor: Pablo Barvo - Apr 24, 2006
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.colegioweb.mundo;

import java.util.ArrayList;

/**
 * Representa un colegio con sus operaciones simples
 */
public class Colegio
{

    // -----------------------------------------------------------------
    // Singleton
    // -----------------------------------------------------------------

    /**
     * Instancia �nica de la clase
     */
    private static Colegio instancia;

    /**
     * Devuelve la instancia �nica de la clase
     * @return Instancia �nica de la clase
     * @throws DatosException Excepci�n al crear la instancia del colegio
     */
    public static Colegio darInstancia( ) throws DatosException
    {
        if( instancia == null )
        {
            instancia = new Colegio( );
        }
        return instancia;
    }

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * DAO de estudiantes
     */
    private EstudianteDAO dao;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Constructor del colegio
     * @throws DatosException Excepci�n al crear el DAO
     */
    private Colegio( ) throws DatosException
    {
        dao = new EstudianteDAO( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Agrega un estudiante al colegio
     * @param estudiante Estudiante a agregar
     * @throws DatosException Excepci�n de error al agregar el estudiante
     */
    public void agregarEstudiante( Estudiante estudiante ) throws DatosException
    {
        dao.agregarEstudiante( estudiante );
    }

    /**
     * Modifica la informaci�n del estudiante especificado <br>
     * <b>pre:</b> El c�digo del estudiante no debe haber cambiado
     * @param estudiante Estudiante con la informaci�n actualizada
     * @throws DatosException Excepci�n de error en la modificaci�n del estudiante
     */
    public void modificarEstudiante( Estudiante estudiante ) throws DatosException
    {
        dao.modificarEstudiante( estudiante );
    }

    /**
     * Elimina un estudiante del colegio
     * @param codigo C�digo del estudiante
     * @throws DatosException Excepci�n de error al eliminar el estudiante
     */
    public void eliminarEstudiante( String codigo ) throws DatosException
    {
        dao.eliminarEstudiante( codigo );
    }

    /**
     * Devuelve todos los estudiantes del colegio
     * @return Todos los estudiantes de la base de datos
     * @throws DatosException Excepci�n al cargar los estudiantes
     */
    public ArrayList<Estudiante> darEstudiantes( ) throws DatosException
    {
        return dao.darEstudiantes( );
    }

    /**
     * Devuelve los estudiantes del colegio que cumplen con la condici�n
     * @param campo Campo por el cual se va a realizar la b�squeda
     * @param valor Valor del campo
     * @return Todos los estudiantes de la base de datos
     * @throws DatosException Excepci�n al cargar los estudiantes
     */
    public ArrayList<Estudiante> darEstudiantes( String campo, String valor ) throws DatosException
    {
        //
        // Valida el campo
        boolean valido = false;
        valido = valido || campo.equalsIgnoreCase( "CODIGO" );
        valido = valido || campo.equalsIgnoreCase( "NOMBRE" );
        valido = valido || campo.equalsIgnoreCase( "APELLIDO" );
        valido = valido || campo.equalsIgnoreCase( "PROMEDIO" );
        valido = valido || campo.equalsIgnoreCase( "COMENTARIOS" );
        valido = valido || campo.equalsIgnoreCase( "CURSO" );
        if( !valido )
        {
            throw new DatosException( "El campo de la selecci�n es inv�lido" );
        }
        return dao.darEstudiantes( campo, valor );
    }

}
