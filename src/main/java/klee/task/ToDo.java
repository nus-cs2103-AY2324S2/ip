package klee.task;

/**
 * Represents a task that has to be completed.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    /**
     * Returns the type and current completion status of task.
     *
     * @return String to represent current state of class.
     */
    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description;
    }

    /**
     * Given input from txt file create new instance of ToDo.
     *
     * @param description
     * @param done
     * @return Instance of Task.
     */
    public static Task fromText(String description, String done) {
        Task task = new ToDo(description);
        task.isDone = done.equals("1");
        return task;
    }

    /**
     * Checks if obj is the same as the current instance.
     *
     * @param obj
     * @return If obj has the same description and type as this.
     */
    @Override
    public boolean equals(Object obj) {
        if (Task.class.isAssignableFrom(obj.getClass())) {
            return super.equals(obj);
        } else {
            return false;
        }
    }
}
