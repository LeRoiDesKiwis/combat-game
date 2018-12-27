package fr.leroideskiwis.combat.game;

public class Player {

    private int attack;
    private int defense;
    private String name;
    private int health;
    private boolean firstTry;

    public Player(String name) {
        this.name = name;
        this.health = 50;
    }

    public void attack(Player attacker, int attack){

        this.health -= attack;
        zero();

    }

    public boolean hasEnoughPtA(int asked){

        return attack >= asked;

    }

    public void zero(){

        health = health < 0 ? 0 : health;
        attack = attack < 0 ? 0 : attack;

    }

    public boolean isFirstTry() {
        return firstTry;
    }

    public void successTry(){
        if(!isFirstTry()) this.firstTry = false;

    }

    public void removeAttack(int attack){

        this.attack -= attack;
        zero();
    }

    public void addAttack(int attack){

        this.attack += attack;
        zero();

    }

    public void sendMessage(String s){

        sendMessage(this, s);

    }

    public void sendMessage(Player p, String s){

        System.out.println(p.getName() + " -> "+s);

    }

    public int getAttack() {
        zero();
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        zero();
        return health;
    }
}
