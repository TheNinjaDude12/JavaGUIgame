package com.example.demo20;

public class Dagger extends Weapon{
    public Dagger() {
        super("Dagger",20,20);
    }
    public void weapon_ability(Warrior warrior) {
        if(warrior.isDefendedLastTurn() && warrior.isDefending()) {
            warrior.setDefense(warrior.getDefense() + 9999999);
        }

    }
}
