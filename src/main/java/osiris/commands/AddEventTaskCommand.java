package osiris.commands;

import java.time.LocalDateTime;

import osiris.task.TaskManager;
import osiris.ui.Ui;

/**
 * Command class representing the addition of an event task.
 */
public class AddEventTaskCommand extends Command {

    /** Keyword used to identify the command. */
    public static final String COMMAND = "event";

    /** Name of the Task. */
    private final String taskName;

    /** Start Date Time associated with the Task. */
    private final LocalDateTime startDateTime;

    /** End Date Time associated with the Task. */
    private final LocalDateTime endDateTime;

    /**
     * Constructs an AddEventTaskCommand object with the specified task name, start date and end date.
     *
     * @param taskName      The name of the event task.
     * @param startDateTime The start date and time of the event.
     * @param endDateTime   The end date and time of the event.
     */
    public AddEventTaskCommand(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.taskName = taskName;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Executes the command to add an event task.
     *
     * @param taskManager   The TaskManager instance.
     * @param userInterface The Ui instance.
     * @return true if the command is executed successfully, false otherwise.
     */
    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        boolean isSuccess = taskManager.addEventTask(taskName, false, startDateTime, endDateTime);

        if (isSuccess) {
            userInterface.displayEventTaskAdditionNotification(taskManager.getTask(
                    taskManager.getTotalTaskCount() - 1).toString(), taskManager.getTotalTaskCount());
        }
        return true;
    }
}
