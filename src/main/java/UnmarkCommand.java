public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);
        curr.markAsUndone();

        ui.printDivider();
        System.out.println("    OK, I've marked this task as not done : ");
        curr.taskPrinter(taskIndex);
        ui.printDivider();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
