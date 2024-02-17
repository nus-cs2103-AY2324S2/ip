package duke;

import duke.commands.*;
import duke.exceptions.DukeException;

/**
 * Represent a parser class that handles user commands.
 */
public class Parser {
    /**
     * An enum to store the specific commands which the user can use.
     */
    enum CommandWords {
        TODO,
        DEADLINE,
        EVENT,
        BYE,
        DELETE,
        LIST,
        MARK,
        UNMARK,
        FIND,
        REMINDER
    }

    /**
     * Read user input to return a command to be executed.
     *
     * @param input Input by the user.
     * @param ui Ui object to interact with user.
     * @param taskList TaskList object that records the task.
     * @param storage Storage object that saves and loads information.
     * @return Command A specific command to execute a certain action.
     */
    public static Command parse(String input, Ui ui, TaskList taskList, Storage storage) throws DukeException {
        try {
            String[] inputs = input.split(" ");
            CommandWords commandWord = CommandWords.valueOf(inputs[0].toUpperCase());
            switch (commandWord) {
            case BYE:
                return new GoodbyeCommand(ui, taskList, storage);
            case DELETE:
                return new DeleteCommand(ui, taskList, storage, Integer.parseInt(inputs[inputs.length - 1]));
            case LIST:
                return new PrintListCommand(ui, taskList, storage);
            case MARK:
                return new MarkDoneCommand(ui, taskList, storage, Integer.parseInt(inputs[inputs.length - 1]));
            case UNMARK:
                return new MarkUndoneCommand(ui, taskList, storage, Integer.parseInt(inputs[inputs.length - 1]));
            case TODO:
                return new CreateTodoCommand(ui, taskList, storage, input);
            case EVENT:
                return new CreateEventCommand(ui, taskList, storage, input);
            case DEADLINE:
                return new CreateDeadlineCommand(ui, taskList, storage, input);
            case FIND:
                return new FindCommand(ui, taskList, storage, input);
            case REMINDER:
                return new ReminderCommand(ui, taskList, storage);
            default:
                return new ErrorCommand(ui, taskList, storage, "Please use the correct command Word!>.<");
            }
        } catch (Exception e) {
            return new ErrorCommand(ui, taskList, storage, "Please use the correct command Word!>.<");
        }
    }
}
