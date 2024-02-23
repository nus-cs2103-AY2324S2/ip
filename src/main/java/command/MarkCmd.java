package command;

public class MarkCmd extends Command{
    private int index;
    @Override
    public String execute() {
        tasks.mark(index);
        response = ui.markResponse(tasks.getTask(index).toString());
        return response;
    }

    public MarkCmd(int i) {
        this.index = i - 1;
    }
}
