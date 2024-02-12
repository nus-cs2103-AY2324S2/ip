package duke.command;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command {

    String taskDesc;
    LocalDate fromDate;
    String fromTime;
    LocalDate toDate;
    String toTime;

    /**
     * Constructs an EventCommand with the specified input, description, start date, start time, end date, and end time.
     *
     * @param input     The input string associated with the command.
     * @param taskDesc  The description of the event task.
     * @param fromDate  The start date of the event task.
     * @param fromTime  The start time of the event task.
     * @param toDate    The end date of the event task.
     * @param toTime    The end time of the event task.
     */
    public EventCommand(String input, String taskDesc, LocalDate fromDate, String fromTime, LocalDate toDate, String toTime) {
        super(input);
        this.taskDesc = taskDesc;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();

        Task t = new Event(taskDesc, fromDate, fromTime, toDate, toTime);
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
