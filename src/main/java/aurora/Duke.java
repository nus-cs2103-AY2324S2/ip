package aurora;

import aurora.command.ByeCommand;
import aurora.command.Command;
import aurora.command.DeadlineCommand;
import aurora.command.DeleteCommand;
import aurora.command.EventCommand;
import aurora.command.FindCommand;
import aurora.command.InvalidCommand;
import aurora.command.ListCommand;
import aurora.command.MarkCommand;
import aurora.command.TodoCommand;
import aurora.command.UnmarkCommand;
import aurora.objects.Deadline;
import aurora.objects.DukeException;
import aurora.objects.Event;
import aurora.objects.Task;
import aurora.objects.Todo;
import aurora.parser.Parser;
import aurora.storage.Storage;
import aurora.tasklist.TaskList;
import aurora.ui.Ui;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
    private Duke() {
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
        executionLoop();
    }

    /**
     * Execution loop for commands.
     */
    public void executionLoop() {
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
}
