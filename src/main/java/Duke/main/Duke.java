package duke.main;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DateCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
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
    private final Storage storage;
    private final UI ui;
    private final TaskList tasks;

    /**
     * Constructs a Duke object with the specified file path.
     * @param filePath The file path to store task data.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new UI();
        this.tasks = new TaskList(storage.readFile());
    }

    private static Command parseCommand(String s) throws IllegalArgumentException {
        String[] words = s.split(" ", 2);
        Command result;
        switch (words[0]) {
        case "bye":
            result = new ByeCommand();
            break;
        case "list":
            result = new ListCommand();
            break;
        case "mark":
            result = new MarkCommand(words);
            break;
        case "unmark":
            result = new UnMarkCommand(words);
            break;
        case "delete":
            result = new DeleteCommand(words);
            break;
        case "todo":
            result = new ToDoCommand(words);
            break;
        case "deadline":
            result = new DeadlineCommand(words);
            break;
        case "event":
            result = new EventCommand(words);
            break;
        case "date":
            result = new DateCommand(words);
            break;
        case "find":
            result = new FindCommand(words);
            break;
        default:
            throw new IllegalArgumentException();
        }
        return result;
    }

    /**
     * Runs the Duke application, displaying the intro message and handling user commands.
     */
    public void run() {
        ui.displayIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String echoInput = this.ui.receiveNextLine();
                Command token = parseCommand(echoInput.trim());
                isExit = token.execute(tasks, ui, storage);
            } catch (IllegalArgumentException e) {
                this.ui.displayExceptionMsg(new UnknownInputException());
            } catch (DukeException e) {
                this.ui.displayExceptionMsg(e);
            }
        }
    }
}


