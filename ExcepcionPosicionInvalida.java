/*** Pedro.J (Pitter) *** 25 may 2022 
*/
package BlackJack;

public class ExcepcionPosicionInvalida extends Exception {

	private int identificadorPosicion = 0;
	
	public ExcepcionPosicionInvalida(int posicionInvalida) {
		identificadorPosicion = posicionInvalida;
		
		System.out.println("Posici�n Invalida "+ posicionInvalida);
	}
	
	private ExcepcionPosicionInvalida() {
		System.out.println("Posici�n Invalida");
	}
	
	public String toString() {
		return (" Intento de obtener una posici�n de una carta que no est� en la baraja  "+this.identificadorPosicion);
	}
	
	public int getIdentificadorPosicion() {
		return identificadorPosicion;
	}
}//End Exception