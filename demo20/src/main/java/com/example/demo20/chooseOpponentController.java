package com.example.demo20;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class chooseOpponentController {

    @FXML
    Text hp = new Text();
    @FXML
    Text def = new Text();
    @FXML
    Text atk = new Text();
    @FXML
    Text spd = new Text();
    @FXML
    Button thiefButton = new Button();
    @FXML
    Button vikingButton = new Button();
    @FXML
    Button minotaurButton = new Button();
    @FXML
    ImageView opponentView = new ImageView();
    Image image;
    static Opponent opponent;
    Opponent thief = new Opponent("Thief", 150, 20, 20, 40);
    Opponent viking = new Opponent("Viking", 250, 30, 30, 30);
    Opponent minotaur = new Opponent("Minotaur", 350, 40, 40, 20);

    public void initialize() {
        if(opponent!=null && opponent.getName().equals("Thief")) {
            System.out.println("works thief");
            hp.setText(""+thief.getHitPoints());
            def.setText(""+thief.getDefense());
            atk.setText(""+thief.getAttack());
            spd.setText(""+thief.getSpeed());
            image = new Image(getClass().getResource("/OpponentAssets/thief.png").toExternalForm());
            opponentView.setImage(image);
        }
        if(opponent!=null && opponent.getName().equals("Viking")) {
            System.out.println("works vik");
            hp.setText(""+viking.getHitPoints());
            def.setText(""+viking.getDefense());
            atk.setText(""+viking.getAttack());
            spd.setText(""+viking.getSpeed());
            image = new Image(getClass().getResource("/OpponentAssets/viking.jpg").toExternalForm());
            opponentView.setImage(image);
        }
        if(opponent!=null && opponent.getName().equals("Minotaur")) {
            System.out.println("works");
            hp.setText(""+minotaur.getHitPoints());
            def.setText(""+minotaur.getDefense());
            atk.setText(""+minotaur.getAttack());
            spd.setText(""+minotaur.getSpeed());
            image = new Image(getClass().getResource("/OpponentAssets/minotaur.png").toExternalForm());
            opponentView.setImage(image);
        }
    }
    public void thiefSelect(ActionEvent e) {
        hp.setText(""+thief.getHitPoints());
        def.setText(""+thief.getDefense());
        atk.setText(""+thief.getAttack());
        spd.setText(""+thief.getSpeed());
        opponent = thief;
        image = new Image(getClass().getResource("/OpponentAssets/thief.png").toExternalForm());
        opponentView.setImage(image);

    }
    public void vikingSelect(ActionEvent e) {
        hp.setText(""+viking.getHitPoints());
        def.setText(""+viking.getDefense());
        atk.setText(""+viking.getAttack());
        spd.setText(""+viking.getSpeed());
        opponent = viking;
        image = new Image(getClass().getResource("/OpponentAssets/viking.jpg").toExternalForm());
        opponentView.setImage(image);
    }
    public void minotaurSelect(ActionEvent e) {
        hp.setText(""+minotaur.getHitPoints());
        def.setText(""+minotaur.getDefense());
        atk.setText(""+minotaur.getAttack());
        spd.setText(""+minotaur.getSpeed());
        opponent = minotaur;
        image = new Image(getClass().getResource("/OpponentAssets/minotaur.png").toExternalForm());
        opponentView.setImage(image);
    }

    public void backButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Creation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Character Creation");
        stage.setScene(scene);
        stage.show();

    }

    public Opponent nextButton(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ChooseEnvironment.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        stage.setTitle("Choose Environment Creation");
        stage.setScene(scene);
        stage.show();
        return opponent;

    }





}