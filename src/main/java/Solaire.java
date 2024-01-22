import java.util.Scanner;
import java.util.ArrayList;
public class Solaire {
    private ArrayList<String> toDoList = new ArrayList<>();
    private Scanner scn = new Scanner(System.in);
    
    public void startConversation() {
        greet();

        while(true) {
            String input = acceptInput();
            
            if (input.equals("bye")) {
                break;
            }
            processInput(input);
        }
        scn.close();
        waveBye();
    }

    private void lineBreak() {
        System.out.println("--------------------------------------------------\n");
    }
    private void greet() {
        String greetingMessage = "Oh hello there. I'm Solaire of Astora.\n"
        + "The sun is a wondrous body. Like a magnificent father!\n"
        + "If only I could be so grossly incandescent!\n";

        System.out.println(greetingMessage);
        lineBreak();
    }

    private void waveBye() {
        String farewellMessage = "Farewell!";
        System.out.println(farewellMessage);
        lineBreak();
    }

    private String acceptInput() {
        
        String input = this.scn.nextLine().toLowerCase();
        return input;
    }

    private void processInput(String input) {
        if (input.equals("list")) {
            showList();
        } else {
            addToList(input);
        }
    }

    private void addToList(String item) {
        this.toDoList.add(item);
        System.out.println("Added " + item + " to list");
        lineBreak();
    }

    private void showList() {
        System.out.println("Your list is as follows:\n " + "-------------------");
        for (String item : toDoList) {
            System.out.println(item);
        }
        lineBreak();
    }


}
