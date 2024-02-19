package talkingjohn;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Parses user input and executes corresponding actions.
 */
public class Parser {

    /**
     * List of invalid inputs that do not trigger any action.
     */
    private final List<String> invalidInputs = Arrays.asList("todo", "deadline",
            "event", "mark", "unmark", "delete", "find");

    private final Ui ui;

    private final Storage storage;

    private final TaskList taskList;

    /**
     * Constructs a Parser object with the specified user interface, storage, and task list.
     *
     * @param ui       The user interface component.
     * @param storage  The storage component.
     * @param taskList The task list.
     */
    public Parser(Ui ui, Storage storage, TaskList taskList) {
        this.ui = ui;
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Handles user input and executes corresponding actions until the "bye" command is received.
     */
    public String handleInput(String input) {
        assert input != null : "user input cannot be null";
        if (Objects.equals(input, "bye")) {
            storage.saveTasksToFile();
            return ui.goodbye();
        }

        if (invalidInputs.contains(input)) {
            return ui.emptyInput(input);
        }
        return taskList.action(input);
    }
}