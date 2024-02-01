public class AddTaskCommand extends Command {
    private final Task task;

    AddTaskCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        ui.println("Got it. I've added this task:");
        ui.println(task);
        tasks.addTask(task);
        ui.println(String.format("Now you have %d tasks in the list.", tasks.getCount()));
        storage.writeToFile(tasks);
    }
}
