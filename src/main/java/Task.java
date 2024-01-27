/**
 * This class represents a task that tracks if it is completed
 *
 * @author Billy Ho Cheng En
 */
abstract class Task{
    private String description;
    private boolean is_done;
    private String done_symbol;
    String task_symbol;

    /**
     * Constructor for a task list.
     *
     * @param description textual description of the task
     */
    public Task(String description, String task_symbol){
        this.description = description;
        this.is_done = false;
        this.done_symbol = "[ ]";
        this.task_symbol = task_symbol;
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
            this.done_symbol = "[X]";
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
            this.done_symbol = "[ ]";
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return this.task_symbol + this.done_symbol + " " + this.description;
    }
}
