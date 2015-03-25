/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n2_bancoDonacion
 * Autor: Catalina Rodr�guez - 11-ago-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.bancoSangre.test;

import junit.framework.TestCase;
import uniandes.cupi2.bancoSangre.mundo.BancoSangre;
import uniandes.cupi2.bancoSangre.mundo.TipoSangre;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase BancoSangre est�n correctamente implementados
 */
public class BancoSangreTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private BancoSangre bancoSangre;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo BancoSangre vac�o
     */
    private void setupEscenario1( )
    {
        bancoSangre = new BancoSangre( );
    }
    
    /**
     * Construye un nuevo BancoSangre con donaciones
     */
    private void setupEscenario2()
    {
    	bancoSangre = new BancoSangre();
    	
    	bancoSangre.registarDonacion(23, BancoSangre.FEMENINO, 55.3, TipoSangre.TIPO_O, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(25, BancoSangre.MASCULINO, 60, TipoSangre.TIPO_O, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_B, TipoSangre.RH_N, false);    	
    }
    
    /**
     * Construye un nuevo BancoSangre con donaciones
     */
    private void setupEscenario3()
    {
    	bancoSangre = new BancoSangre();
    	
    	bancoSangre.registarDonacion(23, BancoSangre.FEMENINO, 55.3, TipoSangre.TIPO_A, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(25, BancoSangre.MASCULINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);    	
    }
    
    /**
     * Prueba 1 - Prueba el m�todo constructor <br>
     * M�todos a probar: <br>
     * BancoSangre, darTipo1, darTipo2, darTipo3, darTipo4 
     */
    public void testBancoSangre( )
    {
        setupEscenario1( );
        
        assertNotNull("No cre� el tipo de sangre 1", bancoSangre.darTipo1());
        assertNotNull("No cre� el tipo de sangre 2", bancoSangre.darTipo2());
        assertNotNull("No cre� el tipo de sangre 3", bancoSangre.darTipo3());
        assertNotNull("No cre� el tipo de sangre 4", bancoSangre.darTipo4());    
    }
    
    /**
     * Prueba 2 - Prueba el m�todo registrarDonacion <br>
     * M�todos a probar: <br>
     * registrarDonacion
     */
    public void testRegistarDonacion()
    {
    	setupEscenario1();
    	
    	String respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	assertTrue("Deber�a realizar la donaci�n", respuesta.toLowerCase().contains(BancoSangre.DONACION.toLowerCase()));
    	
    	TipoSangre tipo = bancoSangre.darTipo2();
    	assertEquals("Deber�a incrementar el n�mero de bolsas disponibles", 1, tipo.darCantidadDisponible());
    	
    	respuesta = bancoSangre.registarDonacion(16, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	assertTrue("No deber�a realizar la donaci�n", respuesta.toLowerCase().contains(BancoSangre.ERROR_DONADOR.toLowerCase()));
    	
    	respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, true);
    	assertTrue("No deber�a realizar la donaci�n", respuesta.toLowerCase().contains(BancoSangre.ERROR_DONADOR.toLowerCase()));
    	
    	respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 49.5, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	assertTrue("No deber�a realizar la donaci�n", respuesta.toLowerCase().contains(BancoSangre.ERROR_DONADOR.toLowerCase()));
    	
    	respuesta = bancoSangre.registarDonacion(25, BancoSangre.FEMENINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_P, false);
    	assertTrue("No deber�a realizar la donaci�n", respuesta.toLowerCase().contains(BancoSangre.ERROR_TIPO_SANGRE.toLowerCase() + "b+"));
    }

    /**
     * Prueba 3 - Prueba del m�todo obtenerTipoSangreMasDisponible <br>
     * M�todos a probar: <br>
     * obtenerTipoSangreMasDisponible, registarDonacion
     */
    public void testObtenerTipoSangreMasDisponible()
    {
    	setupEscenario1();
    	
    	String respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertEquals("El tipo de sangre no es el correcto", BancoSangre.NINGUNO,respuesta);
    	
    	setupEscenario2();
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o-"));
    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_B, TipoSangre.RH_N, false);    	
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("b-"));
    	
    	setupEscenario3();
    	  	  	
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("a-"));
    	
    	bancoSangre.registarDonacion(25, BancoSangre.MASCULINO, 60, TipoSangre.TIPO_B, TipoSangre.RH_N, false);
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);   
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("b-"));
    	
    	bancoSangre.registarDonacion(60, BancoSangre.FEMENINO, 80, TipoSangre.TIPO_O, TipoSangre.RH_P, false);
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);   
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o+"));
    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_O, TipoSangre.RH_N, false);   
    	respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o-"));
    }
    
    /**
     * Prueba 4 - Prueba del m�todo obtenerTipoSangreMasSuministrada <br>
     * M�todos a probar: <br>
     * obtenerTipoSangreMasSuministrada, suministrarSangre
     */
    public void testObtenerTipoSangreMasSuministrada()
    {
    	setupEscenario1();
    	
    	String respuesta = bancoSangre.obtenerTipoSangreMasDisponible();
    	assertEquals("El tipo de sangre no es el correcto", BancoSangre.NINGUNO,respuesta);
    	
    	setupEscenario2();
    	
    	respuesta = bancoSangre.obtenerTipoSangreMasSuministrado();
    	assertTrue("Respuesta incorrecta", respuesta.toLowerCase().contains(BancoSangre.NINGUNO.toLowerCase()));
    	
    	bancoSangre.suministrarSangre(TipoSangre.TIPO_O, TipoSangre.RH_N, 1);    	
    	respuesta = bancoSangre.obtenerTipoSangreMasSuministrado();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("o-"));
    }
    
    /**
     * Prueba 5 - Prueba del m�todo obtenerTipoSangreMenosDisponible <br>
     * M�todos a probar: <br>
     * obtenerTipoSangreMenosDisponible, suministrarSangre
     */
    public void testObtenerTipoSangreMenosDisponible()
    {
    	setupEscenario2();
    	
    	String respuesta = bancoSangre.obtenerTipoSangreMenosDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("a-"));
    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_A, TipoSangre.RH_N, false);    	
    	bancoSangre.registarDonacion(50, BancoSangre.FEMENINO, 55, TipoSangre.TIPO_A, TipoSangre.RH_N, false);    	
    	respuesta = bancoSangre.obtenerTipoSangreMenosDisponible();
    	assertTrue("El tipo de sangre no es el correcto", respuesta.toLowerCase().contains("b-"));
    }
    
    /**
     * Prueba 6 - Prueba del m�todo suministrarSangre<br>
     * M�todos a probar: <br>
     * suministrarSangre
     */
    public void testSuministrarSangre()
    {
    	setupEscenario2();
    	
    	String respuesta = bancoSangre.suministrarSangre(TipoSangre.TIPO_O, TipoSangre.RH_N, 1);
    	assertTrue("Deber�a suministrar las bolsas de sangre", respuesta.toLowerCase().contains(BancoSangre.SUMINITRAR.toLowerCase()));
    	
    	TipoSangre tipo = bancoSangre.darTipo4();
    	assertEquals("Deber�a incrementar el n�mero de bolsas despachadas", 1, tipo.darCantidadSuministrada());
    	    	
    	respuesta = bancoSangre.suministrarSangre(TipoSangre.TIPO_O, TipoSangre.RH_N, 1);
    	assertTrue("No deber�a suministrar las bolsas de sangre", respuesta.toLowerCase().contains(BancoSangre.ERROR_CANTIDAD.toLowerCase()));
    }
}