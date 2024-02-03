package dino.command;

import java.util.Scanner;

/** The main class for the Dino application. */
public class Dino {
    private static final String FILE_PATH = "./data/duke.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Enumeration representing the types of tasks in the Dino application.
     * Tasks can be of type TODO, DEADLINE, or EVENT.
     */
    public enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }

    /**
     * Constructs a new Dino instance with the specified file path.
     *
     * @param filePath The file path for storing tasks data.
     */
    public Dino(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = storage.loadTasksFromFile();

    }

    /** Runs the Dino application, handling user input and executing commands. */
    public void run() {
        ui.welcome();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks, ui, sc);

        while (true) {
            String command = sc.next();
            if (command.equals("bye")) {
                storage.saveTasksToFile(tasks.getTaskList());
                ui.goodbye();
                sc.close();
                break;
            }
            parser.parseCommand(command);
        }
    }

    /**
     * The main entry point of the Dino application.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        new Dino(FILE_PATH).run();
    }
}
