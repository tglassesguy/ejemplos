/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_recetario
 * Autor: Catalina Rodr�guez - 01-feb-2011
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.recetario.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import uniandes.cupi2.recetario.mundo.Receta;
import uniandes.cupi2.recetario.mundo.Recetario;

/**
 * Esta es la clase usada para verificar que los m�todos de la clase Recetario est�n correctamente implementados
 */
public class RecetarioTest extends TestCase
{
    //-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

    /**
     * Es la clase donde se har�n las pruebas
     */
    private Recetario recetario;

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------

    /**
     * Construye un nuevo Recetario vac�o
     *  
     */
    private void setupEscenario1( )
    {
        recetario = new Recetario( );
    }

    /**
     * Construye un nuevo Recetario con 3 recetas
     */
    private void setupEscenario2()
    {
    	recetario = new Recetario( );
    	recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata l�quida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	recetario.agregarReceta("Dulce de casta�as", "./data/imagenes/dulceCastanas.jpg", 5, Receta.CATEGORIA_6, 60, new String[]{"Almendra molida","Az�car","Canela","Casta�as","Chocolate","Huevos","Lim�n"}, "Mezclar los ingredientes y poner en la nevera");
    	recetario.agregarReceta("Berenjenas al Horno", "./data/imagenes/berenjenas.jpg", 3, Receta.CATEGORIA_3, 30, new String[]{"Berenjenas","Carne picada","Huevos","Sal","Queso rallado"}, "Mezclar los ingredientes y poner al horno");
    }
  
    /**
     * Construye un nuevo Recetario con 8 recetas. Escenario usado para probar los m�todos de ordenamiento y de b�squeda
     */
    private void setupEscenario3()
    {
    	recetario = new Recetario( );
    	recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata l�quida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	recetario.agregarReceta("Dulce de casta�as", "./data/imagenes/dulceCastanas.jpg", 5, Receta.CATEGORIA_6, 60, new String[]{"Almendra molida","Az�car","Canela","Casta�as","Chocolate","Huevos","Lim�n"}, "Mezclar los ingredientes y poner en la nevera");
    	recetario.agregarReceta("Berenjenas al Horno", "./data/imagenes/berenjenas.jpg", 3, Receta.CATEGORIA_3, 30, new String[]{"Berenjenas","Carne picada","Huevos","Sal","Queso rallado"}, "Mezclar los ingredientes y poner al horno");
    	recetario.agregarReceta("Pavo en Escabeche", "./data/imagenes/pavoEscabeche.jpg", 7, Receta.CATEGORIA_5, 180, new String[]{"Aceite de oliva","Ajo","Almendras","Az�car","Cebolla","Ciruelas pasas","D�tiles","Higos","Laurel","Orejones","Pasas de corinto","Pavo"}, "Mezclar los ingredientes y poner a cocinar");
    	recetario.agregarReceta("Pasta a la Carbonara", "./data/imagenes/pastaCarbonara.jpg", 4, Receta.CATEGORIA_2, 30, new String[]{"Aceite","Pasta","Huevo","Pimienta negra","Queso pecorino","Sal","Tocino"}, "Mezclar los ingredientes y cocinar");
    	recetario.agregarReceta("Tarta de At�n", "./data/imagenes/tartaAtun.jpg", 8, Receta.CATEGORIA_4, 40, new String[]{"At�n","Pepinillos","Alcaparras","Cebollino","Salsa Perrins","Mostaza Dijon","Aceite de oliva","Zumo lim�n","Lima kafir","Salsa de Soja","Lechuga","Pimienta","Sal"}, "Picamos, mezclamos los ingredientes y cocinamos");
    	recetario.agregarReceta("Cerdo Agridulce", "./data/imagenes/cerdoAgridulce.jpg", 5, Receta.CATEGORIA_5, 45, new String[]{"Maicena","Sal","Pimienta","Cerdo en cubos","Aceite de girasol","Pimiento","Pi�a"}, "Mezclar los ingredientes y poner al horno");
    	recetario.agregarReceta("Mousse de Maracuy�", "./data/imagenes/mousseMaracuya.jpg", 1, Receta.CATEGORIA_6, 15, new String[]{"Leche condensada","Crema de leche","Maracuy�s"}, "Mezclar los ingredientes y refrigerar");
    }
    
    /**
     * Prueba 1 - Verifica que al crearse la clase recetario se inicialice la lista de recetas y que su tama�o sea igual a cero 
     */
    public void testRecetario( )
    {
        setupEscenario1( );
        assertNotNull("La lista debio haberse inicializado", recetario.darRecetas());
        assertEquals("La lista de recetas debe crearse vac�a", 0, recetario.darRecetas().size());
    }
    
    /**
     * Prueba 2 - Verifica que se pueda agregar una receta cuando no se ha agregado ninguna
     */
    public void testRegistrarPrimeraReceta()
    {
        setupEscenario1( );
        
        //Verificar que se haya ingresado la receta
        boolean recetaAgregada = recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata l�quida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	assertTrue( "Siempre se debe poder agregar la primera receta", recetaAgregada);
        
    	ArrayList recetas = recetario.darRecetas();
        assertEquals( "La receta no se agreg� a la lista de recetas", 1, recetas.size() );
        
        //Datos de la receta que se agreg� en la lista
        Receta receta = (Receta) recetas.get( 0 );
        assertEquals("El nombre de la receta no es el esperado", "Sopa de Pepinos", receta.darNombre());
        assertEquals("La categor�a de la receta no es la esperada", Receta.CATEGORIA_1, receta.darCategoria());
        assertEquals("La dificultad de la receta no es la esperada", 2, receta.darDificultad());
        assertEquals("La foto de la receta no es la esperada", "./data/imagenes/sopaPepino.jpg", receta.darFoto());
        assertEquals("Las instrucciones de la receta no son las esperadas",	"Mezclar los ingredientes y poner a cocinar", receta.darInstrucciones());
        assertEquals("El tiempo de preparaci�n de la receta no es el esperado", 25, receta.darTiempoPreparacion());        
    }
    
    /**
     * Prueba 3 - Verifica que se pueda registrar una receta cuando ya existen recetas en el recetario
     */
    public void testRegistrarReceta()
    {
        setupEscenario2( );
        
        //Verificar que se haya ingresado la receta
        boolean recetaAgregada = recetario.agregarReceta("Tarta de At�n", "./data/imagenes/tartaAtun.jpg", 8, Receta.CATEGORIA_4, 40, new String[]{"At�n","Pepinillos","Alcaparras","Cebollino","Salsa Perrins","Mostaza Dijon","Aceite de oliva","Zumo lim�n","Lima kafir","Salsa de Soja","Lechuga","Pimienta","Sal"}, "Picamos, mezclamos los ingredientes y cocinamos");
    	assertTrue( "Error al agregar la receta", recetaAgregada);
        
    	ArrayList recetas = recetario.darRecetas();
        assertEquals( "La receta no se agreg� a la lista de recetas", 4, recetas.size() );
        
        //Datos de la receta que se agreg� en la lista
        Receta receta = (Receta) recetas.get( 3 );
        assertEquals("El nombre de la receta no es el esperado", "Tarta de At�n", receta.darNombre());
        assertEquals("La categor�a de la receta no es la esperada", Receta.CATEGORIA_4, receta.darCategoria());
        assertEquals("La dificultad de la receta no es la esperada", 8, receta.darDificultad());
        assertEquals("La foto de la receta no es la esperada", "./data/imagenes/tartaAtun.jpg", receta.darFoto());
        assertEquals("Las instrucciones de la receta no son las esperadas",	"Picamos, mezclamos los ingredientes y cocinamos", receta.darInstrucciones());
        assertEquals("El tiempo de preparaci�n de la receta no es el esperado", 40, receta.darTiempoPreparacion());  
    }
    
    /**
     * Prueba 4 - Verifica que no se agregue una receta si ya existe en el recetario una receta con el mismo nombre
     */
    public void testRegistrarRecetaConNombreExistente()
    {
        setupEscenario2( );
        
        //Verificar que no se haya ingresado la receta
        boolean recetaAgregada = recetario.agregarReceta("Sopa de Pepinos", "./data/imagenes/sopaPepino.jpg", 2, Receta.CATEGORIA_1, 25, new String[]{"Caldo de pollo", "Nata l�quida", "Pepinos","Queso"}, "Mezclar los ingredientes y poner a cocinar");
    	assertFalse( "No debi� haber registrado la receta", recetaAgregada);
        assertEquals( "El n�mero de recetas del recetario no debi� cambiar", 3, recetario.darRecetas().size() );
    }

    /**
     * Prueba 5 - Verifica que busque correctamente una receta con un nombre dado <br>
     * Si la receta existe se debe retornar el �ndice de la receta en la lista del recetario
     */
    public void testBuscarReceta()
    {
        setupEscenario2( );
        
        int indice = recetario.buscarReceta("Berenjenas al Horno");
        assertTrue( "Debi� haber encontrado una receta con el nombre dado", indice > -1);
        assertEquals( "El �ndice retornado no es el correcto", 2, indice);
    }
    
    /**
     * Prueba 6 - Verifica que busque correctamente una receta con un nombre dado <br>
     * Si la receta no existe se debe retornar -1
     */
    public void testBuscarRecetaNoExistente()
    {
        setupEscenario2( );
        
        int indice = recetario.buscarReceta("Mondongo");
        assertEquals( "No deber�a encontrar una receta con el nombre dado", -1, indice);
    }
    
    /**
     * Prueba 7 - Verifica el m�todo ordenar por nombre
     * Caso de prueba 1: Se espera que "Berenjenas al horno" est� en la primera posici�n
     * Caso de prueba 2: Se espera que "Cerdo Agridulce" est� en la segunda posici�n
     * Caso de prueba 3: Se espera que "Dulce de casta�as" est� en la tercera posici�n
     * Caso de prueba 4: Se espera que "Mousse de Maracuy�" est� en la cuarta posici�n
     * Caso de prueba 5: Se espera que "Pasta a la Carbonara" est� en la quinta posici�n
     * Caso de prueba 6: Se espera que "Pavo en Escabeche" est� en la sexta posici�n
     * Caso de prueba 7: Se espera que "Sopa de Pepinos" est� en la septima posici�n
     * Caso de prueba 8: Se espera que "Tarta de At�n" est� en la octava posici�n
     */
    public void testOrdenarPorNombre()
    {
    	setupEscenario3();
    	recetario.ordenarPorNombre();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Berenjenas al Horno","Cerdo Agridulce","Dulce de casta�as","Mousse de Maracuy�",
    			"Pasta a la Carbonara","Pavo en Escabeche","Sopa de Pepinos","Tarta de At�n"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Fall� el ordenamiento por nombre",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 8 - Verifica el m�todo ordenar por dificultad
	 * Caso de prueba 1: Se espera que "Mousse de Maracuy�" est� en la primera posici�n
     * Caso de prueba 2: Se espera que "Sopa de Pepinos" est� en la segunda posici�n
     * Caso de prueba 3: Se espera que "Berenjenas al horno" est� en la tercera posici�n
     * Caso de prueba 4: Se espera que "Pasta a la Carbonara" est� en la cuarta posici�n
     * Caso de prueba 5: Se espera que "Dulce de casta�as" est� en la quinta posici�n
     * Caso de prueba 6: Se espera que "Cerdo Agridulce" est� en la sexta posici�n
     * Caso de prueba 7: Se espera que "Pavo en Escabeche" est� en la septima posici�n
     * Caso de prueba 8: Se espera que "Tarta de At�n" est� en la octava posici�n
     */
    public void testOrdenarPorDificultad()
    {
    	setupEscenario3();
    	recetario.ordenarPorDificultad();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Mousse de Maracuy�","Sopa de Pepinos","Berenjenas al Horno","Pasta a la Carbonara","Dulce de casta�as","Cerdo Agridulce",
    			"Pavo en Escabeche","Tarta de At�n"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Fall� el ordenamiento por dificultad",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}

    }
    
    /**
     * Prueba 9 - Verifica el m�todo ordenar por n�mero de ingredientes
     * Caso de prueba 1: Se espera que "Mousse de Maracuy�" est� en la primera posici�n
     * Caso de prueba 2: Se espera que "Sopa de Pepinos" est� en la segunda posici�n
     * Caso de prueba 3: Se espera que "Berenjenas al horno" est� en la tercera posici�n
     * Caso de prueba 4: Se espera que "Pasta a la Carbonara" est� en la cuarta posici�n
     * Caso de prueba 5: Se espera que "Cerdo Agridulce" est� en la quinta posici�n
     * Caso de prueba 6: Se espera que "Dulce de casta�as" est� en la sexta posici�n
     * Caso de prueba 7: Se espera que "Pavo en Escabeche" est� en la septima posici�n
     * Caso de prueba 8: Se espera que "Tarta de At�n" est� en la octava posici�n
     */
    public void testOrdenarPorNumeroIngredientes()
    {
    	setupEscenario3();
    	recetario.ordenarPorNumeroIngredientes();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Mousse de Maracuy�","Sopa de Pepinos","Berenjenas al Horno","Pasta a la Carbonara","Cerdo Agridulce","Dulce de casta�as","Pavo en Escabeche",
    			"Tarta de At�n"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Fall� el ordenamiento por n�mero de ingredientes",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 10 - Verifica el m�todo ordenar por categor�a
     * Caso de prueba 1: Se espera que "Sopa de Pepinos" est� en la primera posici�n
     * Caso de prueba 2: Se espera que "Dulce de casta�as" est� en la segunda posici�n
     * Caso de prueba 3: Se espera que "Mousse de Maracuy�" est� en la tercera posici�n
     * Caso de prueba 4: Se espera que "Tarta de At�n" est� en la cuarta posici�n
     * Caso de prueba 5: Se espera que "Pasta a la Carbonara" est� en la quinta posici�n
     * Caso de prueba 6: Se espera que "Berenjenas al horno" est� en la sexta posici�n
     * Caso de prueba 7: Se espera que "Cerdo Agridulce" est� en la septima posici�n
     * Caso de prueba 8: Se espera que "Pavo en Escabeche" est� en la octava posici�n
     */
    public void testOrdenarPorCategoria()
    {
    	setupEscenario3();
    	recetario.ordenarPorCategoria();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Sopa de Pepinos","Dulce de casta�as","Mousse de Maracuy�","Tarta de At�n","Pasta a la Carbonara","Berenjenas al Horno","Cerdo Agridulce",
    			"Pavo en Escabeche"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Fall� el ordenamiento por categor�a",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 11 - Verifica el m�todo ordenar por tiempo de preparaci�n
     * Caso de prueba 1: Se espera que "Mousse de Maracuy�" est� en la primera posici�n
     * Caso de prueba 2: Se espera que "Sopa de Pepinos" est� en la segunda posici�n
     * Caso de prueba 3: Se espera que "Berenjenas al horno" est� en la tercera posici�n
     * Caso de prueba 4: Se espera que "Pasta a la Carbonara" est� en la cuarta posici�n
     * Caso de prueba 5: Se espera que "Tarta de At�n" est� en la quinta posici�n
     * Caso de prueba 6: Se espera que "Cerdo Agridulce" est� en la sexta posici�n
     * Caso de prueba 7: Se espera que "Dulce de casta�as" est� en la septima posici�n
     * Caso de prueba 8: Se espera que "Pavo en Escabeche" est� en la octava posici�n
     */
    public void testOrdenarPorTiempoPreparacion()
    {
    	setupEscenario3();
    	recetario.ordenarPorTiempoPreparacion();
    	ArrayList recetas = recetario.darRecetas();
    	String[] nombres = {"Mousse de Maracuy�","Sopa de Pepinos","Berenjenas al Horno","Pasta a la Carbonara","Tarta de At�n","Cerdo Agridulce","Dulce de casta�as",
    			"Pavo en Escabeche"};
    	
    	for (int i = 0; i < nombres.length; i++) {
    		assertEquals("Fall� el ordenamiento por categor�a",nombres[i],((Receta) recetas.get(i)).darNombre());
    	}
    }
    
    /**
     * Prueba 12 - Verifica que el arreglo de recetas no retorne null, y que tenga el tama�o esperado
     */
    public void testDarRecetas()
    {
        setupEscenario2( );
        
        ArrayList recetas = recetario.darRecetas();
        assertNotNull( "La lista de recetas no debe ser null", recetas);
        assertEquals( "El tama�o de la lista de recetas no es correcto", 3, recetas.size( ) );
    }
    
    /**
     * Prueba 13 - Verifica que busque correctamente una receta con un nombre usando b�squeda binaria <br>
     * Si la receta existe se debe retornar el �ndice de la receta en la lista del recetario
     */
    public void testBuscarBinarioPorNombre()
    {
        setupEscenario2( );
        
        recetario.ordenarPorNombre();
        int indice = recetario.buscarBinarioPorNombre("Sopa de pepinos");
        assertTrue( "Debi� haber encontrado una receta con el nombre dado", indice > -1);
        assertEquals( "El �ndice retornado no es el correcto", 2, indice);
    }
    
    /**
     * Prueba 14 - Verifica que busque correctamente una receta con un nombre usando b�squeda binaria <br>
     * Si la receta no existe se debe retornar -1
     */
    public void testBuscarBinarioPorNombreNoExistente()
    {
        setupEscenario2( );
        
        recetario.ordenarPorNombre();
        int indice = recetario.buscarBinarioPorNombre("Mondongo");
        assertEquals( "No deber�a encontrar una receta con el nombre dado", -1, indice);
    }
    
    /**
     * Prueba 15 - Verifica el m�todo buscar receta m�s f�cil
     */
    public void testBuscarRecetaMasFacil()
    {
    	setupEscenario3();
    	
    	int indice = recetario.buscarRecetaMasFacil();
    	assertEquals("La receta m�s f�cil no es correcta", 7, indice);
    }
    
    /**
    * Prueba 16 - Verifica el m�todo buscar receta m�s dif�cil
    */
   public void testBuscarRecetaMasDificil()
   {
	   setupEscenario3();
   	
	   int indice = recetario.buscarRecetaMasDificil();
	   assertEquals("La receta m�s dif�cil no es correcta", 5, indice);
   }
   
   /**
    * Prueba 17 - Verifica el m�todo buscar recetas con ingrediente <br>
    * Verifica que el n�mero de recetas con el ingrediente dado sea el esperado <br>+
    * Verifica que las receta tengan el ingrediente dado 
    */
   public void testBuscarRecetasConIngrediente()
   {
	   setupEscenario3();
	   
	   ArrayList recetas = recetario.buscarRecetasConIngrediente("Aceite");
	   assertEquals("No se realiz� la b�squeda correctamente", 4, recetas.size());
	   
	   for(int i=0; i<recetas.size( ); i++)
       {
           Receta receta = (Receta) recetas.get(i);
           assertTrue( "No se buscaron correctamente las recetas", receta.tieneIngrediente("Aceite") );
       }
   }
}