package jerry.command;

import jerry.Event;
import jerry.TaskList;
import jerry.Ui;

import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {

    private final String commandDetails;

    public AddEventCommand(Ui ui, TaskList tasks, String commandDetails) {
        super(ui, tasks);
        assert tasks != null : "TaskList should not be null";
        this.commandDetails = commandDetails;

    }

    @Override
    public String execute() {
        try {
            if (!commandDetails.contains(" /from ") || !commandDetails.contains(" /to ")) {
                throw new CommandFormatException("Wrong format, please include start and end time "
                        + "\nUsage: event <task description> /from <start time> /to <end time>");
            }
            String[] eventParts = commandDetails.split(" /from ", 2);
            String[] fromTo = eventParts[1].split(" /to ", 2);
            String description = eventParts[0];
            String start = fromTo[0];
            String end = fromTo[1];
            Event event = new Event(description, start, end);
            if (!event.dateTimeIsNull()) {
                tasks.addTask(event);
                return ui.showAdded(event, tasks);
            } else {
                return ui.showWrong();
            }
        } catch (DateTimeParseException dateTimeException) {
            return "Invalid date/time format. Please use yyyy-MM-dd HH:mm.";
        } catch (CommandFormatException e) {
            return ui.showMessage(e.getMessage());
        }
    }
}
