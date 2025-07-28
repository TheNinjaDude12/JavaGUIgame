package com.example.demo20;

import java.util.Random;

public class Mage extends Opponent {
    public Mage() {
        super("Mage", 250, 30, 30, 30);
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
    public void staffAbility() {
        Random r = new Random();
        switch (r.nextInt(4)) {
            case 0:
                // Fire

                setAttack(getAttack() + 5);
                System.out.println("Staff ability activated: Fire! +5 attack!");

                break;
            case 1:
                // Wind
                speed = (getSpeed() + 5);
                System.out.println("Staff ability activated: Wind! +5 speed!");

                break;
            case 2:
                // Water

                setHitPoints(getHitPoints() + 10);
                System.out.println("Staff ability activated: Water! +10 health!");

                break;
            case 3:
                // Earth
                setDefense(getDefense() + 3);
                System.out.println("Staff ability activated: Earth! +3 defense!");

                break;
        }
    }

    }
