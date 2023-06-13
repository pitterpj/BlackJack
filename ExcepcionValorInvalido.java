/*** Pedro.J (Pitter) *** 10 may 2022 
*
* Clase donde guardo la Excepci�n personalizada en caso de que el
* valor introducido no sea v�lido.
*
*/
package BlackJack;

public class ExcepcionValorInvalido extends Exception{
	
	private int identificadorValor = 0;
	
	public ExcepcionValorInvalido(int valorInvalido) {
		identificadorValor = valorInvalido;
		System.out.println("Valor inv�lido: "+ valorInvalido);		
	}//End ExcepcionValorInvalido
	
	public ExcepcionValorInvalido() {
		System.out.println("Valor Invalido");
	}//En ExcepcionValorInvalido2
	
	public String toString() {
		return ("Se ha intentado crear una carta con un valor erroneo "+ this.identificadorValor);
	}//End toString
	
	public int getIdentificadorValor() {
		return identificadorValor;
	}//End getIdenficadorValor
}//End Class ExcepcionValorInvalido