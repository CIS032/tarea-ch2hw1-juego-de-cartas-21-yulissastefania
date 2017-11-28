/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import javax.swing.JOptionPane;

/**
 *
 * @author Fanny Quezada
 */
public class BlackjackGUI {
  public static void main(String[] args) {

        int dinero; // Cantidad de dinero que tiene el usuario.
        int apuesta; // Cantidad de apuestas de usuario en un juego.
        char opcion;
        boolean usuarioGana; // ¿El usuario ganó el juego?
        String auxp="";
        
        auxp+="Bienvenido al juego de Blackjack\n"; 
        
        

        dinero = 100; // El usuario comienza con $ 100.

        while (true) {
            //JOptionPane.showMessageDialog(null,"Tiene " + dinero + " dólares","Blackjack", JOptionPane.INFORMATION_MESSAGE);
            
            do {
                
                String aux= "Usted tiene: $" + dinero + " para realizar apuestas";
                apuesta = Integer.parseInt(JOptionPane.showInputDialog(null, auxp+ aux+"\n\"Cuanto dinero desea apostar? (Ingresa 0 para finalizar)","Blackjack", JOptionPane.INFORMATION_MESSAGE));
                auxp="";

                if (apuesta < 0 || apuesta > dinero) 
                    JOptionPane.showMessageDialog(null, "Su respuesta debe estar entre 0 y " + dinero + '.',"Blackjack", JOptionPane.INFORMATION_MESSAGE);
            } while (apuesta < 0 || apuesta > dinero);
            if (apuesta == 0) 
                break;
            usuarioGana = jugarBlackjack();
            if (usuarioGana) 
                dinero = dinero + apuesta;
            else 
                dinero = dinero - apuesta;
            
            if (dinero == 0) {
                JOptionPane.showMessageDialog(null, "Parece que te has quedado sin dinero!!!","Blackjack", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "\nSales con $ " + dinero + '.',"Blackjack", JOptionPane.INFORMATION_MESSAGE);

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

        

        /* Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21).
         El jugador con Blackjack gana el juego. El distribuidor gana lazos.
         */
        String aux1 = "";
        if (manoDistribuidor.getBlackjackValor() == 21) {
             aux1+= "El distribuidor tiene la " + manoDistribuidor.getCarta(0)
                    + " y la " + manoDistribuidor.getCarta(1) + ".";
             aux1+="El usuario tiene la " + usuarioMano.getCarta(0)
                    + " y la " + usuarioMano.getCarta(1) + ".";
            
             aux1+= "El distribuidor tiene Blackjack. El distribuidor gana.";
             JOptionPane.showMessageDialog(null,aux1,"Blackjack", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        String aux2 = "";
        if (usuarioMano.getBlackjackValor() == 21) {
            
            aux2+="El distribuidor tiene la " + manoDistribuidor.getCarta(0)
                    + " y la " + manoDistribuidor.getCarta(1) + ".";
            aux2+="El usuario tiene la " + usuarioMano.getCarta(0)
                    + " y la" + usuarioMano.getCarta(1) + ".";
            
            aux2+= "Usted tiene Blackjack. Usted gana.";
            JOptionPane.showMessageDialog(null,aux2,"Blackjack", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }

        /* Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero el usuario
          tiene la oportunidad de robar cartas (es decir, de "golpear"). El bucle while termina
          cuando el usuario elige "Stand". Si el usuario supera los 21,
          el usuario pierde inmediatamente.
         */
        while (true) {
        String aux3= "";
            /* Mostrar tarjetas de usuario, y dejar que el usuario decida golpear o pararse. */
            
            aux3+="Sus cartas son:\n";
            for (int i = 0; i < usuarioMano.getCartaCantidad(); i++) {
                aux3+= usuarioMano.getCarta(i)+"\n";
            }
            aux3+="Su total es: " + usuarioMano.getBlackjackValor();
           
            aux3+= "\n" + "El distribuidor muestra la " + manoDistribuidor.getCarta(0);
             JOptionPane.showMessageDialog(null,aux3,"Blackjack", JOptionPane.INFORMATION_MESSAGE);
            
             
             //char userAction = JOptionPane.showInputDialog(null,"Golpear (G) o Estar (E)?"); // Respuesta del usuario, 'G' o 'E'.
             String userAction="";
            do {
                userAction=JOptionPane.showInputDialog(null,"Golpear (G) o Levantarse (L)?");
                if (!userAction.equalsIgnoreCase("G") && !userAction.equalsIgnoreCase("L")) {
                    userAction+= "Responda G o L:";
                }
                //userAction = (char) Character.toUpperCase(TextIO.getlnInt());
                /*if (userAction. && userAction != "E") {
                    JOptionPane.showMessageDialog(null,"Responda G o E:");
                }*/
            } while (!userAction.equalsIgnoreCase("G") && !userAction.equalsIgnoreCase("L"));

            /* Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario está parado,
              el bucle termina (y es el turno del crupier de robar cartas).
             */
            if (userAction.equalsIgnoreCase("L")) {
                // Loop ends; el usuario ha terminado de tomar cartas.
                break;
            } else {// userAction es 'G'. Dale una tarjeta al usuario.  
                // Si el usuario supera los 21, el usuario pierde.
                Carta newCard = baraja.dealCard();
                usuarioMano.agregarCarta(newCard);
                String aux4="";
                aux4+="El usuario acierta.";
                aux4+="\n" + "Su tarjeta es la " + newCard;
                aux4+="\n"+ "Su total es ahora " + usuarioMano.getBlackjackValor();
                JOptionPane.showMessageDialog(null,aux4,"Blackjack", JOptionPane.INFORMATION_MESSAGE);
                if (usuarioMano.getBlackjackValor() > 21) {
                String aux5="";    
                    aux5+="Has fallado al pasar de 21. Pierdes.";
                    aux5+="\n"+"Otra tarjeta del distribuidor fue el "
                            + manoDistribuidor.getCarta(1);
                JOptionPane.showMessageDialog(null,aux5,"Blackjack", JOptionPane.INFORMATION_MESSAGE);   
                    return false;
                }
            }

        } // end while loop

        /* Si llegamos a este punto, el usuario tiene Stood con 21 o menos. Ahora es
         la oportunidad del distribuidor para dibujar. El distribuidor roba cartas hasta que el crupier
         el total es> 16. Si el crupier supera los 21, el crupier pierde.
         */
       String aux6= "";
        aux6+="Usuario parado.";
        aux6+="\n" +"Las tarjetas del distribuidor son: ";
        aux6+="\n" + manoDistribuidor.getCarta(0);
        aux6+="\n" + manoDistribuidor.getCarta(1);
        JOptionPane.showMessageDialog(null,aux6,"Blackjack", JOptionPane.INFORMATION_MESSAGE);
        while (manoDistribuidor.getBlackjackValor() <= 16) {
            Carta newCard = baraja.dealCard();
            JOptionPane.showMessageDialog(null,"El distribuidor golpea y obtiene la " + newCard + ".","Blackjack", JOptionPane.INFORMATION_MESSAGE);
            manoDistribuidor.agregarCarta(newCard);
            if (manoDistribuidor.getBlackjackValor() > 21) {
            JOptionPane.showMessageDialog(null,"Distribuidor detenido por pasar de 21. Usted gana.","Blackjack", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }
        JOptionPane.showMessageDialog(null,"El total del distribuidor es: " + manoDistribuidor.getBlackjackValor(),"Blackjack", JOptionPane.INFORMATION_MESSAGE);

        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos. */
        
        if (manoDistribuidor.getBlackjackValor() == usuarioMano.getBlackjackValor()) {
            JOptionPane.showMessageDialog(null,"El distribuidor gana en un empate. Pierdes.","Blackjack", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else if (manoDistribuidor.getBlackjackValor() > usuarioMano.getBlackjackValor()) {
            JOptionPane.showMessageDialog(null,"El distribuidor gana, " + manoDistribuidor.getBlackjackValor()
                    + " tu puntuacion " + usuarioMano.getBlackjackValor() + ".","Blackjack", JOptionPane.INFORMATION_MESSAGE);
            return false;
        } else {
            JOptionPane.showMessageDialog(null,"Usted gana, " + usuarioMano.getBlackjackValor()
                    + " puntuacion del crupier" + manoDistribuidor.getBlackjackValor() + ".","Blackjack", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }

    } // fin de jugarBlackjack () 
}
