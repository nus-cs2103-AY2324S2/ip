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
     * Executes the todo command.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @throws InvalidArgumentException If command has invalid or missing arguments.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentException {
        try {
            Task newTask = new Todo(this.arguments);
            taskList.add(newTask);
            ui.addTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("TODO");
        }
    }
}
