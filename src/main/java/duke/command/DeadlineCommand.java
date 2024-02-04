package duke.command;

import duke.task.Deadline;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/*
* The DeadlineCommand class is a subclass of Command and represents a command to add a Deadline task to the task list.
* It takes in a description String and a by as a LocalDate object.
 */
public class DeadlineCommand extends Command {
    protected String description;
    protected LocalDate by;

    /*
     * Constructs DeadlineCommand object with description and by as LocalDate objects.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        ui.showTaskAdded(deadline, tasks.getSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}