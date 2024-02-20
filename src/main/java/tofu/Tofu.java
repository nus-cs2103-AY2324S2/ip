package tofu;

import tofu.task.Task;
import tofu.task.TaskList;
import tofu.ui.Ui;
import tofu.command.Command;

import java.util.ArrayList;

/**
 * Tofu program is an application that records tasks
 * using CLI as the main source of input.
 * <p>
 * Tofu targets an audience with a fondness of cats
 * and a preference for CLI.
 */
public class Tofu {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new Tofu object. This constructor initializes the Ui, Storage, and TaskList objects.
     * It uses the default file path to load any existing tasks from storage.
     */
    public Tofu() {
        ui = new Ui();
        storage = new Storage("./data/tofu.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (TofuException ex) {
            System.out.println(ex.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Constructs a new Tofu object. This constructor initializes the Ui, Storage, and TaskList objects.
     * It uses the provided file path to load any existing tasks from storage.
     * If no such file is found, it creates a file in the filePath and load an empty TaskList.
     *
     * @param filePath The path of the file where tasks are saved and loaded from.
     */
    public Tofu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TofuException ex) {
            System.out.println(ex.getMessage());
            tasks = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Processes the user's input and returns the appropriate response from Tofu.
     *
     * @param input The user's input string to be processed.
     * @return The response string from executing the command or the error message.
     * @throws TofuException If an error occurs while parsing or executing the command.
     */
    public String getResponse(String input) {
        String output;
        try {
            Command c = Parser.parseToCommand(input);
            output = c.execute(tasks, ui);
            storage.save(tasks);
        } catch (TofuException ex) {
            output = ex.getMessage();
        }
        return output;
    }


    /**
     * Runs the Tofu chatbot without a GUI. The program closes after an exit command is received.
     */
    public void run() {
        System.out.println(ui.welcomeMessage());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseToCommand(fullCommand);
                System.out.println(c.execute(tasks, ui));
                isExit = c.isExit();
                storage.save(tasks);
            } catch (TofuException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println(ui.close());
    }

    public static void main(String[] args) {
        new Tofu("./data/tofu.txt").run();
    }
}
