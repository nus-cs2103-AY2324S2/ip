package command;

public class UnmarkCmd extends Command {
    private int index;
    public void execute() {
        tasks.unmark(index);
    }

    public UnmarkCmd(int i) {
        this.index = i;
    }
}
