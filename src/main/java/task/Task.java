package task;

import javax.xml.namespace.QName;

/**
 * The parent class of event, deadline and todo
 */

public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Instantiates a task
     * @param name Describes the task
     * @param isDone A boolean to indicate if the task is completed
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Retrieves the status icon that indicates if the task is completed
     * @return the status icon depending on the isDone boolean
     */

    public String getStatusIcon(){
        return(isDone? "X" : " ");
    }

    /**
     * retrieves the status of the task
     * @return The isDone boolean
     */
    public Boolean getStatus() { return isDone; }

    /**
     * Changes the status of a task to completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Changes the status of a task to not completed
     */
    public void unmark(){
        this.isDone = false;
    }

    /**
     * @return The status of the task represented as a number 1 or 0
     */
    public String getStatusNum() {
        if(this.isDone) {
            return "1";
        }else {
            return "0";
        }
    }

    /**
     * String representation of the task
     * @return the status and name of task
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name ;
    }

    /**
     * String representation of the task in the storage fomat
     * @return the status represented as a number and name of the task
     */
    public String toFileString() {
        return " " + " | " + getStatusNum() + " | " + this.name ;
    }


}