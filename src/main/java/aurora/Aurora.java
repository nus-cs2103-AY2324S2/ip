package aurora;

import java.io.IOException;
import java.util.ArrayList;

import aurora.command.Command;
import aurora.objects.AuroraException;
import aurora.objects.Task;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;

/** Aurora is the main class for the backend API of the application. */
public class Aurora {
    /** Ui of the application. */
    private Ui ui;

    /** TaskList of the application. */
    private TaskList taskList;

    /** Storage of the application. */
    private Storage storage;

    /** Parser for commands. */
    private Parser parser;

    /** Constructs an Aurora object. */
    public Aurora() {
        this.ui = new Ui();
        this.storage = new Storage("./data/aurora.txt");
        try {
            ArrayList<Task> taskList = storage.loadTasks();
            this.taskList = new TaskList(taskList);
        } catch (IOException exception) {
            System.out.println("Error loading tasks from file: " + exception.getMessage());
            ArrayList<Task> emptyTaskList = new ArrayList<>();
            this.taskList = new TaskList(emptyTaskList);
        } catch (AuroraException exception) {
            String exceptionMessage = exception.getExceptionMessage();
            this.ui.printALine();
            System.out.println(exceptionMessage);
            this.ui.printALine();
            ArrayList<Task> emptyTaskList = new ArrayList<>();
            this.taskList = new TaskList(emptyTaskList);
        }
        this.parser = new Parser(this.taskList, this.storage, this.ui);
    }

    /** Runs the text-based UI form of the application. */
    public static void main(String[] args) {
        Aurora aurora1 = new Aurora();
        aurora1.exeAurora();
    }

    /** Runs the loop for the text-based UI form of the application. */
    public void exeAurora() {
        this.ui.printOpeningMessage();
        executeLoop();
    }

    /** Parses user commands and executes them for the text-based UI form of the application. */
    public void executeLoop() {
        boolean isBye = false;
        while (!isBye) {
            try {
                String command = this.ui.nextCommand();
                Command commandObj = this.parser.parseCommand(command);
                String resultString = commandObj.handle();
                this.ui.printALine();
                System.out.println(resultString);
                this.ui.printALine();
                isBye = commandObj.isByeCommand();
            } catch (AuroraException exception) {
                String exceptionMessage = exception.getExceptionMessage();
                this.ui.printALine();
                System.out.println(exceptionMessage);
                this.ui.printALine();
            }
        }
    }

    /**
     * Returns a String representing Aurora's output based on the user's command once the command is executed.
     * Executes user commands for the GUI version of the application.
     *
     * @param command Command from the user.
     * @return String representing Aurora's output based on the user's command once the command is executed.
     */
    public String executeGui(String command) {
        String output = "Failed to get output from Parser.";
        try {
            Command outputCommand = this.parser.parseCommand(command);
            output = outputCommand.handle();
        } catch (AuroraException exception) {
            output = exception.getExceptionMessage();
        }
        return output;
    }
}
