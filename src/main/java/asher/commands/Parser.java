package asher.commands;

import asher.BotException;
import asher.tasks.TaskList;
import asher.ui.Ui;

/**
 * The Parser class handles the parsing of user commands and return the corresponding actions for each command.
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;

    /**
     * Constructs a Parser object with the given Ui and the TaskList.
     *
     * @param ui The Ui object to interact with user.
     * @param taskList The TaskList object to manage tasks.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Parses the user input command and executes the actions for a command.
     *
     * @param input The user input command.
     * @throws BotException BotException is thrown if there is an invalid command.
     */
    public static Command parseCommand(String input) throws BotException {
        assert input != null : "Input should not be null!";

        String[] word = input.split(" ");
        assert word.length > 1 : "Input should contain at least two words!";

        String inputType = word[0];
        assert inputType != null : "Input type should not be null!";

        switch (inputType) {
        case "bye":
            return new ExitCommand("bye");
        case "list":
            return new ListCommand("list");
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "todo":
            return new ToDoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "sort":
            return new SortCommand("sort");
        default:
            throw new BotException("Invalid Command!");
        }
    }
}
