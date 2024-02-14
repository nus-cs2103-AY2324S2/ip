package duke;
import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.InvalidCommand;
import command.MarkCommand;
import command.ShowListCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import command.UpdateCommand;

/**
 * Parser deals with making sense of the user command.
 */
public class Parser {
    private static TaskList taskList;
    private static Ui ui;

    private static Storage storage;

    /**
     * The constructor of Parser.
     *
     * @param taskList The task list which the command will modify.
     * @param ui The ui to get the input of the user.
     */
    public Parser(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Returns a specific command that correspond to the Actions
     * found in the full user input.
     *
     * @param input The full user input.
     * @return A specific command that corresponds to the specific Actions.
     */
    public static Command parse(String input) {
        assert input != null : "There should be an input";
        String temp = input.split(" ")[0];
        Actions action = Actions.valueOf(temp.toUpperCase());
        assert action != null : "There should be a command, eg: hello";
        switch (action) {
        case BYE:
            return new ByeCommand(taskList, ui, storage);
        case LIST:
            return new ShowListCommand(taskList, ui, storage);
        case MARK:
            return new MarkCommand(taskList, ui, storage);
        case UNMARK:
            return new UnmarkCommand(taskList, ui, storage);
        case TODO:
            return new TodoCommand(taskList, ui, storage);
        case DEADLINE:
            return new DeadlineCommand(taskList, ui, storage);
        case EVENT:
            return new EventCommand(taskList, ui, storage);
        case DELETE:
            return new DeleteCommand(taskList, ui, storage);
        case FIND:
            return new FindCommand(taskList, ui, storage);
        case UPDATE:
            return new UpdateCommand(taskList, ui, storage);
        default:
            return new InvalidCommand(taskList, ui, storage);
        }
    }
}
