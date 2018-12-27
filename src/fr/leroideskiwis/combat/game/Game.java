package fr.leroideskiwis.combat.game;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private Player[] players = new Player[2];
    private Player currentPlayer;
    private Scanner scan;

    public Game(Player player, Player player2, Scanner scan){

        players[0] = player;
        players[1] = player2;
        currentPlayer = players[0];
        this.scan = scan;

    }

    public int nextInt(String s){

        System.out.print(s);

        int scanInt = 0;
        do {
            try {

                scanInt = scan.nextInt();
                System.out.println("");

                return scanInt;

            } catch (Exception e) {

                System.out.println("");
                System.out.print("Merci de rentrer un nombre ! ");
                scan.nextLine();

            }



        }while(true);

    }

    public void game(){



        System.out.println("C'est au tour de "+currentPlayer.getName()+" de jouer !");

        if(currentPlayer.isFirstTry()) System.out.println("Règle du jeu : vous devez trouver un nombre entre 1 et 50. Vous avez 10 essais. Le nombre d'essai restant vous sera donné en tant que points d'attaque.");
        currentPlayer.successTry();

        Random random = new Random();

        int randomInt = random.nextInt(50)+1;

        int scanInt = nextInt("Veuillez rentrer un nombre : ");
        int trys = 0;

        for(trys = 10; (randomInt != scanInt); trys--){

            if(randomInt < scanInt){

                System.out.println("Le nombre mystère est plus petit !");

            } else System.out.println("Le nombre mystère est plus grand !");

            System.out.println("Il vous reste "+trys+" essais");

            scanInt = nextInt("Veuillez rentrer un nombre : ");

            if(randomInt == scanInt) break;
            if(trys <= 0) break;

        }

        if(trys <= 0) System.out.println("Vous n'avez gagné aucun point d'attaque.");
        else {

            System.out.println("Vous avez gagné "+trys+" points d'attaque !");
            currentPlayer.addAttack(trys);

        }

        if(currentPlayer.equals(players[0])) {

            switchPlayer();
            game();
        } else {
            switchPlayer();
            attacks();
        }

    }

    public int ptAttack(Player p, String s){

            if(p.getAttack() <= 0) {
                System.out.println("Vous n'avez pas de points d'attaque !");
                return 0;
            }

            int asked = nextInt(s);

        do{
            if(p.getAttack() >= asked) return asked;
            else asked = nextInt("Vous n'avez pas assez de point d'attaque ! Combien voulez vous en utiliser ? ");

        }while(true);

    }

    private void attacks() {

        int hm = ptAttack(currentPlayer, currentPlayer.getName()+", vous avez "+currentPlayer.getAttack()+" points d'attaque. Vous voulez en utiliser combien ? ");

        currentPlayer.removeAttack(hm);
        if(hm > 0) {

            System.out.println("Ok ! Il vous reste désormais " + currentPlayer.getAttack() + " points d'attaque.");

            getOtherPlayer().attack(currentPlayer, hm);

            System.out.println(getOtherPlayer().getName() + " perds " + hm + " vies ! Il lui en reste " + getOtherPlayer().getHealth() + " !");
        }

        checkWin();

        if(currentPlayer.equals(players[0])){
            switchPlayer();
            attacks();
        } else {
            switchPlayer();
            game();
        }

    }

    public Player getOtherPlayer(){

        return players[0].equals(currentPlayer) ? players[1] : players[0];

    }

    public Player getOtherPlayer(Player p){

        return players[0].equals(p) ? players[1] : players[0];

    }

    public void checkWin(){

        for(Player p : players){

            if(p.getHealth() <= 0){

                Player winner = getOtherPlayer(p);
                System.out.println("Félicitation à "+winner.getName()+" qui à gagner !!!");
                System.exit(0);

            }

        }

    }

    public void start(){

        System.out.println("Règle du jeu : tuer l'ennemi");
        System.out.println("Le joueur "+currentPlayer.getName()+" commence !");

        game();

    }

    public void switchPlayer(){

        currentPlayer = currentPlayer.equals(players[0]) ? players[1] : players[0];

    }

}