import java.util.Scanner;
import java.util.ArrayList;

public class Steven {

    public static void main(String args[]) {
        String line = "========\n";
        String bootMsg = ("This is Steven!\nHow can I advise?\n");
        System.out.println(line + bootMsg + line);
        ArrayList<Task> taskList = new ArrayList<Task>();
        boolean exit = false;
        while (!exit) {
            Scanner userInput = new Scanner(System.in);
            String command = userInput.nextLine();
            switch (command) {
                case "list":
                    System.out.println("This is your list so far:");
                    int counter = 1;
                    for (Task t: taskList) {
                        System.out.println(String.format("%d. %s", counter, t.toString()));
                        counter++;
                    }
                    break;
                case "bye":
                    exit = true;
                    break;
                default:
                    Task newTask = new Task(command);
                    taskList.add(newTask);
                    System.out.println("I've added this to the list: " + command);
                    System.out.println("If you want to see what's on the list so far, just type \"list\"!");
            }
            System.out.println(line);
        }

        System.out.println("I'll see you soon then!\n" + line);
    }
}
