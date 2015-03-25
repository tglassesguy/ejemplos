/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n5_crucero
 * Autor: Catalina Rodr�guez - 16-sep-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.crucero.test;

import junit.framework.TestCase;
import uniandes.cupi2.crucero.mundo.Crucero;
import uniandes.cupi2.crucero.mundo.Destino;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Crucero est�n correctamente implementados
 */
public class CruceroTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Crucero crucero;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Crucero
     *  
     */
    private void setupEscenario1( )
    {
        crucero = new Crucero( );
    }
    
    /**
     * Construye un nuevo Crucero con varios destinos
     *  
     */
    private void setupEscenario2( )
    {
        crucero = new Crucero( );
        try 
        {
			crucero.agregarDestino("Medellin", "Colombia");
			crucero.agregarDestino("Cali", "Colombia");
		} 
        catch (Exception e) 
        {
			fail("No se deber�a lanzar la excepci�n: " + e.getMessage());
		}
    }

    /**
     * Prueba 1 - Prueba el m�todo constructor de la clase
     * <b> M�todos a probar: </b>
     * Crucero, darDestinos, darIndiceDestinoActual, darDestinoActual
     */
    public void testCrucero( )
    {
        setupEscenario1( );
        
        assertNotNull("La lista de destino no ha sido inicializada", crucero.darDestinos());
        assertEquals("El �ndice del destino actual no es correcto", 0, crucero.darIndiceDestinoActual());
        assertNotNull("El destino 1 no fue agregado", crucero.darDestinoActual()); 
    }
    
    /**
     * Prueba 2 - Prueba el m�todo buscarDestino
     * <b> M�todos a probar: </b>
     * buscarDestino
     */
    public void testBuscarDestino()
    {
    	setupEscenario1();
    	
    	Destino d = crucero.buscarDestino("Santa Marta", "Colombia");
    	assertNotNull("El resultado de la b�squeda no deber�a ser nulo", d);
    	assertEquals("Resultado incorrecto", "Santa Marta", d.darCiudad());
    	assertEquals("Resultado incorrecto", "Colombia", d.darPais());   
    	
    	d = crucero.buscarDestino("Santa Marta", "Colbia");
    	assertNull("El resultado de la b�squeda deber�a ser nulo", d); 
    }
    
    /**
     * Prueba 3 - Prueba el m�todo agregarDestino
     * <b> M�todos a probar: </b>
     * agregarDestino, darDestinos
     * <b> Resultado esperado: </b>
     * El resultado es agregado a la lista del crucero
     */
    public void testAgregarDestino()
    {
    	setupEscenario1();
    	
    	try 
    	{
			crucero.agregarDestino("Medellin", "Colombia");
			assertEquals("El destino no ha sido agregado a la lista del crucero", 2, crucero.darDestinos().size());
			Destino d = (Destino)crucero.darDestinos().get(1);
			assertEquals("El destino no fue agregado correctamente", "Medellin", d.darCiudad());
			
			crucero.agregarDestino("Cali", "Colombia");
			assertEquals("El destino no ha sido agregado a la lista del crucero", 3, crucero.darDestinos().size());
			d = (Destino)crucero.darDestinos().get(2);
			assertEquals("El destino no fue agregado correctamente", "Cali", d.darCiudad());
		} 
    	catch (Exception e) 
    	{
			fail("No deber�a generar una excepci�n: " + e.getMessage());
		}
    	
    }
    
    /**
     * Prueba 4 - Prueba el m�todo agregarDestino
     * <b> M�todos a probar: </b>
     * agregarDestino
     * <b> Resultado esperado: </b>
     * El resultado no debe ser agregado a la lista del crucero
     */
    public void testAgregarDestinoError()
    {
    	setupEscenario1();
    	
    	try 
    	{
			crucero.agregarDestino("Santa Marta", "Colombia");
			fail("No deber�a agregar el destino. El destino ya existe");
		} 
    	catch (Exception e) 
    	{
    		//Deber�a generar excepci�n
    	}
    	
    }
  
    /**
     * Prueba 5 - Prueba el m�todo darAnteriorDestino <br>
     * <b> M�todos a probar: </b>
     * darAnteriorDestino
     * <b> Resultado esperado: </b>
     * 1. Si el destino es el �ltimo es posible retroceder
     * 2. Si el destino es intermedio es posible retroceder
     */
    public void testDarAnteriorDestino()
    {
    	setupEscenario2();
    	
    	try 
    	{
    		//Destino Actual: Cali
    		Destino d = crucero.darAnteriorDestino();
			assertNotNull("El destino no deber�a ser nulo", d);
	    	assertEquals("Resultado incorrecto", "Medellin", d.darCiudad());
	    	assertEquals("Resultado incorrecto", "Colombia", d.darPais());  
			
	    	//Destino Actual: Medell�n
	    	d = crucero.darAnteriorDestino();
			assertNotNull("El resultado de la b�squeda no deber�a ser nulo", d);
	    	assertEquals("Resultado incorrecto", "Santa Marta", d.darCiudad());
	    	assertEquals("Resultado incorrecto", "Colombia", d.darPais()); 
		} 
    	catch (Exception e) 
    	{
			fail("No se deber�a generar la excepci�n: " + e.getMessage());
		}
    }
  
    /**
     * Prueba 6 - Prueba el m�todo darAnteriorDestino <br>
     * <b> M�todos a probar: </b>
     * darAnteriorDestino
     * <b> Resultado esperado: </b>
     * Si el destino es el primero no se debe permitir retroceder
     */
    public void testDarAnteriorDestinoError()
    {
    	setupEscenario1();
    	
    	try 
    	{
    		//Destino Actual: Santa Marta
    		crucero.darAnteriorDestino();
			fail("No deber�a permitir retroceder");
		} 
    	catch (Exception e) 
    	{
			//Deber�a generar excepci�n
		}
    }
      
    /**
     * Prueba 7 - Prueba el m�todo darSiguienteDestino <br>
     * <b> M�todos a probar: </b>
     * darSiguienteDestino, darAnteriorDestino
     * <b> Resultado esperado: </b>
     * 1. Si el destino es el primero es posible avanzar
     * 2. Si el destino es intermedio es posible avanzar
     */
    public void testDarSiguienteDestino()
    {
    	setupEscenario2();
    	
    	try 
    	{
    		crucero.darAnteriorDestino();
    		crucero.darAnteriorDestino();
    		
    		Destino d = crucero.darSiguienteDestino();
			assertNotNull("El resultado de la b�squeda no deber�a ser nulo", d);
	    	assertEquals("Resultado incorrecto", "Medellin", d.darCiudad());
	    	assertEquals("Resultado incorrecto", "Colombia", d.darPais());  
	    	
	    	d = crucero.darSiguienteDestino();
			assertNotNull("El resultado de la b�squeda no deber�a ser nulo", d);
	    	assertEquals("Resultado incorrecto", "Cali", d.darCiudad());
	    	assertEquals("Resultado incorrecto", "Colombia", d.darPais());  
		} 
    	catch (Exception e) 
    	{
			fail("No se deber�a generar la excepci�n: " + e.getMessage());
		}
    }
   
    /**
     * Prueba 8 - Prueba el m�todo darSiguienteDestino <br>
     * <b> M�todos a probar: </b>
     * darSiguienteDestino, agregarDestino
     * <b> Resultado esperado: </b>
     * Si el destino es el �ltimo no se debe permitir avanzar
     */
    public void testDarSiguienteDestinoError()
    {
    	setupEscenario1();
    	
    	try 
    	{
    		crucero.darSiguienteDestino();
    		fail("No deber�a permitir el avance");
    	} 
    	catch (Exception e) 
    	{
    		//Deber�a generar excepci�n
    	}
    }
       
    /**
     * Prueba 9 - Prueba el m�todo eliminarDestinoActual <br>
     * <b> M�todos a probar: </b>
     * eliminarDestinoActual
     * <b> Resultado esperado: </b>
     * Se elimina el destino actual del crucero
     * El �ndice del destino actual es -1
     */
    public void testEliminarDestinoActual()
    {
    	setupEscenario1();
	   
    	try
	    {
	    	crucero.eliminarDestinoActual();
	    	assertEquals("No modifica correctamente el �ndice del nuevo destino actual", -1, crucero.darIndiceDestinoActual());
	    }
    	catch (Exception e) 
    	{
			fail("No se deber�a generar la excepci�n: " + e.getMessage());
		}
    }
    
    /**
     * Prueba 10 - Prueba el m�todo eliminarDestinoActual <br>
     * <b> M�todos a probar: </b>
     * eliminarDestinoActual
     * <b> Resultado esperado: </b>
     * Se dispara una excepci�n al intentar eliminar un destino cuando el crucero est� vac�o
     */
    public void testEliminarDestinoActualError()
    {
    	setupEscenario1();
    	
    	try
	    {
	    	crucero.eliminarDestinoActual();
	    	crucero.eliminarDestinoActual();	    	
	    	fail("Se deber�a generar una excepci�n. No existen destinos en el crucero");
	    }
    	catch (Exception e) 
    	{
    		//Debe pasar por ac�
    	}
    }
    
    /**
     * Prueba 11 - Prueba de conexi�n con Flickr
     * <b> M�todos a probar: </b>
     * conectarFlickr
     */
    public void testConexionFlickr()
    {
    	setupEscenario1();
    	crucero.conectarFlickr();
    	assertNotNull("No fue posible realizar la conexi�n con Flickr", crucero.darConexionFlickr());
    }
}