package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description Description of the task.
     * */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for a Task.
     *
     * @param description Description of the task.
     * @param isDone Boolean value to state if the task is done or not.
     * */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructor for a Task.
     *
     * @return A checked string if the task is done else returns an empty [].
     * */
    public String getStatusIcon() {
        return isDone ? "[✔]" : "[ ]";
    }

    abstract public void taskPrinter();

    abstract public void taskPrinter(int index);

    /**
     * Sets the isDone value of task to be true.
     * */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone value of task to be false.
     * */
    public void markAsUndone() {
        this.isDone = false;
    }

    abstract public String storagePrinter();


    /**
     * Checks if the keyword exists in the description.
     *
     * @param keyword The keyword that needs to be searched in the description.
     * @return Boolean value if such a keyword exists in the description.
     * */
    public boolean hasKeyword(String keyword) {

        String lowercaseDescription = description.toLowerCase();
        String lowercaseKeyword = keyword.toLowerCase();

        return lowercaseDescription.contains(lowercaseKeyword);
    }

    /**
     * Checks if a Task is equal to the current instance of Task.
     *
     * @param obj The object that is to be checked for equality with current Task object.
     * @return True if the obj is equal else return False.
     * */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Task task = (Task) obj;

        return isDone == task.isDone && description.equals(task.description);
    }

}
