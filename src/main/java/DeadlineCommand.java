import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private final String deadlineDescription;
    private final LocalDateTime deadlineBy;
    public DeadlineCommand(String input) throws CommandException {
        input = input.trim();
        try {
            String[] tokens = input.split("/by");
            this.deadlineDescription = tokens[0].trim();
            if (this.deadlineDescription.isEmpty()) {
                throw new CommandException("Error. Unable to create task.\nFormat: "
                        + Deadline.CREATE_DEADLINE_FORMAT);
            }
            this.deadlineBy = LocalDateTime.parse(tokens[1].trim(), Task.INPUT_DATETIME_FORMAT);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new CommandException("Error. Unable to create task.\nFormat: "
                    + Deadline.CREATE_DEADLINE_FORMAT);
        }
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.addTask(new Deadline(this.deadlineDescription, deadlineBy)));
        storage.save(taskList.toDataString());
    }
}
