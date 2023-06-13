/*** Pedro.J (Pitter) *** 25 may 2022 
* 
* - int indexCarta: variable ir reccoriendo index
* - Carta mazo [52]: crear una baraja de 52 cartas
* 
* - constructor Mazo() donde añades las 52 de cada palo
* - buenIndex: validador de que el index no se pasa del numero de cartas permitido
* - toString
* - cambiarCartas(int, int): intercambiar los valors de las cartas
* - barajar(): aleatorizar el index
* - getCarta() obtener un index para una carta
* - compareTo(): comparar que la carta no exista ya
* - nextCarta(): continua dando otro index* 
* 
*/
package BlackJack;

import java.io.Serializable;
import java.util.Random;

public class Mazo extends Exception implements Serializable {

	private int indexCarta;
	//Creamos un mazo con un espacio total para 52 cartas
	Carta[] mazo = new Carta[52]; 
	
	//Constructor
	public Mazo() {
		int cont = 0;
		
		try {
			for(int i = 1; i<=13 ;i++) mazo[cont++] = new Carta('T',i);
			for(int i = 1; i<=13 ;i++) mazo[cont++] = new Carta('P',i);
			for(int i = 1; i<=13 ;i++) mazo[cont++] = new Carta('C',i);
			for(int i = 1; i<=13 ;i++) mazo[cont++] = new Carta('D',i);
			
		}catch(ExcepcionPaloInvalido | ExcepcionValorInvalido exp1) {
			
		}
		indexCarta = 0;
	}//End Mazo
	
	//Método para validar que el index está dentro de lo posible
	private void buenIndex(int index) throws ExcepcionPosicionInvalida{
		if(index < 0 || index > 51) { 
			throw new ExcepcionPosicionInvalida(index);
		}
		
	}
	
	//Método toString() personalizado
	public String toString() {
		String str = "";
		for(int i = 0;i<mazo.length;i++) {
			str += mazo[i].toString()+" ";
		}
		return str;
	}
	
	//Método para intercambiar los index de las cartas uno y dos
	private void cambiarCartas(int index1,int index2) throws ExcepcionPosicionInvalida {
		Carta hold;
		
		buenIndex(index1);
		buenIndex(index2);
		hold = mazo[index1];
		mazo[index1]=mazo[index2];
		mazo[index2]=hold;
	}
	
	//Método para barajar el mazo y la posición de sus cartas
	public void barajar() throws ExcepcionPosicionInvalida{
		//Instanciamos la clase Random para poder aleatorizar objetos
		Random rm = new Random();
		for(int i = 0; i< 4; i++) {
			for (int j = 0; j< mazo.length;j++) {
				cambiarCartas(i,rm.nextInt(52));
			}
		}
		indexCarta=0;
	}
	
	//Método para obtener una carta del mazo y devuelve una de las 52 cartas
	public Carta getCarta(int index) throws ExcepcionPosicionInvalida{
		buenIndex(index);
		return mazo[index];
	}
	
	//Método para comparar que la carta no está ya en juego
	public boolean compareTo(Mazo otroMazo) throws ExcepcionPosicionInvalida{
		for(int i = 0; i< mazo.length;i++) {
			if(! mazo[i].compararCarta(otroMazo.getCarta(i)) ) {
				return false;
			}
		}
		return true;
	}
	
	public Carta nextCarta() {
		if(indexCarta <0 || indexCarta>51) {
			System.out.println("futura excepción here");
		}
		return mazo[indexCarta++];
	}
}//End class Mazo
