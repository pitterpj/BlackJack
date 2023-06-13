/*** Pedro.J (Pitter) *** 17 may 2022 
 * 
 * - rellenarClasificacion(): Object;
 * - newPuntuacion(Object);
 * - leerFichero();
 * 
*/
package BlackJack;

import java.io.*; //Importado para crear Files
import java.util.Scanner;
/**
* <center><h1><b>@author  *** Pitter *** 17 may 2022 *** </b></h1><S/center>
* <h2>@version V1</h2>
* <h3> Aplicación para la recogida de puntiaciones del juego BlackJack</h3>
*/
public class Clasificacion implements Serializable{
	/**
	 * @param nombre: Nombre introducido por el user o recogido por la aplicación
	 * @param estado: Estado de finalización de la partida (Ganada o perdida)
	 * @param dinero: Suma final del dinero al finalizar la partida
	 */
	
	static String nombre;
	static String estado;
	static int dinero;
	static File file = new File("c:\\Ficheros\\clasificacion.txt"); //Ruta del fichero
	static Scanner sc = new Scanner (System.in);
	
/*** CONSTRUCTORES ***/	
	public Clasificacion() {
		//Constructor vacio
	}

	public Clasificacion(String nombre, String estado, int dinero) {
		this.nombre=nombre;
		this.estado=estado;
		this.dinero=dinero;
	}
	
/*** METODOS ***/	
	/**
	 * @return c1: Un objeto que contiene toda la puntuación recogida
	 * mediante un Scanner o en el caso de mi aplicación desde otras variables
	 * rellenadas anteriormente por la partida.
	 **/
	public Object rellenarClasificacion() {
		System.out.println("Dime un nombre");
		nombre = sc.nextLine();
		System.out.println("Dime como acabo la partida");
		estado = sc.nextLine();
		System.out.println("Dime el dinero final");
		dinero = sc.nextInt();
		
		Clasificacion c1 = new Clasificacion(nombre,estado,dinero);
		
		return c1;
	}

	/**
	 * @param newPuntuacion: Usa un objeto para añadir datos al fichero que escribe
	 * en nuevo fichero si no existe o sigue escribiendo uno que ya existe. 
	 */
	public static void newPuntuacion(Object Clasificacion) {
		FileWriter fw;
		PrintWriter pw;
		
		if(!file.exists()) { //Si no existe hace:
			try {
				file.createNewFile();
			
				fw = new FileWriter(file,true);
				pw = new PrintWriter(fw);
								
				//Guardamos todo y lo escribimos en el fichero
				pw.println("Nombre jugador: "+nombre+ " "+ estado +" con una suma final de: "+ dinero);
	
				fw.close();
				pw.close();		
			
			}catch (Exception e) {
				e.printStackTrace(); //Si hay error pedimos que nos lo pinte
			} 
		}else { //Si ya existe hace:
			try {
				fw = new FileWriter(file,true);
				pw = new PrintWriter(fw);
								
				pw.println("Nombre jugador: "+nombre+ " "+ estado +" con una suma final de: "+ dinero);
	
				fw.close();
				pw.close();		
			
			}catch (Exception e) {
				e.printStackTrace(); //Si hay error pedimos que nos lo pinte	
			} 
		}//End else
		
		System.out.println("Añadida la nueva clasificación");
		
	}//End newPuntuacion
	
	public static void leerFichero() {
		
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String linea;
			
			while((linea=br.readLine())!=null) {
				System.out.println(linea);
			}
							
		} catch (Exception e) {
			e.printStackTrace(); //Si hay error pedimos que nos lo pinte	
		}	
	}//End leerFichero
	
/*** MAIN ***/	
	public static void main(String[] args) {
		
		Clasificacion cla = new Clasificacion ();
		cla.rellenarClasificacion();
		
		newPuntuacion(cla);
		leerFichero();
		
	}//End Main
	
}//End calls principal clasificacion
