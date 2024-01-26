public class TodoCommand extends Command{
    private String description;
    private boolean isDone;

    public TodoCommand(String description) {
        this.description = description;
        this.isDone = false;
    }

    public TodoCommand(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new ToDo(description, isDone);
        tasks.addToList(task);
        ui.addedTaskPrinter(task, tasks.taskNum());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
