package com.example.demo20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HelloController {
    static Warrior warrior = new Warrior();
    Weapon dagger = new Weapon("Dagger", 20, 0);
    Weapon sword = new Weapon("Sword", 30, 10);
    Weapon battleaxe = new Weapon("Battleaxe", 40, 20);
    Armor lightArmor = new Armor("Light Armor", 20, 5);
    Armor mediumArmor = new Armor("Medium Armor", 30, 15);
    Armor heavyArmor = new Armor("Heavy Armor", 40, 25);
    @FXML
    Text hp = new Text();
    @FXML
    Text def = new Text();
    @FXML
    Text atk = new Text();
    @FXML
    Text spd = new Text();
    @FXML
    Button lightArmorButton = new Button();
    @FXML
    Button mediumArmorButton = new Button();
    @FXML
    Button heavyArmorButton = new Button();
    @FXML
    Button nextButton = new Button();
    @FXML
    ImageView armorView = new ImageView();
    @FXML
    ImageView weaponView = new ImageView();

    public void initialize() {
        hp.setText(""+warrior.getHitPoints());
        def.setText(""+warrior.getDefense());
        atk.setText(""+warrior.getAttack());
        spd.setText(""+warrior.getSpeed());
        if(warrior.getArmor()!=null &&warrior.getArmor().getName().equals("Light Armor")) {
            setStats();
            Image lightArmorImage = new Image(getClass().getResource("/WarriorAssets/leather.png").toExternalForm());
            armorView.setImage(lightArmorImage);
        }
        if(warrior.getArmor()!=null &&warrior.getArmor().getName().equals("Medium Armor")) {
            setStats();
            Image mediumArmorImage = new Image(getClass().getResource("/WarriorAssets/2811d2cdb07721c.png").toExternalForm());
            armorView.setImage(mediumArmorImage);
        }
        if(warrior.getArmor()!=null &&warrior.getArmor().getName().equals("Heavy Armor")) {
            setStats();
            Image heavyArmorImage = new Image(getClass().getResource("/WarriorAssets/heavyarmor.jpg").toExternalForm());
            armorView.setImage(heavyArmorImage);
        }
        if(warrior.getWeapon()!=null &&warrior.getWeapon().getName().equals("Dagger")) {
            setStats();
            Image daggerImage = new Image(getClass().getResource("/WarriorAssets/Dagger.jpg").toExternalForm());
            weaponView.setImage(daggerImage);
        }
        if(warrior.getWeapon()!=null &&warrior.getWeapon().getName().equals("Sword")) {
            setStats();
            Image swordImage = new Image(getClass().getResource("/WarriorAssets/Sword.jpg").toExternalForm());
            weaponView.setImage(swordImage);
        }
        if(warrior.getWeapon()!=null &&warrior.getWeapon().getName().equals("Battleaxe")) {
            setStats();
            Image axeImage = new Image(getClass().getResource("/WarriorAssets/Battleaxer.jpg").toExternalForm());
            weaponView.setImage(axeImage);
        }
    }
    public void setStats() {
        hp.setText(""+warrior.getHitPoints());
        def.setText(""+warrior.getDefense());
        atk.setText(""+warrior.getAttack());
        spd.setText(""+warrior.getSpeed());

    }

    public void startgame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Creation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Character Creation");
        stage.setScene(scene);
        stage.show();

    }

    public void wearLightArmor(ActionEvent e) {

        warrior.setDefense(1);
        if(warrior.getWeapon() != null) {
            warrior.setSpeed(50 - warrior.getWeapon().getSpeedPenalty());
        }
        else {
            warrior.setSpeed(50);
        }

        warrior.equip(lightArmor);
        setStats();
        Image lightArmorImage = new Image(getClass().getResource("/WarriorAssets/leather.png").toExternalForm());
        armorView.setImage(lightArmorImage);
    }

    public void wearMediumArmor(ActionEvent e) {

        warrior.setDefense(1);
        if(warrior.getWeapon() != null) {
            warrior.setSpeed(50 - warrior.getWeapon().getSpeedPenalty());
        }
        else {
            warrior.setSpeed(50);
        }
        warrior.equip(mediumArmor);
        setStats();
        Image mediumArmorImage = new Image(getClass().getResource("/WarriorAssets/2811d2cdb07721c.png").toExternalForm());
        armorView.setImage(mediumArmorImage);

    }

    public void wearHeavyArmor(ActionEvent e) {


        warrior.setDefense(1);
        if(warrior.getWeapon() != null) {
            warrior.setSpeed(50 - warrior.getWeapon().getSpeedPenalty());
        }
        else {
            warrior.setSpeed(50);
        }

        warrior.equip(heavyArmor);
        setStats();
        Image heavyArmorImage = new Image(getClass().getResource("/WarriorAssets/heavyarmor.jpg").toExternalForm());
        armorView.setImage(heavyArmorImage);

    }

    public void useDagger() {
        warrior.setAttack(1);
        if(warrior.getArmor() != null) {
            warrior.setSpeed(50 - warrior.getArmor().getSpeedPenalty());
        }
        else {
            warrior.setSpeed(50);
        }
        warrior.equip(dagger);
        setStats();
        Image daggerImage = new Image(getClass().getResource("/WarriorAssets/Dagger.jpg").toExternalForm());
        weaponView.setImage(daggerImage);

    }

    public void useSword() {
        warrior.setAttack(1);
        if(warrior.getArmor() != null) {
            warrior.setSpeed(50 - warrior.getArmor().getSpeedPenalty());
        }
        else {
            warrior.setSpeed(50);
        }
        warrior.equip(sword);
        setStats();
        Image swordImage = new Image(getClass().getResource("/WarriorAssets/Sword.jpg").toExternalForm());
        weaponView.setImage(swordImage);

    }

    public void useAxe() {
        warrior.setAttack(1);
        if(warrior.getArmor() != null) {
            warrior.setSpeed(50 - warrior.getArmor().getSpeedPenalty());
        }
        else {
            warrior.setSpeed(50);
        }
        warrior.equip(battleaxe);
        setStats();
        Image axeImage = new Image(getClass().getResource("/WarriorAssets/Battleaxer.jpg").toExternalForm());
        weaponView.setImage(axeImage);

    }


    public Warrior pickOpponent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChooseOpponent.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Opponent Select");
        stage.setScene(scene);
        stage.show();

        return warrior;


    }


}