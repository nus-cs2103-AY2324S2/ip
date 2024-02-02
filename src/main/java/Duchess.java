
import java.util.Scanner;
import java.util.ArrayList;

public class Duchess {
    private TaskList taskList;
    private Storage storage;

    private Ui ui;

    // Declare the scanner as a static field in the class
    private static Scanner scanner = new Scanner(System.in);

    private static final String FILE_PATH = "./data/duchess.txt";

    public Duchess() throws DuchessException {
        this.storage = new Storage(FILE_PATH);
        this.taskList = new TaskList();
        ArrayList<Task> tasksStored = storage.loadData();
        if (!tasksStored.isEmpty()) {
            this.taskList = new TaskList(storage.loadData());
        }
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        try {
            new Duchess().run();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        }

    }

    public void run() {
        ui.printOpeningGreeting();
        try {
            printEcho();
        } catch (DuchessException e) {
            System.out.println(e.getMessage());
        } finally {
            //Close scanner
            scanner.close();
        }
    }


    //Adds user input to list, exits if user inputs "bye"
    private void printEcho() throws DuchessException {
        // Loop to read user input
        while (true) {
            String userInput = scanner.nextLine();

            // Split user input into tokens
            String[] tokens = userInput.split(" ");

            // Based on user input, change output
            switch (tokens[0].toLowerCase()) {
                case "bye":
                    ui.printClosingGreeting();
                    return;

                case "list":
                    taskList.printTaskList();
                    break;

                case "mark":
                    if (tokens.length > 1) {
                        int taskIndexToMark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.markTaskAsDone(taskIndexToMark);
                        storage.saveData(taskList);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: mark <taskIndex>");
                    }
                    break;

                case "unmark":
                    if (tokens.length > 1) {
                        int taskIndexToUnmark = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.unmarkTaskAsDone(taskIndexToUnmark);
                        storage.saveData(taskList);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                case "todo":
                    taskList.addToDo(userInput);
                    storage.saveData(taskList);
                    break;

                case "deadline":
                    taskList.addDeadline(userInput);
                    storage.saveData(taskList);
                    break;

                case "event":
                    taskList.addEvent(userInput);
                    storage.saveData(taskList);
                    break;

                case "delete":
                    if (tokens.length > 1) {
                        int taskIndexToDelete = Integer.parseInt(tokens[1]) - 1; //Minus 1 to match zero-index
                        taskList.deleteTask(taskIndexToDelete);
                        storage.saveData(taskList);
                    } else {
                        throw new DuchessException("Oh dear! That is an invalid command. Try: unmark <taskIndex>");
                    }
                    break;

                default:
                    throw new DuchessException("Oh dear, I can't make out what that is.");

            }
            ui.printHorizontalLine();
        }
    }
}
