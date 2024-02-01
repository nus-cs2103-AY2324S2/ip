public class EditCommand extends Command{
    protected boolean isMark;
    protected int index;

    public EditCommand(Boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(index);
        if (isMark) {
            task.mark();
            ui.mark(task);
        } else {
            task.unmark();
            ui.mark(task);
        }
        try {
            storage.save(tasks);
        } catch (Exception e) {}
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
