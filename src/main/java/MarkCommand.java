public class MarkCommand extends Command {

    private final int index;
    private final boolean markDone;


    public MarkCommand(int index, boolean markDone) {
        this.index = index;
        this.markDone = markDone;
    }

    public void execute(TaskList tasks,Ui ui, Storage storage) {
        Task task = tasks.get(this.index-1);
        if (markDone) {
            task.markDone();
            System.out.println(ui.getBot() + task.toString());
        } else {
            task.markUndone();
            System.out.println(ui.getBot() + task.toString());
        }
    }
}
