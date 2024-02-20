package dibo.command;

import java.time.LocalDate;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.DoAfter;

/**
 * The AddDoAfterCommand class represents a command to add a do-after task to TaskList.
 */
public class AddDoAfterCommand extends Command {
    private final String description;
    private final LocalDate after;

    /**
     * Constructs a new AddDoAfterCommand object with the specified parameters.
     *
     * @param description The String description of the do-after task.
     * @param after The LocalDate object of the date the task needs to be done after.
     */
    public AddDoAfterCommand(String description, LocalDate after) {
        this.description = description;
        this.after = after;
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        DoAfter doAfter = new DoAfter(this.description, this.after);
        taskList.addTask(doAfter);
        ui.storeAddedMessage(doAfter.toString(), taskList.getSize());
        storage.saveData(taskList);
    }
}
