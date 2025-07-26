package com.example.demo20;

public class Dagger extends Weapon{
    public Dagger() {
        super("Dagger",20,0);
    }
    public void weapon_ability(Warrior warrior) {
        if(warrior.isDefendedLastTurn() && warrior.isDefending()) {
            System.out.println("EVADING");
            warrior.setDefense(warrior.getDefense() + 9999999);
        }

    }
}
