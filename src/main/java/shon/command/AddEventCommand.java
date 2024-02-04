package shon.command;

import java.time.format.DateTimeParseException;

import shon.TaskList;
import shon.Ui;

public class AddEventCommand extends AddTaskCommand{
    private String from;
    private String to;

    public AddEventCommand(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws DateTimeParseException {
        ui.print(tasks.addEvent(this.description, this.from, this.to));
    }
}
