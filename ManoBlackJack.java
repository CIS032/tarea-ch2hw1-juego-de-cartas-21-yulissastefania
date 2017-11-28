
package blackjack;

/**
 *
 * @author Fanny Quezada
 */

public class ManoBlackJack extends Mano{
  
    /**
     * Calcula y devuelve el valor de esta mano en el juego
     * de Blackjack.
     * @return 
     */
    public int getBlackjackValor() {

        int val; // El valor calculado para la mano.
       boolean ace; // Esto se establecerá en verdadero si el
        // la mano contiene un as.
        int cartas; // Número de cartas en la mano.

        val = 0;
        ace = false;
        cartas = getCartaCantidad (); // (método definido en la clase Hand)

        for (int i = 0; i <cartas; i ++) {
            // Agrega el valor de la i-ésima carta en la mano.
             Carta carta; // La i-ésima tarjeta;
            int cardVal; // El valor de blackjack de la i-ésima carta.
            carta = getCarta(i);
            cardVal = carta.getValor(); // El valor normal, de 1 a 13.
            if (cardVal> 10) {
                cardVal = 10; // Para un Jack, Queen o King.
            }
            if (cardVal == 1) {
                ace = true; // Hay al menos un as.
            }
            val = val + cardVal;
        }

        // Ahora, val es el valor de la mano, contando cualquier as como 1.
        // Si hay un as, y si cambia su valor de 1 a 
        // 11 dejaría el puntaje menor o igual a 21,
        // entonces hazlo sumando los 10 puntos adicionales a val. 

        if (ace == true && val + 10 <= 21)
            val = val + 10;

        return val;

    } // end getBlackjackValue ()

   

   

} // fin de clase BlackjackHand