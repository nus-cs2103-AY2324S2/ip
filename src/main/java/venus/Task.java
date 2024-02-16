package venus;

/**
 * This is a Task class that is used to save tasks.
 *
 * @author peterXGD
 * @since 2024-02-05
 */

public class Task {
    private String item;
    private boolean mark;

    /**
     * Returns Task that saves given item string and mark as false.
     *
     * @param item String representation of task.
     */
    public Task(String item) {
        super();
        this.mark = false;
        this.item = item;
    }

    public void mark() {
        this.mark = true;
    }

    public void unmark() {
        this.mark = false;
    }

    public boolean getMark() {
        return mark;
    }

    public String getItem() {
        return item;
    }

    /**
     * Returns string of Task with mark indication in front.
     *
     * @return Task string.
     */
    @Override
    public String toString() {
        String x = (mark) ? "X" : " ";
        return ("[" + x + "] " + item);
    }
}
