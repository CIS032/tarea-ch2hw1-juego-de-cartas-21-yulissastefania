
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

/**
 *
 * @author Fanny Quezada
 */

/**
 * Este programa permite al usuario jugar Blackjack. El ordenador actúa como el
 * distribuidor. El usuario tiene una apuesta de $ 100 y hace una apuesta en
 * cada juego. El usuario puede irse en cualquier momento, o será expulsado
 * cuando pierda todo el dinero. Reglas de la casa: el repartidor golpea a un
 * total de 16 o menos y se encuentra en un total de 17 o más. El distribuidor
 * gana lazos. Se usa una nueva baraja de cartas para cada juego.
 *
 */
public class Blackjack {

    public static void main(String[] args) {

        int dinero; // Cantidad de dinero que tiene el usuario.
        int apuesta; // Cantidad de apuestas de usuario en un juego.
        boolean usuarioGana; // ¿El usuario ganó el juego?

        System.out.println("Bienvenido al juego de Blackjack");
        System.out.println();

        dinero = 100; // El usuario comienza con $ 100.

        while (true) {
            System.out.println("Usted tiene: $" + dinero + " para realizar apuestas");
            do {
                System.out.println("Cuanto dinero desea apostar?(Ingrese 0 para finalizar)\n");
                System.out.print("?");
                apuesta = TextIO.getlnInt();
                if (apuesta < 0 || apuesta > dinero) 
                    System.out.println("Su respuesta debe estar entre 0 y " + dinero + '.');
            } while (apuesta < 0 || apuesta > dinero);
            if (apuesta == 0) 
                break;
            usuarioGana = jugarBlackjack();
            if (usuarioGana) 
                dinero = dinero + apuesta;
            else 
                dinero = dinero - apuesta;
            System.out.println();
            if (dinero == 0) {
                System.out.println("Parece que te has quedado sin dinero!!!");
                break;
            }
        }
        System.out.println();
        System.out.println("Sales con $ " + dinero + '.');

    }
    

    /**
     * Permita que el usuario juegue un juego de Blackjack , con la computadora
     * como distribuidor .
     *
     * @
     * return true si el usuario gana el juego, falso si el usuario pierde
     *
     * .
     *
     */
    
    static boolean jugarBlackjack() {
        Baraja baraja; // Una baraja de cartas. Una nueva baraja para cada juego.
        ManoBlackJack manoDistribuidor; // La mano del crupier.
        ManoBlackJack usuarioMano; // La mano del usuario.

        baraja = new Baraja();
        manoDistribuidor = new ManoBlackJack();
        usuarioMano = new ManoBlackJack();

        /* Baraja el mazo, reparte dos cartas a cada jugador. */
        baraja.barajar();
        manoDistribuidor.agregarCarta(baraja.dealCard());
        manoDistribuidor.agregarCarta(baraja.dealCard());
        usuarioMano.agregarCarta(baraja.dealCard());
        usuarioMano.agregarCarta(baraja.dealCard());

        System.out.println();
        System.out.println();

        /* Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21).
         El jugador con Blackjack gana el juego. El distribuidor gana lazos.
         */
        if (manoDistribuidor.getBlackjackValor() == 21) {
            System.out.println("El distribuidor tiene la " + manoDistribuidor.getCarta(0)
                    + " y la " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene la " + usuarioMano.getCarta(0)
                    + " y la " + usuarioMano.getCarta(1) + ".");
            System.out.println();
            System.out.println("El distribuidor tiene Blackjack. El distribuidor gana.");
            return false;
        }

        if (usuarioMano.getBlackjackValor() == 21) {
            System.out.println("El distribuidor tiene la " + manoDistribuidor.getCarta(0)
                    + " y la " + manoDistribuidor.getCarta(1) + ".");
            System.out.println("El usuario tiene la " + usuarioMano.getCarta(0)
                    + " y la " + usuarioMano.getCarta(1) + ".");
            System.out.println();
            System.out.println("Usted tiene Blackjack. Usted gana.");
            return true;
        }

        /* Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero el usuario
          tiene la oportunidad de robar cartas (es decir, de "golpear"). El bucle while termina
          cuando el usuario elige "Stand". Si el usuario supera los 21,
          el usuario pierde inmediatamente.
         */
        while (true) {

            /* Mostrar tarjetas de usuario, y dejar que el usuario decida golpear o pararse. */
            System.out.println();
            System.out.println();
            System.out.println("Sus cartas son: ");
            for (int i = 0; i < usuarioMano.getCartaCantidad(); i++) {
                System.out.println("" + usuarioMano.getCarta(i));
            }
            System.out.println("Su total es " + usuarioMano.getBlackjackValor());
            System.out.println();
            System.out.println("El distribuidor muestra la " + manoDistribuidor.getCarta(0)+".");
            System.out.println();
            System.out.print("Golpear (G) o Levantarse (L)?");
            char userAction; // Respuesta del usuario, 'G' o 'E'.
            do {
                userAction = Character.toUpperCase( TextIO.getlnChar() );
                if (userAction != 'G' && userAction != 'L') {
                    System.out.print("Responda G o L:");
                }
            } while (userAction != 'G' && userAction != 'L');

            /* Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario está parado,
              el bucle termina (y es el turno del crupier de robar cartas).
             */
            if (userAction == 'L') {
                // Loop ends; el usuario ha terminado de tomar cartas.
                break;
            } else {// userAction es 'G'. Dale una tarjeta al usuario.  
                // Si el usuario supera los 21, el usuario pierde.
                Carta newCard = baraja.dealCard();
                usuarioMano.agregarCarta(newCard);
                System.out.println();
                System.out.println("El usuario acierta");
                System.out.println("Su tarjeta es la " + newCard);
                System.out.println("Su total es ahora " + usuarioMano.getBlackjackValor());
                if (usuarioMano.getBlackjackValor() > 21) {
                    System.out.println();
                    System.out.println("Has fallado al pasar de 21. Pierdes.");
                    System.out.println("Otra tarjeta del distribuidor fue el."
                            + manoDistribuidor.getCarta(1));
                    return false;
                }
            }

        } // end while loop

        /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora es
         la oportunidad del distribuidor para dibujar. El distribuidor roba cartas hasta que el crupier
         el total es> 16. Si el crupier supera los 21, el crupier pierde.
         */
        System.out.println();
        System.out.println("Usuario parado.");
        System.out.println("Las tarjetas del distribuidor son: ");
        System.out.println("" + manoDistribuidor.getCarta(0));
        System.out.println("" + manoDistribuidor.getCarta(1));
        while (manoDistribuidor.getBlackjackValor() <= 16) {
            Carta newCard = baraja.dealCard();
            System.out.println("El distribuidor golpea y obtiene la " + newCard + 
                    ".");
            manoDistribuidor.agregarCarta(newCard);
            if (manoDistribuidor.getBlackjackValor() > 21) {
                System.out.println();
                System.out.println("Distribuidor detenido por pasar de 21. Usted gana.");
                return true;
            }
        }
        System.out.println("El total del distribuidor es: " + manoDistribuidor.getBlackjackValor());

        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos. */
        System.out.println();
        if (manoDistribuidor.getBlackjackValor() == usuarioMano.getBlackjackValor()) {
            System.out.println("El distribuidor gana en un empate. Pierdes.");
            return false;
        } else if (manoDistribuidor.getBlackjackValor() > usuarioMano.getBlackjackValor()) {
            System.out.println("El distribuidor gana, " + manoDistribuidor.getBlackjackValor()
                    + " tu puntuación " + usuarioMano.getBlackjackValor() + ".");
            return false;
        } else {
            System.out.println("Usted gana, " + usuarioMano.getBlackjackValor()
                    + " puntuacion del crupier " + manoDistribuidor.getBlackjackValor() + ".");
            return true;
        }

    } // fin de jugarBlackjack () 

} // clase final Blackjack

