package Model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * The Model class encapsulates the data and behavior that represents the game.
 * It can be used to play the game in Plan Text or Graphical mode.
 * @author Yancarlos Diaz
 * @author Alex Scholeno
 * @author Alec Mahoney
 */
public class Model{

    /**
     * HashMap used to look up Scenes by scene name
     */
    private HashMap<String, Scene> scenes;
    /**
     * HashMap used to look up Scenes by choice name
     */
    private HashMap<String, Choice> choices;
    /**
     * The current scene being played
     */
    private Scene currentScene;
    /**
     * An array containing the desired width and height of the game
     */
    private ArrayList<String> imgDimensions;

    /**
     * Creates a model object by reading the provided json file
     * The json file must contain a scene called "start" that will be used to initiate the game
     * @param filename a json file containing the scenes and choices at each scene
     */
    public Model(String filename){
        this.choices = new HashMap<String, Choice>();
        this.scenes = new HashMap<String, Scene>();
        this.imgDimensions = new ArrayList<>();
        readFile(filename);
        this.currentScene = scenes.get("start");
    }

    /**
     * Reads the json file with choices and scenes and populates the HashMaps.
     * It also finds the desired width and height coordinates.
     * @param filename the json file to read
     */
    private void readFile(String filename){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));
            JSONObject jsonObject = (JSONObject) obj;

            // Read the choices and populate the appropriate HashMap
            JSONArray choices = (JSONArray) jsonObject.get("choices");
            for(Object object: choices){
                JSONObject choiceObject = (JSONObject) object;

                String choiceText = (String) choiceObject.get("name");
                String choiceCurrentScene = (String) choiceObject.get("current scene");
                String choiceResultScene = (String) choiceObject.get("result scene");

                Choice choice = new Choice(choiceText, choiceCurrentScene, choiceResultScene);
                this.choices.put(choiceText, choice);
            }

            // Read the scenes and populate the appropriate HashMap
            JSONArray scenes = (JSONArray) jsonObject.get("scenes");
            for(Object object: scenes){
                JSONObject sceneObject = (JSONObject) object;

                String sceneName = (String) sceneObject.get("name");
                String background = (String) sceneObject.get("background");
                String dialog = (String) sceneObject.get("dialog");
                JSONArray sceneChoiceNames = (JSONArray) sceneObject.get("choices");

                ArrayList<Choice> sceneChoices = new ArrayList<Choice>();

                for(Object sceneChoiceName: sceneChoiceNames){
                    String choiceName = (String) sceneChoiceName;
                    sceneChoices.add(this.choices.get(choiceName));
                }
                Scene scene = new Scene(sceneName, background, sceneChoices, dialog);
                this.scenes.put(sceneName, scene);
            }

            // Read the desired image dimensions
            JSONArray dimensions = (JSONArray) jsonObject.get("image dimensions");
            JSONObject width = (JSONObject) dimensions.get(0);
            JSONObject height = (JSONObject) dimensions.get(1);

            String widthString = (String) width.get("width");
            String heightString = (String) height.get("height");

            imgDimensions.add(widthString);
            imgDimensions.add(heightString);


        } catch(FileNotFoundException e) {
            System.out.println("We could not find your file. Please try again.");
            System.exit(-1);
        } catch(Exception e) {
            System.out.println("It seems something went wrong reading your file.");
            System.exit(-1);
        }
    }

    /**
     * Returns the current scene being played.
     * This is used by the observers.
     * @return the current scene
     */
    public Scene getCurrentScene(){
        return this.currentScene;
    }

    /**
     * Returns the user-defined image dimensions.
     * @return An ArrayList with the desired width and height, in tat order.
     */
    public ArrayList<String> getImgDimensions(){
        return this.imgDimensions;
    }

    /**
     * Given a choice number, makes the appropriate choice and changes the state.
     * If the choice number is out of range, the function has no effect.
     * @param choiceNumber the index of the choice the user is taking
     */
    public void makeChoice(int choiceNumber){
        if (currentScene.getChoices().size() > choiceNumber && choiceNumber >= 0) {
            Choice choice = this.currentScene.getChoices().get(choiceNumber);
            String resultString = choice.getEnd();
            this.currentScene = this.scenes.get(resultString);
        }
    }
}
