package command;

public class MarkCmd extends Command{
    private int index;
    public void execute() {
        tasks.mark(index);
    }

    public MarkCmd(int i) {
        this.index = i;
    }
}
