package meanduke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import meanduke.tasks.Event;
import meanduke.tasks.TaskList;

/**
 * This class represents a Command that adds an Event to a TaskList
 */
public class AddEventCommand extends AddCommand {
    private final String description;
    private final LocalDate fromDate;
    private final LocalTime fromTime;
    private final LocalDate toDate;
    private final LocalTime toTime;

    /**
     * Constructs a new AddEventCommand that adds a new Event to the specified TaskList
     *
     * @param taskList    The TaskList to add the new Event to.
     * @param description The description of the new Event to be added.
     * @param fromDate    The starting date of the event.
     * @param fromTime    The starting time of the event.
     * @param toDate      The ending date of the event.
     * @param toTime      The ending time of the event.
     */
    public AddEventCommand(TaskList taskList, String description,
                           LocalDate fromDate, LocalTime fromTime,
                           LocalDate toDate, LocalTime toTime) {
        super(taskList);
        this.description = description;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;

    }

    /**
     * Returns the string describing the proper format to add an Event Task
     */
    public static String getUsage() {
        return Command.getUsage() + " add event <description> /from <YYYY-MM-DD> [HH:MM] /to <YYYY-MM-DD> [HH:MM]";
    }

    @Override
    public String execute() {
        super.getTaskList().add(new Event(this.description, false, this.fromDate,
                this.fromTime, this.toDate, this.toTime));
        return "Added Event task: " + this.description
                + "\n(from: " + this.fromDate + (this.fromTime == null ? "" : " " + this.fromTime)
                + "\n   to: " + this.toDate + (this.toTime == null ? "" : " " + this.toTime) + ")";
    }
}
