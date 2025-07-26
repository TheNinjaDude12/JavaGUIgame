package com.example.demo20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

public class chooseEnvironmentController {


    public static Environment environment;
    Environment arena = new Environment("Arena");
    Environment swamp = new Environment("Swamp");
    Environment colosseum = new Environment("Colosseum");
    @FXML
    Text text;
    @FXML
    ImageView environmentView;
    @FXML
    ImageView arenaView;
    @FXML
    ImageView swampView;
    @FXML
    ImageView colosseumView;
    @FXML
    ImageView backView;
    @FXML
    ImageView nextView;
    @FXML
    ImageView effectsView;
    @FXML
    ImageView enviView;
    @FXML
    ImageView textArea;
    private Clip clip;


    public void initialize() {
        environment = arena;
        Image textImage = new Image(getClass().getResource("/GameAssets/textArea.png").toExternalForm());
        textArea.setImage(textImage);
        text.setText("No buffs or penalties - Fair fight!");
        Image image = new Image(getClass().getResource("/environmentAssets/arena.png").toExternalForm());
        environmentView.setImage(image);
        Image arenaImage = new Image(getClass().getResource("/GameAssets/Arena.png").toExternalForm());
        arenaView.setImage(arenaImage);
        Image swampImage = new Image(getClass().getResource("/GameAssets/Swamp.png").toExternalForm());
        swampView.setImage(swampImage);
        Image colosseumImage = new Image(getClass().getResource("/GameAssets/Colosseum.png").toExternalForm());
        colosseumView.setImage(colosseumImage);
        Image enviImage = new Image(getClass().getResource("/GameAssets/Environment.png").toExternalForm());
        enviView.setImage(enviImage);
        Image effectsImage = new Image(getClass().getResource("/GameAssets/Effect.png").toExternalForm());
        effectsView.setImage(effectsImage );
        Image backImage = new Image(getClass().getResource("/GameAssets/Back.png").toExternalForm());
        backView.setImage(backImage );
        Image nextImage = new Image(getClass().getResource("/GameAssets/Next.png").toExternalForm());
        nextView.setImage(nextImage);




    }

    public void selectArena() {
        environment = arena;
        text.setText("No buffs or penalties - Fair fight!");
        Image image = new Image(getClass().getResource("/environmentAssets/arena.png").toExternalForm());
        environmentView.setImage(image);
        environmentView.minWidth(1920);
        environmentView.minHeight(1080);

    }

    public void selectSwamp() {
        environment = swamp;
        text.setText("Player loses 1 HP per turn\n" +
                "Opponent gains 1 ATK per turn");
        Image image = new Image(getClass().getResource("/environmentAssets/swamp.png").toExternalForm());
        environmentView.setImage(image);
        environmentView.minWidth(1920);
        environmentView.minHeight(1080);


    }

    public void selectColosseum() {
        environment = colosseum;
        text.setText("Player gains 1 ATK per turn\n" +
                "Opponent loses 1 DEF per turn");
        Image image = new Image(getClass().getResource("/environmentAssets/colosseum.png").toExternalForm());
        environmentView.setImage(image);
        environmentView.minWidth(1920);
        environmentView.minHeight(1080);


    }

    public void nextButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        nextView.setImage(hoverImage);

        playSelectSound();
    }
    public void nextButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Next.png").toExternalForm());
        nextView.setImage(normalImage);
    }
    public void backButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        backView.setImage(hoverImage);

        playSelectSound();
    }
    public void bachButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Back.png").toExternalForm());
        backView.setImage(normalImage);
    }

    public void arenaButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        arenaView.setImage(hoverImage);

        playSelectSound();
    }
    public void arenaButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Arena.png").toExternalForm());
        arenaView.setImage(normalImage);
    }

    public void swampButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        swampView.setImage(hoverImage);

        playSelectSound();
    }
    public void swampButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Swamp.png").toExternalForm());
        swampView.setImage(normalImage);
    }

    public void colosseumButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        colosseumView.setImage(hoverImage);

        playSelectSound();
    }
    public void colosseumButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Colosseum.png").toExternalForm());
        colosseumView.setImage(normalImage);
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

    public void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChooseOpponent.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Character Creation");
        String css = getClass().getResource("Bro.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();

    }

    public void nextButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("battlePhase.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        String css = getClass().getResource("Bro.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Battle");
        stage.setScene(scene);
        stage.show();

    }





}