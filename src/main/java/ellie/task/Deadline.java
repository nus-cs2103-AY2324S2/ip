package ellie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//ellie.task.Deadline: tasks that need to be done before a specific date/time
//e.g., submit report /by 11/10/2019 5pm
public class Deadline extends Task {
    
    private String dueDateString;
    private LocalDate dueDate;


    public Deadline(String description, String dueDateString) {
        super(description);
        this.dueDateString = dueDateString;

        // parse date if it is in the correct format.
        try {
            dueDate = LocalDate.parse(dueDateString);
        } catch (DateTimeParseException e) {
            dueDate = null;
        }
    }

    public Deadline(String description, int isDone, String dueDateString) {
        super(description, isDone);
        this.dueDateString = dueDateString;

        try {
            dueDate = LocalDate.parse(dueDateString);
        } catch (DateTimeParseException e) {
            dueDate = null;
        }
    }

    @Override
    public char getTaskType() {
        return 'D';
    }

    @Override
    public String listTaskString() {
        if (dueDate == null) {
            return "[D]" + super.listTaskString()
                    + " (by: " + this.dueDateString + ")";
        } else {
            return "[D]" + super.listTaskString()
                    + " (by: " + this.dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }

    public String getDueDate() {
        return this.dueDateString;
    }

}
