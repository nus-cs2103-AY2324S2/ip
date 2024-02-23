package eve.parser;
import java.io.IOException;
import java.util.*;

import eve.TaskList;
import eve.exceptions.EveExceptions;
import eve.fileStorage.Storage;
import eve.tasks.Task;
// Might want to change this name to the UI class

/**
 * Commands class is used to handle the commands that are input by the user
 * It contains the methods to handle the commands
 */

public class Commands {

    /**
     * This method is used to print out the welcome message
     */
    public static void commandHello() {
        System.out.println(" Hello! I'm Eve");
        System.out.println(" What can I do for you?");
    }

    /**
     * This method is used to print out the goodbye message
     * it also saves the tasks into the .txt file locally
     * @param tasks is the list of tasks
     */
    public static void commandBye(ArrayList<Task> tasks) {
        System.out.println(" Bye. Hope to see you again soon !");
        try {
            Storage.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("hi");
        }
    }
    /**
     * This method is used to listen to the commands input by the user
     * it also loads the locally saved tasks from the .txt file into the list (if any)
     */

    public static void commandListener() {
        Scanner sc = new Scanner(System.in);

        String input = "";
        ArrayList<Task> list = new ArrayList<>();
        // Storage storeFile = new Storage();

       
        // refactor into cases
        while (!input.equals("bye")) {
            input = sc.nextLine();

            String[] tempyArr = input.split(" ", 2);
            String commandCheck = tempyArr[0];
           


        sc.close();
    }
}
