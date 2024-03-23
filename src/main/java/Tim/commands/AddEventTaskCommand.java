package Tim.commands;

import Tim.exception.TimException;
import Tim.gui.GUI;
import Tim.storage.TaskList;
import Tim.task.Event;

import java.nio.file.Path;

import static Tim.exception.ErrorMessages.MESSAGE_DUPLICATE_TASK;

public class AddEventTaskCommand extends Command{
    public static final String COMMAND_WORD = "event";

    private final Event task;
    private final Path filePath;

    /**
     * Creates an AddEventTaskCommand to add the specified task.
     * @param task
     * @param filePath
     */
    public AddEventTaskCommand(Event task, Path filePath) {
        this.task = task;
        this.filePath = filePath;
    }

    /**
     * Add Event task in to taskList.
     * @param taskList TaskList containing all tasks
     * @return String message that Event task is added
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
