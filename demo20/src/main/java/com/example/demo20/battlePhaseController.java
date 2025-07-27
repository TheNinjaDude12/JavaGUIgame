package com.example.demo20;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;

public class battlePhaseController {

    // ==================== GAME STATE VARIABLES ====================
    private Warrior warrior = warriorController.warrior;
    private Environment environment = chooseEnvironmentController.environment;
    private Opponent opponent = chooseOpponentController.opponent;
    public static int faux = 1;
    public static int gameOver = 0;
    private int armorValue;
    private Clip clip = startController.clip;

    // ==================== FXML COMPONENTS ====================
    @FXML private ImageView warriorView;
    @FXML private ImageView armorView;
    @FXML private ImageView weaponView;
    @FXML private ImageView opponentView;
    @FXML private ImageView environmentView;

    // Stats Text Elements
    @FXML private Text warriorHP;
    @FXML private Text warriorDefense;
    @FXML private Text warriorAttack;
    @FXML private Text warriorSpeed;
    @FXML private Text opponentHP;
    @FXML private Text opponentDefense;
    @FXML private Text opponentAttack;
    @FXML private Text opponentSpeed;

    // Action Buttons
    @FXML private Button attackButton;
    @FXML private Button defendButton;
    @FXML private Button chargeButton;

    // UI Image Views
    @FXML private ImageView attackView;
    @FXML private ImageView defendView;
    @FXML private ImageView chargeView;
    @FXML private ImageView warriorHpView;
    @FXML private ImageView warriorDefView;
    @FXML private ImageView warriorAtkView;
    @FXML private ImageView warriorSpdView;
    @FXML private ImageView opponentHpView;
    @FXML private ImageView opponentDefView;
    @FXML private ImageView opponentAtkView;
    @FXML private ImageView opponentSpdView;
    @FXML private ImageView opponentActionView;
    @FXML private ImageView warriorActionView;
    @FXML private Text warriorAction;
    @FXML private Text opponentAction;

    // ==================== ANIMATION VARIABLES ====================
    // Viking/Olaf Animation
    private Timeline vikingIdleAnimation;
    private Timeline vikingDefendAnimation;
    private Timeline vikingAttackAnimation;
    private Image olafImage1;
    private Image olafImage2;
    private Image olafDefendImage;
    private Image olafAttackImage;

    // Thief/Talon Animation
    private Timeline thiefIdleAnimation;
    private Timeline thiefAttackAnimation;
    private Image talonImage1;
    private Image talonImage2;
    private Image talonAttackImage;

    // Minotaur/Alistar Animation
    private Timeline minotaurIdleAnimation;
    private Timeline minotaurAttackAnimation;
    private Timeline minotaurChargeAnimation;
    private Timeline minotaurCritAnimation;
    private Image alistarImage1;
    private Image alistarImage2;
    private Image alistarAttackImage;
    private Image alistarChargeImage;
    private Image alistarCritImage;

    // General Animation Control
    private boolean showingFirstImage = true;
    private Timeline combatDelayTimeline;
    private boolean combatInProgress = false;

    // ==================== INITIALIZATION ====================
    public void initialize() {
        initializeAudio();
        initializeUI();
        initializeWarrior();
        initializeOpponent();
        initializeEnvironment();
        initializeStats();
    }

    private void initializeAudio() {
        if (startController.clip != null) {
            startController.clip.stop();
            startController.clip.flush();
            startController.clip.close();
            startController.clip = null;
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
    }

    private void initializeUI() {
        // Load button images
        Image attackPNG = new Image(getClass().getResource("/GameAssets/Attack.png").toExternalForm());
        Image defendPNG = new Image(getClass().getResource("/GameAssets/Defend.png").toExternalForm());
        Image chargePNG = new Image(getClass().getResource("/GameAssets/Charge.png").toExternalForm());
        Image textFieldPng = new Image(getClass().getResource("/GameAssets/textArea.png").toExternalForm());

        attackView.setImage(attackPNG);
        defendView.setImage(defendPNG);
        chargeView.setImage(chargePNG);
        warriorActionView.setImage(textFieldPng);
        opponentActionView.setImage(textFieldPng);

        // Load stat icons
        Image hpPNG = new Image(getClass().getResource("/HP.png").toExternalForm());
        Image defPNG = new Image(getClass().getResource("/DEF.png").toExternalForm());
        Image atkPNG = new Image(getClass().getResource("/ATK.png").toExternalForm());
        Image spdPNG = new Image(getClass().getResource("/SPD.png").toExternalForm());

        warriorHpView.setImage(hpPNG);
        warriorDefView.setImage(defPNG);
        warriorAtkView.setImage(atkPNG);
        warriorSpdView.setImage(spdPNG);
        opponentHpView.setImage(hpPNG);
        opponentDefView.setImage(defPNG);
        opponentAtkView.setImage(atkPNG);
        opponentSpdView.setImage(spdPNG);
    }

    private void initializeWarrior() {
        armorValue = warrior.getDefense();

        // Warrior image
        Image warriorJPG = new Image(getClass().getResource("/WarriorAssets/warrior.jpg").toExternalForm());
        warriorView.setImage(warriorJPG);

        // Armor setup
        setupArmorImage();

        // Weapon setup
        setupWeaponImage();
    }

    private void setupArmorImage() {
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
    }

    private void setupWeaponImage() {
        switch (warrior.getWeapon().getName()) {
            case "Dagger":
                Image daggerImage = new Image(getClass().getResource("/WarriorAssets/Dagger.jpg").toExternalForm());
                weaponView.setImage(daggerImage);
                break;
            case "Sword":
                Image swordImage = new Image(getClass().getResource("/WarriorAssets/Sword.jpg").toExternalForm());
                weaponView.setImage(swordImage);
                break;
            case "Axe":
                Image axeImage = new Image(getClass().getResource("/WarriorAssets/Battleaxer.jpg").toExternalForm());
                weaponView.setImage(axeImage);
                break;
        }
    }

    private void initializeOpponent() {
        switch (opponent.getName()) {
            case "Thief":
                setupThiefImages();
                startThiefIdleAnimation();
                break;
            case "Viking":
                setupVikingImages();
                startVikingIdleAnimation();
                break;
            case "Minotaur":
                setupMinotaurImages();
                startMinotaurIdleAnimation();
                break;
        }
    }

    private void setupThiefImages() {
        talonImage1 = new Image(getClass().getResource("/OpponentAssets/talon1.png").toExternalForm());
        talonImage2 = new Image(getClass().getResource("/OpponentAssets/talon2.png").toExternalForm());
        talonAttackImage = new Image(getClass().getResource("/OpponentAssets/talonAttack.png").toExternalForm());
        opponentView.setImage(talonImage1);
    }

    private void setupVikingImages() {
        olafImage1 = new Image(getClass().getResource("/OpponentAssets/olaf1.png").toExternalForm());
        olafImage2 = new Image(getClass().getResource("/OpponentAssets/olaf2.png").toExternalForm());
        olafDefendImage = new Image(getClass().getResource("/OpponentAssets/olafDefend.png").toExternalForm());
        olafAttackImage = new Image(getClass().getResource("/OpponentAssets/olafAttack.png").toExternalForm());
        opponentView.setImage(olafImage1);
    }

    private void setupMinotaurImages() {
        alistarImage1 = new Image(getClass().getResource("/OpponentAssets/alistar1.png").toExternalForm());
        alistarImage2 = new Image(getClass().getResource("/OpponentAssets/alistar2.png").toExternalForm());
        alistarAttackImage = new Image(getClass().getResource("/OpponentAssets/alistarAttack.png").toExternalForm());
        alistarChargeImage = new Image(getClass().getResource("/OpponentAssets/alistarCharge.png").toExternalForm());
        alistarCritImage = new Image(getClass().getResource("/OpponentAssets/alistarCrit.png").toExternalForm());
        opponentView.setImage(alistarImage1);
    }

    private void initializeEnvironment() {
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
    }

    private void initializeStats() {
        environment.environmentEffects(warrior, opponent);
        setStats();
    }

    // ==================== ANIMATION METHODS ====================
    private void startVikingIdleAnimation() {
        vikingIdleAnimation = new Timeline(
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
        vikingIdleAnimation.setCycleCount(Timeline.INDEFINITE);
        vikingIdleAnimation.play();
    }

    private void startThiefIdleAnimation() {
        thiefIdleAnimation = new Timeline(
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
        thiefIdleAnimation.setCycleCount(Timeline.INDEFINITE);
        thiefIdleAnimation.play();
    }

    private void startMinotaurIdleAnimation() {
        minotaurIdleAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.5), e -> {
                    if (showingFirstImage) {
                        opponentView.setImage(alistarImage2);
                        showingFirstImage = false;
                    } else {
                        opponentView.setImage(alistarImage1);
                        showingFirstImage = true;
                    }
                })
        );
        minotaurIdleAnimation.setCycleCount(Timeline.INDEFINITE);
        minotaurIdleAnimation.play();
    }

    private void playWarriorAttackAnimation() {
        double originalX = warriorView.getLayoutX();

        // Move warrior to attack position
        warriorView.setLayoutX(1013);

        // Create animation to return warrior to original position
        Timeline warriorReturnAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.2), e -> {
                    warriorView.setLayoutX(133); // Return to original position
                })
        );
        warriorReturnAnimation.play();
    }

    private void playVikingAttackAnimation() {
        stopIdleAnimation("Viking");
        double originalX = opponentView.getLayoutX();

        // Play Viking attack sound
        playEnemySound("olafAttack.wav");

        // Move to attack position and show attack image
        opponentView.setLayoutX(428);
        opponentView.setImage(olafAttackImage);
        opponentAction.setText("Viking swings his weapon fiercely!");

        vikingAttackAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.2), e -> {
                    // Return to original position
                    opponentView.setLayoutX(1036);
                    returnToIdleWithDelay("Viking");
                })
        );
        vikingAttackAnimation.play();
    }

    private void playVikingDefendAnimation() {
        stopIdleAnimation("Viking");

        // Play Viking defend sound
        playEnemySound("olafDefend.wav");

        opponentView.setImage(olafDefendImage);
        opponentAction.setText("Viking raises his shield defensively!");

        vikingDefendAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.2), e -> {
                    returnToIdleWithDelay("Viking");
                })
        );
        vikingDefendAnimation.play();
    }




    private void playThiefAttackAnimation() {
        stopIdleAnimation("Thief");

        // Play Thief attack sound
        playEnemySound("thiefAttack.wav");

        opponentView.setImage(talonAttackImage);
        opponentView.setLayoutX(428); // Move to attack position (changed from 352)
        opponentAction.setText("Thief strikes swiftly!");

        thiefAttackAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.2), e -> {
                    opponentView.setLayoutX(1036); // Return to original position
                    returnToIdleWithDelay("Thief");
                })
        );
        thiefAttackAnimation.play();
    }

    private void playMinotaurAttackAnimation() {
        stopIdleAnimation("Minotaur");
        double originalX = opponentView.getLayoutX();

        // Play Minotaur attack sound
        playEnemySound("minotaurAttack.wav");

        // Move to attack position and show attack image
        opponentView.setLayoutX(428);
        opponentView.setImage(alistarAttackImage);
        opponentAction.setText("Minotaur attacks with brute force!");

        minotaurAttackAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.2), e -> {
                    // Return to original position
                    opponentView.setLayoutX(1036);
                    returnToIdleWithDelay("Minotaur");
                })
        );
        minotaurAttackAnimation.play();
    }

    private void playMinotaurChargeAnimation() {
        stopIdleAnimation("Minotaur");
        double originalX = opponentView.getLayoutX();

        // Play Minotaur charge sound
        playEnemySound("minotaurCharge.wav");

        // Move to attack position and show charge image
        opponentView.setImage(alistarChargeImage);
        opponentAction.setText("Minotaur charges forward with thunderous hooves!");

        minotaurChargeAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.2), e -> {
                    // Return to original position
                    opponentView.setLayoutX(1036);
                    returnToIdleWithDelay("Minotaur");
                })
        );
        minotaurChargeAnimation.play();
    }

    private void playMinotaurCritAnimation() {
        stopIdleAnimation("Minotaur");
        double originalX = opponentView.getLayoutX();

        // Move to attack position and show crit image
        opponentView.setLayoutX(428);
        opponentView.setImage(alistarCritImage);
        opponentAction.setText("Minotaur unleashes a devastating critical strike!");

        minotaurCritAnimation = new Timeline(
                new KeyFrame(Duration.seconds(1.5), e -> { // Longer duration for crit
                    // Return to original position
                    opponentView.setLayoutX(1036);
                    returnToIdleWithDelay("Minotaur");
                })
        );
        minotaurCritAnimation.play();
    }

    private void stopIdleAnimation(String opponentType) {
        if (opponentType.equals("Viking") && vikingIdleAnimation != null) {
            vikingIdleAnimation.stop();
        } else if (opponentType.equals("Thief") && thiefIdleAnimation != null) {
            thiefIdleAnimation.stop();
        } else if (opponentType.equals("Minotaur") && minotaurIdleAnimation != null) {
            minotaurIdleAnimation.stop();
        }
    }

    private void returnToIdleWithDelay(String opponentType) {
        Timeline delayTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.8), e -> {
                    if (opponentType.equals("Viking")) {
                        opponentView.setImage(olafImage1);
                        showingFirstImage = true;
                        opponentAction.setText("Viking returns to combat stance");
                        startVikingIdleAnimation();
                    } else if (opponentType.equals("Thief")) {
                        opponentView.setImage(talonImage1);
                        showingFirstImage = true;
                        opponentAction.setText("Thief prepares for next move");
                        startThiefIdleAnimation();
                    } else if (opponentType.equals("Minotaur")) {
                        opponentView.setImage(alistarImage1);
                        showingFirstImage = true;
                        opponentAction.setText("Minotaur stomps the ground menacingly");
                        startMinotaurIdleAnimation();
                    }
                })
        );
        delayTimeline.play();
    }

    public void stopAllAnimations() {
        if (vikingIdleAnimation != null) vikingIdleAnimation.stop();
        if (vikingDefendAnimation != null) vikingDefendAnimation.stop();
        if (vikingAttackAnimation != null) vikingAttackAnimation.stop();
        if (thiefIdleAnimation != null) thiefIdleAnimation.stop();
        if (thiefAttackAnimation != null) thiefAttackAnimation.stop();
        if (minotaurIdleAnimation != null) minotaurIdleAnimation.stop();
        if (minotaurAttackAnimation != null) minotaurAttackAnimation.stop();
        if (minotaurChargeAnimation != null) minotaurChargeAnimation.stop();
        if (minotaurCritAnimation != null) minotaurCritAnimation.stop();
        if (combatDelayTimeline != null) combatDelayTimeline.stop();
    }

    // ==================== COMBAT CONTROL METHODS ====================
    private void setButtonsDisabled(boolean disabled) {
        attackButton.setDisable(disabled);
        defendButton.setDisable(disabled);
        chargeButton.setDisable(disabled);
    }

    private void processCombatWithDelay(ActionEvent e, Runnable combatAction) {
        if (combatInProgress) {
            return; // Ignore if combat is already in progress
        }

        combatInProgress = true;
        setButtonsDisabled(true);

        // Execute the combat action
        combatAction.run();

        // Create delay before allowing next action (longer delay for sequential actions)
        combatDelayTimeline = new Timeline(
                new KeyFrame(Duration.seconds(5.0), event -> {
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

    // ==================== COMBAT ACTION METHODS ====================
    public void warriorAttack(ActionEvent e) throws IOException {
        boolean charged = warrior.isCharging();
        processCombatWithDelay(e, () -> {
            try {
                int dmg = 0;
                String opponentName = opponent.getName();
                if(warrior.getWeapon().getName().equals("Sword") && !warrior.isCharging()) {
                    System.out.println("Sword");
                    warrior.getWeapon().weapon_ability(warrior);
                }

                // Special case: Viking defending always goes first
                if (opponentName.equals("Viking") && faux == 2) {
                    // Viking defends first regardless of speed
                    playVikingDefendAnimation();
                    opponent.think(warrior, faux);
                    dmg = warrior.getDamageDealt(opponent);

                    // Delay before warrior attacks
                    Timeline delayTimeline = new Timeline(
                            new KeyFrame(Duration.seconds(2.0), event -> {
                                warrior.attack(opponent);
                                playWarriorAttackAnimation();
                                setStats();
                            })
                    );
                    warriorAction.setText("Warrior attacks Viking for " + dmg + " dmg");
                    delayTimeline.play();
                } else if (warrior.getSpeed() > opponent.getSpeed()) {
                    // Warrior acts first
                    dmg = warrior.getDamageDealt(opponent);
                    warrior.attack(opponent);
                    playWarriorAttackAnimation();
                    warriorAction.setText("Warrior attacks " + opponentName + " for " + dmg + " dmg");
                    setStats();

                    // Delay before opponent responds
                    Timeline delayTimeline = new Timeline(
                            new KeyFrame(Duration.seconds(2.0), event -> {
                                handleOpponentResponse();
                            })
                    );
                    delayTimeline.play();
                } else {
                    // Opponent acts first
                    handleOpponentResponse();
                    dmg = warrior.getDamageDealt(opponent);

                    // Delay before warrior attacks
                    Timeline delayTimeline = new Timeline(
                            new KeyFrame(Duration.seconds(2.0), event -> {
                                warrior.attack(opponent);
                                playWarriorAttackAnimation();
                                setStats();
                            })
                    );
                    warriorAction.setText("Warrior attacks " + opponentName + " for " + dmg + " dmg");
                    delayTimeline.play();
                }

                // Apply environment effects after both actions (with additional delay)
                Timeline environmentDelayTimeline = new Timeline(
                        new KeyFrame(Duration.seconds(4.0), event -> {
                            applyEnvironmentEffects();

                            // Apply post-combat effects AFTER environment effects
                            // Sword attack reduction
                            if(warrior.getWeapon().getName().equals("Sword") && !charged) {
                                warrior.setAttack(warrior.getAttack()-10);
                            }

                            // Axe charge attack and speed reduction


                            // Reset opponent defending state
                            opponent.setDefending(false);

                            setStats();
                            incrementFaux();
                        })
                );
                environmentDelayTimeline.play();
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
                } else {
                    warrior.defend();
                    warriorAction.setText("Warrior raises shield defensively");
                }

                // Delay before opponent responds
                Timeline delayTimeline = new Timeline(
                        new KeyFrame(Duration.seconds(1.5), event -> {
                            // Use handleOpponentResponse instead of handleOpponentAttack
                            // This will properly check for Viking's defend behavior
                            handleOpponentResponse();
                            setStats();

                            // Apply environment effects after opponent's action
                            Timeline environmentDelayTimeline = new Timeline(
                                    new KeyFrame(Duration.seconds(2.0), envEvent -> {
                                        applyEnvironmentEffects();

                                        // Reset warrior defense after environment effects
                                        warrior.setDefense(armorValue);
                                        warrior.setDefending(false);
                                        setStats();
                                        incrementFaux();
                                    })
                            );
                            environmentDelayTimeline.play();
                        })
                );
                delayTimeline.play();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        opponent.setDefending(false);
    }
    public void warriorCharge(ActionEvent e) throws IOException {
        processCombatWithDelay(e, () -> {
            try {
                if(warrior.getWeapon().getName().equals("Axe")) {
                    warrior.getWeapon().weapon_ability(warrior);
                    setStats();
                }
                if (!warrior.isCharging()) {
                    // Special case: Viking defending always goes first
                    if (opponent.getName().equals("Viking") && faux == 2) {
                        // Viking defends first regardless of speed
                        playVikingDefendAnimation();
                        opponent.think(warrior, faux);

                        // Delay before warrior charges
                        Timeline delayTimeline = new Timeline(
                                new KeyFrame(Duration.seconds(2.0), event -> {
                                    warrior.charge();
                                    warriorAction.setText("Warrior builds momentum for a powerful charge!");
                                    setStats();
                                })
                        );
                        delayTimeline.play();
                    } else if (warrior.getSpeed() > opponent.getSpeed()) {
                        // Warrior acts first
                        warrior.charge();
                        warriorAction.setText("Warrior builds momentum for a powerful charge!");
                        setStats();

                        // Delay before opponent responds
                        Timeline delayTimeline = new Timeline(
                                new KeyFrame(Duration.seconds(1.5), event -> {
                                    handleOpponentResponse();
                                })
                        );
                        delayTimeline.play();
                    } else {
                        // Opponent acts first
                        handleOpponentResponse();

                        // Delay before warrior charges
                        Timeline delayTimeline = new Timeline(
                                new KeyFrame(Duration.seconds(2.0), event -> {
                                    warrior.charge();
                                    warriorAction.setText("Warrior charges forward with determination!");
                                    setStats();
                                })
                        );
                        delayTimeline.play();
                    }

                    // Apply environment effects after both actions (with additional delay)
                    Timeline environmentDelayTimeline = new Timeline(
                            new KeyFrame(Duration.seconds(4.0), event -> {
                                applyEnvironmentEffects();
                                incrementFaux();
                            })
                    );
                    environmentDelayTimeline.play();
                } else {
                    warriorAction.setText("Warrior is already building up charge!");
                    System.out.println("Already charging.\n");
                    incrementFaux();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        opponent.setDefending(false);
    }

    // ==================== OPPONENT AI METHODS ====================
    private void handleOpponentResponse() {
        String opponentName = opponent.getName();

        if (opponentName.equals("Thief")) {
            // Thief always attacks regardless of faux
            handleOpponentAttack();
        } else if (opponentName.equals("Viking")) {
            if (faux == 2) {
                // Viking defends on faux == 2
                playVikingDefendAnimation();
            } else {
                // Viking attacks on other faux values
                handleOpponentAttack();
            }
            opponent.think(warrior, faux);
        } else if (opponentName.equals("Minotaur")) {
            if (faux == 3) {
                // Minotaur does critical attack on faux == 3
                playMinotaurCritAnimation();
            } else if (faux == 2) {
                // Minotaur charges on faux == 2
                playMinotaurChargeAnimation();
            } else {
                // Minotaur attacks normally on faux == 1
                playMinotaurAttackAnimation();
            }
            opponent.think(warrior, faux);
        } else {
            // Default behavior for other opponents
            opponent.think(warrior, faux);
        }
    }

    private void handleOpponentAttack() {
        String opponentName = opponent.getName();

        if (opponentName.equals("Thief")) {
            playThiefAttackAnimation();
            opponent.think(warrior, faux); // Thief still uses think method for actual combat logic
        } else if (opponentName.equals("Viking")) {
            playVikingAttackAnimation();
        } else if (opponentName.equals("Minotaur")) {
            if (faux == 3) {
                // Minotaur does critical attack on faux == 3
                playMinotaurCritAnimation();
            } else {
                playMinotaurAttackAnimation();
            }
        } else {
            opponentAction.setText(opponentName + " attacks!");
        }
    }

    // ==================== UTILITY METHODS ====================
    private void applyEnvironmentEffects() {
        if (gameOver == 0) {
            environment.environmentEffects(warrior, opponent);
            setStats();
        }
    }

    private void incrementFaux() {
        if (faux < 3) {
            System.out.println("Faux incremented to: " + (faux + 1));
            faux++;
        } else {
            System.out.println("Faux reset to 1");
            faux = 1;
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

    // ==================== GAME STATE METHODS ====================
    public void checkWin(ActionEvent e) throws IOException {
        if (warrior.getHitPoints() <= 0) {
            gameOver = 1; // Player loses
            stopAllAnimations();
            showGameOverScreen(e, false);
            return;
        }

        if (opponent.getHitPoints() <= 0) {
            gameOver = 2; // Player wins
            stopAllAnimations();
            showGameOverScreen(e, true);
            return;
        }

        gameOver = 0; // Game continues
    }
    public void attackButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        attackView.setImage(hoverImage);

        playSelectSound();
    }
    public void attackButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Attack.png").toExternalForm());
        attackView.setImage(normalImage);
    }
    public void defendButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        defendView.setImage(hoverImage);

        playSelectSound();
    }
    public void defendkButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Defend.png").toExternalForm());
        defendView.setImage(normalImage);
    }
    public void chargeButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        chargeView.setImage(hoverImage);

        playSelectSound();
    }
    public void chargeButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Charge.png").toExternalForm());
        chargeView.setImage(normalImage);
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
    private void playEnemySound(String soundFileName) {
        try {
            URL soundURL = getClass().getResource("/OpponentAssets/" + soundFileName);
            if (soundURL == null) {
                System.out.println("Audio file not found: " + soundFileName);
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip enemyClip = AudioSystem.getClip();
            enemyClip.open(audioIn);
            enemyClip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void showGameOverScreen(ActionEvent e, boolean playerWon) throws IOException {
        FXMLLoader fxmlLoader;

        if (playerWon) {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("victory.fxml"));
        } else {
            fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("defeat.fxml"));
        }

        try {
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
            String css = getClass().getResource("Bro.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle(playerWon ? "Victory!" : "Defeat!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            // Fallback to main menu if victory/defeat screens don't exist
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
}