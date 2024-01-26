public class AddCommand extends Command {

    private final Task task;
    public AddCommand(Task task) {
        this.task = task;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        System.out.println(ui.getBot() + "I have added " + task.toString() + " to your list of tasks. You have " + tasks.size() + " task(s) in list");
    }
}
