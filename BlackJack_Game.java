/*** Pedro.J (Pitter) *** 27 may 2022 
*
*	Clase principal desde la cuál se inicializa el juego
*
*/
package BlackJack;

public class BlackJack_Game {

	public static void main(String[] args) throws Exception {
		
		//Creamos un juego
		BlackJack_Functions miJuego = new BlackJack_Functions();
		
		miJuego.comenzarJuego();
		do {
			miJuego.barajar();
			miJuego.apuestaJugador();
			miJuego.darCartas();
			miJuego.mostrarEstado();
			miJuego.checkBlackJack();
			miJuego.pedirPlantar();
			miJuego.jugarCrupier();
			miJuego.resolverApuestas();
			miJuego.mostrarDinero();
			miJuego.clearManos();
		}while(miJuego.jugarOtra());	
	}//end main
}//End BlackJack_Game
