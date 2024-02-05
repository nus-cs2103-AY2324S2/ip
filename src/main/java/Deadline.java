import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    private LocalDateTime dueDate;
    private DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(dueDate, inputFormat);
    }

    @Override
    public String print() throws DateTimeParseException{
        String time = dueDate.format(outputFormat);
        String str = "[D]" + super.print() + "(by: " + time + ")";
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
                + "] / " + super.getTaskInfo() + " / " + time;
    }
}
