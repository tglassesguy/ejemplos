package uniandes.cupi2.bodyCupi2.test;


import junit.framework.TestCase;
import uniandes.cupi2.bodyCupi2.mundo.Fecha;
import uniandes.cupi2.bodyCupi2.mundo.Suscripcion;

/**
 * Clase para probar los m�todos de la clase Suscripci�n   
 *
 */
public class SuscripcionTest extends TestCase
{
	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Suscripcion suscripcion;
    
    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Construye una nueva suscripci�n de tipo NOMBRE_SUSCRIPCION_CORTA
     */
    public void setupEscenario1()
    {
        String tipoSuscripcion=Suscripcion.SUSCRIPCION_AMATEUR;
        Fecha laFechaApertura=new Fecha(2011,2,22,0,0);
        suscripcion=new Suscripcion( "11", laFechaApertura, tipoSuscripcion );
    }
    
    /**
     * Verifica que al crear una suscripci�n el tipo asignado sea correcto
     */
    public void testSuscripcion()
    {
        setupEscenario1();
        String tipo=suscripcion.darTipoSuscripcion( );
        assertEquals("El tipo de suscripci�n no es el esperado",Suscripcion.SUSCRIPCION_AMATEUR, tipo );
        int duracion=suscripcion.darDuracion();
        assertEquals("La duraci�n de la suscripci�n no es la esperada",Suscripcion.NUM_DIAS_AMATEUR,duracion);
        assertEquals("El ID no se asign� correctamente","11",suscripcion.darId( ));

    }
        
    /**
     * Valida que se calcule correctamente si la suscripci�n est� vencida
     */
    public void testDarEstado()
    {
        setupEscenario1();
        String estado=suscripcion.darEstado( );
        assertEquals( "La suscripci�n deber�a estar vencida", Suscripcion.ESTADO_VENCIDA,estado );   
    }
    
    /**
     * Valida que se retorne la fecha de inicio de la suscripci�n con un formato apropiado
     */
    public void testDarFechaInicioConFormato()
    {
        setupEscenario1( );
        new Fecha(2011,2,22,0,0);
        String fechaFormato=suscripcion.darFechaInicioConFormato( );
        assertEquals( "La fecha no se est� retornando en el formato esperado" ,"22-2-2011 0:0 hh", fechaFormato );
    }
    

}
