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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class chooseEnvironmentController {


    public static Environment environment;
    Environment arena = new Environment("Arena");
    Environment swamp = new Environment("Swamp");
    Environment colosseum = new Environment("Colosseum");
    @FXML
    TextArea textArea;
    @FXML
    ImageView environmentView;

    public void initialize() {
        environment = arena;
        textArea.setText("No buffs or penalties - Fair fight!");
        Image image = new Image(getClass().getResource("/environmentAssets/arena.jpg").toExternalForm());
        environmentView.setImage(image);

    }

    public void selectArena() {
        environment = arena;
        textArea.setText("No buffs or penalties - Fair fight!");
        Image image = new Image(getClass().getResource("/environmentAssets/arena.jpg").toExternalForm());
        environmentView.setImage(image);
        environmentView.minWidth(1920);
        environmentView.minHeight(1080);

    }

    public void selectSwamp() {
        environment = swamp;
        textArea.setText("Player loses 1 HP per turn\n" +
                "Opponent gains 1 ATK per turn");
        Image image = new Image(getClass().getResource("/environmentAssets/swamp.jpg").toExternalForm());
        environmentView.setImage(image);
        environmentView.minWidth(1920);
        environmentView.minHeight(1080);


    }

    public void selectColosseum() {
        environment = colosseum;
        textArea.setText("Player gains 1 ATK per turn\n" +
                "Opponent loses 1 DEF per turn");
        Image image = new Image(getClass().getResource("/environmentAssets/colosseum.jpg").toExternalForm());
        environmentView.setImage(image);
        environmentView.minWidth(1920);
        environmentView.minHeight(1080);


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