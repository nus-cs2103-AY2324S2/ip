package duke.tasks;

/**
 * Class representing different possible tasks given to chatbot.
 */
public abstract class Task {
    /* Task is a class, what does it do??? */
    public String name; 
    public boolean isDone;
    public char taskType;
    // static String COMPLETED_MESSAGE_END = " is complete!";
    // static String INCOMPLETE_MESSAGE_END = " has yet to be completed.";

    /**
     * Constructor for newly created tasks.
     * @param name Description or name of the given task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for existing tasks that may or may not be completed.
     * @param name Description or name of the given task.
     * @param isDone true if task has been completed, false otherwise.
     */
    public Task(String name, String isDone) {
        this.name = name;

        if (isDone.equals("false")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }
    
    public Boolean checkDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    /**
     * Checks if task has been completed and returns appropriate message based on completion status.
     * @return Message based on task completion status.
     */
    public String checkStatus() {
        if (this.isDone) {
            return "[" + this.taskType + "] " + this.name + " is complete!";
        } else {
            return "[" + this.taskType + "] " + this.name + " has yet to be completed.";
        }
    }

    /**
     * Returns values of different attributes of the task.
     * @return Values of the different attributes of the task.
     */
    public abstract String getAttributes();

    /**
     * Sets task to completed.
     */
    public void completeTask() {
        this.isDone = true;
    }

    /**
     * Reverts task to incomplete.
     */
    public void revertStatus() {
        this.isDone = false;
    }
    
}
