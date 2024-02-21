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

    private String taskDesc;
    private LocalDate fromDate;
    private String fromTime;
    private LocalDate toDate;
    private String toTime;
    private String tag;
    private boolean isTagged;

    /**
     * Constructs an EventCommand with the specified input, description, start date, start time, end date, and end time.
     *
     * @param input     The input string associated with the command.
     * @param taskDesc  The description of the event task.
     * @param fromDate  The start date of the event task.
     * @param fromTime  The start time of the event task.
     * @param toDate    The end date of the event task.
     * @param toTime    The end time of the event task.
     * @param tag       The tag of the event task.
     */
    public EventCommand(String input, String taskDesc, LocalDate fromDate, String fromTime, LocalDate toDate, String toTime, String tag) {
        super(input);
        this.taskDesc = taskDesc;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.tag = tag;
        this.isTagged = true;
    }

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
        this.tag = "null";
        this.isTagged = false;
    }


    @Override
    public String executeAndReply(Ui ui, TaskList tasks, Storage storage) {
        int counter = tasks.getCounter();

        Task t;
        if (isTagged) {
            t = new Event(taskDesc, tag, fromDate, fromTime, toDate, toTime);
        } else {
            t = new Event(taskDesc, fromDate, fromTime, toDate, toTime);
        }
        tasks.addTask(t);

        return ui.showAddTaskMessage(t, counter);
    }
}
