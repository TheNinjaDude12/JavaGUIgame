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
    Warrior warrior;
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
    ImageView armorView = new ImageView();

    public void initialize() {
        warrior = new Warrior();
        hp.setText(""+warrior.getHitPoints());
        def.setText(""+warrior.getDefense());
        atk.setText(""+warrior.getAttack());
        spd.setText(""+warrior.getSpeed());
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
        Image lightArmorImage = new Image(getClass().getResource("/WarriorAssets/leather.png").toExternalForm());
        armorView.setImage(lightArmorImage);
        warrior.equip(lightArmor);
        setStats();
    }

    public void wearMediumArmor(ActionEvent e) {
        Image mediumArmorImage = new Image(getClass().getResource("/WarriorAssets/2811d2cdb07721c.png").toExternalForm());
        armorView.setImage(mediumArmorImage);
        warrior.equip(mediumArmor);
        setStats();

    }

    public void wearHeavyArmor(ActionEvent e) {
        Image heavyArmorImage = new Image(getClass().getResource("/WarriorAssets/heavyarmor.jpg").toExternalForm());
        armorView.setImage(heavyArmorImage);
        warrior.equip(heavyArmor);
        setStats();

    }


}