package area;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     *
     * @param description
     */
    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get status of task.
     * 
     * @return String
     */
    public String getStatusIcon(){
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get description of task
     * 
     * @return String
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Set work as done
     */
    public void taskDone(){
        isDone = true;
    }

    /**
     * Set work as undone
     */
    public void taskUndone(){
        isDone = false;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
