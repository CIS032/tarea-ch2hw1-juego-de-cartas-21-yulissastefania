/**
 * Un objeto de tipo Mano representa una mano de cartas. los
 * las tarjetas pertenecen a la clase Tarjeta. Una mano está vacía cuando
 * se crea y se puede agregar cualquier cantidad de tarjetas.
 **/
package blackjack;

import java.util.ArrayList;
/**
 *
 * @author Fanny Quezada
 */
 
public class Mano {
    
     private ArrayList<Carta> mano; // Las cartas en la mano.

    /**
     * Crea una mano que está inicialmente vacía.
     **/
    public Mano () {
        mano = new ArrayList <>();
         
    }

    /**
     * Retire todas las cartas de la mano, dejándolas vacías.
     **/
    public void limpiarMano () {
        mano.clear ();
    }

    /**
     * Agrega una carta a la mano. Se agrega al final de la mano actual.
     * @param c la tarjeta no nula que se agregará.
     * @throws NullPointerException si el parámetro c es nulo.
     **/
    public void agregarCarta (Carta c) {
        if (c == null)
           throw new NullPointerException ("No se puede agregar una carta nula a una mano");
        mano.add(c);
    }

    /**
     * Retire una tarjeta de la mano, si está presente.
     * @param c la tarjeta que se eliminará. Si c es nulo o si la tarjeta no está en
     * la mano, entonces no se hace nada.
     **/
    public void retirarCarta (Carta c) {
        mano.remove (c);
    }

    /**
     * Retire la tarjeta en una posición específica de la mano.
     * @param posicion
     * @throws IllegalArgumentException si el puesto no existe en
     * la mano, eso es si la posición es menor que 0 o mayor que
     * o igual a la cantidad de cartas en la mano.
     **/
    public void retirarCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size())
            throw new IllegalArgumentException ("La posición no existe en la mano:"
                    + posicion);
        mano.remove (posicion);
    }

    /**
     * Devuelve la cantidad de cartas en la mano.
     * @return 
     **/
    public int getCartaCantidad () {
        return mano.size ();
    }

    /**
     * Obtiene la carta en una posición específica en la mano. (Tenga en cuenta que esta tarjeta
     * no se elimina de la mano!)
     * @param posicion
     * @throws IllegalArgumentException si la posición no existe en la mano
     **/
     public Carta getCarta(int posicion) {
        if (posicion < 0 || posicion >= mano.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + posicion);
        return mano.get(posicion);
    }

    /**
     * Clasifica las cartas en la mano para que las cartas del mismo palo sean
     * agrupados, y dentro de un palo, las tarjetas se ordenan por valor.
     * Tenga en cuenta que se considera que los ases tienen el valor más bajo, 1.
     **/
    public void sortBySuit () {
        ArrayList<Carta> nuevaMano = new ArrayList<>();
        while (mano.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Carta c = mano.get(0);  // Minimal card.
            for (int i = 1; i < mano.size(); i++) {
                Carta c1 = mano.get(i);
                if ( c1.getTraje()< c.getTraje()||
                        (c1.getTraje()== c.getTraje()&& c1.getValor()< c.getValor()) ) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove(pos);
            nuevaMano.add(c);
        }
        mano = nuevaMano;
    }

    /**
     * Clasifica las cartas en la mano para que las cartas del mismo valor sean
     * agrupados. Las cartas con el mismo valor se clasifican por palo.
     * Tenga en cuenta que se considera que los ases tienen el valor más bajo, 1.
     **/
    
    public void sortByValor() {
        ArrayList <Carta> nuevaMano = new ArrayList <> ();
        while (mano.size ()> 0) {
            int pos = 0; // Posición de la tarjeta mínima.
            Carta c; // Tarjeta mínima.
            c = mano.get (0);
            for (int i = 1; i <mano.size (); i ++) {
                Carta c1 = mano.get (i);
                if (c1.getValor()< c.getValor()||
                        (c1.getValor()== c.getValor()&& c1.getTraje()<c.getTraje())) {
                    pos = i;
                    c = c1;
                }
            }
            mano.remove (pos);
            nuevaMano.add (c);
        }
        mano = nuevaMano;
    }

    
}

    

  
 


