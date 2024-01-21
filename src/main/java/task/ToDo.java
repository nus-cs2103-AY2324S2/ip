package task;

public class ToDo extends Task {
    public ToDo(String msg) {
        super();
        this.msg = msg;
    }

    @Override
    public String toString() {
        String is_done = this.done ? "X" : " ";
        return String.format("[T][%s] %s", is_done, this.msg);
    }
}
