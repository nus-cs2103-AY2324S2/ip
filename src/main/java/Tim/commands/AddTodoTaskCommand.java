package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;
import Tim.task.ToDo;

import java.nio.file.Path;

import static Tim.exception.ErrorMessages.MESSAGE_DUPLICATE_TASK;

/**
 * Adds a todo Task to the List.
 */
public class AddTodoTaskCommand extends Command{
    public static final String COMMAND_WORD = "todo";

    private final ToDo task;
    private final Path filePath;

    /**
     * Creates an AddTodoTaskCommand to add the specified task.
     * @param task
     */
    public AddTodoTaskCommand(ToDo task, Path filePath) {
        this.task = task;
        this.filePath = filePath;
    }

    /**
     * Add Todo task in to taskList.
     * @param taskList TaskList containing all tasks
     * @return String message that Todo task is added
     * @throws TimException
     */
    @Override
    public String execute(TaskList taskList) throws TimException{
        if (taskList.hasDuplicates(task)) {
            return MESSAGE_DUPLICATE_TASK;
        }
        taskList.add(task);
        taskList.saveTasks(filePath);
        int taskNum = taskList.size();
        return GUI.showAddMsg(task, taskNum);

    }
}
