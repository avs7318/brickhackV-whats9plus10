package Model;

public class Choice {
    //the options the player has to choose from
    private String choice;

    //the starting scene for the player
    private Scene start;

    //the ending scene, that the player goes to after choosing option
    private Scene end;

    /**
     * This is the constructor for a Choice
     * @param choice (String)
     * @param start (Scene)
     * @param end (Scene)
     */
    public Choice(String choice, Scene start, Scene end){
        this.choice = choice;
        this.start = start;
        this.end = end;
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
    public Scene getStart(){
        return this.start;
    }

    /**
     * This is simply a getter for end
     * @return this.end
     */
    public Scene getEnd(){
        return this.end;
    }

    /**
     * This method override toString(), having it return the
     * choice as a string
     * @return
     */
    @Override
    public String toString(){
       return "Choice{option: " + this.getChoice() + ",\nstartscene: " + start + "\nendscene: " + end ;
    }

    /**
     * Just a test method, creating a new choice called choice1,
     * and setting it to a choice called "Fight?" with null start
     * and null end.  It is then converted to a string and
     * printed out
     * @param args
     */
    public static void main(String[] args){
        Choice choice1 = new Choice("Fight?",null,null);
        System.out.println(choice1.toString());
    }

}
