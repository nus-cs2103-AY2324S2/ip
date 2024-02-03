package Duke.Command;

import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Storage;
import Duke.Ui;

public class AddDeadlineCommand extends Command {
    Task task;

    public AddDeadlineCommand(String description) {
        String[] components = description.split(" /by ", 2);
        String deadlineDetails = components[0];
        String byDate = components[1];
        String formattedByDate = formatDate(byDate);
        String formattedDescription = deadlineDetails + " (by: " + formattedByDate + ")";

        this.task = new Deadline(formattedDescription);
    }
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IllegalArgumentException {
        try {
            Task deadline = this.task;
            taskList.addTask(deadline);
            Storage.save(taskList);
            ui.displayNewTask(deadline, taskList);
        } catch (IllegalArgumentException e) {
            System.out.println("illegal argument exception");
        }
    }
}
