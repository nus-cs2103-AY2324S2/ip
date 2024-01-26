/**
 * This class represents a task that tracks if it is completed
 *
 * @author Billy Ho Cheng En
 */
class Task{
    private String description;
    private boolean is_done;

    /**
     * Constructor for a task list.
     *
     * @param description textual description of the task
     */
    public Task(String description){
        this.description = description;
        this.is_done = false;
    }

    /**
     * Marks this task as done.
     *
     * @return true if task was change from not done to done. Else false.
     */
    public boolean mark_done(){
        if(this.is_done){
            return false;
        } else {
            this.is_done = true;
            return true;
        }
    }

    /**
     * Marks this task as not done.
     *
     * @return true if task was change from done to not done. Else false.
     */
    public boolean unmark_done(){
        if(this.is_done){
            this.is_done = false;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return is_done ? "[X] " + description : "[ ] " + description;
    }
}
