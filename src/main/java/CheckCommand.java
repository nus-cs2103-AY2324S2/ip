import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CheckCommand extends Command {
    private String inputs;

    public CheckCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(inputs, DateTimeFormatter.ofPattern("d/M/yyyy"));
            ui.sendReply(tasks.check(date));
        } catch (DateTimeParseException e1) {
            throw new DukeInvalidDateTimeFormat("d/M/yyyy");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
