
import java.util.ArrayList;
import java.util.Scanner;

/*
 * The Duke class represent a simple task management program.
 * Users can add, mark as done, unmark, list, delete and exit tasks
 * Supports three types of tasks: Todo, Deadline, and Event
 * Provides a command-line interface for user interaction
 * 
 * @author Kailin Teo
 * 
 */

public class Duke {

    private Ui ui;

    public Duke(Storage filePath) {
        ui = new Ui();
        ui.message();

        // Create an ArrayList to store tasks
        //ArrayList<Task> myList = new ArrayList<>();
        ArrayList<Task> myList = Storage.loadTasks();

        if (myList == null) {
            myList = new ArrayList<>();
        }

        // Initialize Scanner for user input
        Scanner sc = new Scanner(System.in);
        ui.blank();
        boolean result = true;

        while(result) {
            // Read user input
            String userInput = sc.nextLine();

            Parser parser = new Parser(userInput, myList);
            result = parser.parseCommand();

            Storage.saveTasks(myList);

            if(!result) {
                ui.finalMessage();
            }
        }
        ui.blank();
    }

    public static void main(String[] args) {
        Storage storage = new Storage("./data/duke.txt");
        
        new Duke(storage);
    }
}

