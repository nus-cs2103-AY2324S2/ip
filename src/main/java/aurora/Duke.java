package aurora;

import aurora.command.Command;
import aurora.objects.DukeException;
import aurora.objects.Task;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke is the main class for the application that runs according to the commands given to it by the user.
 */
public class Duke {
    /**
     * Ui for the application.
     */
    private Ui ui;

    /**
     * TaskList for the application.
     */
    private TaskList taskList;

    /**
     * Storage for the application.
     */
    private Storage storage;

    /**
     * Parser for commands.
     */
    private Parser parser;

    /**
     * Constructor for the Duke class
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        try {
            ArrayList<Task> taskList = storage.loadTasks();
            this.taskList = new TaskList(taskList);
        } catch (IOException exception) {
            System.out.println("Error loading tasks from file: " + exception.getMessage());
            ArrayList<Task> emptyTaskList = new ArrayList<>();
            this.taskList = new TaskList(emptyTaskList);
        } catch (DukeException exception) {
            String exceptionMessage = exception.getExceptionMessage();
            this.ui.printALine();
            System.out.println(exceptionMessage);
            this.ui.printALine();
            ArrayList<Task> emptyTaskList = new ArrayList<>();
            this.taskList = new TaskList(emptyTaskList);
        }
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }

    public static void main(String[] args) {
        Duke aurora1 = new Duke();
        aurora1.exeAurora();
    }

    /**
     * Method for the execution of the application.
     */
    public void exeAurora() {
        this.ui.printOpeningMessage();
        executeLoop();
    }

    /**
     * Execution loop for commands.
     */
    public void executeLoop() {
        boolean isBye = false;
        while (!isBye) {
            try {
                String command = this.ui.nextCommand();
                Command commandObj = this.parser.parseCommand(command);
                commandObj.handle();
                isBye = commandObj.isBye();
            } catch (DukeException exception) {
                String exceptionMessage = exception.getExceptionMessage();
                this.ui.printALine();
                System.out.println(exceptionMessage);
                this.ui.printALine();
            }
        }
    }

    /**
     * Function to get a response from the program via a Gui
     *
     * @param command String input from the user.
     * @return String output of Aurora.
     */
    public String executeGui(String command) {
        String output = "Failed to get output from Parser.";
        try {
            Command outputCommand = this.parser.parseCommand(command);
            output = outputCommand.handleGui();
        } catch (DukeException exception) {
            output = exception.getExceptionMessage();
        }
        return output;
    }
}
