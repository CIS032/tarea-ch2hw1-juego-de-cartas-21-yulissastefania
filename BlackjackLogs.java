/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author Fanny Quezada
 */
public class BlackjackLogs {
    public static void main(String[] args) {

        try {
            File file = new File("logs.txt");
            FileWriter escribir = new FileWriter(file, true);

            int dinero;
            int apuestas = 0;
            boolean usuarioGano;
            String texto = "Bienvenido al juego de Blackjack\n";
            System.out.println(texto);
            escribir.write(texto);
            //monto para apuestas
            dinero = 100;
            while (true) {
                texto = "Usted tiene: $" + dinero + " para realizar apuestas";
                System.out.println(texto);
                escribir.write(texto);
                do {
                    texto = "Cuanto dinero desea apostar?(Ingrese 0 para finalizar)\n ";
                    System.out.println(texto);
                    escribir.write(texto);
                    Scanner entrada = new Scanner(System.in);
                    apuestas = Integer.parseInt(entrada.nextLine());
                    escribir.write(String.valueOf(apuestas));
                    if (apuestas < 0 || apuestas > dinero) {
                        texto = "Su respuesta debe estar entre 0 y " + dinero + " ";
                        System.out.println(texto);
                        escribir.write(texto);
                    }
                } while (apuestas < 0 || apuestas > dinero);
                if (apuestas == 0) {
                    break;
                }
                usuarioGano = jugarBlackjack();
                if (usuarioGano) {
                    dinero = dinero + apuestas;
                    // escribir.write(auxiliar);
                } else {
                    dinero = dinero - apuestas;
                }
                System.out.println();
                if (dinero == 0) {
                    texto = "Parece que te has quedado sin dinero!!!";
                    System.out.println(texto);
                    escribir.write(texto);
                    break;
                }
            }
            texto = "\nSales con $ " + dinero + " ";
            System.out.println(texto);
            escribir.write(texto);
            escribir.close();
        } catch (Exception e) {
            System.out.println("no se encontro el archivo" + e);

        }
    }//termina el metodo main

    /* Permita que el usuario juegue un juego de Blackjack, con la computadora como distribuidor.
    * @return true si el usuario gana el juego, falso si el usuario pierde.*/
    static boolean jugarBlackjack() throws IOException {
        
        File file = new File("logs.txt");
        FileWriter escribir = new FileWriter(file, true);
        String aux = " ";
        Baraja baraja = new Baraja();//una nueva baraja de cartas para el juego
        ManoBlackJack manoDistribuidor = new ManoBlackJack();//la mano de crupier
         ManoBlackJack manoUsuario = new ManoBlackJack();//la mano del usuario
        // Baraja el mazo, reparte dos cartas a cada jugador. *
        baraja.barajar();
        manoDistribuidor.agregarCarta(baraja.dealCard());
        manoDistribuidor.agregarCarta(baraja.dealCard());
        manoUsuario.agregarCarta(baraja.dealCard());
        manoUsuario.agregarCarta(baraja.dealCard());
        System.out.println();
        /*Verifica si uno de los jugadores tiene Blackjack (dos cartas que suman un total de 21).
       El jugador con Blackjack gana el juego. El distribuidor gana lazos.*/
        if (manoDistribuidor.getBlackjackValor()== 21) {
            aux = "\nEl distribuidor tiene la " + manoDistribuidor.getCarta(0) + " y la " + manoDistribuidor.getCarta(1) + "."
                    + "\nEl usuario tiene la " + manoUsuario.getCarta(0) + " y la" + manoUsuario.getCarta(1) + " . "
                    + "\nEl distribuidor tiene Blackjack. El distribuidor gana";
            System.out.println(aux);
            escribir.write(aux);
            return false;
        }
        if (manoUsuario.getBlackjackValor()== 21) {
            aux = "\nEl distribuidor tiene la " + manoDistribuidor.getCarta(0) + " y la " + manoDistribuidor.getCarta(1) + "."
                    + "\nEl usuario tiene la " + manoUsuario.getCarta(0) + " y la" + manoUsuario.getCarta(1) + " . "
                    + "\nUsted tiene Blackjack. Usted gana.";
            System.out.println(aux);
            escribir.write(aux);
            return true;
        }
        /*Si ninguno de los jugadores tiene Blackjack, juega el juego. Primero el usuario
          tiene la oportunidad de robar cartas (es decir, de "golpear"). El bucle while termina
          cuando el usuario elige "Stand". Si el usuario supera los 21,
          el usuario pierde inmediatamente.*/
        while (true) {
            //Mostrar tarjetas de usuario, y dejar que el usuario decida golpear o pararse. 
            aux = "\nSus cartas son: ";
            System.out.println(aux);
            escribir.write(aux);
            for (int i = 0; i < manoUsuario.getCartaCantidad(); i++) {
                //System.out.println("      " + manoUsuario.getCarta(i));
                aux = "      " + manoUsuario.getCarta(i);
                System.out.println(aux);
                escribir.write(aux);
            }
            aux = "\nSu total es " + manoUsuario.getBlackjackValor()
                    + "\nEl distribuidor muestra la " + manoDistribuidor.getCarta(0)
                    + "\nGolpear (G) o Levantarse (L) ?";
            System.out.println(aux);
            escribir.write(aux);
            char accionUsuario = 'H'; // Respuesta del usuario, 'H' o 'S'
            do {
                Scanner entrada = new Scanner(System.in);
                accionUsuario = entrada.nextLine().charAt(0);
                accionUsuario = Character.toUpperCase(accionUsuario);
                escribir.write(String.valueOf("\n" + accionUsuario));
                if (accionUsuario != 'G' && accionUsuario != 'L') {
                    aux = "\nPor favor responda G o L:  ";
                    System.out.println(aux);
                    escribir.write(aux);
                }
            } while (accionUsuario != 'G' && accionUsuario != 'L');
            
            /*Si el usuario tiene éxito, el usuario obtiene una tarjeta. Si el usuario está parado,
              el bucle termina (y es el turno del crupier de robar cartas)*/
            
            if (accionUsuario == 'L') {
                //Finaliza el ciclo; el usuario ha terminado de tomar cartas.
                break;
            } else {
                //userAction es 'G'. Dale una tarjeta al usuario.  
                // Si el usuario supera los 21, el usuario pierde.
                Carta nuevaCarta = baraja.dealCard();
                manoUsuario.agregarCarta(nuevaCarta);
                aux = "\nEl usuario acierta"
                        + "\nSu tarjeta es la " + nuevaCarta
                        + "\nSu total es ahora " + manoUsuario.getBlackjackValor();
                System.out.println(aux);
                escribir.write(aux);
                if (manoUsuario.getBlackjackValor()> 21) {
                    aux = "\nHas fallado al pasar de 21. Usted Pierde"
                            + "\nOtra tarjeta del distribuidor fue el " + manoDistribuidor.getCarta(1);
                    System.out.println(aux);
                    escribir.write(aux);
                    return false;
                }
            }
        }//fin del While
        
        /* Si llegamos a este punto, el usuario tiene valor de 21 o menos. Ahora es
         la oportunidad del distribuidor para dibujar. El distribuidor roba cartas hasta que el crupier
         el total es> 16. Si el crupier supera los 21, el crupier pierde.*/
        aux = "\nUsuario parado"
                + "\nLas tarjetas del distribuidor son: "
                + "\n     " + manoDistribuidor.getCarta(0)
                + "\n     " + manoDistribuidor.getCarta(1);
        System.out.println(aux);
        escribir.write(aux);
        while (manoDistribuidor.getBlackjackValor()<= 16) {
            Carta nuevaCarta = baraja.dealCard();
            aux = "\nEl distribuidor golpea y obtiene la " + nuevaCarta;
            System.out.println(aux);
            escribir.write(aux);
            manoDistribuidor.agregarCarta(nuevaCarta);
            if (manoDistribuidor.getBlackjackValor()> 21) {
                aux = "\nDistribuidor detenido por pasar de 21. Usted gana";
                System.out.println(aux);
                escribir.write(aux);
                return true;
            }
        }
        aux = "\nEl total del distribuidor es: " + manoDistribuidor.getBlackjackValor();
        System.out.println(aux);
        escribir.write(aux);
        /* Si llegamos a este punto, ambos jugadores tienen 21 o menos. Nosotros
         puede determinar el ganador comparando los valores de sus manos*/
        if (manoDistribuidor.getBlackjackValor()== manoUsuario.getBlackjackValor()) {
            aux = "\nEl distribuidor gana en un empate. Pierdes";
            System.out.println(aux);
            escribir.write(aux);
            return false;
        } else if (manoDistribuidor.getBlackjackValor()> manoUsuario.getBlackjackValor()) {
            aux = "\nEl distribuidor gana " + manoDistribuidor.getBlackjackValor() + " tu puntuacion " + manoUsuario.getBlackjackValor() + " . ";
            System.out.println(aux);
            escribir.write(aux);
            return false;
        } else {
            aux = "\nUsted gana " + manoUsuario.getBlackjackValor() + " puntuacion del crupier " + manoDistribuidor + " . ";
            System.out.println(aux);
            escribir.write(aux);
            return true;
        }
        
}
}
