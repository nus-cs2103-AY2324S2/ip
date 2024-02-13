import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private final String message;
    public EventCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String task = message.split(" ", 2)[1];
            String description = task.split(" /from ", 2)[0];
            String fromBy = task.split(" /from ", 2)[1];
            String from = fromBy.split(" /to ", 2)[0];
            String to = fromBy.split(" /to ", 2)[1];
            LocalDate fromDate = null;
            LocalDate toDate = null;
            try {
                fromDate = LocalDate.parse(from);
                toDate = LocalDate.parse(to);
            } catch (DateTimeParseException e) {
                ui.showDateFormat();
            }
            if (fromDate != null && toDate != null) {
                taskList.createEvent(description, fromDate, toDate);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showEventFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
