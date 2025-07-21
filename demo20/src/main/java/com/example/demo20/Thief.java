package com.example.demo20;


public class Thief extends Opponent {
    public Thief() {
        super("Thief", 150, 20, 20, 40);
    }

    public void think(Warrior warrior, int faux) {
        // Thief AI: Always attacks (aggressive glass cannon strategy)
        attack(warrior);
    }
}