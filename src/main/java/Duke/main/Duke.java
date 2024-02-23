package duke.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.commands.ByeCommand;
import duke.commands.ClearCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.NameCommand;
import duke.commands.ToDoCommand;
import duke.commands.UnMarkCommand;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownInputException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.UI;

/**
 * Duke is a task management application that allows users to keep track of their tasks.
 * It provides functionalities such as adding, deleting, listing, and marking tasks as done.
 */
public class Duke {
    private static final String filepath = "./duke.txt";
    private Storage storage;
    private UI ui;
    private TaskList tasks;

    /**
     * Constructs a Duke object with the default file path.
     */
    public Duke() {
        createTextFileIfNotExist();
        this.storage = new Storage(filepath);
        this.ui = new UI();
        this.tasks = new TaskList(storage.readFile());
    }

    /**
     * Constructs a Duke object with the specified file path.
     * @param filePath The file path to store task data.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI();
        this.tasks = new TaskList(storage.readFile());
    }

    /**
     * Private method to create a text file if it doesn't exist.
     */
    private static void createTextFileIfNotExist() {
        try {
            Path filePath = Paths.get(filepath);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println("error loading file");
        }
    }

    /**
     * Parses the user input and returns the corresponding command object.
     * @param s The user input.
     * @return The command object.
     * @throws DukeException If the user input is unknown or invalid.
     */
    private static Command parseCommand(String s) throws DukeException {
        String[] commandAndDescription = s.split(" ", 2);
        String lowerCaseCommandWithoutSpace = commandAndDescription[0].trim().toLowerCase();
        Command result;

        switch (lowerCaseCommandWithoutSpace) {
        case "bye":
            result = new ByeCommand();
            break;
        case "list":
            result = new ListCommand();
            break;
        case "clear":
            result = new ClearCommand();
            break;
        case "mark":
            result = new MarkCommand(commandAndDescription);
            break;
        case "unmark":
            result = new UnMarkCommand(commandAndDescription);
            break;
        case "delete":
            result = new DeleteCommand(commandAndDescription);
            break;
        case "todo":
            result = new ToDoCommand(commandAndDescription);
            break;
        case "deadline":
            result = new DeadlineCommand(commandAndDescription);
            break;
        case "event":
            result = new EventCommand(commandAndDescription);
            break;
        case "date":
            result = new DateCommand(commandAndDescription);
            break;
        case "find":
            result = new FindCommand(commandAndDescription);
            break;
        case "name":
            result = new NameCommand(commandAndDescription);
            break;
        default:
            assert false : "Should not reach here";
            throw new UnknownInputException();
        }
        return result;
    }

    /**
     * Runs the Duke application with the given user input.
     * @param userInput The user input.
     * @return The response to be displayed to the user.
     */
    public String run(String userInput) {
        String result;
        try {
            String userInputWithoutSpace = userInput.trim();
            Command userCommand = parseCommand(userInputWithoutSpace);
            assert userCommand instanceof Command;
            result = userCommand.executeForString(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            result = this.ui.exceptionMsg(e);
        }
        return result;
    }
    /**
     * Returns the introduction message of the Duke application.
     * @return The introduction message.
     */
    public String getIntroMessage() {
        return ui.introMessage();
    }
}


