package Duke.Command;

import Duke.Exception.InvalidArgumentException;
import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.Task.TaskList;
import Duke.Storage;
import Duke.Ui;
import Duke.Parser;
public class AddDeadlineCommand extends Command {
    String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidArgumentException {
        try {
            String[] components = this.description.split(" /by ", 2);
            String deadlineDetails = components[0];
            String byDate = components[1];

            String formattedByDate = Parser.formatDate(byDate);
            String formattedDescription = deadlineDetails + " (by: " + formattedByDate + ")";
            Task deadline = new Deadline(formattedDescription);

            taskList.addTask(deadline);
            Storage.save(taskList);
            ui.displayNewTask(deadline, taskList);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("DEADLINE");
        }
    }
}
