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

package uniandes.cupi2.blog.testComun;

import java.util.Date;

import junit.framework.TestCase;
import uniandes.cupi2.blog.comun.Articulo;
import uniandes.cupi2.blog.comun.Comentario;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Comentario est�n correctamente implementados
 */
public class ComentarioTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Comentario comentario;
    
    /**
     * El art�culo relacionado con el comentario
     */
    private Articulo articulo;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Comentario vac�o
     *  
     */
    private void setupEscenario1( )
    {
        articulo = new Articulo("Usuario 1", "El t�tulo", "La Categor�a", "El contenido", new Date());
        comentario = new Comentario( "Usuario", articulo, "El contenido del comentario", new Date(2010, 02, 10) );
    }

    /**
     * Prueba del constructor del comentario
     */
    public void testComentario( )
    {
        setupEscenario1( );
        
        assertEquals("El nombre de usuario debe ser el mismo", comentario.darUsuario( ), "Usuario");
        assertEquals("El art�culo debe ser el mismo", comentario.darArticulo( ), articulo);
        assertEquals("El contenido del comentario es el mismo", comentario.darContenido( ), "El contenido del comentario");
        assertEquals("La fecha debe ser la misma", comentario.darFechaPublicacion( ), new Date(2010, 02, 10));
    }

}