package kwuntalk.command;

import java.io.IOException;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;
import kwuntalk.exception.InvalidArgumentException;
import kwuntalk.exception.NoTaskFoundException;
import kwuntalk.exception.TasksFileException;
import kwuntalk.task.Task;


/**
 * Represents the command to mark tasks as done.
 */
public class MarkCommand extends Command {
    private String arguments;


    /**
     * Constructor for MarkCommand.
     *
     * @param arguments Argument for command
     */
    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }


    /**
     * Executes the mark command and generates the response.
     *
     * @param taskList List of tasks.
     * @param ui User Interface of chatbot.
     * @param storage Storage that stores data.
     * @return The reply to the user's input.
     * @throws InvalidArgumentException If command has invalid or missing arguments
     * @throws NoTaskFoundException If the task to be marked can't be found.
     * @throws TasksFileException If tasks file can't be written to for saving of data into storage.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, NoTaskFoundException, TasksFileException {
        try {
            int taskId = Integer.parseInt(this.arguments);
            Task task = taskList.get(taskId);
            task.changeMark("MARK");
            storage.saveTasksFile(taskList);
            return ui.showMarkTask(task);

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("MARK");

        } catch (IndexOutOfBoundsException e) {
            throw new NoTaskFoundException(this.arguments);

        } catch (IOException e) {
            throw new TasksFileException();
        }
    }
}
