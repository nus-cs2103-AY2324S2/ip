package Duke.Command;

import Duke.Exception.InvalidArgumentException;
import Duke.Storage;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui;
public class AddEventCommand extends Command {
    String description;

    public AddEventCommand(String description) {
        this.description = description;

    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            String[] components = this.description.split(" /from | /to ", 3);
            String eventDetails = components[0];
            String fromDate = components[1];
            String toDate = components[2];

            // format the dates
            String formattedFromDate = formatDate(fromDate);
            String formattedToDate = formatDate(toDate);

            // instantiate event
            String formattedDescription = eventDetails + " (from: " + formattedFromDate + " to: " + formattedToDate + ")";
            Task event = new Event(formattedDescription);

            taskList.addTask(event);
            Storage.save(taskList);
            ui.displayNewTask(event, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("EVENT");
        }

    }


}
