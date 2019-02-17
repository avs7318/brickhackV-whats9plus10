package PTUI;
import Model.Model;
import Model.Scene;
import Model.Choice;

import java.util.ArrayList;
import java.util.Scanner;

public class BrickerSnatchPTUI {

    public static Model model;

    public static void main(String[] args){
        String filename;
        Scanner sc = new Scanner(System.in);
        if(args.length > 0){
            filename = args[0];
        } else{
            System.out.println("Please provide a filename to create a story: ");
            filename = sc.nextLine();
        }
        model = new Model(filename);
        Scene scene = model.getCurrentScene();
        while(scene != null){
            System.out.println(scene.getDialogue());
            ArrayList<Choice> choices = scene.getChoices();
            if(choices.size() == 0) {
                return;
            } else{
                for(int i=0; i < choices.size(); i++){
                    Choice choice = choices.get(i);
                    System.out.println("\t" + (i + 1) + ") " + choice.getChoice());
                }
            }
            System.out.print("Select a choice: ");
            int userChoice = sc.nextInt() - 1;
            model.makeChoice(userChoice);
            scene = model.getCurrentScene();
        }
        System.out.println("You ded");

    }
}
