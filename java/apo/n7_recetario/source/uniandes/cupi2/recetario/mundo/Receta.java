package uniandes.cupi2.recetario.mundo;

/**
 * Clase que representa una receta del recetario
 * <b>inv: </b> <br>
 * inv:
 * nombre != null && nombre !="" <br>
 * foto != null && foto != "" <br>
 * dificultad > 0 && dificultad <= 10 <br>
 * categoria != null && categoria != "" <br>
 * tiempoPreparacion > 0 <br>
 * ingredientes != null <br>
 * instrucciones != null && instrucciones != "" <br>
 * <br>
 * La categor�a debe corresponder a una de las constantes de la clase <br>
 * La receta debe tener al menos un ingrediente <br>
 * La dificultad debe estar dentro del rango establecido de acuerdo al n�mero de ingredientes <br>
 * 
 */
public class Receta 
{
	//-----------------------------------------------------------------
    // Constantes
    //-----------------------------------------------------------------

	/**
	 * Constante que representa la categor�a Sopas y Cremas
	 */
	public final static String CATEGORIA_1 = "Sopas y Cremas";
	
	/**
	 * Constante que representa la categor�a Pastas y Arroces
	 */
	public final static String CATEGORIA_2 = "Pastas y Arroces";
	
	/**
	 * Constante que representa la categor�a Ensaladas
	 */
	public final static String CATEGORIA_3 = "Ensaladas";
	
	/**
	 * Constante que representa la categor�a Pescados y Mariscos
	 */
	public final static String CATEGORIA_4 = "Pescados y Mariscos";

	/**
	 * Constante que representa la categor�a Carnes
	 */
	public final static String CATEGORIA_5 = "Carnes";
	
	/**
	 * Constante que representa la categor�a Postres
	 */
	public final static String CATEGORIA_6 = "Postres";

	//-----------------------------------------------------------------
    // Atributos
    //-----------------------------------------------------------------

	/**
	 * Nombre del plato
	 */
	private String nombre;
	
	/**
	 * Ruta de la foto del plato
	 */
	private String foto;
	
	/**
	 * Dificultad de la receta
	 */
	private int dificultad;
	
	/**
	 * Categor�a de la receta
	 */
	private String categoria;
	
	/**
	 * Tiempo de preparaci�n (en minutos) del plato
	 */
	private int tiempoPreparacion;
	
	/**
	 * Ingredientes de la receta
	 */
	private String[] ingredientes;
	
	/**
	 * Instrucciones para la preparaci�n del plato 
	 */
	private String instrucciones;
	
    //-----------------------------------------------------------------
    // Constructores
    //-----------------------------------------------------------------

    /**
     *  M�todo constructor de la Receta.
     *  Inicializa los atributos con los valores dados por par�metro.
     * @param elNombre Nombre del plato. elNombre != null y elNombre != ""
     * @param laFoto Ruta de la imagen del plato. laFoto != null y laFoto != ""
     * @param laDificultad Dificultad de la preparaci�n del plato. laDificultad > 0 y laDificultad <= 10
     * @param laCategoria Categor�a de la receta. Debe corresponder a algunas de las constantes de la clase
     * @param elTiempoPreparacion Tiempo de preparaci�n (en minutos) del plato. elTiempoPreparacion > 0
     * @param losIngredientes Lista de los ingredientes de la receta. losIngredientes != null
     * @param lasInstrucciones Instrucciones de preparaci�n del plato. lasInstrucciones != null y lasInstrucciones != ""
     */
    public Receta(String elNombre, String laFoto, int laDificultad, String laCategoria, int elTiempoPreparacion, String[] losIngredientes, String lasInstrucciones )
    {
    	nombre = elNombre;
    	foto = laFoto;
    	dificultad = laDificultad;
    	categoria = laCategoria;
    	tiempoPreparacion = elTiempoPreparacion;
    	ingredientes = losIngredientes;
    	instrucciones = lasInstrucciones; 
    	verificarInvariante();
    }

    //-----------------------------------------------------------------
    // M�todos
    //-----------------------------------------------------------------
    
    /**
     * Retorna el nombre del plato
     * @return Nombre del plato
     */
    public String darNombre()
    {
    	return nombre;
    }
    
    /**
     * Retorna la ruta de la foto del plato
     * @return Ruta de la foto
     */
    public String darFoto()
    {
    	return foto;
    }
    
    /**
     * Retorna la dificultad de la preparaci�n del plato
     * @return Dificultad
     */
    public int darDificultad()
    {
    	return dificultad;
    }
    
    /**
     * Retorna la categor�a del plato
     * @return Categor�a
     */
    public String darCategoria()
    {
    	return categoria;
    }
    
    /**
     * Retorna el tiempo de preparaci�n del plato
     * @return Tiempo de preparaci�n
     */
    public int darTiempoPreparacion()
    {
    	return tiempoPreparacion;
    }
    
    /**
     * Retorna la lista de ingredientes de la receta
     * @return Lista de ingredientes
     */
    public String[] darIngredientes()
    {
    	return ingredientes;
    }
    
    /**
     * Retorna las instrucciones para la preparaci�n del plato
     * @return Instrucciones de preparaci�n
     */
    public String darInstrucciones()
    {
    	return instrucciones;
    }
    
    /**
     * Compara dos recetas seg�n el nombre. <br>
     * @param receta Es la receta contra la que se est� comparando. receta !=null
     * @return Retorna 0 si las recetas tiene el mismo nombre. <br>
     *         Retorna -1 si la receta par�metro tiene una valor "MAYOR" para el nombre. <br>
     *         Retorna 1 si la receta par�metro tiene una valor "MENOR" para el nombre. <br>
     */
    public int compararPorNombre(Receta receta)
    {
    	int comparacion = nombre.compareToIgnoreCase(receta.darNombre());
    	
    	if (comparacion < 0) return -1;
    	if (comparacion == 0) return 0;
    	return 1;
    }
    
    /**
     * Compara dos recetas seg�n la categor�a. <br>
     * @param receta Es la receta contra la que se est� comparando. receta !=null
     * @return Retorna 0 si las recetas son de la misma categor�a. <br>
     *         Retorna -1 si la receta par�metro tiene una valor "MAYOR" para la categor�a. <br>
     *         Retorna 1 si la receta par�metro tiene una valor "MENOR" para la categor�a. <br>
     */
    public int compararPorCategoria(Receta receta)
    {
    	int comparacion = categoria.compareToIgnoreCase(receta.darCategoria());
    	
    	if (comparacion < 0) return -1;
    	if (comparacion == 0) return 0;
    	return 1;
    }
    
    /**
     * Compara dos recetas seg�n la dificultad. <br>
     * @param receta Es la receta contra la que se est� comparando. receta !=null
     * @return Retorna 0 si las recetas tiene la misma dificultad. <br>
     *         Retorna -1 si la receta par�metro tiene una valor mayor para la dificultad. <br>
     *         Retorna 1 si la receta par�metro tiene una valor menor para la dificultad. <br>
     */
    public int compararPorDificultad(Receta receta)
    {
    	if (dificultad < receta.darDificultad()) return -1;
    	if (dificultad == receta.darDificultad()) return 0;
    	return 1;
    }
    
    /**
     * Compara dos recetas seg�n el n�mero de ingredientes. <br>
     * @param receta Es la receta contra la que se est� comparando. receta !=null
     * @return Retorna 0 si las recetas tiene el mismo n�mero de ingredientes. <br>
     *         Retorna -1 si la receta par�metro tiene una valor mayor para el n�mero de ingredientes. <br>
     *         Retorna 1 si la receta par�metro tiene una valor menor para el n�mero de ingredientes. <br>
     */
    public int compararPorNumeroIngredientes(Receta receta)
    {
    	if (ingredientes.length < (receta.darIngredientes()).length) return -1;
    	if (ingredientes.length == (receta.darIngredientes()).length) return 0;
    	return 1;
    }
    
    /**
     * Compara dos recetas seg�n el tiempo de preparaci�n. <br>
     * @param receta Es la receta contra la que se est� comparando. receta !=null
     * @return Retorna 0 si las recetas tiene el mismo tiempo de preparaci�n. <br>
     *         Retorna -1 si la receta par�metro tiene una valor mayor para el tiempo de preparaci�n. <br>
     *         Retorna 1 si la receta par�metro tiene una valor menor para el tiempo de preparaci�n. <br>
     */
    public int compararPorTiempoPreparacion(Receta receta)
    {
    	if (tiempoPreparacion < receta.darTiempoPreparacion()) return -1;
    	if (tiempoPreparacion == receta.darTiempoPreparacion()) return 0;
    	return 1;	
    }
    
    /**
     * Indica si en la lista de ingredientes de la receta se encuentra el ingrediente dado por par�metro <br>
     * @param ingrediente Ingrediente que se desea buscar. ingrediente != null e ingrediente != ""
     * @return True si en la lista de ingredientes se encuentra el ingrediente. False en caso contrario.
     */
    public boolean tieneIngrediente(String ingrediente)
    {
    	boolean tieneIngrediente = false;
    	
    	for (int i = 0; i < ingredientes.length && !tieneIngrediente; i++) 
    	{
			String ingredienteR = ingredientes[i];
			if(ingredienteR.contains(ingrediente))
			{
				tieneIngrediente = true;
			}
		}
    	
    	return tieneIngrediente;
    }
    
    /**
     * Retorna una cadena con el nombre de la receta
     * @return La representaci�n de la receta en String
     */
    public String toString()
    {
    	return nombre.toString();
    }
    
    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------
    
    private void verificarInvariante() {
    	 assert (nombre != null && nombre !="") : "Nombre inv�lido (null/vacio)";
    	 assert (foto != null && foto != "") : "Foto inv�lida (null/vacio)";
    	 assert (dificultad > 0 && dificultad <= 10) : "Rango dificultad inv�lido";
    	 assert (categoria != null && categoria != "") : "Categor�a inv�lida (null/vacio)";
    	 assert (tiempoPreparacion > 0) : "Tiempo preparaci�n inv�lido (<= 0)";
    	 assert (ingredientes != null) : "Ingredientes inv�lidos (null)";
    	 assert (instrucciones != null && instrucciones != "") : "Instrucciones inv�lidas (null/vacio)";
    	 
    	 assert (categoria.equals(CATEGORIA_1) ||
    			 categoria.equals(CATEGORIA_2) ||
    			 categoria.equals(CATEGORIA_3) ||
    			 categoria.equals(CATEGORIA_4) ||
    			 categoria.equals(CATEGORIA_5) ||
    			 categoria.equals(CATEGORIA_6) ) 
    			 : "La categor�a de la receta no corresponde a una de las categorias de la clase";
    }

}
