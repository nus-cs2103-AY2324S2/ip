import java.util.ArrayList;
import java.util.Scanner;

/**
 * HERE IS THE SEQUENCE OF EVENTS:
 * In duke you can access TaskList which is the storage of the class
 * You can also access Storage to store TaskList
 * You can also access Ui to manage the user interactions
 *
 * first make a new ui
 * make a new storage to the filepath
 * then try to make a tasklist and take in the storage that is arleady inside
 */

/**
 * Duke - Main class that handles user interactions and task management.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui(null, null);
        tasks = new TaskList();
        storage = new Storage(filePath, tasks);

        try {
            ArrayList<Task> loadedTasks = storage.loadFile();
            tasks.getTasks().addAll(loadedTasks);
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.sayHi();
        storage.loadFile();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String input = scanner.nextLine();
            ui.handleInput(input);
        }


    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
