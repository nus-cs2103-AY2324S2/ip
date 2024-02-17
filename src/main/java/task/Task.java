package task;

/**
 * Class for Task object encapsulate commonalities in subclasses.
 */
public class Task {
    private int done;
    private final String msg;

    /**
     * Constructor for a Task object.
     * @param msg Description of the task.
     */
    public Task(String msg) {
        this.msg = msg;
        this.done = 0;
    }

    /**
     * Get the msg of a Task.
     * @returns Message of the task.
     */
    public String getMSG() {
        return this.msg;
    }

    /**
     * Mark done after complete the Task.
     */
    public void complete() {
        this.done = 1;
    }

    /**
     * Unmark done if Task not completed.
     */
    public void uncomplete() {
        this.done = 0;
    }

    /**
     * Store the information of the task into one line.
     * @returns Specific information of the task.
     */
    public String storeInfo() {
        StringBuilder sb = new StringBuilder("");
        if (this instanceof Todo) {
            sb.append('T');
            if (this.done == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
            sb.append(this.msg);
        } else if (this instanceof Deadline) {
            sb.append('D');
            if (this.done == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
            sb.append(this.msg + " /by " + ((Deadline) this).dueDate);
        } else if (this instanceof Event) {
            sb.append('E');
            if (this.done == 0) {
                sb.append('0');
            } else {
                sb.append('1');
            }
            sb.append(this.msg + " /from " + ((Event) this).fromDate + " /to " + ((Event) this).toDate);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (this.done==0)  {
            return "[ ] " + this.msg;
        } else {
            return "[X] " + this.msg;
        }
    }
}