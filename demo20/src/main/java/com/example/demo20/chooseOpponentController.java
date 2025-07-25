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
    @FXML
    ImageView thiefView = new ImageView();
    @FXML
    ImageView vikingView = new ImageView();
    @FXML
    ImageView minotaurView = new ImageView();
    @FXML
    ImageView backView = new ImageView();
    @FXML
    ImageView nextView = new ImageView();
    @FXML
    ImageView backgroundView = new ImageView();
    @FXML
    ImageView opponenTextView = new ImageView();
    private Image image;
    public static Opponent opponent;
    Opponent thief = new Thief();
    Opponent viking = new Viking();
    Opponent minotaur = new Minotaur();
    private Clip clip;

    public void initialize() {
        Image backgroundlImage = new Image(getClass().getResource("/GameAssets/Dungeon.jpeg").toExternalForm());
        backgroundView.setImage(backgroundlImage);
        Image normalImage = new Image(getClass().getResource("/GameAssets/Next.png").toExternalForm());
        nextView.setImage(normalImage);
        Image opponentImage = new Image(getClass().getResource("/GameAssets/Opponent.png").toExternalForm());
        opponenTextView.setImage(opponentImage);
        Image minoImage = new Image(getClass().getResource("/GameAssets/Minotaur.png").toExternalForm());
        minotaurView.setImage(minoImage);

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
    public void thiefButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        thiefView.setImage(hoverImage);
        playSelectSound();
    }
    public void thiefButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Thief.png").toExternalForm());
        thiefView.setImage(normalImage);
    }
    public void vikingButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        vikingView.setImage(hoverImage);

        playSelectSound();
    }
    public void vikingfButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Viking.png").toExternalForm());
        vikingView.setImage(normalImage);
    }
    public void minotaurButtonEnter(MouseEvent e)  {
        Image hoverImage = new Image(getClass().getResource("/GameAssets/Hover.png").toExternalForm());
        minotaurView.setImage(hoverImage);

        playSelectSound();
    }
    public void minotaurButtonExit(MouseEvent e) {   // Changed to MouseEvent
        Image normalImage = new Image(getClass().getResource("/GameAssets/Minotaur.png").toExternalForm());
        minotaurView.setImage(normalImage);
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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Creation.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1920, 1080);
        String css = getClass().getResource("Bro.css").toExternalForm();
        scene.getStylesheets().add(css);
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