package uniandes.cupi2.crucero.test;

import javax.swing.ImageIcon;

import junit.framework.TestCase;
import uniandes.cupi2.crucero.mundo.Destino;
import uniandes.cupi2.crucero.mundo.Foto;

/**
 * Verifica la correcta implementaci�n de la clase Destino
 */
public class DestinoTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Destino destino;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Destino
     */
    private void setupEscenario1()
    {
    	destino = new Destino("ciudad1", "pais1");
    }
    
    /**
     * Construye un nuevo Destino con im�genes
     */
    private void setupEscenario2()
    {
    	destino = new Destino("cuidad2", "pais2");
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	destino.agregarImagen(new Foto("titulo2", new ImageIcon()));
    	destino.agregarImagen(new Foto("titulo3", new ImageIcon()));    	
    }
    
    /**
     * Prueba 1 - Prueba el m�todo constructor de la clase <br>
     * <b> M�todos a probar: </b>
     * Destino, darCiudad, darPais, darImagenes, darImagenActual
     */
    public void testDestino()
    {
    	setupEscenario1();
    	
    	assertEquals("La ciudad destino no es inicializada correctamente", "ciudad1", destino.darCiudad());
    	assertEquals("El pa�s destino no es inicializado correctamente", "pais1", destino.darPais());
    	assertNotNull("El arreglo de im�genes deber�a ser inicializado", destino.darImagenes());
    	assertEquals("El tama�o del arreglo deber�a ser " + Destino.MAX_FOTOS, Destino.MAX_FOTOS, destino.darImagenes().length);
    	assertNull("La destino no deber�a tener una imagen actual", destino.darImagenActual());
    }
    
    /**
     * Prueba 2 - Prueba el m�todo agregarImagen
     *  <b> M�todos a probar: </b>
     * agregaImagen, darImagenes
     */
    public void testAgregarImagen()
    {
    	setupEscenario1();
    	
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	assertNotNull("No agrega la imagen", destino.darImagenes()[0]);
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	assertNotNull("No agrega la imagen", destino.darImagenes()[1]);
    }
    
    /**
     * Prueba 3 - Prueba el m�todo darImagenSiguiente <br>
     * <b> M�todos a probar: </b>
     * darImagenSiguiente
     * <b> Resultado esperado: </b>
     * 1. Si la imagen es la primera es posible avanzar
     * 2. Si la imagen es intermedia es posible avanzar
     */
    public void testDarImagenSiguiente()
    {
    	setupEscenario2();
    	
    	try 
    	{
			Foto f = destino.darImagenSiguiente();
			assertNotNull("La imagen siguiente no deber�a ser nula", f);
			assertEquals("Imagen siguiente incorrecta", "titulo2", f.darTitulo());
			
			f = destino.darImagenSiguiente();
			assertNotNull("La imagen siguiente no deber�a ser nula", f);
			assertEquals("Imagen siguiente incorrecta", "titulo3", f.darTitulo());
		} 
    	catch (Exception e) 
    	{
			fail("No se deber�a generar la excepci�n: " + e.getMessage());
		}
    }
  
    /**
     * Prueba 4 - Prueba el m�todo darImagenSiguiente <br>
     * <b> M�todos a probar: </b>
     * darImagenSiguiente, agregarImagen
     * <b> Resultado esperado: </b>
     * Si la imagen es la �ltima no se debe permitir avanzar
     */
    public void testDarImagenSiguienteError()
    {
    	setupEscenario1();
    	destino.agregarImagen(new Foto("titulo1", new ImageIcon()));
    	
    	try 
    	{
			destino.darImagenSiguiente();
			fail("No deber�a permitir el avance");
		} 
    	catch (Exception e) 
    	{
			//Deber�a generar excepci�n
		}
    }
   
    /**
     * Prueba 5 - Prueba el m�todo darImagenAnterior <br>
     * <b> M�todos a probar: </b>
     * darImagenAnterior, darImagenSiguiente
     * <b> Resultado esperado: </b>
     * 1. Si la imagen es la �ltima es posible retroceder
     * 2. Si la imagen es intermedia es posible retroceder
     */
    public void testDarImagenAnterior()
    {
    	setupEscenario2();
    	
    	try 
    	{
			destino.darImagenSiguiente();
			destino.darImagenSiguiente();
			
			Foto f = destino.darImagenAnterior();
			assertNotNull("La imagen anterior no deber�a ser nula", f);
			assertEquals("Imagen anterior incorrecta", "titulo2", f.darTitulo());
			
			f = destino.darImagenAnterior();
			assertNotNull("La imagen anterior no deber�a ser nula", f);
			assertEquals("Imagen anterior incorrecta", "titulo1", f.darTitulo());
		} 
    	catch (Exception e) 
    	{
			fail("No se deber�a generar la excepci�n: " + e.getMessage());
		}
    }
  
    /**
     * Prueba 4 - Prueba el m�todo darImagenSiguiente <br>
     * <b> M�todos a probar: </b>
     * darImagenAnterior
     * <b> Resultado esperado: </b>
     * Si la imagen es la primera no se debe permitir retroceder
     */
    public void testDarImagenAnteriorError()
    {
    	setupEscenario2();
    	
    	try 
    	{
			destino.darImagenAnterior();
			fail("No deber�a permitir retroceder");
		} 
    	catch (Exception e) 
    	{
			//Deber�a generar excepci�n
		}
    }
 }