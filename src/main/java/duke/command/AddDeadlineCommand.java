package duke.command;

import duke.Ui;
import duke.Parser;
import duke.Storage;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;

import duke.exception.InvalidArgumentException;


public class AddDeadlineCommand extends Command {

    private String description;

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
