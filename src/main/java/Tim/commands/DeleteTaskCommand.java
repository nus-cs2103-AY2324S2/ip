package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;
import Tim.task.Task;

import java.nio.file.Path;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_INDEX;

public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    private final int taskNum;
    private final Path filePath;

    /**
     * Create a DeleteTaskCommand object to mark tasks
     * @param taskNum
     * @param filePath
     */
    public DeleteTaskCommand(int taskNum, Path filePath) {
        this.taskNum = taskNum;
        this.filePath = filePath;
    }

    /**
     * Delete task in the TaskList.
     * @param taskList TaskList containing all tasks
     * @return String message that task is deleted
     * @throws TimException
     */
    @Override
    public String execute(TaskList taskList) throws TimException {
        if (!taskList.isPresent(taskNum)) {
            throw new TimException(MESSAGE_INVALID_INDEX);
        }
        Task task = taskList.delete(taskNum);
        taskList.saveTasks(filePath);
        return GUI.deleteMsg(task, taskList);
    }
}
