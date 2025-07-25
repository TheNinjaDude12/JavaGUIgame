
package com.example.demo20;

public class BattleAxe extends Weapon {
    public BattleAxe() {
        super("Axe",40,20);
    }
    public void weapon_ability(Warrior warrior) {
        if (warrior.isCharging()) {
            warrior.setSpeed(warrior.getSpeed() + 5);
            warrior.setAttack(warrior.getAttack() + 5);
            System.out.println("Battleaxe ability activated: +5 speed and +5 attack!");
        }
    }
}
