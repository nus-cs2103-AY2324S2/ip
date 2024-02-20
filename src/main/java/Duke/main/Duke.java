package duke.main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.commands.ByeCommand;
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
    private Storage storage;
    private UI ui;
    private TaskList tasks;

    /**
     * No argument constructor to be used in GUI
     */
    public Duke() {
        try {
            // Create directory if it does not exist
            Path path = Paths.get("./data");
            if (!Files.exists(path)) {
                Files.createDirectory(path);
            }
            // Create data file if it does not exist
            Path filePath = Paths.get("./data/duke.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            this.storage = new Storage(filePath.toString());
            this.ui = new UI();
            this.tasks = new TaskList(storage.readFile());
        } catch (IOException e) {
            System.out.println("An error occurred while reading file");
        }
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
            throw new UnknownInputException();
        }
        return result;
    }
    /**
     * Method to be called by GUI to respond to user message
     * @param userInput message keyed in by user
     * @return duke response to be displayed to user by gui
     */
    public String run(String userInput) {
        String result;
        try {
            String userInputWithoutSpace = userInput.trim();
            Command userCommand = parseCommand(userInputWithoutSpace);
            result = userCommand.executeForString(this.tasks, this.ui, this.storage);
        } catch (DukeException e) {
            result = this.ui.exceptionMsg(e);
        }
        return result;
    }
    public String getUserName() {
        return ui.getName();
    }
    public String getIntroMessage() {
        return ui.introMessage();
    }
}


