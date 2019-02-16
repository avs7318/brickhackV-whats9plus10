package Model;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Model{

    private HashMap<String, Scene> scenes;
    private HashMap<String, Choice> choices;
    private Scene currentScene;

    public Model(String filename){
        this.choices = new HashMap<String, Choice>();
        this.scenes = new HashMap<String, Scene>();
        readFile(filename);
    }

    public void readFile(String filename){
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(filename));

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray choices = (JSONArray) jsonObject.get("choices");

            for(Object object: choices){
                JSONObject choiceObject = (JSONObject) object;

                String choiceText = (String) choiceObject.get("name");
                String choiceCurrentScene = (String) choiceObject.get("current scene");
                String choiceResultScene = (String) choiceObject.get("result scene");
                Choice choice = new Choice(choiceText, choiceCurrentScene, choiceResultScene);
                this.choices.put(choiceText, choice);
            }

            JSONArray scenes = (JSONArray) jsonObject.get("scenes");
            for(Object object: scenes){
                JSONObject sceneObject = (JSONObject) object;

                String sceneName = (String) sceneObject.get("name");
                String background = (String) sceneObject.get("background");
                String dialog = (String) sceneObject.get("dialog");
                ArrayList<Choice> sceneChoices = new ArrayList<Choice>();
                JSONArray sceneChoiceNames = (JSONArray) sceneObject.get("choices");
                for(Object sceneChoiceName: sceneChoiceNames){
                    String choiceName = (String) sceneChoiceName;
                    sceneChoices.add(this.choices.get(choiceName));
                }
                Scene scene = new Scene(sceneName, background, sceneChoices, dialog);
                this.scenes.put(sceneName, scene);
            }

            System.out.println(this.choices);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        Model m = new Model("src/Stories/test.json");
    }

}
