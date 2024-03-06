package paimon.command;

import paimon.ChatException;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.task.TodoTask;
import paimon.util.UiHandler;

/**
 * Represents a command to add a new to-do task to the task list. A to-do task consists
 * only of a description and does not have a specific time or date by which it must be completed.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the specified description for the new to-do task.
     *
     * @param description The textual description of the to-do task to be added.
     */
    public TodoCommand(String description) {
        this.description = description;

    }

    /**
     * Executes the to-do command by creating a new {@link TodoTask} with the provided description
     * and adding it to the task list. Notifies the user through the UI handler about the addition
     * of the new task and the updated number of tasks in the list.
     *
     * @param taskList The task list to which the new to-do task will be added.
     * @return A String to be displayed.
     * @throws ChatException If any errors occur during the execution of the command. In the current
     *                       implementation, this exception is unlikely to be thrown, but it is included to conform
     *                       to the abstract method's signature.
     */
    public String execute(TaskList taskList) throws ChatException {
        Task eventTask = new TodoTask(this.description);
        taskList.addTask(eventTask);
        return UiHandler.getAddTaskMessage(eventTask.getTask(), taskList.getSize());
    }

    /**
     * Indicates that executing this command does not signal the application to exit.
     *
     * @return false always, as adding a to-do task does not terminate the application.
     */
    public boolean isExit() {
        return false;
    }

}
