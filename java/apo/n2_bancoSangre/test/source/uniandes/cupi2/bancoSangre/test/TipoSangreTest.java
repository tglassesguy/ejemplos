package uniandes.cupi2.bancoSangre.test;

import junit.framework.TestCase;
import uniandes.cupi2.bancoSangre.mundo.TipoSangre;

/**
 * Esta es la clase usada para verificar la correcta implementaci�n de TipoSangre
 */
public class TipoSangreTest extends TestCase 
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
	private TipoSangre tipo;
	
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo TipoSangre
     */
	private void setupEscenario1()
	{
		tipo = new TipoSangre(TipoSangre.TIPO_AB, TipoSangre.RH_P);
	}
	
    /**
     * Prueba 1 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * TipoSangre, darCantidadDespachada, darCantidadDisponible, darRh, darTipo
     */
    public void testTipoSangre()
    {
    	setupEscenario1();
    	
    	assertTrue("El n�mero de bolsas despachadas deber�a ser cero", tipo.darCantidadSuministrada()==0);
    	assertTrue("El n�mero de bolsas disponibles deber�a ser cero", tipo.darCantidadDisponible()==0);
    	assertEquals("El factor RH no es correcto", "+", tipo.darRh());
    	assertTrue("El tipo de sangre no es correcto", tipo.darTipo().toLowerCase().contains("ab"));
    }
    
    /**
     * Prueba 2 - Prueba el m�todo registrarDonacion <br>
     * M�todos a probar: <br>
     * registrarDonacion, darCantidadDespachada, darCantidadDisponible
     */
    public void testRegistrarDonacion()
    {
    	setupEscenario1();
    	
    	tipo.registrarDonacion();
    	assertTrue("El n�mero de bolsas despachadas deber�a ser cero", tipo.darCantidadSuministrada()==0);
    	assertTrue("El n�mero de bolsas disponibles deber�a incrementar en 1", tipo.darCantidadDisponible()==1);
    }
    
    /**
     * Prueba 3 - Prueba el m�todo suministrar <br>
     * M�todos a probar: <br>
     * suministrar, registrarDonacion, darCantidadDespachada, darCantidadDisponible
     */
    public void testSuministrar()
    {
    	setupEscenario1();
    	
    	tipo.registrarDonacion();
    	boolean suministro = tipo.suministrar(1);
    	assertFalse("No deber�a suministrar las bolsas", suministro);
    	assertTrue("El n�mero de bolsas despachadas no deber�a cambiar", tipo.darCantidadSuministrada()==0);
    	assertTrue("El n�mero de bolsas disponibles no deber�a cambiar", tipo.darCantidadDisponible()==1);

    	tipo.registrarDonacion();
    	suministro = tipo.suministrar(1);
    	assertTrue("Deber�a suministrar las bolsas", suministro);
    	assertTrue("El n�mero de bolsas despachadas deber�a incrementar", tipo.darCantidadSuministrada()==1);
    	assertTrue("El n�mero de bolsas disponibles deber�a disminuir", tipo.darCantidadDisponible()==1);
    }
}
