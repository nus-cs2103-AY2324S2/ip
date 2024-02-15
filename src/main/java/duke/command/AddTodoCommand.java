package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.task.Todo;


/**
 * Represents the command to add todo task.
 */
public class AddTodoCommand extends Command {

    private String arguments;


    /**
     * Constructor for AddTodoCommand.
     *
     * @param arguments Argument of command.
     */
    public AddTodoCommand(String arguments) {
        this.arguments = arguments;
    }

    /**
     * Executes the todo command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     * @throws InvalidArgumentException If command has invalid or missing arguments.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentException {
        try {
            Task newTask = new Todo(this.arguments);
            taskList.add(newTask);
            return ui.showAddTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("TODO");
        }
    }
}
