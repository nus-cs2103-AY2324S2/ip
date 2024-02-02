import java.time.LocalDate;

public class Deadline extends Task {
    private LocalDate time;
    Deadline(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
    }

    String getCommand() {
        return "deadline " + name + " /by " + time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time.format(Task.DATE_FORMATTER) + ")";
    }
}
