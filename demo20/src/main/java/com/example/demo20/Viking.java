package com.example.demo20;

public class Viking extends Opponent{

    public Viking() {
        super("Viking", 250, 30, 30, 30);
    }

    public void think(Warrior warrior, int faux) {
        switch (faux) {
            case 1, 3:      // On turns 1 and 3 of the pattern
                attack(warrior);
                break;
            case 2:         // On turn 2 of the pattern
                defend();
                System.out.println("VIKING IS DEFENDING!!!");
                break;
        }
    }
}
