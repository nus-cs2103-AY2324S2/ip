package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.NoTaskFoundException;
import duke.task.Task;


/**
 * Represents the command to delete tasks.
 */
public class DeleteCommand extends Command {
    private String arguments;


    /**
     * Constructor for DeleteCommand.
     *
     * @param arguments Argument of command.
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Executes the delete command.
     *
      * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @throws InvalidArgumentException If command has invalid or missing arguments
     * @throws NoTaskFoundException If the task to be deleted can't be found.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, NoTaskFoundException {
        try {
            int taskId = Integer.parseInt(this.arguments);
            Task task = taskList.get(taskId);
            taskList.remove(taskId);
            ui.deleteTask(task, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("DELETE");

        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFoundException(this.arguments);
        }
    }
}
