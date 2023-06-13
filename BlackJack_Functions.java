/*** Pedro.J (Pitter) *** 10 may 2022 
* 
* Clase donde se guardan todos los métodos comunes que se utilizan
* para jugar al BlackJack.
* 
* 	- sc:Scanner
* 	- user:int
* 	- jugador:Jugador[]
* 	- mazo: Mazo
* 	- crupier: Crupier
* 
* - comenzarJuego()
* - barajar()
* - apuestaJugador()
* - repartirCartas()
* - checkBlackjack()
* - pedirPlantar()
* - dealerPlays() 
* - resolverApuestas()
* - mostrarEstado()
* - mostrarDinero()
* - clearManos()
* - jugarOtra(): boolean;
* - forzarFinJuego(): boolean;
* - finJuego()
* 
*
*/
package BlackJack;

import java.util.Scanner;

public class BlackJack_Functions {
	private Scanner sc = new Scanner(System.in);
	private int users; //Variable que será usada por el usuario
	private Jugador[] jugadores; //Array para añadir varios jugadores aunque esta versión solo una uno
	private Mazo mazo;
	private Crupier crupier = new Crupier();
	
	
	//Iniciar el juego
	public void comenzarJuego() {
		String name;
		System.out.println("Bienvenido al BlackJack");
		System.out.println(" \t REGLAS BÁSICAS: ");
		System.out.println(" - Cada jugador recibe 2 cartas. El Crupier una de ellas boca abajo");
		System.out.println(" - Los jugadores pueden 'Hit' para pedir más cartas o 'plantarse' para quedarse con su mano.");
		System.out.println(" - El objetivo es sumar tus cartas y quedarte o igualar lo más cerca a 21 puntos.");
		System.out.println(" - Si el total del jugador iguala al Crupier es un 'empuje' y la mano termina.");
		System.out.println(" - Los jugadores ganan su apuesta si superan en puntos al Crupier.");
		System.out.println(" - En caso de conseguir 21 puntos 'BlackJack' su apuesta se multiplica por 1,5.\n");
		
		//validador num jugadores totales
		do {
			System.out.println("¿Con cuantos jugadores desea jugar? (1-6)");
			users = sc.nextInt();
		}while(users > 6 || users < 0);
		
		jugadores = new Jugador[users];//Establezco el número de jugadores
		mazo = new Mazo(); //Se crea un mazo de cero
		
		for(int i = 0; i< users;i++) {
			System.out.println("Jugador "+(i+1)+" introduce su nombre: ");
			System.out.print("User-->");	
			name = sc.next();
			jugadores[i] = new Jugador();
			jugadores[i].setNombre(name);
		}

		
	}//End comenzarJuego();
	
	//Se baraja el mazo
	public void barajar() throws ExcepcionPosicionInvalida, ExcepcionPaloInvalido, ExcepcionValorInvalido {
		mazo.barajar();
	}
	
	//Obtiene las apuestas de los jugadores
	public void apuestaJugador() {
		int apuesta;
		for(int i = 0; i< users;i++) {
			if(jugadores[i].getDinero()>0) {
				do {
					System.out.print("¿Cuánto quieres apostar? "+ jugadores[i].getNombre()+(" (1-"+jugadores[i].getDinero()+")? "));
					apuesta = sc.nextInt();
					jugadores[i].setApuesta(apuesta);
					//En caso de que no se cumpla: (Mientras se apueste mas de cero y menos o igual del total del jugador)
				}while(!(apuesta>0 && apuesta <=jugadores[i].getDinero()));
			System.out.println("");
		}

		}
	}//End apuestaJugador()
	
	//Reparte cartas al jugador y al Crupier
	public void darCartas() {
		for(int i=0;i<2;i++) {
			for (int j =0; j < users; j++) {
				if(jugadores[j].getDinero()>0) jugadores[i].addCarta(mazo.nextCarta());
			}
		}
		crupier.addCarta(mazo.nextCarta());
	}//End cartas
	
	//Mirar si jugador o crupier tienen blackjack 
	public void checkBlackJack() {
		
		if(crupier.hayBlackJack()) {//Si tiene BlackJack
			System.out.println("¡El Crupier tiene Blackjack!");
			for (int i =0; i < users; i++) {
				if(jugadores[i].getTotalMano() == 21) {
					System.out.println("Pero "+jugadores[i].getNombre()+" también y empatan.");
					jugadores[i].presiona();
				}else {
					System.out.println(jugadores[i].getNombre()+" pierde.");
					jugadores[i].quiebra();
				}
			}

		}else {//Si no tiene BlackJack
			if(crupier.mirarCartaCrupier())	System.out.println("El crupier mira sus cartas y no tiene BlackJack");
			
			for (int i =0; i < users; i++) {
				if(jugadores[i].getTotalMano()==21) {
					System.out.println("El jugador tiene BlackJack");
					jugadores[i].BlackJack();
				}
			}
		}
	}//End checkBlackJack
	
	//Para pedir carta o plantarse en la partida
	public void pedirPlantar() {
		String opt;//Variable de la opción elegida
		char c;
		for (int i = 0; i < users; i++) {
			if(jugadores[i].getApuesta()>0) {
				System.out.println(" ");
				System.out.println(jugadores[i].getNombre()+ " tiene "+jugadores[i].getManoString());
				
				do {
					do {
						System.out.println(jugadores[i].getNombre()+ "Elige: (G)Golpear o (P)Plantarse.");
						opt = sc.next();
						//Validación para el char
						c = opt.toUpperCase().charAt(0);
						//Pedirá opción mientras no sea una de estas dos
					}while(!(c== 'G' || c == 'P'));
					if(c == 'G') {//Si "golpea" le darán otra carta
						jugadores[i].addCarta(mazo.nextCarta());
						System.out.println(jugadores[i].getNombre()+" tiene "+jugadores[i].getManoString());
					}
				}while(c != 'P' && jugadores[i].getTotalMano() <=21);
			}//if1
		}//end for
	}//End pedriPasar()
	
	//Automatiza las deciciones del Crupier y chequea que el jugador sigue vivo. Si es asi el crupier sigue jugando
	public void jugarCrupier() {
		boolean jugadorVivo = false;
		for (int i = 0; i < users; i++) {
			if(jugadores[i].getApuesta()>0 && jugadores[i].getTotalMano()<=21) jugadorVivo = true;
		}
		
		if(jugadorVivo)	crupier.playCrupier(mazo);
	}//endJugarCrupier
	
	//Calcula el resultado del juego y finaliza si el jugador perdió
	public void resolverApuestas() {
		System.out.println(" ");
		
		for (int i = 0; i < users; i++) {
			if(jugadores[i].getApuesta()>0) {
				if(jugadores[i].getApuesta()>21) {
					System.out.println(jugadores[i].getNombre()+" ha quebrado.");
					jugadores[i].quiebra();
				}else if (jugadores[i].getTotalMano() == crupier.calcularTotal()) {
					System.out.println(jugadores[i].getNombre()+" ha igualado el resultado.");
					jugadores[i].presiona();
				}else if(jugadores[i].getTotalMano() < crupier.calcularTotal() && crupier.calcularTotal()<=21) {
					System.out.println(jugadores[i].getNombre()+" ha perdido.");
					jugadores[i].perder();
				}else if(jugadores[i].getTotalMano() == 21) {
					System.out.println(jugadores[i].getNombre()+" ha gando con BlackJack.");
					jugadores[i].BlackJack();
				}else {
					System.out.println(jugadores[i].getNombre()+" ha ganado.");
					jugadores[i].ganar();
				}
			}//if1
		}//end for
	}//End resolverApuesta()
	
	//Imprime resultado  de la mano del jugador o crupier
	public void mostrarEstado() {
		for (int i = 0; i < users; i++) {
			if(jugadores[i].getDinero()>0) System.out.println(jugadores[i].getNombre()+" tiene "+jugadores[i].getManoString());
		}
	
		System.out.println("El Crupier tiene "+crupier.getManoString(true, true));//true,true porque sabemos que están inicializadas
	}//end mostrarEstado()
	

	//imprime el dinero al finalizar
	public void mostrarDinero() {
		for (int i = 0; i < users; i++) {
			if(jugadores[i].getDinero()>0) System.out.println(jugadores[i].getNombre()+" tiene "+jugadores[i].getDinero());
			
			if(jugadores[i].getDinero()==0) {
				System.out.println(jugadores[i].getNombre()+" tiene "+jugadores[i].getDinero()+" y se acaba el juego.");
				jugadores[i].removerJugadorJuego();
			}
		}
	}//end mostrar dinero
	
	//reseta las manos
	public void clearManos() {
		for (int i = 0; i < users; i++) {
			jugadores[i].clearMano();
			crupier.clearMano();	
		}
	}
	
	//Fuerza el fin juego cuando el jugador pierde y da elección de jugar de nuevo
	public boolean jugarOtra() {
		String opt;
		char c;
		Boolean decision = true;
		if(forzarFinJuego()) decision = false;
		else {
			do {
				System.out.println("¿Quieres jugar otra partida? (Y)Yes / (N)No");
				opt = sc.next();
				//Validador de la opción introducida
				c = opt.toUpperCase().charAt(0);
				//Se repite mientras no sea Y o N
			}while(!(c=='Y' || c=='N'));
			
			if(c=='N') decision = false;
		}
		return decision;
	}//End jugarOtra()
	
	//forzar fin del juego si el jugador pierde
	public boolean forzarFinJuego() {
		boolean fin = false;
		int cont = 0;
		
		for (int i = 0; i < users; i++) {
			if(jugadores[i].getDinero() == -1) cont++;
		}
		if(cont == users) fin = true; //Si el cont es = al numero jugadores el juego continua
		if(fin) System.out.println("El jugador ha perdido y se acaba el juego.");//si no quedan jugadores
		return fin;
	}//End forzarFinJuego

	//para acabar el juego si se acaba o el jugador decide terminar
	public void finJuego() {
		int importe;
		String estadoFinal = " sin cambios.";
		System.out.println(" ");
		
		for (int i = 0; i < users; i++) {
			if(jugadores[i].getDinero() == -1) jugadores[i].resetDinero();
			
			importe = jugadores[i].getDinero()-100;
			
			if(importe > 0) estadoFinal = " ganancias de ";
			else if(importe <0) estadoFinal = " perdidas de ";
			
			System.out.println(jugadores[i].getNombre()+" acabo el juego con "+jugadores[i].getDinero()+".");
			if(estadoFinal != "Sin cambios.") System.out.println("El "+estadoFinal + Math.abs(importe)+".");
			else System.out.println("Sin cambios desde el valor inicial"); 
			System.out.println(" ");
		}
		
		System.out.println("¡Gracias por jugar!");
	}//End finjuego
	
}//End Class
