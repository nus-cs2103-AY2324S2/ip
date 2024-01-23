/**
 * Represent a class Task that stores some information to be used with a Chatbot
 * CS2103T
 * AY23/24 Semester 2
 * Author: Chua Zen Khoon
 */
public class Task {
    /**
     * A Task instance contains a description to be read out
     */
    protected String description;
    /**
     * A Task instance contains a label to determine whether it is marked or not
     */
    protected boolean isDone;

    /**
     * The Task class keeps track of the current no. of tasks currently made
     */
    protected static int currentTaskNo = 0;

    /**
     * A Task instance has a task no. to be tracked in the Task array
     */
    protected int taskNo;

    /**
     * Constructor for a Task instance,
     * @param description to be used to identify a task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        currentTaskNo++;
        this.taskNo = currentTaskNo;
    }

    /**
     * Determines if a task is marked/unmarked for list printing later
     * @return  X or blank space in relation to whether task is marked/unmarked
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Prints Task description in Task Array or when task is marked/unmarked/added
     * @return a string representing the task description
     */

    public String toString(){
        return "[" + this.getStatusIcon() +"]"
                + this.description;
    }

    /**
     * Marks a valid Task in the Task array
     */
    public String markAsDone(){
        if(this.isDone == true){
            return "This task is already completed.\n";
        } else {
            this.isDone = true;
            return "Very well. This task is now completed.\n"
                    + Integer.toString(this.taskNo) + this.toString();
        }
    }

    /**
     * Unmarks a valid Task in the Task array
     */
    public String unMarkTask(){
        if(this.isDone == false){
            return "This task is already unmarked.\n";
        } else {
            this.isDone = false;
            return "Very well. This task is now marked as not completed.\n"
                    + Integer.toString(this.taskNo) + this.toString();
        }
    }
}
