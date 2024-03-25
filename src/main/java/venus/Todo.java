package venus;

/**
 * This is a Todo class that is used to save tasks.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Todo extends Task {
    public Todo(String s) {
        super(s);
    }

    /**
     * Returns todo task given a String and a mark to indicate completion.
     *
     * @param s String to represent content.
     * @param mark Mark to include in mark.
     */
    public Todo(String s, boolean mark) {
        super(s);
        if (mark) {
            this.mark();
        } else {
            this.unmark();
        }
    }

    /**
     * Returns string representation of this class with mark and task type.
     *
     * @return String of todo class.
     */
    @Override
    public String toString() {
        String x = (this.getMark()) ? "X" : " ";
        return ("[T]" + "[" + x + "] " + this.getItem());
    }
}
