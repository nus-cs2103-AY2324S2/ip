package eve;
import java.io.IOException;
import java.util.*;

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

        try {
            Storage.loadFileContents(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // refactor into cases
        while (!input.equals("bye")) {
            input = sc.nextLine();

            String[] tempyArr = input.split(" ", 2);
            String commandCheck = tempyArr[0];
            try {
                switch (commandCheck) {
                case "bye":
                    Commands.commandBye(list);
                    break;
                case "list":
                    TaskList.commandList(list);
                    break;
                case "mark":
                    TaskList.commandMark(tempyArr, list);
                    break;
                case "unmark":
                    TaskList.commandUnMark(tempyArr, list);
                    break;
                case "delete":
                    TaskList.commandDelete(tempyArr, list);
                    break;
                case "todo":
                    TaskList.commandTodo(tempyArr, list);
                    break;
                case "deadline":
                    TaskList.commandDeadline(tempyArr, list);
                    break;
                case "event":
                    TaskList.commandEvent(tempyArr, list);
                    break;

                default:
                    throw new EveExceptions("OOPS!!! I'm sorry, but I don't know what that means, please try again");

                }
            } catch (EveExceptions e) {
                System.out.println(e.getMessage());
            }

        }


        sc.close();
    }
}
