import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ui {
    public static String lineBreak = "---------------------------------------------------------";

    public void printLineBreak() {
        System.out.println(lineBreak);
    }
    public void greet() {
        System.out.println("Hi! This is Bit!\nWhat shall we do today?\n");
    }

    public void sayBye() {
        System.out.println("Alright. See you soon!!");
    }

    public void fileError() {
        System.out.println("Error! Something went wrong while creating your file!");
    }

    public void listOut(ArrayList<Task> list) {
        System.out.println("Sure! Here is the list:\n");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == null) {
                break;
            }
            System.out.println((i + 1) + "." + list.get(i).toString());
        }
    }

    public void  sayMarked(Task task) {
        System.out.println("Done and dusted: " + task.toString());
    }

    public void sayUnmarked(Task task) {
        System.out.println("Alright, let me uncheck that for you: " + task.toString());
    }

    public void sayAdded(int i, String string, Task task) {
        switch (string) {
            case "todo":
                System.out.println("I have added this todo: " + i + '.' + task.toString());
                break;
            case "event":
                System.out.println("I have added this event: " + (i) + " " + task.toString());
                break;
            case "deadline":
                System.out.println("Done! I have added this deadline" + task.toString());
            default:
                break;
        }
    }

    public void sayDeleted(String s) {
        System.out.println("Got it! I have deleted this item: " + s);
    }


    public void handleErrorMessage(String string) {
        switch (string) {
            case "absent":
                System.out.println("Hey, I don't think you have added that yet!");
                break;
            case "Not a number":
                System.out.println("Woah! That is not a number!");
                break;
            case "forget":
                System.out.println("Did you forget something?");
                break;
            case "NotaDate":
                System.out.println("The date you entered is invalid.");
                break;
            default:
                System.out.println("Sorry, I don't understand what you mean\n");
                break;
        }
    }


    public void printException(Exception e) {
        System.out.println(e.getMessage());
    }
}
