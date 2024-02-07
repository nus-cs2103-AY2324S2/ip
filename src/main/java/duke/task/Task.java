package duke.task;

/**
 * Abstract parent class for todo, deadline, and event tasks
 */
public abstract class Task {

    protected Boolean done;
    protected final String name;
    protected Type type;
    protected String fileFormat;

    /**
     * Enum for type of task
     */
    enum Type {
        T,D,E;

        /**
         * String representation of enum constant
         *
         * @return Return string representation of the enum constant
         */
        @Override
        public String toString() {
            return name();
        }
    }

    /**
     * Constructor for Task
     *
     * @param name Name of the DeadlineTask
     * @param type Type of task
     * @param file_format File format for the task
     */
    Task(String name, Type type, String fileFormat) {
        this.done = false;
        this.name = name;
        this.type = type;
        this.fileFormat = fileFormat;
    }

    /**
     * Mark tasks as done
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Unmark tasks as done
     */
    public void unmark() {
        this.done = false;
    }

    /**
     * Gets task name
     *
     * @return Return name as string
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets file format of task
     *
     * @return Return file format as string
     */
    public String getFileFormat() {
        return this.fileFormat;
    }

}
