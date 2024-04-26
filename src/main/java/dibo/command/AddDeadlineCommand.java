package dibo.command;

import java.time.LocalDate;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.Deadline;


/**
 * The AddDeadlineCommand class represents a command to add a deadline task to TaskList.
 */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    /**
     * Constructs a new AddDeadlineCommand object with the specified parameters.
     *
     * @param description The String description of deadline task.
     * @param by The LocalDate object of the deadline of the task.
     */
    public AddDeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        Deadline deadline = new Deadline(this.description, this.by);
        taskList.addTask(deadline);
        ui.storeAddedMessage(deadline.toString(), taskList.getSize());
        storage.saveData(taskList);
    }
}
