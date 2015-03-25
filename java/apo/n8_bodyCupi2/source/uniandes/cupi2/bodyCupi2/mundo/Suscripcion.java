package uniandes.cupi2.bodyCupi2.mundo;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Clase para modelar una suscripci�n de un usuario en el gimnasio <br>
 * <b> inv: </b><br>
 * La fechaInicio est� inicializada <br>
 * El tipo de suscripci�n debe ser: AMATEUR, REGULAR o MASTER <br>
 * La duraci�n de la suscripci�n corresponda al tipo de suscripci�n <br>
 */
public class Suscripcion implements Serializable
{
    
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
	public static final long serialVersionUID = 400L;
    
    /**
     * Constante que representa una suscripci�n amateur
     */
    public final static String SUSCRIPCION_AMATEUR="AMATEUR";
    
    /**
     * Constante que representa una suscripci�n regular
     */
    public final static String SUSCRIPCION_REGULAR="REGULAR";
    
    /**
     * Constante que representa una suscripci�n master
     */
    public final static String SUSCRIPCION_MASTER="MASTER";
    
    /**
     * El numero de d�as de una suscripci�n AMATEUR
     */
    public final static int NUM_DIAS_AMATEUR=2;
    
    /**
     * El numero de d�as de una suscripci�n REGULAR
     */
    public final static int NUM_DIAS_REGULAR=3;
    
    /**
     * El numero de d�as de una suscripci�n MASTER
     */
    public final static int NUM_DIAS_MASTER=4;
    
    /**
     * La suscripci�n est� activa
     */
    public final static String ESTADO_ACTIVA="ACTIVA";
    
    /**
     * La suscripci�n est� vencida
     */
    public final static String ESTADO_VENCIDA="VENCIDA";
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * El id �nico de una suscripci�n
     */
    private String id;
    
    /**
     * La fecha de inicio de la suscripci�n
     */
    private Fecha fechaInicio;
    
    /**
     * El tipo de la suscripci�n
     */
    private String tipoSuscripcion;
    
    /**
     * La duraci�n en d�as de la suscripci�n
     */
    private int duracion;

    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Construye una nueva suscripci�n con la informaci�n dada por par�metro <br>
     * <b> post: <b/> Se le asigno a duraci�n, la duraci�n correspondiente seg�n el tipo de suscripci�n <br>
     * @param laId identificaci�n de la suscripci�n - laId !0 null
     * @param laFechaApertura la fecha de apertura - laFechaApertura != null
     * @param elTipoDeSuscripcion el tipo de suscripci�n - elTipoDeSuscripcion != null
     */
    public Suscripcion(String laId, Fecha laFechaApertura, String elTipoDeSuscripcion)
    {
    	id = laId;
        fechaInicio=laFechaApertura;
        tipoSuscripcion=elTipoDeSuscripcion;
        if(tipoSuscripcion.equals( SUSCRIPCION_AMATEUR ))
        {
            duracion=NUM_DIAS_AMATEUR;   
        }
        else if(tipoSuscripcion.equals( SUSCRIPCION_REGULAR ))
        {
            duracion=NUM_DIAS_REGULAR;   
        }
        else
        {
            duracion=NUM_DIAS_MASTER;
        }
        verificarInvariante();
        
    }
    
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------
    
    
    /**
     * Retorna el id de la suscripci�n
     * @return El id de la suscripci�n
     */
    public String darId()
    {
        return id;
    }
    
    /**
     * Retorna la fecha de inicio de la suscripci�n con formato
     * @return La fecha de inicio de la suscripci�n con formato
     */
    public String darFechaInicioConFormato()
    {
        return fechaInicio.toString( );
    }

    
    /**
     * Retorna el tipo de la suscripci�n
     * @return El tipo de la suscripci�n 
     */
    public String darTipoSuscripcion()
    {
        return tipoSuscripcion;
    }
    
    /**
     * Retorna la duraci�n de la suscripci�n
     * @return La duraci�n de la suscripci�n
     */
    public int darDuracion()
    {
    	return duracion;
    }
    
    /**
     * La suscripci�n est� vencida si la fecha actual es mayor o igual a la fecha de apertura
     * de la suscripci�n m�s el n�mero de d�as que esta dura
     * @return El estado de la suscripci�n ESTADO_ACTIVA o ESTADO_VENCIDA
     */
    public String darEstado()
    {
        String estado="";
        
        //Obtener la fecha actual
        Calendar fechaTempActual=Calendar.getInstance( );
        int dia=fechaTempActual.get( Calendar.DAY_OF_MONTH );
        int mes=fechaTempActual.get( Calendar.MONTH)+1;
        int anio=fechaTempActual.get( Calendar.YEAR );
        Fecha fechaActual= new Fecha(anio, mes, dia, 0, 0);
        
        //Obtener la fecha de vencimiento: Suma a la fecha de inicio el n�mero de d�as de la suscripci�n
        Calendar fechaTempVencimientoSuscripcion=Calendar.getInstance( );
        fechaTempVencimientoSuscripcion.set( fechaInicio.darAnio( ), fechaInicio.darMes( )-1, fechaInicio.darDia( ) );
        fechaTempVencimientoSuscripcion.add( Calendar.DAY_OF_MONTH, darDuracion());
        
        int diaVencimiento=fechaTempVencimientoSuscripcion.get( Calendar.DAY_OF_MONTH );
        int mesVencimiento=fechaTempVencimientoSuscripcion.get( Calendar.MONTH)+1;
        int anioVencimiento=fechaTempVencimientoSuscripcion.get( Calendar.YEAR );
        Fecha fechaVencimiento=new Fecha(anioVencimiento,mesVencimiento,diaVencimiento,0,0);
        
        //Comparar si la fecha actual es igual o posterior a la fecha de vencimiento de la suscripci�n
        int comparacion=fechaActual.compararFechas( fechaVencimiento );
        if(comparacion==0 || comparacion==1)
            estado=ESTADO_VENCIDA;
        else
            estado=ESTADO_ACTIVA;
        return estado; 
    }
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    
    /**
     * Verifica el invariante de la clase <br>
	 * <b> inv: </b><br>
	 * La fechaInicio est� inicializada <br>
	 * El tipo de suscripci�n debe ser: AMATEUR, REGULAR o MASTER <br>
	 * La duraci�n de la suscripci�n corresponda al tipo de suscripci�n <br>
	 */
    private void verificarInvariante()
    {
    	assert(fechaInicio!=null): "La fecha de inicio debe estar inicializada";
    	assert(tipoSuscripcion.equals(SUSCRIPCION_AMATEUR)||tipoSuscripcion.equals(SUSCRIPCION_REGULAR)||tipoSuscripcion.equals(SUSCRIPCION_MASTER)): "El tipo de suscripci�n debe ser: AMATEUR, REGULAR o MASTER";
    	if(tipoSuscripcion.equals( SUSCRIPCION_AMATEUR ))
        {
            assert(duracion==NUM_DIAS_AMATEUR):"La duraci�n no es la esperada";   
        }
        else if(tipoSuscripcion.equals( SUSCRIPCION_REGULAR ))
        {
        	assert(duracion==NUM_DIAS_REGULAR):"La duraci�n no es la esperada";   
        }
        else
        {
        	assert(duracion==NUM_DIAS_MASTER):"La duraci�n no es la esperada";
        }
    }

}
