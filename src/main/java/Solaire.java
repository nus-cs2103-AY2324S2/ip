import java.util.Scanner;
import java.util.ArrayList;
public class Solaire {
    private ArrayList<Task> toDoList = new ArrayList<>();
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
            // Parse user input

            // If input starts with mark/unmark
            // Try to mark the task done if the format is correct
            String[] inputCommand = input.split(" ");
            if (inputCommand[0].equals("mark") || inputCommand[0].equals("unmark")) {
                if (inputCommand.length != 2) {
                    addToList(input);
                } else {
                    int taskId = Integer.parseInt(inputCommand[1]);
                    if (inputCommand[0].equals("mark")) {
                        markDone(taskId);
                    } else if (inputCommand[0].equals("unmark")) {
                        unmarkDone(taskId);
                    } else {
                        addToList(input);
                    }
                }
                    
            } else {
                addToList(input);
            }
        }
    }         

    private void addToList(String item) {
        this.toDoList.add(new Task(item));
        System.out.println("Added " + item + " to list");
        lineBreak();
    }

    private void showList() {
        System.out.println("Your list is as follows:\n " + "-------------------");
        for (Task item : toDoList) {
            System.out.println(item);
        }
        lineBreak();
    }

    private void markDone(int id) {
        for (Task item : toDoList) {
            if (item.getId() == id) {
                item.markAsDone();
                System.out.println("Marked item number: " + item.getId());
                return;
            }
        }

        System.out.println("Couldn't find task associated with given id");
    }

    private void unmarkDone(int id) {
        for (Task item : toDoList) {
            if (item.getId() == id) {
                item.unmarkDone();
                System.out.println("Unmarked  item number: " + item.getId());
                return;
            }
        }

        System.out.println("Couldn't find task associated with given id");
    }


}