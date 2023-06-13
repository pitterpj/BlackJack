/*** Pedro.J (Pitter) *** 10 may 2022 
*
* Clase para crear el objeto carta, con su palo y su número
* 
* 	- palo: char
* 	- valor: int
* 
* 	- Carta()
* 	- Carta(char,int)
* 
* 	- mostrarCarta()
* 	- getPalo()
* 	- getPaloCarta()
* 	- getValor()
* 	- getValorCarta()
* 	- compararPalo()
* 	- compararValor()
* 	- compararCartas()
*
*/
package BlackJack;

public class Carta{
	
	private char palo; //Palo que tendrá la carta
	private int valor; //Valor de la carta

/******* CONSTRUCTORES ********/
	//Constructor de cartas
	private Carta() {
		palo = ' ';
		valor = 0;
	}//End Carta
	
	//Constructor de cartas insertándole valores
	public Carta(char nuevoPalo, int nuevoValor) throws ExcepcionValorInvalido,ExcepcionPaloInvalido {
		//validador valores de cartas permitidas
		if(nuevoValor < 1 || nuevoValor > 13) {
			throw new ExcepcionValorInvalido(nuevoValor);
		}
		else this.valor = nuevoValor;
		//validador palo de los cuatro existentes
		if(nuevoPalo != 'T' || nuevoPalo != 'P' ||nuevoPalo != 'D' || nuevoPalo != 'C') {
			throw new ExcepcionValorInvalido(nuevoPalo);
		}
		else this.palo=nuevoPalo;
	}//End Carta2
	
/********* MÉTODOS ********/	
	//Devuelve que carta es con su palo y su valor
	public String mostrarCarta() {
		return getPaloCarta()+ " "+ this.valor;
	}//End mostrarCarta;
	
	//Devuelve el palo de la Carta
	public String getPaloCarta() {
		String palo;
		
		if(this.palo=='T') palo = "Tréboles";
		else if(this.palo=='P') palo = "Picas";
		else if(this.palo=='D') palo = "Diamantes";
		else if(this.palo=='C') palo = "Corazones";
		else palo="Desconocido";
		return palo;	
	}//End getPaloCarta;
	
	//Regresa el palo en Char
	public char getPalo() {
		return palo;
	}
	
	//Regresa el valor de la carta
	public int getValor() {
		return this.valor;
	}
	
	//Devuelve el valor numérico en letras
	public String getValorCarta(){
		String nombre = "Unknown";
		
		if (this.valor == 1) nombre = "As";
		else if (this.valor == 2) nombre = "Dos";
		else if (this.valor == 3) nombre = "Tres";
		else if (this.valor == 4) nombre = "Cuatro";
		else if (this.valor == 5) nombre = "Cinco";
		else if (this.valor == 6) nombre = "Seis";
		else if (this.valor == 7) nombre = "Siete";
		else if (this.valor == 8) nombre = "Ocho";
		else if (this.valor == 9) nombre = "Nueve";
		else if (this.valor == 10) nombre = "Diez";
		else if (this.valor == 11) nombre = "Jack";
		else if (this.valor == 12) nombre = "Reina";
		else if (this.valor == 13) nombre = "Rey";
		return nombre;
	}//End getValorNombre
		
	//Comparar el palo de las cartas
	public boolean compararPalo(Carta carta) {
		return this.palo==carta.getPalo();
	}//End compararPalo	
	
	//Comparar el valor de las cartas
	public boolean compararValor(Carta carta) {
		return this.valor==carta.getValor();
	}//End compararValor
		
	//Comparar y que no sea la misma carta sacada dos veces
	public boolean compararCarta(Carta carta) {
		return this.palo==carta.getPalo() && this.valor==carta.getValor();
	}//End compararCartas
	
}//End Class Carta
