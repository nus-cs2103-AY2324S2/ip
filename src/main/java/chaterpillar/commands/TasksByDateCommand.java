package chaterpillar.commands;

import chaterpillar.datetime.DateTime;
import chaterpillar.exceptions.ChaterpillarException;
import chaterpillar.tasks.TaskList;
import chaterpillar.ui.Ui;
import chaterpillar.storage.Storage;

public class TasksByDateCommand extends Command {
    DateTime date;
    public TasksByDateCommand(String date) throws ChaterpillarException {
        this.date = new DateTime(date);
    }
    public TasksByDateCommand(DateTime date) {
        this.date = date;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChaterpillarException {
        if (tasks.size() == 0) {
            throw new ChaterpillarException(
                    "Congrats! You have no tasks for today. :)");
        } else {
            ui.echo(String.format("For %s,kq", this.date));
            TaskList tasksNew = tasks.getTasksOnDate(this.date);
            new ListCommand(tasksNew).execute(tasks, ui, storage);
        }
    }
}
