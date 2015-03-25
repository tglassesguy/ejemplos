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

package uniandes.cupi2.cupiFinca.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import junit.framework.TestCase;
import uniandes.cupi2.cupiFinca.mundo.Casa;
import uniandes.cupi2.cupiFinca.mundo.Construccion;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Construccion esten bien implementados
 */
public class ConstruccionTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Construccion construccion;

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Construye una nueva Construcci�n
     * 
     */
    private void setupEscenario1( )
    {
        construccion = new Casa( 0, 0, 30 );
    }

    /**
     * Prueba del constructor de un elemento de construcci�n
     */
    public void testConstruccion( )
    {
        setupEscenario1( );

        // C�digo de la prueba de valores iniciales
        assertEquals( "La finca debe tener el nombre definido por defecto para el terreno", construccion.darNombre( ), Casa.NOMBRE_CONSTRUCCION );
        assertEquals( "La finca debe tener el costo definido por defecto para el terreno", construccion.darCosto( ), Casa.COSTO_CONSTRUCCION );
        assertEquals( "El tiempo inicial es el definido por par�metro", construccion.darTiempo( ), 0 );
        assertEquals( "El tiempo inicial es el definido por par�metro", construccion.darGanancia( ), 0 );
        assertFalse( "El estado de recolecci�n del producto que se cultiva en el terreno debe ser verdadero", construccion.esRecolectable( ) );
    }
    
    
    /**
     * Prueba que el terreno de construcci�n se guarda con el formato correspondiente
     */
    public void testGuardarTerreno(){
setupEscenario1( );
        
        File file = new File("./test/data/testCultivable.dat");
        PrintWriter pw;
        
        //1. Guarda el archivo
        try
        {
            pw = new PrintWriter( new FileOutputStream( file ), true );
            construccion.guardarTerreno( pw );
            pw.close( );
        }
        catch( FileNotFoundException e )
        {
            fail("No debe haber error guardando el archivo.");
        }
        
        //2. Carga el archivo
        BufferedReader br;
        try
        {
            br = new BufferedReader( new FileReader( file ) );
            String linea = br.readLine( );
            if(linea == null){
                fail("La l�nea no deber�a estar vac�a");
            }
            String[] terminos = linea.split( ";" );
            assertEquals( "El nombre del producto debe ser el mismo", terminos[0], construccion.darNombre( ) );
        }
        catch( FileNotFoundException e )
        {
            fail("No debe haber error abriendo el archivo.");
        }
        catch( IOException e )
        {
            fail("No debe haber error leyendo el archivo.");
        }
    }
}