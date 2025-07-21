package com.example.demo20;

public class Minotaur extends Opponent {

    public Minotaur() {
        super("Minotaur", 350, 40, 40, 20);
    }

    public void think(Warrior warrior, int faux) {
        switch (faux) {
            case 1:         // Turn 1: Attack
                attack(warrior);
                break;
            case 2:         // Turn 2: Charge for next turn
                charge();
                break;
            case 3:         // Turn 3: Attack (will be charged attack from turn 2)
                attack(warrior);
                break;
        }
    }
}
