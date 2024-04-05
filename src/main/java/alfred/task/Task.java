package alfred.task;

/**
 * Represents a tasks in available in duke.Duke chat-bot.
 * A task can be of three types: Todo (T), Deadline (D), or Event (E).
 * Each task has a name and a completion status.
 */
public class Task {
    private boolean done = false;
    private String name;
    private String type; /* T, D or E*/
    private String date;

    /**
     * Constructs a new Task with the specified name.
     *
     * @param name The name of the task.
     */
    public Task(String name){
        this.name = name;
    }
    /**
     * Checks if the task is marked as done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone(){
        return this.done;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return The task's name.
     */
    public String getName(){
        return this.name;
    }

    /**
     * Marks the task as done.
     */
    public void makeDone(){
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void makeUndone(){
        this.done = false;
    }

    /**
     * Retrieves the type of the task.
     *
     * @return A string representing the task type. This method is intended to be overridden by subclasses.
     */
    public String getType(){
        return "";
    }

    /**
     * Sets the completion status of the task.
     *
     * @param state The new completion status of the task.
     */
    public void setDone(Boolean state) {
        this.done = state;
    }

    /**
     * Retrieves the formatted time associated with the task.
     *
     * @return A string representing the formatted time. Intended for overriding by subclasses.
     */
    public String getTime(){
        return "";
    }

    /**
     * Retrieves the raw time data associated with the task.
     *
     * @return A string representing the raw time data. Intended for overriding by subclasses.
     */
    public String getRawTime(){
        return "";
    }

    /**
     * Generates a status string for the task, including its type, completion status, name, and time.
     *
     * @return A formatted string representing the task's status.
     */
    public String getStatus(){
        if (this.isDone()) {
            return this.getType() + "[X] " + this.getName() + " " + this.getTime();
        } else {
            return this.getType() + "[ ] " + this.getName() + " " + this.getTime();
        }
    }

    public void snooze(){
    }

    public void postpone(int days){
    }
    public void reschedule(String end){
    }
    public void reschedule(String start, String end){
    }
}

