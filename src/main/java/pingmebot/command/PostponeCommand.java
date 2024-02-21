package pingmebot.command;
import java.time.LocalDateTime;

import pingmebot.PingMeException;
import pingmebot.Storage;
import pingmebot.TaskList;
import pingmebot.UI;



/**
 * Represents a command for the user to postpone a task from the task list in the application.
 */
public class PostponeCommand extends Command {

    private int taskNumber;
    private String timingStart;
    private String timingEnd;
    private LocalDateTime by;

    /**
     * Creates an PostponeCommand object with the number from the task list, the new start
     * and end timing.
     *
     * @param taskNumber The number in the task list.
     * @param timingStart The new, postponed start timing for the events task.
     * @param timingEnd The new, postponed end timing for the events task.
     * @param by The new timing to complete the deadline task by.
     */
    public PostponeCommand(int taskNumber, String timingStart, String timingEnd, LocalDateTime by) {
        this.taskNumber = taskNumber;
        this.timingStart = timingStart;
        this.timingEnd = timingEnd;
        this.by = by;
    }


    /**
     * Executes the command to allow user to postpone a task, updating the
     * local storage and showing the message to the user.
     *
     * @param tasks The task list containing all the tasks which the user has added.
     * @param storage The local storage holding all the information about the entire task list.
     * @param ui The object responsible for displaying corresponding response to the user.
     * @throws PingMeException
     */
    @Override
    public void execute(TaskList tasks, Storage storage, UI ui) throws PingMeException {
        tasks.postponeTask(taskNumber, timingStart, timingEnd, by);
        tasks.updateTaskToStorage(storage);
        ui.postponeTaskText(taskNumber, tasks);
    }


}
