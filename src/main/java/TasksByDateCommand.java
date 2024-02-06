public class TasksByDateCommand extends Command {
    DateTime date;
    public TasksByDateCommand(String date) {
        this.date = new DateTime(date);
    }
    public TasksByDateCommand(DateTime date) {
        this.date = date;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.echo(String.format("Here are the tasks on %s:", this.date));
        TaskList tasksNew = tasks.getTasksOnDate(this.date);
        new ListCommand(tasksNew).execute(tasks, ui, storage);
    }
}
