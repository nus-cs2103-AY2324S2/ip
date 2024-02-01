package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.InvalidArgumentException;
import duke.exception.NoTaskFoundException;
import duke.task.Task;


/**
 * Represents the command to mark tasks as not done yet.
 */
public class UnmarkCommand extends Command {
    private String arguments;


    /**
     * Constructor for UnmarkCommand.
     *
     * @param arguments Argument for command.
     */
    public UnmarkCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Executes the unmark command.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @throws InvalidArgumentException If command has invalid or missing arguments
     * @throws NoTaskFoundException If the task to be unmarked can't be found.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, NoTaskFoundException {
        try {
            int taskId = Integer.parseInt(this.arguments);
            Task task = taskList.get(taskId);
            task.changeMark("UNMARK");
            ui.unmarkTask(task);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("UNMARK");

        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFoundException(this.arguments);
        }
    }
}
