package scribbles;

import scribbles.parser.CommandResponder;
import scribbles.parser.Parser;
import scribbles.storage.Storage;
import scribbles.tasklist.TaskList;
import scribbles.ui.Ui;

/**
 * This class implements the chatbot Scribbles.
 *
 * @author danielle
 */
public class Scribbles {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String filePath;
    private CommandResponder commandResponder;

    /**
     * Constructs a new Scribbles object with the specified file path.
     */
    public Scribbles(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
        commandResponder = new CommandResponder();
        taskList = new TaskList();
        storage = new Storage(this.filePath, taskList);
    }

    /**
     * Greets the user.
     *
     * @return Greeting message to the user when Scribbles is launched.
     */
    public String greet() {
        return ui.greet();
    }

    /**
     * Takes the input of user as command and executes the list of actions that should follow the given command.
     *
     * @param input The command input by user.
     * @return The response by Scribbles to the user input
     */
    public String getResponse(String input) {
        Parser parsedInput = new Parser(input);

        String command = parsedInput.getCommand();
        assert command != null: "command should not be null";

        return respondToCommand(parsedInput, command);
    }

    /**
     * Takes in the parsed input and command of input and returns the response.
     *
     * @param parsedInput Parsed input
     * @param command Command of input
     * @return Response to command
     */
    public String respondToCommand(Parser parsedInput, String command) {
        switch (command) {
        case "bye":
            return ui.printExitMessage();
        case "list":
            return commandResponder.respondToList(taskList);
        case "mark":
            return commandResponder.respondToMark(parsedInput, taskList, storage);
        case "unmark":
            return commandResponder.respondToUnmark(parsedInput, taskList, storage);
        case "todo":
            return commandResponder.respondToTodo(parsedInput, taskList, storage);
        case "deadline":
            return commandResponder.respondToDeadline(parsedInput, taskList, storage);
        case "event":
            return commandResponder.respondToEvent(parsedInput, taskList, storage);
        case "delete":
            return commandResponder.respondToDelete(parsedInput, taskList, storage);
        case "find":
            return commandResponder.respondToFind(parsedInput, taskList);
        case "sortBy":
            return commandResponder.respondToSortBy(parsedInput, taskList, storage);
        case "help":
        default:
            return ui.printOtherInputMessage();
        }
    }

}
