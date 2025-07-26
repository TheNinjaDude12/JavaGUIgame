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

import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;


public class startController {
    @FXML
    private Button startButton;
    @FXML
    ImageView startView;
    @FXML
    ImageView exitView;



    public static Clip clip;
    public Clip sfxClip;

    public void initialize() {
        try {
            URL soundURL = getClass().getResource("/GameAssets/Music/DarkSouls3.wav");
            if (soundURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop background music
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void startButtonEnter(MouseEvent e) {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        startView.setImage(hoverImage);


        try {
            URL soundURL = getClass().getResource("/GameAssets/Music/select.wav");
            if (soundURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            sfxClip = AudioSystem.getClip();
            sfxClip.open(audioIn);
            sfxClip.start();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void startButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Start.png").toExternalForm());
        startView.setImage(normalImage);
    }
    public void exitButtonEnter(MouseEvent e) {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        exitView.setImage(hoverImage);

        try {
            URL soundURL = getClass().getResource("/GameAssets/Music/select.wav");
            if (soundURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            sfxClip = AudioSystem.getClip();
            sfxClip.open(audioIn);
            sfxClip.start();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void exitButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Exit.png").toExternalForm());
        exitView.setImage(normalImage);

    }

    public void confirmSelect(MouseEvent e) {   // Changed to MouseEvent
        try {
            URL soundURL = getClass().getResource("/GameAssets/Music/confirm.wav");
            if (soundURL == null) {
                System.out.println("Audio file not found!");
                return;
            }

            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            sfxClip = AudioSystem.getClip();
            sfxClip.open(audioIn);
            sfxClip.start();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("");

    }


    public void startGame(ActionEvent event) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Creation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        String css = getClass().getResource("Bro.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Character Creation");
        stage.setScene(scene);
        stage.show();

    }
    public void exitGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Creation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();

    }
}
