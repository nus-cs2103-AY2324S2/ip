package duke.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event which is a type of Task
 */
public class Events extends Task{

    /** The start date and time of the Event */
    private LocalDateTime from;
    /** The end date and time of the Event */
    private LocalDateTime to;

    /**
     * Constructs a Event object with the specified name, the start and end date of the Event and mark whether it is
     * done.
     * @param name The name of the Event
     * @param from The start date and time of the Event
     * @param to The end date and time of the Event
     * @param status The status of the Event
     */
    public Events(String name, LocalDateTime from, LocalDateTime to, Boolean status) {
        super(name, status);
        this.from = from;
        this.to = to;

    }

    @Override
    public String happenOn(LocalDate date) {
        if ((date.isAfter(from.toLocalDate()) && date.isBefore(to.toLocalDate()))
                || date.isEqual(from.toLocalDate()) || date.isEqual(to.toLocalDate())) {
            return taskInfo();
        }
        return "";
    }

    /**
     * @InheritDoc Includes task type Events, from date and to date to string.
     */
    @Override
    public String saveOutput() {
        return "E " + super.saveOutput() + String.format(" | %s/%s", from, to);
    }

    /**
     * @InheritDoc Includes task type Events, from date and to date to string.
     */
    @Override
    public String taskInfo() {
        String output = "";
        output += "[E]";
        output += super.taskInfo();
        return output + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "hrs to: "
                + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm")) + "hrs)" + System.lineSeparator();
    }
}
