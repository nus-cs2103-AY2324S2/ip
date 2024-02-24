import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static String TASK_TYPE = "[D] ";
    private static String DEADLINE = "deadline";
    private LocalDate date;
    static String COMPLETED_MESSAGE_END = " is complete!";
    static String INCOMPLETE_MESSAGE_END = " by ";
    
    // Deadline class needs to be able to parse dates
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    public Deadline(String name, String isDone, LocalDate date) {
        super(name, isDone);
        this.date = date;
    }    

    @Override
    public String checkStatus() {
        if (this.checkDone()) {
            return TASK_TYPE + this.getName() + COMPLETED_MESSAGE_END;
        } else {
            return TASK_TYPE + this.getName() + INCOMPLETE_MESSAGE_END + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
    }

    public String getAttributes() {
        String isDoneString = "";
        if (this.isDone) {
            isDoneString = "true";
        } else {
            isDoneString = "false";
        }

        return DEADLINE + " " + isDoneString + " " + this.date + " " + this.name;
    }
}