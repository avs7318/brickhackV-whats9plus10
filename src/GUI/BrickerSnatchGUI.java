package GUI;

import Model.Choice;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.ArrayList;



public class BrickerSnatchGUI extends Application {
    private Label dialogue;
    private ArrayList<Button> buttonChoices;
    private ImageView background;
    private Stage stage;
    private static String filename;
    private Model.Model model;
    private Model.Scene currentScene;
    private VBox boxyBoi;
    private BorderPane layout;

    @Override
    public void start(Stage stage) throws Exception {
        this.model = new Model.Model(filename);
        this.currentScene = model.getCurrentScene();
        ArrayList<Choice> choices = currentScene.getChoices();
        String dialogueString = currentScene.getDialogue();
        String backgroundName = currentScene.getBackground();

        this.stage = stage;
        layout = new BorderPane();
        dialogue = new Label(dialogueString);
        dialogue.setStyle("-fx-min-width: 661;" +
                "-fx-max-width: 661;" +
                "-fx-background-color: rgba(255, 255, 255, .7);");
        dialogue.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        dialogue.setWrapText(true);
        dialogue.setPadding(new Insets(5,10, 5,10));
        dialogue.setTextAlignment(TextAlignment.CENTER);
        layout.setCenter(dialogue);

        boxyBoi = new VBox();
        for (int i = 0; i < choices.size(); i++) {
            Choice choice = choices.get(i);
            Button button = new Button();
            int index = i;
            button.setOnMouseClicked(event -> choiceEvent(index));
            button.setText(choice.getChoice());
            boxyBoi.getChildren().add(button);
        }
        layout.setBottom(boxyBoi);

        //Image img = new Image("Assets/Alex.jpg");
        //background = new ImageView(img);
        layout.setMinSize(661, 551);
        String imagePath = "Assets/" + backgroundName;
        layout.setStyle("-fx-background-image: url(" + imagePath + ");" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 661 551");

        Scene sc = new Scene(layout);
        stage.setScene(sc);
        stage.setMaxWidth(661);
        stage.setResizable(false);
        stage.show();
    }

    public void choiceEvent(int choiceId) {
        model.makeChoice(choiceId);
        currentScene = model.getCurrentScene();

        ArrayList<Choice> choices = currentScene.getChoices();
        String dialogueString = currentScene.getDialogue();
        String backgroundName = currentScene.getBackground();

        String imagePath = "Assets/" + backgroundName;
        layout.setStyle("-fx-background-image: url(" + imagePath + ");" +
                "-fx-background-position: center center;" +
                "-fx-background-size: 661 551");

        boxyBoi.getChildren().clear();
        buttonChoices = new ArrayList<Button>();
        for (int i = 0; i < choices.size(); i++) {
            Choice choice = choices.get(i);
            Button button = new Button();
            int index = i;
            button.setOnMouseClicked(event -> choiceEvent(index));
            button.setText(choice.getChoice());
            boxyBoi.getChildren().add(button);
        }
        dialogue.setText(dialogueString);
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Bad");
        } else {
            filename = args[0];
            Application.launch(BrickerSnatchGUI.class);
        }
    }
}
