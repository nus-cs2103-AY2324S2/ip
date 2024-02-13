import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private final String message;
    public DeadlineCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String task = message.split(" ", 2)[1];
            String description = task.split(" /by ", 2)[0];
            String by = task.split(" /by ", 2)[1];
            LocalDate byDate = null;
            try {
                byDate = LocalDate.parse(by);
            } catch (DateTimeParseException e) {
                ui.showDateFormat();
            }
            if (byDate != null) {
                taskList.createDeadline(description, byDate);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showDeadlineFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
