package dibo.command;

import java.time.LocalDate;

import dibo.Storage;
import dibo.TaskList;
import dibo.Ui;
import dibo.exception.DiboException;
import dibo.task.DoAfter;

/**
 * Class to handle the command which adds a do-after task.
 */
public class AddDoAfterCommand extends Command {
    private final String description;
    private final LocalDate after;

    /**
     * Constructs the AddDoAfterCommand class.
     *
     * @param description The description of the do-after task.
     * @param after The LocalDate object of the date the task needs to be done after.
     */
    public AddDoAfterCommand(String description, LocalDate after) {
        this.description = description;
        this.after = after;
    }

    /**
     * Runs the add do-after task command.
     *
     * @param taskList The TaskList object which contains all the tasks.
     * @param ui The Ui object which is responsible for printing the added message.
     * @param storage The Storage object which is responsible to save the changes into a text file.
     * @throws DiboException when an error occurs when trying to save the changes into a text file.
     */
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        DoAfter doAfter = new DoAfter(this.description, this.after);
        taskList.addTask(doAfter);
        ui.showAdded(doAfter.toString(), taskList.getSize());
        storage.saveData(taskList);
    }
}
