package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Locale;


/**
 * Represents a task with a deadline in the task manager application.
 * A deadline task has a description and a due date.
 */
public class Deadline extends Task{
    private LocalDateTime dueDate;
    private static final String LINE = "\t______________________________________________________";
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH);

    /**
     * Constructs a Deadline task with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param dueDate The due date of the deadline task in the format "dd/MM/yyyy HHmm".
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(dueDate, inputFormat);
    }

    @Override
    public String print(){
        String str = "";
        try {
            String time = dueDate.format(outputFormat);
            str = "[D]" + super.print() + "(by: " + time + ")";
        } catch (DateTimeParseException e) {
            System.out.println(LINE);
            System.out.println("\t I think you haven't had enough vitamin C."
                    + "\n\t Your time format should be :"
                    + "\n\t\t { dd/MM/yyyy HHmm }"
                    + "\n\t I suggest you take some LEMONA.");
            System.out.println(LINE);
        }
        return str;
    }

    @Override
    public String getDescription() {
        String time = dueDate.format(outputFormat);
        String str = "[D]" + super.getDescription() + " " + time;
        return str;
    }

    @Override
    public String getTaskInfo() {
        String time = dueDate.format(outputFormat);
        return "[D] " + "/ [" + super.getStatusIcon()
                + "] / " + super.getTaskInfo() + "/ " + time;
    }
}
