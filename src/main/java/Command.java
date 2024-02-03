import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public static LocalDateTime parseDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
    }

    public boolean isExit() {
        return false;
    }
}
