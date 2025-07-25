package com.example.demo20;

public class Sword extends Weapon {
    public Sword() {
        super("Sword",30,10);
    }
    public void weapon_ability(Warrior warrior) {
        if (!warrior.isCharging()) {
            warrior.setAttack(getAttack() + 10);
            System.out.println("Sword ability activated: +10 Attack!");
        }
    }
}
