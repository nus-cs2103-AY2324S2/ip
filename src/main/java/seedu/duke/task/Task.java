package seedu.duke.task;

/**
 * <h1> Task </h1>
 * This Task class is a superclass of the Deadlines and Events classes. This class represents the individual Task objects
 * and has attributes description and isDone which represents the description of the task
 * and whether the task is completed respectively. This class handles all the functionalities of a Task object,
 * such as the marking, unmarking of the task and printing the task description to be displayed to the user.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description.trim();
        this.isDone = false;
    }

    public void markDone(boolean isNew) {
        this.isDone = true;
        if (isNew) {
            this.printMarking(this, this.getTag());
        }
    }

    public void unmark() {
        this.isDone = false;
        this.printMarking(this, this.getTag());
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns a string that contains information of the task to be stored in the hard disk.
     * Used as an argument in Storage class to be written into the file.
     * @return String that represents information of the Task object.
     */
    public String toStore() {
        return " " + this.getTag() +  " | " + (this.isDone? "1" : "0")
                +  " | "  + this.description + "\n";
    }

    public String getDescription() {
        return this.description;
    }

    public String getTag() {
        return "T";
    }

    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription());
            } else {
                System.out.printf("      %d.[%s][%s] %s\n", num, this.getTag(), this.getStatusIcon(), this.getDescription());
            }
        } else {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription());
                System.out.print("      ________________________________________________________\n");
            } else {
                System.out.printf("      %d.[%s][%s] %s \n", num, this.getTag(), this.getStatusIcon(), this.getDescription());
                System.out.print("      ________________________________________________________\n");
            }
        }
    }

    public void printFullDesc() {
        System.out.printf("         [%s][%s] %s\n", this.getTag(), this.getStatusIcon(), this.getDescription());
    }

    public void printMarking(Task task, String tag) {
        if (this.isDone) {
            System.out.print("      ________________________________________________________\n"
                    + "      Great job! I've marked this task as done:\n");
            System.out.printf("      [%s][%s] %s\n", tag, task.getStatusIcon(), task.getDescription());
            System.out.print("      ________________________________________________________\n");
        } else {
            System.out.print("      ________________________________________________________\n"
                    + "      Ok, I've marked this task as not done yet:\n");
            System.out.printf("      [%s][%s] %s\n", tag, task.getStatusIcon(), task.getDescription());
            System.out.print("      ________________________________________________________\n");
        }
    }
}
