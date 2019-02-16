package PTUI;

import Model.Model;

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


    }
}
