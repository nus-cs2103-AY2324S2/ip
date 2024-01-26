public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task curr = tasks.getTask(taskIndex);
        tasks.removeFromList(taskIndex);

        ui.printDivider();
        System.out.println("    Noted, I've removed this task: ");
        curr.taskPrinter();
        ui.printDivider();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
