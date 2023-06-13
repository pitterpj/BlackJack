/*** Pedro.J (Pitter) *** 10 may 2022 
*
* Clase donde guardo la Excepci�n personalizada en caso de que el
* palo introducido no sea v�lido.
*
*/
package BlackJack;

public class ExcepcionPaloInvalido extends Exception {
	
	private char identificadorPalo = '?';
	
	public ExcepcionPaloInvalido (char paloInvalido) {
		identificadorPalo = paloInvalido;
		System.out.println("Valor inv�lido "+paloInvalido);
	}//End ExcepcionPaloInvalido
	
	public ExcepcionPaloInvalido () {
		System.out.println("Valor inv�lido ");
	}//End ExcepcionPaloInvalido2	
	
	public String toString() {
		return ("Se ha intentado crear una carta con un palo erroneo "+ this.identificadorPalo);
	}//End toString
	
	public int getIdentificadorPalo() {
		return identificadorPalo;
	}//End getIdentificadorPalo()
}//End Excepiton class