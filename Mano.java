/*** Pedro.J (Pitter) *** 24 may 2022 
*
*	- mano[]= Carta[]
*	- numCartas: int
*
*	- calcularTotal(): int;
*	- toString(): String;
*	- toString(boolean, boolean): String;
*	- addCarta();
*	- clearMano();
*	- verCrupier(): boolean;
*
*/
package BlackJack;

import java.io.Serializable;

public class Mano implements Serializable {
	
	//Crea una array 'mano' con un valor máximo de 12.
	private Carta[] LaMano = new Carta[12];
	//Int para guardar el número de cartas que hay.
	private int numCartas = 0;
	
	//Calcula el total de tu mano y decide si el 'As' vale '1' u '11'
	public int calcularTotal() {
		int total = 0;
		//Bandera para el 'As' valor 1 u 11.
		boolean banderaAs = false;
		
		/*For que recorre cada cada carta de la mano y va sumando valores. Comprueba
		 * si alguna es un uno. Si es un uno activa bandera.
		 * Luego va sumando cada carta.
		 * Si la bandera está activa suma 11 y si es menor o igual a 21 sumará ese valor.
		 * Suma 10 y no 11 porque el 1 ya está sumado.
		 */
		
		for(int i = 0; i < numCartas;i++) {
			int valor = LaMano[i].getValor();
			if(valor > 10) valor = 10;
			else if (valor == 1) banderaAs = true;
			total += valor;
		}//End For
		
		if(banderaAs && total +10 <=21) total +=10;
		
		return total;		
	}//End calcularTotal();
	
	
	//llama al siguiente toString indicando que no hay parametros
	public String toString(){
		return this.toString(false, false);
	}
	
	//Método toString
	public String toString(boolean esCrupier, boolean cartaOculta) {
		String str = " "; 
		int total = 0; 
		boolean banderaAs = false; //boolean para el As
		String StringAs = " ";
		
		for(int i = 0; i< numCartas;i++) {
			if(esCrupier && cartaOculta && i==0) str = " Mostrando";
			else {
					int valor = LaMano[i].getValor();
					String nombreValor;
					if(valor > 10) nombreValor = LaMano[i].getValorCarta().substring(0,1);
					else if (valor == 1) nombreValor = "A";
					else nombreValor = Integer.toString(valor); //Convertimos a String
					
					str += " " + nombreValor + LaMano[i].getValor();
					
					if(valor>10) valor = 10;
					else if(valor == 1) banderaAs = true;
					total += valor;
				}
			}//end for
		if(banderaAs && total + 10 <=21) StringAs = " o "+(total+10);
		
		if(cartaOculta) return str;
		else return str + " total " + total + StringAs;	
	}//end toString()
			
	
	//Añade a tu mano una carta y además aumenta el valor de la variable numero de cartas
	public void addCarta(Carta carta) {
		LaMano[numCartas++] = carta;
	}
		
	//Reinicia el número de cartas a cero.
	public void clearMano() {
		numCartas = 0;
	}
	
	//Devuelve si se muestra la segunda carta del crupier o no
	public boolean verCrupier() {
		int valor = LaMano[1].getValor();
		//devolvera verdadero o falso en función de la resolución:
		return valor == 1 || valor >= 10;
	}
	
}//End Class Mano