package com.example.demo20;

public class Axe extends Weapon{
    public Axe() {
        super("Axe",40,20);
    }
    public void weapon_ability(Warrior warrior) {
        if(warrior.isCharging()) {
            warrior.setAttack(warrior.getAttack() + 5);
            warrior.setSpeed(warrior.getSpeed()+5);
        }

    }
}
