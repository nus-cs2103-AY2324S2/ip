package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;
import Tim.task.Task;

import java.nio.file.Path;

import static Tim.exception.ErrorMessages.MESSAGE_INVALID_INDEX;

public class UnmarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    private final int taskNum;
    private final Path filePath;

    /**
     * Create a MarkTaskCommand object to mark tasks
     * @param taskNum
     * @param filePath
     */
    public UnmarkTaskCommand(int taskNum, Path filePath) {
        this.taskNum = taskNum;
        this.filePath = filePath;
    }

    /**
     * Unmark task in the TaskList.
     * @param taskList TaskList containing all tasks
     * @return String message that task is unmarked
     * @throws TimException
     */
    @Override
    public String execute(TaskList taskList) throws TimException {
        if (!taskList.isPresent(taskNum)) {
            throw new TimException(MESSAGE_INVALID_INDEX);
        }
        boolean toMark = false;
        Task task = taskList.mark(taskNum, toMark);
        taskList.saveTasks(filePath);
        return GUI.unMarkedMsg(task);
    }
}
