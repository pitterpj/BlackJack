/*** Pedro.J (Pitter) *** 25 may 2022 
*/
package BlackJack;

public class ExcepcionPosicionInvalida extends Exception {

	private int identificadorPosicion = 0;
	
	public ExcepcionPosicionInvalida(int posicionInvalida) {
		identificadorPosicion = posicionInvalida;
		
		System.out.println("Posición Invalida "+ posicionInvalida);
	}
	
	private ExcepcionPosicionInvalida() {
		System.out.println("Posición Invalida");
	}
	
	public String toString() {
		return (" Intento de obtener una posición de una carta que no está en la baraja  "+this.identificadorPosicion);
	}
	
	public int getIdentificadorPosicion() {
		return identificadorPosicion;
	}
}//End Exception