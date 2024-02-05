package seedu.duke.task;

/**
 * <h1> Events </h1>
 * This Events class is a subclass of the Task class, holding all the attributes and methods
 * of its parent class. This program also has methods that overrides the methods from its parent class Task,
 * to handle the cases differently when the task is an event and more specific than a normal todo task.
 * Events class objects have an attribute from and to which represents the start and end timing of the event respectively.
 * This class handles all the functionalities of an Events object, such as the printing of the task description
 * and the function that returns the data to be stored.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Events extends Task{
    private String from;
    private String to;
    public Events(String event, String from, String to) {
        super(event);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String getTag() {
        return "E";
    }

    /**
     * Returns a string that contains information of the event task to be stored in the hard disk.
     * Used as an argument in Storage class to be written into the file.
     * @return String that represents information of the Deadlines object.
     */
    @Override
    public String toStore() {
        return " E | " + (this.isDone ? "1" : "0") +  " | "  + this.description + " | " + this.from
                    + " | " + this.to + "\n";
    }

    @Override
    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
            } else {
                System.out.printf("      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
            }
        } else {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
                System.out.print("      ________________________________________________________\n");

            } else {
                System.out.printf("      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
                System.out.print("      ________________________________________________________\n");
            }
        }
    }

    @Override
    public void printFullDesc() {
        System.out.printf("         [%s][%s] %s (from: %s to: %s)\n",
                this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
    }
}
