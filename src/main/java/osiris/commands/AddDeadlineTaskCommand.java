package osiris.commands;

import java.time.LocalDate;

import osiris.exceptions.OsirisException;
import osiris.task.Task;
import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the addition of a deadline task.
 */
public class AddDeadlineTaskCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "deadline";

    /** Name of the Task. */
    private final String taskName;

    /** Deadline date associated with the task. */
    private final LocalDate deadline;

    /**
     * Constructs an AddDeadlineTaskCommand object with the specified task name and deadline.
     *
     * @param taskName The name of the deadline task.
     * @param deadline The deadline of the task.
     */
    public AddDeadlineTaskCommand(String taskName, LocalDate deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    /**
     * Executes the command to add a deadline task.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return String notification if the command is executed successfully.
     * @throws OsirisException If an error occurs that has not been detected.
     */
    @Override
    public String execute(TaskManager taskManager, Ui userInterface) {
        Task newTask = taskManager.addDeadlineTask(taskName, false, deadline);
        String taskDetails = newTask.toString();
        int totalTasks = taskManager.getTotalTaskCount();
        return userInterface.displayDeadlineTaskAdditionNotification(taskDetails, totalTasks);
    }
}
