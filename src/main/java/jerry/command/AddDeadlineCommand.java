package jerry.command;

import jerry.Deadline;
import jerry.TaskList;
import jerry.Ui;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {

    private final String commandDetails;

    public AddDeadlineCommand(Ui ui, TaskList tasks, String commandDetails) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.commandDetails = commandDetails;
    }

    @Override
    public String execute() {
        try {
            if (!commandDetails.contains(" /by ")) {
                throw new CommandFormatException("Wrong format, please include deadline "
                        + "\nUsage: deadline <task description> /by <date/time>");
            }
            String[] deadlineParts = commandDetails.split(" /by ", 2);
            String description = deadlineParts[0];
            String by = deadlineParts[1];
            Deadline deadline = new Deadline(description, by);
            if (!deadline.byIsNull()) {
                tasks.addTask(deadline);
                return ui.showAdded(deadline, tasks);
            } else {
                return ui.showWrong();
            }
        } catch (DateTimeParseException e) {
            return ("Invalid date format. Please use yyyy-MM-dd.");
        } catch (CommandFormatException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
