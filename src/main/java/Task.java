/**
 * Stores information regarding tasks to be recorded.
 *
 * @author KohGuanZeh
 */
public abstract class Task {
    // Task description.
    private String description;
    // Task completion status.
    protected boolean isDone;

    /**
     * Creates a new task with given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a Task based on given String.
     * String should follow convention based on respective subclass.
     *
     * @param line Data String of task.
     * @return Task specified by given String.
     * @throws TaskException Exception when String does not comply with any known format.
     */
    public static Task getTaskFromString(String line) throws TaskException {
        String[] tokens = line.split(" \\| ", 3);
        try {
            Task task = null;
            boolean marked = Integer.parseInt(tokens[1]) > 0;
            switch (tokens[0]) {
            case "T":
                task = ToDo.createToDo(tokens[2]);
                if (marked) {
                    task.markAsDone();
                }
                return task;
            case "D":
                task = Deadline.createDeadline(tokens[2]);
                if (marked) {
                    task.markAsDone();
                }
                return task;
            case "E":
                task = Event.createEvent(tokens[2]);
                if (marked) {
                    task.markAsDone();
                }
                return task;
            }
        } catch (Exception e) {
            throw new TaskException("Task not saved in correct format. Skipping.");
        }
        throw new TaskException("Task not saved in correct format. Skipping.");
    }

    /**
     * Returns String to be saved in data file and loaded for future use.
     *
     * @return String data of task.
     */
    public abstract String saveTaskAsString();

    /**
     * Returns the task and its completion status.
     * Tasks that are done are marked with "[X]" whereas tasks that are not done are marked with "[ ]".
     *
     * @return Task completion status and description.
     */
    public String getTaskInformation() {
        String marker = this.isDone ? "[X]" : "[ ]";
        return marker + " " + this.description;
    }

    /**
     * Sets task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets task as not done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }
}
