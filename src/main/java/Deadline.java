import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Deadline extends Task {
    private final LocalDateTime deadline;

    Deadline(String name, String deadline) throws DukeException {
        super(name);
        this.deadline = Task.parse(deadline);
    }

    @Override
    String taskToLine() {
        return "D | " + super.taskToLine() + " | " + deadline.format(DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Override
    boolean matchDate(LocalDate localDate) {
        return deadline.toLocalDate().equals(localDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s) ", deadline
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
