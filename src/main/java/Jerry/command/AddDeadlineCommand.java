package Jerry.command;

import Jerry.Deadline;
import Jerry.TaskList;
import Jerry.Ui;

public class AddDeadlineCommand extends Command {

    private String commandDetails;

    public AddDeadlineCommand(Ui ui, TaskList tasks, String commandDetails) {
        super(ui, tasks);
        this.commandDetails = commandDetails;
    }

    @Override
    public void execute() {
        try {
            if (!commandDetails.contains(" /by ")) {
                throw new CommandFormatException("Wrong format, please include deadline \nUsage: deadline <task description> /by <date/time>");
            }
            String[] deadlineParts = commandDetails.split(" /by ", 2);
            String description = deadlineParts[0];
            String by = deadlineParts[1];
            Deadline deadline = new Deadline(description, by);
            if (!deadline.byIsNull()) {
                tasks.addTask(deadline);
                ui.showAdded(deadline, tasks);
            }
        } catch (CommandFormatException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
