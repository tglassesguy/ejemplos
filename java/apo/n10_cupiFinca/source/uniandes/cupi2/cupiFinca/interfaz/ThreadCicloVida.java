/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n10_cupiFinca
 * Autor: Luis Ricardo Ruiz Rodr�guez - 28-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiFinca.interfaz;

/**
 * Hilo de Ejecuci�n que automatiza el ciclo de vida de los productos
 */
public class ThreadCicloVida extends Thread
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicaci�n
     */
    private InterfazCupiFinca principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el hilo de ejecuci�n del manejo del ciclo con la ventana principal
     * @param ventana La ventana principal de la interfaz. ventana != null
     */
    public ThreadCicloVida( InterfazCupiFinca ventana )
    {
        principal = ventana;
    }

    /**
     * El m�todo para las acciones del hilo de ejecuci�n
     */
    public void run( )
    {
        try
        {
            while( true )
            {
                principal.simularEtapaCicloVidaTerrenos( );
                sleep( 1000 );
            }
        }
        catch( InterruptedException e )
        {
            e.printStackTrace( );
        }
    }
}
