import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean isDone, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public void taskPrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");
        String result = "    " + "[D][ ]" + " " + description + "(by: " + deadline.format(output) + ")";
        System.out.println(result);
    }

    @Override
    public void taskPrinter(int index) {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd MMMM yyyy hhmm a");
        String result = "    " + (index+1) + ".[D]" + getStatusIcon() + " " + description  + "(by: " + deadline.format(output) + ")";
        System.out.println(result);
    }

    @Override
    public String storagePrinter() {
        DateTimeFormatter output = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return "D" + "|isdone" + (isDone ? 1 : 0) + "|desc" + description + "|by" + deadline.format(output);
    }
}
