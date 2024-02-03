package Duke.Command;

import Duke.Storage;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Ui;
public class AddEventCommand extends Command {
    Task task;

    public AddEventCommand(String description) {

        // extract and validate eventDetails, fromDate, and toDate
        String[] components = description.split(" /from | /to ", 3);
//        validateFormat(fragments, 3);
        String eventDetails = components[0];
        String fromDate = components[1];
        String toDate = components[2];

        // format the dates
        String formattedFromDate = formatDate(fromDate);
        String formattedToDate = formatDate(toDate);

        // instantiate event
        String formattedDescription = eventDetails + " (from: " + formattedFromDate + " to: " + formattedToDate + ")";
        this.task = new Event(formattedDescription);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IllegalArgumentException {
        try {
            Task event = this.task;
            taskList.addTask(event);
            Storage.save(taskList);
            ui.displayNewTask(event, taskList);
        } catch (IllegalArgumentException e) {
            System.out.println("illegal argument exception");
        }
    }


}
