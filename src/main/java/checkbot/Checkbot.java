package checkbot;

import checkbot.command.Command;
import checkbot.exception.CheckbotException;
import checkbot.task.TodoList;

import java.util.Scanner;

/**
 * Represents the main class of the Checkbot program.
 */
public class Checkbot {
    public static final String TASK_FILE_DIR = "./tasks.txt";

    private final TodoList todoList;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructor for Checkbot.
     * 
     * @param filePath The file path to the file where the tasks are stored.
     */
    public Checkbot(String filePath) {
        this.storage = new Storage(filePath);
        this.todoList = this.storage.loadTasks();
        this.parser = new Parser();
        this.ui = new Ui();
    }

    /**
     * Main method to run the Checkbot program.
     * 
     * @param args Command line arguments, not used.
     */
    public static void main(String[] args) {
        new Checkbot(TASK_FILE_DIR).run();
    }

    /**
     * Runs the Checkbot program.
     */
    public void run() {
        ui.showWelcome();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            try {
                Command c = parser.parse(input);
                c.execute(todoList, storage, ui);
                if (c.isBye()) {
                    break;
                }
            } catch (CheckbotException e) {
                ui.showError(e);
            }
        }

        scanner.close();
    }
}
