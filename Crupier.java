package BlackJack;

import java.io.Serializable;
/** 
* <center><h1>CLASE CRUPIER </h1></center>
* <h3>Clase que guarda los métodos del Crupier</h3>
*
* <p>Esta clase es donde he creado todos los métodos para crear un Crupier
* y que pueda tomar decisiones automáticamente
* en función de la situación de la partida.</p>
* 
* @author  *** Pedro.J (Pitter) *** 29 may 2022 ***
* @version V.1
*/

public class Crupier implements Serializable {
	
	//Creamos la mano del crupier
	private Mano mano = new Mano();
	
	//Comprobar si el Crupier tiene BlackJack
	/**
	 * @return boolean en función de si el Crupier tiene BlackJack o no
	 */
	public boolean hayBlackJack() {
		if(mano.calcularTotal()==21) return true;
		else return false;
	}
	
	//Automatiza el juego del Crupier
	/**
	 * @param mazo pasas el mazo del crupier por parámetro y automatiza sus acciones
	 * en función de como se desarrolle el juego.
	 */
	public void playCrupier(Mazo mazo) {
		System.out.println(" ");
		while(mano.calcularTotal()<= 16) {
			System.out.println("Crupier tiene "+ mano.calcularTotal()+ "y golpea la mesa.");
			mano.addCarta(mazo.nextCarta());
			System.out.println("Crupier "+ this.getManoString(true,false));
		}
		if(mano.calcularTotal()>21) System.out.println("El Crupier se pasa y pierde."+ this.getManoString(true,false));
		else System.out.println("Crupier pasa turno "+ this.getManoString(true,false));
	}//End playCrupier
	
	//Añade cartas
	/**
	 * @param Objeto pasado por parámetro que añade la carta a la mano del Crupier
	 */
	public void addCarta(Carta carta) {
		mano.addCarta(carta);
	}
		
	//Obtiene la mano en String
	/**
	 * @param esCrupier booleano para comprobar si el Crupier está creado
	 * @param cartaOculta booleando para comprobar si la carta sigue oculta
	 * @return String con la palabra 'Cartas' + la mano del Crupier
	 */
	public String getManoString(boolean esCrupier, boolean cartaOculta) {
		String str = "Cartas "+ mano.toString(esCrupier, cartaOculta);
		return str;
	}//End getManoString
	
	//Calcula el total de la mano
	/**
	 * @return int con la suma total de las cartas en la mano del Crupier
	 */
	public int calcularTotal() {
		return mano.calcularTotal();
	}//End calcularTotal
	
	//Reinicar mano
	public void clearMano() {
		mano.clearMano();
	}//End clearMano
	
	//Mira carta boca abajo del crupier
	/**
	 * @return booleano que dice si la carta se está mostrando o no
	 */
	public boolean mirarCartaCrupier() {
		return mano.verCrupier();
	}//End mirarCartaCrupier	

}//End class Crupier
