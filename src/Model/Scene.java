package Model;

import java.util.ArrayList;

public class Scene {
    private String sceneName;
    // name of the background file
    private String background;
    // the available choices
    private ArrayList<Choice> choices;
    // opening dialogue;
    private String dialogue;

    /**
     * Construct a new scene
     * @param sceneName a string representing the name for this scene
     * @param background a string containing the background filename
     * @param choices an array containing the possible choices for this scene
     * @param dialogue a string acting as the opening dialogue shown at the start of the scene
     */
    public Scene(String sceneName, String background, ArrayList<Choice> choices, String dialogue) {
        this.sceneName = sceneName;
        this.background = background;
        this.choices = choices;
        this.dialogue = dialogue;
    }

    public String getSceneName() {
        return sceneName;
    }

    public String getBackground() {
        return background;
    }

    public String getDialogue() {
        return dialogue;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    @Override
    public String toString() {
        return this.sceneName +
                " \nBackground file: " +
                this.background +
                " \nInitial Dialogue: " +
                this.dialogue +
                " \nPossible Choices: " +
                this.choices;
    }

    public static void main(String[] args) {
        Scene testScene = new Scene("Scene 1", "txt.1", new ArrayList<Choice>(),
                "Hey this is the opening dialogue");
        System.out.println(testScene);
    }
}