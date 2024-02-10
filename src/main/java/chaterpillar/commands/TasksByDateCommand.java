package chaterpillar.commands;

import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

/**
 * <code>Command</code> to list all tasks on a specified date.
 */
public class TasksByDateCommand extends Command {
    DateTime date;

    /**
     * Constructor for when class is invoked with a String for the date specified.
     * @param date displays the list of tasks on this date.
     * @throws ChaterpillarException if date string provided is invalid
     */
    public TasksByDateCommand(String date) throws ChaterpillarException {
        this.date = new DateTime(date);
    }

    /**
     * Overloaded Constructor for when class is invoked with a DateTime
     * object for the date specified.
     * @param date displays the list of tasks on this date.
     */
    public TasksByDateCommand(DateTime date) {
        this.date = date;
    }

    /**
     * Lists the tasks that fall on or within the specified date.
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        ui.echo(String.format("For %s,", this.date));
        if (tasks.size() == 0) {
            ui.echo("Congrats! You have no tasks for today. :)");
        } else {
            TaskList tasksNew = tasks.getTasksOnDate(this.date);
            new ListCommand(tasksNew).execute(tasks, ui, storage);
        }
    }
}
