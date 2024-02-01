public class AddCommand extends Command {
    private Task task;
    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.say("Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
