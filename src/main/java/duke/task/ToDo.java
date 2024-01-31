package duke.task;

/**
 * A Todo Task.
 */
public class ToDo extends Task {
    /**
     * Todo task contains *what* to todo.
     *
     * @param todo what it is the user needs to do.
     */
    public ToDo(String todo) {
        super();
        this.event = todo;
    }

    @Override
    public String toString() {
        String is_done = this.done ? "X" : " ";
        return String.format("[T][%s] %s", is_done, this.event);
    }
}
