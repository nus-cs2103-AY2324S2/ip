package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;
import Tim.task.Deadline;

import java.nio.file.Path;

import static Tim.exception.ErrorMessages.MESSAGE_DUPLICATE_TASK;

public class AddDeadlineTaskCommand extends Command{
    public static final String COMMAND_WORD = "deadline";

    private final Deadline task;
    private final Path filePath;

    /**
     * Creates an AddDeadlineTaskCommand to add the specified task.
     * @param task
     */
    public AddDeadlineTaskCommand(Deadline task, Path filePath) {
        this.task = task;
        this.filePath = filePath;
    }

    /**
     * Add Deadline task in to taskList.
     * @param taskList contains all tasks
     * @return String message that Deadline task is added
     * @throws TimException
     */
    @Override
    public String execute(TaskList taskList) throws TimException {
        if (taskList.hasDuplicates(task)) {
            return MESSAGE_DUPLICATE_TASK;
        }
        taskList.add(task);
        taskList.saveTasks(filePath);
        int taskNum = taskList.size();
        return GUI.showAddMsg(task, taskNum);

    }
}
