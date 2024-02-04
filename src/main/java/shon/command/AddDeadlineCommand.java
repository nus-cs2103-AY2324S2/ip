package shon.command;

import java.time.format.DateTimeParseException;

import shon.TaskList;
import shon.Ui;

public class AddDeadlineCommand extends AddTaskCommand {
    private String by;

    public AddDeadlineCommand(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DateTimeParseException {
        ui.print(tasks.addDeadline(this.description, this.by));
    }
}
