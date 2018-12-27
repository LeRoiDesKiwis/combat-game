package fr.leroideskiwis.combat;

import fr.leroideskiwis.combat.game.Game;
import fr.leroideskiwis.combat.game.Player;

import java.util.Scanner;

public class Main implements Runnable{

    private Game game;
    private Scanner scan = new Scanner(System.in);

    public Main(){

        String player1 = nextLine("Nom du joueur 1 : ");
        String player2 = nextLine("Nom du joueur 2 : ");

        game = new Game(new Player(player1), new Player(player2), scan);
        game.start();
    }

    public String nextLine(String s){

        System.out.print(s);
        String result = scan.nextLine();
        System.out.println("");
        return result;

    }

    public static void main(String... args){

        new Thread(new Main(), "main").start();

    }

    @Override
    public void run() {

    }
}