package baron.models;

/**
 * An abstraction class for todos, deadlines and events. It is not abstract because
 * we want to list todos, events and deadlines together in the same list still.
 */
public class Task extends BaseModel {

    private final String name;
    private boolean isDone;

    /**
     * Creates a task with a name and false for isDone
     * @param name Name of task
     */
    public Task(String name) {
        super(0);
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
        super(id);
        this.name = name;
        this.isDone = isDone;
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
