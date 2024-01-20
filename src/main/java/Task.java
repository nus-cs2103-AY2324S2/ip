import java.util.ArrayList;

/**
 * A class for managing all kinds of Tasks.
 */
class Task {
    /** The task list, update upon creation of task */
    protected static ArrayList<Task> task_list= new ArrayList<>();
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     *
     * @param text the description of the task
     */
    public Task(String text) {
        this.description=text;
        this.isDone=false;
    }

    /**
     * @return if task is Done, we cross it out.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Change the status of task from not Done to Done
     */
    public void Done() {
        this.isDone=true;
    }

    /**
     * Change the status of task from not Done to Done
     */
    public void unDone() {
        this.isDone=false;
    }
    /**
     *
     * @return the String representation of Task
     */
    @Override
    public String toString() {
        String temp = String.format("[%s] %s",this.getStatusIcon(),this.description);
        return temp;
    }
}
