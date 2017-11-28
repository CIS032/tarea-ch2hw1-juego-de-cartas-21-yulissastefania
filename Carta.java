
package blackjack;
/**
 *
 * @author Fanny Quezada
 */

    /**
 * Un objeto de tipo Carta representa un naipe de una
 * baraja de poker estándar, incluidos comodines. La tarjeta tiene un traje, que
 * pueden ser espadas, corazones, diamantes, palos o bromista. Una pala, corazón,
 * diamante, o club tiene uno de los 13 valores: as, 2, 3, 4, 5, 6, 7,
 * 8, 9, 10, jota, reina o rey. Tenga en cuenta que se considera que "as" es
 * el valor más pequeño. Un comodín también puede tener un valor asociado;
 * este valor puede ser cualquier cosa y se puede utilizar para realizar un seguimiento de varios
 * diferentes bromistas.
 **/
   
public class Carta {
    public final static int PICAS = 0; // Códigos para los 4 palos, más Joker.
    public final static int CORAZONES = 1;
    public final static int DIAMANTES= 2;
    public final static int TREBOLES = 3;
    public final static int JOKER = 4;

    public final static int AS = 1; // Códigos para las tarjetas no numéricas.
    public final static int JOTA = 11; // Las tarjetas 2 a 10 tienen su
    public final static int REINA = 12; // valores numéricos para sus códigos.
    public final static int REY = 13;

    /**
     * El juego de esta carta, una de las constantes SPADES, HEARTS, DIAMONDS,
     * CLUBS, o JOKER. La demanda no puede ser cambiada después de que la tarjeta es
     * construido.
     **/
     private final int traje;  

    /**
     * El valor de la tarjeta. Para una carta normal, este es uno de los valores
     * 1 a 13, con 1 representando ACE. Para un JOKER, el valor
     * puede ser cualquier cosa. El valor no se puede cambiar después de la tarjeta
     * esta construido.
     **/
    private final int valor;
   
    /**
     * Crea un Joker, con 1 como el valor asociado. (Tenga en cuenta que
     * "nueva Tarjeta ()" es equivalente a "nueva Tarjeta (1, Tarjeta.JADORA)".)
     **/
    public Carta () {
        traje = JOKER;
        valor = 1;
    }

    /**
     * Crea una carta con un palo y valor especificados.
     * @param theValue el valor de la nueva tarjeta. Para una tarjeta regular (no bromista),
     * el valor debe estar en el rango de 1 a 13, donde 1 representa un As.
     * Puede usar las constantes Card.ACE, Card.JACK, Card.QUEEN y Card.KING.  
     * Para un Joker, el valor puede ser cualquier cosa.
     * @param theSuit el traje de la nueva tarjeta. Este debe ser uno de los valores
     * Card.SPADES, Card.HEARTS, Card.DIAMONDS, Card.CLUBS o Card.JOKER.
     * @throws IllegalArgumentException si los valores de los parámetros no están en el
     * rangos permisibles
     **/
      public Carta(int elValor, int elTraje) {
        if (elTraje != PICAS && elTraje != CORAZONES && elTraje != DIAMANTES && 
                elTraje != TREBOLES && elTraje != JOKER)
            throw new IllegalArgumentException("Palo de naipes ilegal");
        if (elValor != JOKER && (elValor < 1 ||elValor > 13))
            throw new IllegalArgumentException("Valor de naipes ilegal");
        valor = elValor;
        traje = elTraje;
    }

    /**
     * Devuelve la demanda de esta tarjeta.
     * @ devuelve el traje, que es una de las constantes Card.SPADES, 
     * Card.HEARTS, Card.DIAMONDS, Card.CLUBS o Card.JOKER
     **/
    public int getTraje () {
        return traje;
    }

    /**
     * Devuelve el valor de esta tarjeta.
     * @return el valor, que es uno de los números del 1 al 13, inclusive para
     * una tarjeta regular, y que puede ser de algún valor para un Joker.
     **/
    public int getValor () {
        return valor;
    }

    /**
     * Devuelve una representación de Cadena del palo de la carta.
     * @return una de las cadenas "Picas", "Corazones", "Diamantes", "Clubes"
     * o "Joker".
     **/
     public String getSuitAsString() {
        switch ( traje ) {
        case PICAS:   return "Picas";
        case CORAZONES:   return "Corazones";
        case DIAMANTES: return "Diamantes";
        case TREBOLES:    return "Treboles";
        default:       return "Joker";
    }

    /**
     * Devuelve una representación de cadena del valor de la tarjeta.
     * @return para una carta regular, una de las cuerdas "Ace", "2",
     * "3", ..., "10", "Jack", "Queen" o "King". Para un Joker, el
     * la cadena siempre es numérica.
     **/
     }
     public String getValueAsString() {
        if (traje == JOKER)
            return "" + valor;
        else {
            switch ( valor ) {
            case 1:   return "Ace";
            case 2:   return "2";
            case 3:   return "3";
            case 4:   return "4";
            case 5:   return "5";
            case 6:   return "6";
            case 7:   return "7";
            case 8:   return "8";
            case 9:   return "9";
            case 10:  return "10";
            case 11:  return "Jota";
            case 12:  return "Reina";
            default:  return "Rey";
            }
        }
    }

    /**
     * Devuelve una representación de cadena de esta tarjeta, incluidos ambos
     * su palo y su valor (excepto que para un Joker con valor 1,
     * el valor de retorno es solo "Joker"). Valores de retorno de muestra
     * son: "Reina de Corazones", "10 de Diamantes", "As de Picas",
     * "Joker", "Joker # 2"
     **/
    public String toString() {
        if (valor == JOKER) {
            if (valor == 1)
                return "Joker";
            else
                return "Joker #" + valor;
        }
        else
            return getValueAsString() + " de " + getSuitAsString();
    }


} 