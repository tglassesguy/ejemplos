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
package uniandes.cupi2.toDoList.mundo;

/**
 * Excepci�n que se lanza si se pregunta por un elemento que no existe.
 */
public class ElementoNoExisteException extends Exception
{

    /**
     * Construye la excepci�n con el mensaje que describe el problema
     * 
     * @param mensaje Mensaje que describe la causa de la excepci�n
     */
    public ElementoNoExisteException( String mensaje )
    {
        super( mensaje );
    }
}
