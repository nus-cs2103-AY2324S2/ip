package commands;

import datetime.DateTime;
import exceptions.ChaterpillarException;
import tasks.TaskList;
import ui.Ui;
import storage.Storage;

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
            ui.echo(String.format("Here are the tasks on %s:", this.date));
            TaskList tasksNew = tasks.getTasksOnDate(this.date);
            new ListCommand(tasksNew).execute(tasks, ui, storage);
        }
    }
}
