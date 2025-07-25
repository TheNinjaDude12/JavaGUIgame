package com.example.demo20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

public class warriorController {
    static Warrior warrior = new Warrior();
    private Dagger dagger = new Dagger();
    private Sword sword = new Sword();
    private Axe axe = new Axe();
    private  Armor lightArmor = new Armor("Light Armor", 20, 5);
    private  Armor mediumArmor = new Armor("Medium Armor", 30, 15);
    private  Armor heavyArmor = new Armor("Heavy Armor", 40, 25);
    @FXML
    private  Text hp = new Text();
    @FXML
    private  Text def = new Text();
    @FXML
    private  Text atk = new Text();
    @FXML
    private  Text spd = new Text();
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
    @FXML
    ImageView daggerView = new ImageView();
    @FXML
    ImageView swordView = new ImageView();
    @FXML
    ImageView axeView = new ImageView();
    @FXML
    ImageView lightView = new ImageView();
    @FXML
    ImageView mediumView = new ImageView();
    @FXML
    ImageView heavyView = new ImageView();
    @FXML
    ImageView nextView = new ImageView();


    private Clip clip;

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
        warrior.equip(axe);
        setStats();
        Image axeImage = new Image(getClass().getResource("/WarriorAssets/Battleaxer.jpg").toExternalForm());
        weaponView.setImage(axeImage);

    }

    public void daggerViewEnter(MouseEvent e) {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        daggerView.setImage(hoverImage);

        playSelectSound();
    }
    public void daggerButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Dagger.png").toExternalForm());
        daggerView.setImage(normalImage);

    }
    public void axeViewEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        axeView.setImage(hoverImage);
        playSelectSound();


    }
    public void axeButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Axe.png").toExternalForm());
        axeView.setImage(normalImage);
    }
    public void lightViewEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        lightView.setImage(hoverImage);

        playSelectSound();
    }
    public void swordButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Sword.png").toExternalForm());
        swordView.setImage(normalImage);
    }
    public void swordViewEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        swordView.setImage(hoverImage);

        playSelectSound();
    }
    public void lightButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Light.png").toExternalForm());
        lightView.setImage(normalImage);
    }

    public void mediumViewEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        mediumView.setImage(hoverImage);

        playSelectSound();
    }
    public void mediumButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Medium.png").toExternalForm());
        mediumView.setImage(normalImage);
    }
    public void heavyViewEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        heavyView.setImage(hoverImage);

        playSelectSound();
    }
    public void heavyButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Heavy.png").toExternalForm());
        heavyView.setImage(normalImage);
    }
    public void nextViewEnter(MouseEvent e) {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        nextView.setImage(hoverImage);

        playSelectSound();
    }
    public void nextButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Next.png").toExternalForm());
        nextView.setImage(normalImage);

    }
    public void confirmSelect(MouseEvent e) {   // Changed to MouseEvent
        try {
            URL soundURL = getClass().getResource("/GameAssets/Music/confirm.wav");
            if (soundURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("");

    }



    public void pickOpponent(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChooseOpponent.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        String css = getClass().getResource("Bro.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Opponent Select");
        stage.setScene(scene);
        stage.show();


    }
    public void playSelectSound() {
        try {
            URL soundURL = getClass().getResource("/GameAssets/Music/select.wav");
            if (soundURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}