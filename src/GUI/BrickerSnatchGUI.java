package GUI;

import Model.Choice;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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

    @Override
    public void start(Stage stage) throws Exception {
        this.model = new Model.Model(filename);
        this.currentScene = model.getCurrentScene();
        ArrayList<Choice> choices = currentScene.getChoices();
        String dialogueString = currentScene.getDialogue();
        String backgroundName = currentScene.getBackground();

        this.stage = stage;
        BorderPane layout = new BorderPane();
        Text text = new Text(dialogueString);
        layout.setCenter(text);

        VBox boxyBoi = new VBox();
        for (Choice choice : choices) {
            Button button = new Button();
            button.setOnMouseClicked(event -> System.out.println("Button pressed!!!"));
            button.setText(choice.getChoice());
            boxyBoi.getChildren().add(button);
        }
        layout.setBottom(boxyBoi);

        Scene sc = new Scene(layout);
        stage.setScene(sc);
        stage.show();
    }

    public void choiceEvent(Button button) {

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
