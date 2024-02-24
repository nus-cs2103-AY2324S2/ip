package kwuntalk.command;

import java.io.IOException;

import kwuntalk.Storage;
import kwuntalk.TaskList;
import kwuntalk.Ui;
import kwuntalk.exception.DuplicateTaskException;
import kwuntalk.exception.InvalidArgumentException;
import kwuntalk.exception.TasksFileException;
import kwuntalk.task.Task;
import kwuntalk.task.Todo;


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
     * @throws DuplicateTaskException If there exist a task with the same name.
     * @throws TasksFileException If tasks file can't be written to for saving of data into storage.
     */
    @Override
    public String generateReply(TaskList taskList, Ui ui, Storage storage)
            throws InvalidArgumentException, DuplicateTaskException, TasksFileException {
        try {
            Task newTask = new Todo(this.arguments);
            taskList.add(newTask);
            storage.saveTasksFile(taskList);
            return ui.showAddTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("TODO");

        } catch (IOException e) {
            throw new TasksFileException();
        }
    }
}
