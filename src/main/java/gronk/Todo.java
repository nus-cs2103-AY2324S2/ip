package gronk;

/**
 * Todo class.
 * Simple todo object with no specified deadline.
 */
public class Todo extends Task {
    public Todo(String d, int s) {
        super(d, s);
    }

    @Override
    public String statusMessage() {
        if (this.getStatus() == 0) {
            return "Well done! Task: " + this.getDesc() + " completed.";
        } else {
            return "Task updated. Task: " + this.getDesc() + " marked as incomplete.";
        }
    }

    @Override
    public String saveFormat() {
        return Integer.toString(this.getStatus()) + "," + this.getDesc();
    }

    @Override
    public String toString() {
        if (this.getStatus() == 0) {
            return "[T] [ ] " + this.getDesc();
        } else {
            return "[T] [X] " + this.getDesc();
        }
    }
}
