/*** Pedro.J (Pitter) *** 10 may 2022 
*
* Clase donde guardaré los métodos del usuario como jugador
* y donde se guardará todo lo relacionado con el usuario.
* 
* ATRIBUTOS
* 	-Nombre:
* 	-Dinero:
* 	-Apuesta:
* 
* METODOS
* 	-getDinero()
* 	-perder()
* 	-ganar()
* 	-removerJugadorJuego()
* 	-resetDinero();
* 	-blackjack() *1.5
*  	-presiona();
* 	-setApuesta() inicia una apuesta
* 	-getApuesta()
* 	-getNombre()
* 	-getTotalMano()
* 	-setNombre
* 	-getTotalMano() (gettotal)
* 	-addCarta()
* 	-getManoString()
* 	-clearMano()* 	
* 
* 
* LAS MANOS ES UNA CLASE QUE NO VOY A CREAR SERÁN METODOS CON MATH RANDOM
*
*/
package BlackJack;

public class Jugador {
	
	private String nombre;
	private int dinero;
	private int apuesta;
	private Mano mano;
	
//CONSTRUTORS
	//Constructor del jugador donde se crea el jugador con el nombre y la apuesta inicial.
	public Jugador() {
		dinero = 100;;
		mano = new Mano();
	}
	
//METODOS
	//Método que devuelve el dinero total actual del jugador.
	public int getDinero() {
		return dinero;
	}
	
	//Método que hace el jugador quiebre
	public void quiebra() {
		dinero -= apuesta;
		apuesta=0;
	}
	
	//Método que actualiza el dinero del jugador al perder la partida.
	public void perder() {
		dinero -= apuesta;
		apuesta = 0;
	}
	
	//Remover jugador del juego
	public void removerJugadorJuego() {
		dinero = -1;
	}
	
	//Método que actualiza el dinero del jugador al ganar la partida
	public void ganar() {
		dinero += apuesta;
		apuesta = 0;
	}
	
	//Método que iguala el resultado y nada pasa
	public void presiona() {
		apuesta = 0;
	}
	
	//Resetea el dinero del jugador a 0.
	public void resetDinero() {
		dinero= 0;
	}
	
	//Método que actualiza el dinero del jugador al ganar la partida con un BlackJack
	public void BlackJack() {
		dinero += apuesta+1.5;
		apuesta = 0;
	}
	
	//Método que devuelve el String nombre del jugador
	public String getNombre() {
		return nombre;
	}
	
	//Método para establecer el nombre del jugador
	public void setNombre(String name){
		nombre=name;
	}
	
	//Método que actualiza la primera o siguiente apuesta
	public void setApuesta(int nuevaApuesta) {
		apuesta = nuevaApuesta;
	}
	
	//Método que obtiene la apuesta actual
	public int getApuesta() {
		return apuesta;
	}
	
	//Método que suma el total de cartas de la mano del jugador o crupier
	public int getTotalMano() {
		return mano.calcularTotal();
	}
	
	//Método que añade carta a la mano del jugador o crupier
	public void addCarta(Carta carta) {
		mano.addCarta(carta);
	}
	
	//Método que obtiene la mano del jugador en un String
	public String getManoString() {
		String str = "Cartas" + mano.toString();
		return str;
	}
	
	//Método que reinicia la mano del jugador o del crupier
	public void clearMano() {
		mano.clearMano();
	}
	
}//End class Jugador
