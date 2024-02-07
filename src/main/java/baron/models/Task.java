package baron.models;

/**
 * An abstraction class for todos, deadlines and events
 */
public class Task {

    private final String name;
    private long id;
    private boolean isDone;

    /**
     * Creates a task with a name and false for isDone
     * @param name Name of task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Creates a task with fully-customised attributes. Usually used when loading tassk from database
     * @param id id of the task
     * @param name name of task
     * @param isDone whether task is done
     */
    public Task(int id, String name, boolean isDone) {
        this.id = id;
        this.name = name;
        this.isDone = isDone;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String done = isDone ? "X" : " ";
        return "[" + done + "] " + name;
    }

    /**
     * Converts object to its data representation format
     *
     * @return Data String representation format
     */
    public String toDataString() {
        String done = this.isDone() ? "1" : "0";
        return done + " | " + this.getName();
    }

    public boolean isDone() {
        return isDone;
    }

    public String getName() {
        return name;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
