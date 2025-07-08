package com.example.demo20;

import java.util.Random;

/**
 * Represents an opponent in a combat scenario with attributes such as name, hit points, attack, defense, and speed.
 * The opponent can perform different combat actions such as attacking, defending, or charging.
 */
public class Opponent {


    // Opponent statistics
    /**
     * The name/type of the opponent (e.g., "Thief", "Viking", "Minotaur").
     */
    private final String name;            // Name/type of the opponent (Thief, Viking, Minotaur)

    /**
     * Health points of the opponent. The opponent dies when this reaches 0.
     */
    private int hitPoints;          // Health points - opponent dies when this reaches 0

    /**
     * The attack damage value of the opponent.
     */
    private int attack;             // Attack damage value

    /**
     * The defense value of the opponent. Reduces incoming damage.
     */
    private int defense;            // Defense value (reduces incoming damage)

    /**
     * The speed value of the opponent. Determines the turn order.
     */
    private int speed;              // Speed value (determines turn order)

    // Battle state tracking
    /**
     * True when the opponent is charging for the next attack (triple damage).
     */
    private boolean isCharging = false;     // True when opponent is charging for next attack

    /**
     * True when the opponent is currently defending (halves incoming damage).
     */
    private boolean isDefending = false;    // True when opponent is currently defending

    /**
     * Constructs a new Opponent with the specified combat statistics.
     * Both charging and defending states are initialized to false.
     *
     * @param name The name/type of the opponent (e.g., "Thief", "Viking", "Minotaur")
     * @param hitPoints The initial hit points of the opponent
     * @param attack The attack damage value of the opponent
     * @param defense The defense value of the opponent
     * @param speed The speed value of the opponent (used for turn order)
     */
    public Opponent(String name, int hitPoints, int attack, int defense, int speed) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;

    }

    // Getter methods for accessing opponent statistics
    /**
     * Retrieves the name of the opponent.
     *
     * @return The name/type of the opponent.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the hit points of the opponent.
     *
     * @return The opponent's current hit points.
     */
    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * Retrieves the attack value of the opponent.
     *
     * @return The opponent's attack value.
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Retrieves the defense value of the opponent.
     *
     * @return The opponent's defense value.
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Retrieves the speed value of the opponent.
     *
     * @return The opponent's speed value.
     */
    public int getSpeed() {
        return speed;
    }

    // Setter methods for modifying opponent statistics

    /**
     * Sets the hit points of the opponent.
     *
     * @param hitPoints The hit points to be set for the opponent.
     */
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Sets the attack value of the opponent.
     *
     * @param attack The attack value to be set for the opponent.
     */
    public void setAttack(int attack) {
        this.attack = attack;
    }

    /**
     * Sets the defense value of the opponent.
     *
     * @param defense The defense value to be set for the opponent.
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    // Defense state management
    /**
     * Returns whether the opponent is currently defending.
     *
     * @return true if the opponent is defending, false otherwise.
     */
    public boolean isDefending() {
        return isDefending;
    }

    /**
     * Resets the opponent's defending state to false.
     *
     * @param state sets the state for whether the opponent is defending.
     */
    public void setDefending(boolean state) {
        isDefending = state;
    }

    // Combat action: Attack the warrior
    /**
     * Attacks the warrior and calculates the damage based on whether the opponent is charging,
     * and whether the warrior is defending.
     *
     * @param warrior The warrior to be attacked.
     */
    public void attack(Warrior warrior) {
        int damage;

        // Check if this is a charged attack (triple damage)
        if(isCharging) {
            System.out.println(getName() + " CHARGED, ATTACK WILL DO TRIPLE DAMAGE!!!");
            damage = (getAttack() * 3 - warrior.getDefense());      // Triple damage minus warrior's defense
            isCharging = false;                                     // Reset charging state after use
        }
        else{
            damage = (getAttack() - warrior.getDefense());          // Normal damage calculation
        }

        // Check if warrior is defending (halves damage)
        if(warrior.isDefending()) {
            damage = (getAttack()/2 - warrior.getDefense()); //Damage divided by 2 then reduced
            warrior.setDefending(false);                            // Reset warrior's defending state
        }

        // Ensure damage is never negative
        if(damage < 0) {
            damage = 0;
        }



        System.out.println(getName()+ " attacks Warrior for " + damage + " damage!\n");
        warrior.setHitPoints(warrior.getHitPoints() - damage);      // Apply damage to warrior
    }

    // Combat action: Defend (reduces incoming damage by half)
    /**
     * Sets the opponent to a defending state, which halves the incoming damage.
     */
    public void defend() {
        isDefending = true;         // Set defending state
    }



    // Combat action: Charge for next attack (enables triple damage on next attack)
    /**
     * Sets the opponent to a charging state, enabling triple damage for the next attack.
     */
    public void charge() {
        isCharging = true;
        //Charge effects for Magician Enemy
        if(name.equals("Magician")) {
            Random r = new Random();
            switch (r.nextInt(4)) {
                case 0:
                    // Fire
                    attack += 5;
                    System.out.println("Magician ability activated: Fire! +5 attack!");
                    break;
                case 1:
                    // Wind

                    speed += 5;
                    System.out.println("Magician ability activated: Wind! +5 speed!");

                    break;
                case 2:
                    // Water

                    hitPoints += 10;
                    System.out.println("Magician ability activated: Water! +10 health!");

                    break;
                case 3:
                    // Earth

                    defense += 3;
                    System.out.println("Magician ability activated: Earth! +3 defense!");


            }
        }// Set charging state
    }

    // AI decision making - determines opponent's action based on their type and turn pattern
    /**
     * Determines the opponent's action based on their type and turn pattern.
     * The opponent can either attack, defend, or charge depending on their type.
     *
     * @param warrior The warrior involved in the combat.
     * @param faux A value representing the current turn in the pattern (used for AI decision-making).
     */
    public void think(Warrior warrior, int faux) {
        switch(getName()) {
            case "Thief":
                // Thief AI: Always attacks (aggressive glass cannon strategy)
                attack(warrior);
                break;

            case "Viking":
                // Viking AI: Alternates between attacking and defending
                switch (faux) {
                    case 1, 3:      // On turns 1 and 3 of the pattern
                        attack(warrior);
                        break;
                    case 2:         // On turn 2 of the pattern
                        attack(warrior);
                        break;
                }
                break;

            case "Minotaur":
                // Minotaur AI: Attack, charge, attack pattern (uses charge for big damage)
                switch (faux) {
                    case 1:         // Turn 1: Attack
                        attack(warrior);
                        break;
                    case 2:         // Turn 2: Charge for next turn
                        attack(warrior);
                        break;
                    case 3:         // Turn 3: Attack (will be charged attack from turn 2)
                        attack(warrior);
                        break;
                }
                break;
            case "Magician":
                // Minotaur AI: Attack, charge, attack pattern (uses charge for big damage)
                switch (faux) {
                    case 1:         // Turn 1: Attack
                        attack(warrior);
                        break;
                    case 2:         // Turn 2: Charge for next turn
                        System.out.println("MAGICIAN IS CHARGING!!!");
                        charge();
                        break;
                    case 3:         // Turn 3: Attack (will be charged attack from turn 2)
                        attack(warrior);
                        break;
                }
                break;
        }
    }
}
