package uniandes.cupi2.crucero.test;

import javax.swing.ImageIcon;

import junit.framework.TestCase;
import uniandes.cupi2.crucero.mundo.Foto;

/**
 * Verifica la correcta implementaci�n de la clase Foto
 */
public class FotoTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Foto foto;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye una nueva Foto
     *  
     */
    private void setupEscenario1()
    {
    	foto = new Foto("titulo", new ImageIcon());
    }
    
    /**
     * Prueba 1 - Prueba el m�todo constructor de la clase Foto <br>
     * <b> M�todos a probar: </b>
     * Foto, darTitulo, darImagen
     */
    public void testFoto()
    {
    	setupEscenario1();
    	
    	assertEquals("El titulo de la foto no se inicializa correctamente", "titulo", foto.darTitulo());
    	assertNotNull("La imagen de la foto no se inicializa correctamente", foto.darImagen());	
    }
}
