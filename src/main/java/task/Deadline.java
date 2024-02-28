package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 *
 * This class is the representation of a Deadline task.
 * It extends from its parent class the Task class.
 */
public class Deadline extends Task implements Comparable<Deadline> {
    public static final String TASK_TYPE = "deadline";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private LocalDateTime endDateTime;

    /**
     * Creates a Deadline object.
     * Will call the super constructor with the task name variable.
     *
     * @param name The name of the task.
     * @param end The end of the event.
     */
    public Deadline(String name, LocalDateTime endDateTime) {
        super(name);
        this.endDateTime = endDateTime;
    }

    /**
     * Creates a Deadline object.
     * Will call the super constructor with the task name variable.
     *
     * @param name The name of the task.
     * @param end The end of the event.
     * @param mark To indicate if this is marked or not.
     */
    public Deadline(String name, String end, String mark) {
        super(name);
        this.endDateTime = LocalDateTime.parse(end.trim(), DATE_FORMAT);
        if (mark.equals("1")) {
            super.mark();
        }
    }

    /**
     * Returns a string representation of this Deadlines object for storage in DataWriter.
     * This includes the formating required for the reader to split and read it.
     *
     * @return a formatted string representation of this object.
     */
    @Override
    public String formatDataLine() {
        return "Deadlines|" + super.getCompleted() + "|"
                + super.getCommand() + "|" + this.endDateTime.format(DATE_FORMAT);
    }

    /**
     * Returns a string representation of this Deadlines.
     * This includes an indicator that this is a Deadlines object.
     *
     * @return a string representation of this Deadlines object.
     */
    @Override
    public String toString() {
        String s = "[D]" + super.toString() + "(by: " + this.endDateTime.format(DATE_FORMAT) + ")";
        return s;
    }

    /**
     * Executes the necessary action created from the parsed results.
     * In this case, will add the Deadline object to the TaskStorage of the application.
     *
     * @param taskStorage The storage space where the action will take place.
     */
    @Override
    public String execute(TaskStorage taskStorage) {
        taskStorage.addTask(this);
        String printMessage = "Gotchu! I've added this task:";
        printMessage += "\n" + this + "\n";
        printMessage += "You now have " + taskStorage.size() + " tasks in the list.";
        return printMessage;
    }

    /**
     * Overrides the compareTo function of Object, to compare the Deadline's
     * endDate followed by it's name if they have similar deadlines.
     *
     * @param o The Deadline it is comparing to.
     */
    @Override
    public int compareTo(Deadline o) {
        if (this.endDateTime.compareTo(o.endDateTime) == 0) {
            return super.getCommand().compareTo(o.getCommand());
        }
        return this.endDateTime.compareTo(o.endDateTime);
    }
}
