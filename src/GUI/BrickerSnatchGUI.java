/*
 * The BrickerSnatchGUI class implements the graphical user interface to play the game
 * @author: Alec Mahoney
 * @author: Yancarlos Diaz
 */
package GUI;

import Model.Choice;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.ArrayList;

public class BrickerSnatchGUI extends Application {
    private Label dialogue;
    private static String filename;
    private Model.Model model;
    private Model.Scene currentScene;
    private VBox userOptions;
    private BorderPane layout;
    private String backgroundWidth;
    private String backgroundHeight;

    /**
     * Instantiates a model and extracts the current scene.
     * Creates a basic GUI for the game
     * Precondition: user file must use correct json formatting
     * @param stage the stage where the scene is placed;
     */
    public void start(Stage stage) {
        this.model = new Model.Model(filename);
        this.currentScene = model.getCurrentScene();
        this.backgroundWidth = model.getImgDimensions().get(0);
        this.backgroundHeight = model.getImgDimensions().get(1);
        // List of possible choices for the currentScene.
        ArrayList<Choice> choices = currentScene.getChoices();
        // String containing the dialogue to be displayed for the current scene.
        String dialogueString = currentScene.getDialogue();
        // Name of the Scene's background image.
        String sceneImage = currentScene.getBackground();

        layout = new BorderPane();
        // Label containing the dialogueString and its respective formatting.
        dialogue = new Label(dialogueString);
        dialogue.setStyle("-fx-min-width: " + this.backgroundWidth + ";" +
                "-fx-max-width: " + this.backgroundWidth + ";" +
                "-fx-background-color: rgba(255, 255, 255, .7);");
        dialogue.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        dialogue.setWrapText(true);
        dialogue.setPadding(new Insets(5,10, 5,10));
        dialogue.setTextAlignment(TextAlignment.CENTER);
        layout.setCenter(dialogue);

        // Stores the buttons for each scene
        userOptions = new VBox();
        updateUserChoices(choices);
        layout.setBottom(userOptions);

        layout.setMinSize(Integer.parseInt(this.backgroundWidth), Integer.parseInt(this.backgroundHeight));
        String imagePath = "/Assets/" + sceneImage;
        layout.setStyle("-fx-background-image: url(" + imagePath + ");" +
                "-fx-background-position: center center;" +
                "-fx-background-size: " + this.backgroundWidth + " " + this.backgroundHeight);

        Scene sc = new Scene(layout);
        stage.setScene(sc);
        stage.setMaxWidth(Integer.parseInt(this.backgroundWidth));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Upon selecting a choice, this event is triggered, changing the state of the game.
     * @param choiceId the choice number the user chose.
     */
    public void choiceEvent(int choiceId) {
        model.makeChoice(choiceId);
        currentScene = model.getCurrentScene();

        ArrayList<Choice> choices = currentScene.getChoices();
        String dialogueString = currentScene.getDialogue();
        String backgroundName = currentScene.getBackground();

        String imagePath = "Assets/" + backgroundName;
        layout.setStyle("-fx-background-image: url(" + imagePath + ");" +
                "-fx-background-position: center center;" +
                "-fx-background-size: " + this.backgroundWidth + " " + this.backgroundHeight);

        userOptions.getChildren().clear();
        updateUserChoices(choices);
        dialogue.setText(dialogueString);
    }

    /**
     * Helper function to update the buttons in the screen given the list of current choices
     * @param choices the list of current choices
     */
    private void updateUserChoices(ArrayList<Choice> choices){
        for (int i = 0; i < choices.size(); i++) {
            Choice choice = choices.get(i);
            Button button = new Button();
            int index = i;
            button.setOnMouseClicked(event -> choiceEvent(index));
            button.setText(choice.getChoice());
            userOptions.getChildren().add(button);
        }
    }

    /**
     * Runs the program
     * @param args args[0] must contain the path to the json file to read
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please specify a filename when running this program");
        } else {
            filename = args[0];
            Application.launch(BrickerSnatchGUI.class);
        }
    }
}
