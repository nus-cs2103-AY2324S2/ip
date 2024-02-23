package kwuntalk.command;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;
import kwuntalk.exception.InvalidArgumentException;
import kwuntalk.exception.NoTaskFoundException;
import kwuntalk.task.Task;


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
     * Executes the unmark command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     * @throws InvalidArgumentException If command has invalid or missing arguments
     * @throws NoTaskFoundException If the task to be unmarked can't be found.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, NoTaskFoundException {
        try {
            int taskId = Integer.parseInt(this.arguments);
            Task task = taskList.get(taskId);
            task.changeMark("UNMARK");
            return ui.showUnmarkTask(task);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("UNMARK");

        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFoundException(this.arguments);
        }
    }
}
