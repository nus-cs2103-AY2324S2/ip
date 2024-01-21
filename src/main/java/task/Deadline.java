package task;

public class Deadline extends Task {

    private final String dueBy;
    public Deadline(String msg) {
        super();
        String[] res = msg.split(" /by ");
        this.msg = res[0];
        this.dueBy = res[1];
    }

    @Override
    public String toString() {
        String is_done = this.done ? "X" : " ";
        return String.format("[D][%s] %s (by: %s)", is_done, this.msg, this.dueBy);
    }

}
