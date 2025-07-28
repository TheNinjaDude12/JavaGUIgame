package com.example.demo20;

import java.util.Random;

public class Staff extends Weapon{

    public Staff() {
        super("Staff",30,20);
    }


    public void weapon_ability(Warrior warrior) {
        Random r = new Random();
        switch (r.nextInt(4)) {
            case 0:
                // Fire

                    warrior.setAttack(warrior.getAttack() + 5);
                    System.out.println("Staff ability activated: Fire! +5 attack!");

                break;
            case 1:
                // Wind

                    warrior.setSpeed(warrior.getSpeed() + 5);
                    System.out.println("Staff ability activated: Wind! +5 speed!");

                break;
            case 2:
                // Water

                    warrior.setHitPoints(warrior.getHitPoints() + 10);
                    System.out.println("Staff ability activated: Water! +10 health!");

                break;
            case 3:
                // Earth
                    warrior.setDefense(warrior.getDefense() + 3);
                    System.out.println("Staff ability activated: Earth! +3 defense!");

                break;
        }
    }
}
