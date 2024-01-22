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
     * Prints Task description in Task Array or when task is marked/unmarked
     */
    public void callTask(){
        System.out.println(Integer.toString(this.taskNo)
                + ".[" + this.getStatusIcon() +"] " + this.description);
        System.out.println();
    }

    /**
     * Marks a valid Task in the Task array
     */
    public void markAsDone(){
        if(this.isDone == true){
            System.out.println("This task is already completed.\n");
            return;
        } else {
            this.isDone = true;
            System.out.println("Very well. This task is now completed.\n");
            this.callTask();
        }
    }

    /**
     * Unmarks a valid Task in the Task array
     */
    public void unMarkTask(){
        if(this.isDone == false){
            System.out.println("This task is already unmarked.\n");
            return;
        } else {
            this.isDone = false;
            System.out.println("Very well. This task is now marked as not completed.\n");
            this.callTask();
        }
    }
}
