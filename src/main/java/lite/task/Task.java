package lite.task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    /**
     * Returns true if input is within description
     * @param input
     * @return
     */
    public boolean contains (String input) {
        return this.description.contains(input);
    }

    /**
     * Returns string representation of Task
     */
    public String getStatusIcon() {
        String done = (this.isDone ? "X" : "");
        return "[" + done + "] ";
    }

    /**
     * Outputs a marked notification and outputs the string representation
     * @return Task string representation
     */
    public String mark() {
        setDone();
        System.out.println("Nice! I've marked this lite.task as done:");
        return this.toString();
    }

    /**
     * To set the Task to done
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Outputs an unmarked notification and outputs the string representation
     * @return Task string representation
     */
    public String unmark() {
        System.out.println("OK, I've marked this lite.task as not done yet:");
        setUndone();
        return this.toString();
    }

    /**
     * To set the Task to undone
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns string representation of Task
     */
    @Override
    public String toString() {
        return getStatusIcon() + this.description;
    }


    /**
     * Returns string representation when it is saved in a file
     */
    public String saveToFile() {
        return (this.isDone ? "1" : "0")
                + "!" + this.description;
    }

    /**
     * Check if two tasks are equal based on their toString representation
     * @param obj Task being compared to
     * @return Equality of two tasks
     */
    @Override
    public boolean equals (Object obj) {
        Task task = (Task) obj;
        System.out.println(this.toString());
        System.out.println(task.toString());
        return this.toString().equals(task.toString());
    }
}
