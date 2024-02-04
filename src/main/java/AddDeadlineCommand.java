import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends AddTaskCommand {
    private String by;

    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DateTimeParseException {
        ui.print(tasks.addDeadline(this.getDescription(), this.by));
    }
}
