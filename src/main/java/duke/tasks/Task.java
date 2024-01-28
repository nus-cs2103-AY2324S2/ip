package duke.tasks;

/**
 * This class implements functionality for Tasks in the bot.
 * 
 * @author delishad21
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Basic constructor for Task.
     * 
     * @param isDone Marks if task is completed.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Method for marking task as done.
     */
    public void doTask() {
        this.isDone = true;
    }
    
    /**
     * Method for marking task as undone.
     */
    public void undoTask() {
        this.isDone = false;
    }

    /**
     * Method used for printing task marker.
     * 
     * @return marker depending on whether this task is completed.
     */
    private String statusIcon() {
        return isDone ? "X" : " ";
    }
    
    /**
     * Method for displaying Task as a viewable String
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.statusIcon() + "] " + this.description;
    } 

    /** 
     * Method for converting Task into a String for saving in save file.
     * 
     * @return String
     */
    public String toSave() {
        return "[" + this.statusIcon() + "]|" + this.description;
    }
}
