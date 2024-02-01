import java.io.IOException;

public class EditCommand extends Command{
    protected boolean isMark;
    protected int index;

    public EditCommand(Boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException{
        try {
            Task task = tasks.get(index);
            if (isMark) {
                task.mark();
                ui.mark(task);
            } else {
                task.unmark();
                ui.mark(task);
            }
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new ThamesException("Task list index is out of bounds!");
        } catch (IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        }

    }
    @Override
    public boolean isExit() {
        return false;
    }
}
