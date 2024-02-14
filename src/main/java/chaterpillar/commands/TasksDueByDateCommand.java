package chaterpillar.commands;

import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.storage.Storage;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;

/**
 * <code>Command</code> to list all tasks on a specified date.
 */
public class TasksDueByDateCommand extends Command {
    private final DateTime date;

    /**
     * Constructor for when class is invoked with a String for the date specified.
     *
     * @param date displays the list of tasks on this date.
     * @throws ChaterpillarException if date string provided is invalid
     */
    public TasksDueByDateCommand(String date) throws ChaterpillarException {
        this.date = new DateTime(date);
    }

    /**
     * Overloaded Constructor for when class is invoked with a DateTime
     * object for the date specified.
     *
     * @param date displays the list of tasks on this date.
     */
    public TasksDueByDateCommand(DateTime date) {
        this.date = date;
    }

    /**
     * Lists the tasks that fall on or within the specified date.
     *
     * @param tasks the list of tasks.
     * @param ui object that handles the UI of this application.
     * @param storage object that is used for storage.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        TaskList tasksNew = tasks.getTasksOnDate(this.date);
        String output = String.format("For %s,\n", this.date);
        if (tasksNew.size() == 0) {
            output += "Congrats! You have no tasks for today. :)";
        } else {
            return output + new ListCommand(tasksNew).execute(tasks, ui, storage);
        }
        ui.echo(output);
        return output;
    }
}
