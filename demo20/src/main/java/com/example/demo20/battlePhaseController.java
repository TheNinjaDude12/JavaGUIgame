package com.example.demo20;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

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
    @FXML
    ImageView attackView;
    @FXML
    ImageView  defendView;
    @FXML
    ImageView  chargeView;
    @FXML
    ImageView warriorHpView;
    @FXML
    ImageView warriorDefView;
    @FXML
    ImageView warriorAtkView;
    @FXML
    ImageView warriorSpdView;
    @FXML
    ImageView opponentHpView;
    @FXML
    ImageView opponentDefView;
    @FXML
    ImageView opponentAtkView;
    @FXML
    ImageView opponentSpdView;
    @FXML
    ImageView opponentActionView;
    @FXML
    ImageView warriorActionView;
    @FXML
    Text warriorAction;
    @FXML
    Text opponentAction;
    @FXML
    ImageView environmentView;

    int armorValue = warrior.getDefense();
    Clip clip = startController.clip;

    // Animation variables for Viking/Olaf
    private Timeline vikingAnimation;
    private Image olafImage1;
    private Image olafImage2;

    // Animation variables for Thief/Talon
    private Timeline thiefAnimation;
    private Timeline thiefAttackAnimation;
    private Image talonImage1;
    private Image talonImage2;
    private Image talonAttackImage; // Add attack image
    private boolean showingFirstImage = true;

    // Combat delay variables
    private Timeline combatDelayTimeline;
    private boolean combatInProgress = false;

    public void initialize() {
        if (startController.clip != null) {
            startController.clip.stop();
            startController.clip.flush();
            startController.clip.close();
            startController.clip = null;  // release reference
        }

        try {
            URL soundURL = getClass().getResource("/GameAssets/Music/baldursgate.wav");
            if (soundURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            startController.clip = AudioSystem.getClip();
            startController.clip.open(audioIn);
            startController.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Buttons Setup
        Image attackPNG = new Image(getClass().getResource("/GameAssets/Attack.png").toExternalForm());
        Image defendPNG = new Image(getClass().getResource("/GameAssets/Defend.png").toExternalForm());
        Image chargePNG = new Image(getClass().getResource("/GameAssets/Charge.png").toExternalForm());
        Image textFieldPng = new Image(getClass().getResource("/GameAssets/textArea.png").toExternalForm());
        attackView.setImage(attackPNG);
        defendView.setImage(defendPNG);
        chargeView.setImage(chargePNG);
        warriorActionView.setImage(textFieldPng);
        opponentActionView.setImage(textFieldPng);

        // Warrior Setup
        Image warriorJPG = new Image(getClass().getResource("/WarriorAssets/warrior.jpg").toExternalForm());
        warriorView.setImage(warriorJPG);
        Image hpPNG = new Image(getClass().getResource("/HP.png").toExternalForm());
        warriorHpView.setImage(hpPNG);
        Image defPNG = new Image(getClass().getResource("/DEF.png").toExternalForm());
        warriorDefView.setImage(defPNG);
        Image atkPNG = new Image(getClass().getResource("/ATK.png").toExternalForm());
        warriorAtkView.setImage(atkPNG);
        Image spdPNG = new Image(getClass().getResource("/SPD.png").toExternalForm());
        warriorSpdView.setImage(spdPNG);

        //Opponent Setup
        opponentHpView.setImage(hpPNG);
        opponentDefView.setImage(defPNG);
        opponentAtkView.setImage(atkPNG);
        opponentSpdView.setImage(spdPNG);
        Image environmentImage;

        switch(environment.getEnvironmentName()) {
            case "Arena":
                environmentImage = new Image(getClass().getResource("/environmentAssets/arena.png").toExternalForm());
                environmentView.setImage(environmentImage);
                break;
            case "Swamp":
                environmentImage = new Image(getClass().getResource("/environmentAssets/swamp.png").toExternalForm());
                environmentView.setImage(environmentImage);
                break;
            case "Colosseum":
                environmentImage = new Image(getClass().getResource("/environmentAssets/colosseum.png").toExternalForm());
                environmentView.setImage(environmentImage);
                break;
        }

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
                talonImage1 = new Image(getClass().getResource("/OpponentAssets/talon1.png").toExternalForm());
                talonImage2 = new Image(getClass().getResource("/OpponentAssets/talon2.png").toExternalForm());
                // Add attack image - adjust path as needed
                talonAttackImage = new Image(getClass().getResource("/OpponentAssets/talonAttack.png").toExternalForm());
                opponentView.setImage(talonImage1);
                startThiefIdleAnimation();
                break;
            case "Viking":
                // Load both Olaf images
                olafImage1 = new Image(getClass().getResource("/OpponentAssets/olaf1.png").toExternalForm());
                olafImage2 = new Image(getClass().getResource("/OpponentAssets/olaf2.png").toExternalForm());

                // Set initial image
                opponentView.setImage(olafImage1);

                // Create and start the animation
                startVikingIdleAnimation();
                break;
            case "Minotaur":
                Image Minoimage = new Image(getClass().getResource("/OpponentAssets/minotaur.png").toExternalForm());
                opponentView.setImage(Minoimage);
                break;
        }

        environment.environmentEffects(warrior, opponent);
        warriorHP.setText("" + warrior.getHitPoints());
        warriorDefense.setText("" + warrior.getDefense());
        warriorAttack.setText("" + warrior.getAttack());
        warriorSpeed.setText("" + warrior.getSpeed());
        opponentHP.setText("" + opponent.getHitPoints());
        opponentDefense.setText("" + opponent.getDefense());
        opponentAttack.setText("" + opponent.getAttack());
        opponentSpeed.setText("" + opponent.getSpeed());
    }

    private void startVikingIdleAnimation() {
        // Create a timeline that switches images every 1.5 seconds (adjust as needed)
        vikingAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.5), e -> {
                    if (showingFirstImage) {
                        opponentView.setImage(olafImage2);
                        showingFirstImage = false;
                    } else {
                        opponentView.setImage(olafImage1);
                        showingFirstImage = true;
                    }
                })
        );

        // Set the animation to repeat indefinitely
        vikingAnimation.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        vikingAnimation.play();
    }

    private void startThiefIdleAnimation() {
        // Create a timeline that switches images every 1.5 seconds (adjust as needed)
        thiefAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.5), e -> {
                    if (showingFirstImage) {
                        opponentView.setImage(talonImage2);
                        showingFirstImage = false;
                    } else {
                        opponentView.setImage(talonImage1);
                        showingFirstImage = true;
                    }
                })
        );

        // Set the animation to repeat indefinitely
        thiefAnimation.setCycleCount(Timeline.INDEFINITE);

        // Start the animation
        thiefAnimation.play();
    }

    // Method to play thief attack animation
    private void playThiefAttackAnimation() {
        if (thiefAnimation != null) {
            thiefAnimation.stop(); // Stop idle animation
        }

        // Store original position
        double originalX = opponentView.getLayoutX();

        // Show attack image and move to attack position
        opponentView.setImage(talonAttackImage);
        opponentView.setLayoutX(352); // Move to attack position

        // Create attack animation timeline
        thiefAttackAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0.8), e -> {
                    // After attack animation, return to idle position and image
                    opponentView.setLayoutX(1036); // Return to original position
                    opponentView.setImage(talonImage1);
                    showingFirstImage = true;
                    startThiefIdleAnimation(); // Resume idle animation
                })
        );

        thiefAttackAnimation.play();
    }

    // Add this method to stop all animations when the battle ends or controller is destroyed
    public void stopAllAnimations() {
        if (vikingAnimation != null) {
            vikingAnimation.stop();
        }
        if (thiefAnimation != null) {
            thiefAnimation.stop();
        }
        if (thiefAttackAnimation != null) {
            thiefAttackAnimation.stop();
        }
        if (combatDelayTimeline != null) {
            combatDelayTimeline.stop();
        }
    }

    // Method to disable buttons during combat
    private void setButtonsDisabled(boolean disabled) {
        attackButton.setDisable(disabled);
        // Add other buttons here if you have them as @FXML fields
        // defendButton.setDisabled(disabled);
        // chargeButton.setDisabled(disabled);
    }

    // Method to handle combat with delays
    private void processCombatWithDelay(ActionEvent e, Runnable combatAction) {
        if (combatInProgress) {
            return; // Ignore if combat is already in progress
        }

        combatInProgress = true;
        setButtonsDisabled(true);

        // Execute the combat action
        combatAction.run();

        // If thief is attacking, play attack animation
        if (opponent.getName().equals("Thief")) {
            playThiefAttackAnimation();
        }

        // Create delay before allowing next action
        combatDelayTimeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), event -> {
                    combatInProgress = false;
                    setButtonsDisabled(false);

                    // Check win condition after delay
                    try {
                        checkWin(e);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                })
        );

        combatDelayTimeline.play();
    }

    public void warriorAttack(ActionEvent e) throws IOException {
        processCombatWithDelay(e, () -> {
            try {
                int dmg = 0;
                if (warrior.getSpeed() > opponent.getSpeed()) {
                    if(opponent.getName().equals("Viking") && faux==2){
                        opponent.think(warrior, faux);
                        dmg = warrior.getDamageDealt(opponent);
                        warrior.attack(opponent);
                    }
                    else{
                        dmg = warrior.getDamageDealt(opponent);
                        warrior.attack(opponent);
                        opponent.think(warrior,faux);
                    }
                } else if (warrior.getSpeed() < opponent.getSpeed()) {
                    opponent.think(warrior, faux);
                    dmg = warrior.getDamageDealt(opponent);
                    warrior.attack(opponent);
                }
                warriorAction.setText("Warrior attacks " + opponent.getName() +" for " + dmg + " dmg");

                setStats();

                // Only apply environment effects if game is still ongoing
                if (gameOver == 0) {
                    environment.environmentEffects(warrior, opponent);
                    setStats();
                }

                if (faux < 3) {
                    System.out.println("check");
                    faux++;
                } else {
                    System.out.println("check2");
                    faux = 1;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void warriorDefend(ActionEvent e) throws IOException {
        processCombatWithDelay(e, () -> {
            try {
                if (warrior.getWeapon().getName().equals("Dagger") && warrior.isDefendedLastTurn()) {
                    warrior.defend();
                    warrior.getWeapon().weapon_ability(warrior);
                    warrior.setDefending(false);
                    warriorAction.setText("Warrior dagger ability activated!");
                }
                else {
                    warrior.defend();
                    warriorAction.setText("Warrior is defending");
                }

                opponent.think(warrior, faux);

                setStats();

                // Only continue if game is ongoing
                if (gameOver == 0) {
                    environment.environmentEffects(warrior, opponent);
                    warrior.setDefense(armorValue);
                    warrior.setDefending(false);
                    setStats();
                }

                if (faux < 3) {
                    System.out.println("check");
                    faux++;
                } else {
                    System.out.println("check2");
                    faux = 1;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void warriorCharge(ActionEvent e) throws IOException {
        processCombatWithDelay(e, () -> {
            try {
                if (!warrior.isCharging()) {
                    if (warrior.getSpeed() > opponent.getSpeed()) {
                        warrior.charge();
                        warriorAction.setText("Warrior is charging");
                        opponent.think(warrior, faux);
                    } else if (warrior.getSpeed() < opponent.getSpeed()) {
                        opponent.think(warrior, faux);
                        warrior.charge();
                        warriorAction.setText("Warrior is charging");
                    }

                    setStats();

                    // Only continue if game is ongoing
                    if (gameOver == 0) {
                        environment.environmentEffects(warrior, opponent);
                        setStats();
                    }
                } else {
                    System.out.println("Already charging.\n");
                }

                if (faux < 3) {
                    System.out.println("check");
                    faux++;
                } else {
                    System.out.println("check2");
                    faux = 1;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void checkWin(ActionEvent e) throws IOException {
        // Check if warrior is defeated
        if (warrior.getHitPoints() <= 0) {
            gameOver = 1; // Player loses
            stopAllAnimations();
            showGameOverScreen(e, false); // false = player lost
            return;
        }

        // Check if opponent is defeated
        if (opponent.getHitPoints() <= 0) {
            gameOver = 2; // Player wins
            stopAllAnimations();
            showGameOverScreen(e, true); // true = player won
            return;
        }

        // If neither is defeated, continue the game (don't navigate anywhere)
        gameOver = 0;
    }

    // Helper method to show appropriate game over screen
    private void showGameOverScreen(ActionEvent e, boolean playerWon) throws IOException {
        // You can create different FXML files for win/lose screens
        // or pass a parameter to determine what to show
        FXMLLoader fxmlLoader;

        if (playerWon) {
            // Load a victory screen or main menu with victory message
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("victory.fxml"));
        } else {
            // Load a defeat screen or main menu with defeat message
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("defeat.fxml"));
        }

        // Fallback to main menu if victory/defeat screens don't exist
        try {
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            String css = getClass().getResource("Bro.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle(playerWon ? "Victory!" : "Defeat!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            // If victory/defeat screens don't exist, go to main menu
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start.fxml"));
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            String css = getClass().getResource("Bro.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Game Over");
            stage.setScene(scene);
            stage.show();
        }
    }

    public void setStats() {
        warriorHP.setText("" + warrior.getHitPoints());
        warriorDefense.setText("" + warrior.getDefense());
        warriorAttack.setText("" + warrior.getAttack());
        warriorSpeed.setText("" + warrior.getSpeed());
        opponentHP.setText("" + opponent.getHitPoints());
        opponentDefense.setText("" + opponent.getDefense());
        opponentAttack.setText("" + opponent.getAttack());
        opponentSpeed.setText("" + opponent.getSpeed());
    }
}