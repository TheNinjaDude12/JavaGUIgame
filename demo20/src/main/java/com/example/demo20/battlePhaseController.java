package com.example.demo20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class battlePhaseController {

    Warrior warrior = warriorController.warrior;
    Environment environment = chooseEnvironmentController.environment;
    Opponent opponent = chooseOpponentController.opponent;
    public static int faux = 1;
    public static int gameOver = 0;
    @FXML
    ImageView warriorView;
    @FXML
    ImageView armorView;
    @FXML
    ImageView weaponView;
    @FXML
    ImageView opponentView;
    @FXML
    Text warriorHP;
    @FXML
    Text warriorDefense;
    @FXML
    Text warriorAttack;
    @FXML
    Text warriorSpeed;
    @FXML
    Text opponentHP;
    @FXML
    Text opponentDefense;
    @FXML
    Text opponentAttack;
    @FXML
    Text opponentSpeed;

    @FXML
    Button attackButton;

    public void initialize() {
        Image warriorJPG = new Image(getClass().getResource("/WarriorAssets/warrior.jpg").toExternalForm());
        warriorView.setImage(warriorJPG);
        switch (warrior.getArmor().getName()) {
            case "Light Armor":
                Image lightArmorImage = new Image(getClass().getResource("/WarriorAssets/leather.png").toExternalForm());
                armorView.setImage(lightArmorImage);
                break;
            case "Medium Armor":
                Image mediumArmorImage = new Image(getClass().getResource("/WarriorAssets/2811d2cdb07721c.png").toExternalForm());
                armorView.setImage(mediumArmorImage);
                break;
            case "Heavy Armor":
                Image heavyArmorImage = new Image(getClass().getResource("/WarriorAssets/heavyarmor.jpg").toExternalForm());
                armorView.setImage(heavyArmorImage);
                break;
        }
        switch (warrior.getWeapon().getName()) {
            case "Dagger":
                Image daggerImage = new Image(getClass().getResource("/WarriorAssets/Dagger.jpg").toExternalForm());
                weaponView.setImage(daggerImage);
                break;
            case "Sword":
                Image swordImage = new Image(getClass().getResource("/WarriorAssets/Sword.jpg").toExternalForm());
                weaponView.setImage(swordImage);
                break;
            case "Battleaxe":
                Image axeImage = new Image(getClass().getResource("/WarriorAssets/Battleaxer.jpg").toExternalForm());
                weaponView.setImage(axeImage);
                break;
        }
        switch (opponent.getName()) {
            case "Thief":
                Image image = new Image(getClass().getResource("/OpponentAssets/thief.png").toExternalForm());
                opponentView.setImage(image);
                break;
            case "Viking":
                image = new Image(getClass().getResource("/OpponentAssets/viking.jpg").toExternalForm());
                opponentView.setImage(image);
                break;
            case "Minotaur":
                image = new Image(getClass().getResource("/OpponentAssets/minotaur.png").toExternalForm());
                opponentView.setImage(image);
                break;
        }
        warriorHP.setText("" + warrior.getHitPoints());
        warriorDefense.setText("" + warrior.getDefense());
        warriorAttack.setText("" + warrior.getAttack());
        warriorSpeed.setText("" + warrior.getSpeed());
        opponentHP.setText("" + opponent.getHitPoints());
        opponentDefense.setText("" + opponent.getDefense());
        opponentAttack.setText("" + opponent.getAttack());
        opponentSpeed.setText("" + opponent.getSpeed());


    }

    public void warriorAttack(ActionEvent e) throws IOException {
        if (warrior.getSpeed() > opponent.getSpeed()) {
            warrior.attack(opponent);
            opponent.think(warrior, faux);

        } else if (warrior.getSpeed() < opponent.getSpeed()) {
            opponent.think(warrior, faux);
            warrior.attack(opponent);
        }

        opponentHP.setText("" + opponent.getHitPoints());
        warriorHP.setText("" + warrior.getHitPoints());
        if (opponent.getHitPoints() > 0 && warrior.getHitPoints() > 0) {
            if (faux < 3) {
                faux++;
            } else {
                faux = 1;
            }
        } else {
            if (opponent.getHitPoints() <= 0 && warrior.getHitPoints() <= 0) {
                gameOver = 1;
            } else if (opponent.getHitPoints() <= 0) {
                gameOver = 2;
            } else {
                gameOver = 0;
            }
            checkWin(gameOver, e);
        }
    }

    public void warriorDefend(ActionEvent e) throws IOException {
        warrior.defend();
        opponent.think(warrior, faux);

        opponentHP.setText("" + opponent.getHitPoints());
        warriorHP.setText("" + warrior.getHitPoints());
        if (opponent.getHitPoints() > 0 && warrior.getHitPoints() > 0) {
            if (faux < 3) {
                faux++;
            } else {
                faux = 1;
            }
        } else {
            if (opponent.getHitPoints() <= 0 && warrior.getHitPoints() <= 0) {
                gameOver = 1;
            } else if (opponent.getHitPoints() <= 0) {
                gameOver = 2;
            } else {
                gameOver = 0;
            }
            checkWin(gameOver, e);
        }
    }

    public void warriorCharge(ActionEvent e) throws IOException {
        if (!warrior.isCharging()) {
            if (warrior.getSpeed() > opponent.getSpeed()) {
                warrior.charge();
                opponent.think(warrior, faux);

            } else if (warrior.getSpeed() < opponent.getSpeed()) {
                opponent.think(warrior, faux);
                warrior.charge();
            }


            opponentHP.setText("" + opponent.getHitPoints());
            warriorHP.setText("" + warrior.getHitPoints());

            if (opponent.getHitPoints() > 0 && warrior.getHitPoints() > 0) {
                if (faux < 3) {
                    faux++;
                } else {
                    faux = 1;
                }
            } else {
                if (opponent.getHitPoints() <= 0 && warrior.getHitPoints() <= 0) {
                    gameOver = 1;
                } else if (opponent.getHitPoints() <= 0) {
                    gameOver = 2;
                } else {
                    gameOver = 0;
                }
                checkWin(gameOver, e);
            }
        } else {
            System.out.println("Already charging.\n");
        }
    }

    public void checkWin(int gameOver, ActionEvent e) throws IOException {
        if (gameOver == 1) {
            //insert tie
        } else if (gameOver == 2) {
            //insert win
        } else {
            //insert lose
        }
    }
}
