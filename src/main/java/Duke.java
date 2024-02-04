import java.util.ArrayList;
import java.util.Scanner;

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
