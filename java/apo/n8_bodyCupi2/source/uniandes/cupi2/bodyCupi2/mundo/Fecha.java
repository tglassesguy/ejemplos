package uniandes.cupi2.bodyCupi2.mundo;

import java.io.Serializable;

/**
 * Clase para manejar una fecha: 
 *<b> inv: </b>	<br>
 * minutos>=0 && minutos<60 <br>
 * hora>=0 && hora<=24	<br>
 * dia>=1 && dia<=31	<br>
 * mes>=1 && mes<=12	<br>
 */
public class Fecha implements Serializable
{
    
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la serializaci�n
     */
    private static final long serialVersionUID = 500L;
    
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------
    
    /**
     * La hora
     */
    private int hora;
    
    /**
     * Los minutos
     */
    private int minutos;
    
    /**
     * El d�a
     */
    private int dia;
    
    /**
     * El mes
     */
    private int mes;
    
    /**
     * El a�o
     */
    private int anio;
    

    
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------
    
    /**
     * Construye una nueva fecha a partir de los datos recibidos por par�metro
     * @param elAnio El a�o de la fecha
     * @param elMes El mes de la fecha
     * @param elDia El d�a de la fecha
     * @param laHora La hora de la fecha
     * @param losMinutos Los minutos de la fecha
     */
    public Fecha(int elAnio, int elMes, int elDia, int laHora, int losMinutos)
    {
        anio=elAnio;
        mes=elMes;
        dia=elDia;
        hora=laHora;
        minutos=losMinutos;
        verificarInvariante();

    }
    
    
    //-----------------------------------------------------------------
    // Metodos
    //-----------------------------------------------------------------
    
    /**
     * Retorna en una cadena de caracteres solamente la fecha sin incluir la hora ni los minutos,
     * en el formato: dia-mes-a�o
     * @return La fecha en el formato dia-mes-a�o
     */
    public String darFechaConFormato()
    {
        String laFecha=dia+"-"+mes+"-"+anio;
        return laFecha;
    }
 
    /**
     * Retorna la fecha y hora en el formato dia-mes-aa hh:mm
     */
    public String toString()
    {
    	String fechaYHora= dia+"-"+mes+"-"+anio + " " + hora+":"+minutos+" hh";
    	return fechaYHora;
    }
    
    /**
     * Dada una segunda fecha recibida por par�metro (la cual es del mismo d�a
     * pero con una hora posterior), retorna el n�mero de horas y de minutos de diferencia
     * entre ambas.
     * @param fechaSalida La fecha contra la cual se realiza la comparaci�n
     * @return Un string con el n�mero de horas y minutos de diferencia
     */
    public String darDiferenciaHorasYMinutos(Fecha fechaSalida)
    {
    	String horasYminutosDiferencia="";
    	int minutosDiferencia=-1;
    	int horasDiferencia=-1;
    	int horaSalida=fechaSalida.darHora();
    	int minutosSalida=fechaSalida.darMinutos();
    	if(minutosSalida<minutos)
    	{
    		minutosDiferencia=(60+minutosSalida)-minutos;
    		horasDiferencia=(horaSalida-hora)-1;
    	}
    	else
    	{
    		minutosDiferencia=minutosSalida-minutos;
    		horasDiferencia=horaSalida-hora;
    	}
    	
    	horasYminutosDiferencia=horasDiferencia+" horas y "+minutosDiferencia+" minutos"; 
    	
    	return horasYminutosDiferencia;
    }
    
    /**
     * Retorna el a�o de la fecha
     * @return el a�o
     */
    public int darAnio()
    {
        return anio;
    }
    
    /**
     * Retorna el mes de la fecha
     * @return el mes
     */
    public int darMes()
    {
        return mes;
    }
    
    /**
     * Retorna el d�a de la fecha
     * @return el d�a
     */
    public int darDia()
    {
        return dia;
    }
    
    /**
     * Retorna las horas de la fecha
     * @return las horas
     */
    public int darHora()
    {
        return hora;
    }
    
    /**
     * Retorna los minutos de la fecha
     * @return los minutos
     */
    public int darMinutos()
    {
        return minutos;
    }
    
    
    /**
     * Compara si esta fecha es anterior, igual o posterior a la fecha recibida por par�metro.
     * teniendo en cuenta el a�o, mes y d�a
     * @param fecha2 La fecha contra la cual se va a comparar
     * @return 0 si las dos fechas son iguales,
     * 1 si la fecha actual es posterior a la recibida por par�metro o -1 si es anterior
     */
    public int compararFechas(Fecha fecha2)
    {
        
        if(anio == fecha2.darAnio( ) && mes == fecha2.darMes( ) && dia == fecha2.darDia( ))
           return 0;
        
        else
        {
            if(fecha2.darAnio( )>anio)
                return -1;
            else if(fecha2.darAnio( )<anio)
                return 1;
            else
            {
                if(fecha2.darMes( )>mes)
                    return -1;
                else if(fecha2.darMes( )<mes)
                    return 1;
                else
                {
                    if(fecha2.darDia( )>dia)
                        return -1;
                    else
                        return 1;
                }    
            }
        }
        
    }
    
    //-----------------------------------------------------------------
    // Invariante
    //-----------------------------------------------------------------
    
    
    /**
     * Verifica que los componentes de la fecha est�n en rangos v�lidos
     * minutos>=0 && minutos<60
     * hora>=0 && hora<=24
     * dia>=1 && dia<=31
     * mes>=1 && mes<=12
     */
    private void verificarInvariante()
    {
    	assert(minutos>=0 && minutos<60): "Los minutos no est�n en el rango correcto";
    	assert(hora>=0 && hora<=24): "La hora no est� en el rango correcto";
    	assert(dia>=1 && dia<=31): "El d�a no est� en el rango correcto";
    	assert(mes>=1 && mes<=12): "El mes no est� en el rango correcto";
    	
    }

}

