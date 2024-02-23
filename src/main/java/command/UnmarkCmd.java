package command;

public class UnmarkCmd extends Command {
    private int index;
    @Override
    public String execute() {
        tasks.unmark(index);
        response = ui.unmarkResponse(tasks.getTask(index).toString());
        return response;
    }

    public UnmarkCmd(int i) {
        this.index = i - 1;
    }
}
