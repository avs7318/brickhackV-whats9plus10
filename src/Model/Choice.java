package Model;

import java.util.ArrayList;

public class Choice {
    //the options the player has to choose from
    private String choice;

    //the starting scene for the player
    private String currentscene;

    //the ending scene, that the player goes to after choosing option
    private String resultscene;

    /**
     * This is the constructor for a Choice
     * @param choice (String)
     * @param currentscene (String)
     * @param resultscene (String)
     */
    public Choice(String choice, String currentscene, String resultscene){
        this.choice = choice;
        this.currentscene = currentscene;
        this.resultscene = resultscene;
    }

    /**
     * This is simply a getter for choice
     * @return this.choice
     */
    public String getChoice(){
        return this.choice;
    }

    /**
     * This is simply a getter for start
     * @return this.start
     */
    public String getStart(){
        return this.currentscene;
    }

    /**
     * This is simply a getter for end
     * @return this.end
     */
    public String getEnd(){
        return this.resultscene;
    }

    /**
     * This method override toString(), having it return the
     * choice as a string
     * @return
     */
    @Override
    public String toString(){
       return "Choice{ option: '" + this.getChoice() + "'\nStartscene: Scene{" + currentscene + "}\nEndscene: Scene{" + resultscene + "} }";
    }

    /**
     * Just a test method, creating a new choice called choice1,
     * and setting it to a choice called "Fight?" with null start
     * and null end.  It is then converted to a string and
     * printed out
     * @param args
     */
    public static void main(String[] args){
        ArrayList<Choice> starting = new ArrayList<Choice>();
        ArrayList<Choice> ending = new ArrayList<Choice>();
        Scene scene1 = new Scene("Found the devil", "Room", starting, "You fight!");
        Scene scene2 = new Scene("Run", "Hell", ending, "He stabs you with his pitchfork.");
        Choice choice1 = new Choice("Flee?", scene1.getSceneName(), scene2.getSceneName());
        System.out.println(choice1.toString());
        System.out.println();
        Scene scene3 = new Scene("Fight", "Room", ending, "You behead the devil.");
        Choice choice2 = new Choice("Fight?", scene1.getSceneName(), scene3.getSceneName());
        System.out.println(choice2.toString());
    }

}
