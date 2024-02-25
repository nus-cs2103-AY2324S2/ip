package ken.parser;

import static com.sun.javafx.application.PlatformImpl.exit;

import javafx.application.Platform;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import ken.exception.KenException;
import ken.response.Response;
import ken.task.TaskList;
import ken.storage.Storage;
import ken.ui.Ui;

import java.util.Scanner;

/**
 * The Parser Class handles the parsing of user commands in the Ken application.
 * It interprets user input and performs corresponding actions on the TaskList and Storage.
 */
public class Parser {

    private Scanner scanner;
    private TaskList taskList;
    private Storage storage;

    Ui ui = new Ui();

    /**
     * Constructs a new Parser with the specified TaskList and Storage.
     *
     * @param taskList the list of tasks to be manipulated by the parser
     * @param storage  the storage to save and load tasks
     */
    public Parser(TaskList taskList, Storage storage) {
        this.scanner = new Scanner(System.in);
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Parses user commands until the "bye" command is received.
     *
     * @throws KenException if there is an error during command parsing or task manipulation
     */

    public Response processUserCommands(String command) throws KenException {
        if (command.equals("list")) {
            return taskList.listTasks();
        } else if (command.startsWith("mark ")) {
            return taskList.markTask(Integer.parseInt(command.substring(5)));
        } else if (command.startsWith("unmark ")) {
            return taskList.unmarkTask(Integer.parseInt(command.substring(7)));
        } else if (command.startsWith("delete ")) {
            return taskList.deleteTask(Integer.parseInt(command.substring(7)));
        } else if (command.startsWith("todo ")) {
            return taskList.addTodoTask(command.substring(5));
        } else if (command.equals("help")) {
            return taskList.giveHelp();
        } else if (command.startsWith("deadline ")) {
            return taskList.addDeadlineTask(command.substring(9));
        } else if (command.startsWith("event ")) {
            return taskList.addEventTask(command.substring(6));
        } else if (command.equalsIgnoreCase("bye")) {
            storage.saveTasks(taskList.getTasks());
            exitWithDelay();
            return new Response(ui.byeMessage().getMessage());
        } else if (command.startsWith("seek ")) {
            return taskList.findTasks(command.substring(5));
        } else if (command.startsWith("seek")) {
            return new Response("OKAY I'LL GO HIDE!\n");
        } else if (command.startsWith("todo")) {
            return new Response("do what?\n");
        } else if (command.startsWith("deadline")) {
            return new Response("oh no! which line died?\n");
        } else if (command.startsWith("event")) {
            return new Response("where you going?\n");
        } else if (!command.equals("bye")) {
            return new Response("i'm Kenfused...\n");
        } else {
            return new Response("nothing");
        }
    }

    /**
     * Exits the application after introducing a delay.
     *
     * This method schedules the exit code to run on the JavaFX Application Thread
     * after a specified delay using a {@link PauseTransition}.
     *
     * Note: The delay is set to 1 seconds (1000 milliseconds) by default.
     */
    private void exitWithDelay() {
        Duration delayDuration = Duration.seconds(1);
        PauseTransition pause = new PauseTransition(delayDuration);
        pause.setOnFinished(event -> exit());
        pause.play();
    }

}
