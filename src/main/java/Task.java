/**
 * Class object which represents a Task, which has a name and a status completion
 */
public class Task {

    boolean completed;
    final String taskName;

    /**
     * @param taskName
     * completed? - is set to false by default
     */
    Task (String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }
    public static void main(String[] args) {

    }

    public void markComplete() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String xMarker = this.completed ? "[X]" : "[ ]";
        return String.format("%s %s", xMarker, this.taskName);
    }
}
