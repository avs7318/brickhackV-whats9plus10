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

    /**
     * gets this scene name
     * @return this.sceneName
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * gets this background file as a string
     * @return this.background
     */
    public String getBackground() {
        return background;
    }

    /**
     * gets this opening dialogue
     * @return this.dialogue
     */
    public String getDialogue() {
        return dialogue;
    }

    /**
     * gets this list of choices
     * @return this.choices
     */
    public ArrayList<Choice> getChoices() {
        return choices;
    }

    /*
    public String printScene() {
        return this.sceneName +
                " \nInitial Dialogue: " +
                this.dialogue;
    }
    */

    /**
     * convert the object Scene into a string format
     * @return scene name, background, dialogue, and choices
     */
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

    /**
     * main method to test Scene
     * @param args not used
     */
    public static void main(String[] args) {
        // create and print test scenes
        Scene testScene1 = new Scene("Scene 1", "1.txt", new ArrayList<Choice>(),
                "Opening dialogue for scene 1");
        System.out.println(testScene1);

        Scene testScene2 = new Scene("Scene 2", "2.txt", new ArrayList<Choice>(),
                "Opening dialogue for scene 2");
        System.out.println("\n" + testScene2);

        // create test choices for testScene1
        Choice c1 = new Choice("Fight", testScene1.getSceneName(), testScene2.getSceneName());
        testScene1.choices.add(c1);
        Choice c2 = new Choice("run", testScene1.getSceneName(), testScene2.getSceneName());
        testScene1.choices.add(c2);

        // print
        System.out.println("\n" + testScene1);
        System.out.println("\n" + testScene2);
    }
}