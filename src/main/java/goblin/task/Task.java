package goblin.task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "";

    /**
     * create a new task
     * @param description what the task is
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * the status of the task(either it's done or not)
     * @return a string
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] "); // mark done task with X
    }

    /**
     * a getter for the description of the task
     * @return the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * change the status of the task to done
     */
    public void done() {
        isDone = true;
    }

    /**
     * change the status of the task to undone
     */
    public void undone() {
        isDone = false;
    }

    /**
     * print the task
     */
    public void print() {

    }

    /**
     * a string representation
     * @return a string
     */
    public String notPrint() {
        return "";
    }

    /**
     * a getter for the type
     * @return the type
     */
    public String type(){
        return type;
    }//...

    /**
     * whether the task is done
     * @return a boolean
     */
    public  boolean isDone() {
        return isDone;
    }
}
