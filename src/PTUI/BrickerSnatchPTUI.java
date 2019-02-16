package PTUI;

import Model.Model;

import java.util.Scanner;

public class BrickerSnatchPTUI {

    public static Model model;

    public static void main(String[] args){
        String filename;
        if(args.length > 0){
            filename = args[0];
        } else{
            System.out.println("Please provide a filename to create a story: ");
            Scanner sc = new Scanner(System.in);
            filename = sc.nextLine();
        }
        model = new Model(filename);

    }
}
