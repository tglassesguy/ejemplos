package uniandes.cupi2.calculadoraPunnett.mundo;

public class Gen {
	// -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
	private Alelo[] alelos;
	private String caracteristicaGen;
	private boolean heterocigoto; 
	// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
	/**
	 * Constructor de los genes. Recibe como par�metros un arreglo de dos alelos y el nombre de la caracter�stica
	 * que el gen representa. El constructor determina el alelo dominante si existe y lo almacena en la primera 
	 * posici�n de su arreglo de alelos.
	 */
	public Gen(Alelo[] alelosP, String caracteristicaGenP) throws Exception {
		if (alelosP.length != 2) throw new Exception("Cada gen solo puede contener dos alelos");
		// Validando correspondencia alelos
		heterocigoto = Math.abs(alelosP[0].darRepresentacion() - alelosP[1].darRepresentacion()) == 32;
		boolean homocigoto = alelosP[0].darRepresentacion() - alelosP[1].darRepresentacion() == 0;
		if (!(heterocigoto ^ homocigoto)) 
			throw new Exception("Los alelos "+(char)alelosP[0].darRepresentacion()+" y "+(char)alelosP[1].darRepresentacion()+" no tienen representaciones correspondientes.");
		// Determinando alelos dominantes
		char representacion1 = alelosP[1].darRepresentacion();
		boolean dominante1 = (representacion1 >= 65 && representacion1 <= 90);
		// Inicializando arreglo de alelos para el gen
		alelos = new Alelo[2];
		/* Almacenado alelo dominante en la posici�n 0 del arreglo.
		 * Si el segundo alelo NO es dominante alelos se puede guardar igual a alelosP.
		 * Si el segundo alelo SI es dominante se puede guardar �ste en la posici�n 0 y el primer alelo 
		 * en la posici�n 1 sin p�rdida de  generalidad.
		 */
		if (dominante1) {alelos[0] = alelosP[1]; alelos[1] = alelosP[0];}
		else alelos = alelosP;
		// Almacenando las caracter�sticas del gen
		caracteristicaGen = caracteristicaGenP;
		System.out.println("Se ha creado el gen "+darStringGen()+": "+darCaracteristica());
	}
	// -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------
	/**
	 * M�todo que devuelve el string que representa el gen
	 */
	public String darStringGen() {
		char[] caracteres = {alelos[0].darRepresentacion(), alelos[1].darRepresentacion()};
		String respuesta = new String(caracteres);
		return respuesta;
	}
	/**
	 * M�todo que devuelve el nombre de la caracter�sitca que este gen representa.
	 */
	public String darCaracteristica() {
		return caracteristicaGen;
	}
	/**
	 * M�todo de devuelve el arreglo de alelos que este gen representa
	 * @return Nombre caracter�stica gen
	 */
	public Alelo[] darAlelos() {
		return alelos;
	}
	/**
	 * M�todo que devuelve la caracter�stica del alelo dominante de este gen. 
	 */
	public String darCaracteristicaDominante() {
		return alelos[0].darCaracteristica();
	}
	/**
	 * M�todo que devuelve la caracter�stica del alelo recesivo de este gen
	 */
	public String darCaracteristicaRecesiva() {
		return alelos[1].darCaracteristica();
	}
	/**
	 * M�todo que indica si �ste es un gen heterocigoto
	 */
	public boolean esHeterocigoto() {
		return heterocigoto;
	}
}
