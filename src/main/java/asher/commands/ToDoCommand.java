package asher.commands;

import asher.BotException;
import asher.tasks.TaskList;
import asher.tasks.Todo;
import asher.ui.Ui;

/**
 * Represents a command to create a todo task.
 */
public class ToDoCommand extends Command {

    /**
     * Constructs a ToDo Command object with the given input string.
     *
     * @param input The input string for the ToDo Command.
     */
    public ToDoCommand(String input) {
        super(input);
    }

    /**
     * Parses the input string to create a ToDo object.
     *
     * @return The created ToDo object.
     * @throws BotException BotException is thrown if description is invalid or if the input format is wrong.
     */
    public Todo createToDoCommand() throws BotException {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            throw new BotException("Todo description cannot be empty!");
        }
        String description = parts[1].trim();

        return new Todo(description);
    }

    /**
     * Executes the ToDo Command.
     *
     * @param ui The UI object to interact with the user.
     * @param taskList The list of tasks.
     * @param storage The storage object for reading and writing tasks from a file.
     * @return The string input for ToDo Command.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        try {
            Todo todo = createToDoCommand();
            taskList.addTask(todo);
            String addTaskMessage = ui.showAddTaskMessage(todo);
            int totalTask = taskList.getSize();
            String numberOfTaskMessage = ui.showNumberOfTaskInListMessage(totalTask);

            return addTaskMessage + "\n" + numberOfTaskMessage;
        } catch (BotException e) {
            return ui.showErrorMessage("Error: " + e.getMessage());
        }
    }
}
