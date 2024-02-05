package seedu.duke.task;

/**
 * <h1> Event </h1>
 * This Event class is a subclass of the Task class, holding all the attributes and methods
 * of its parent class. This program also has methods that overrides the methods from its parent class Task,
 * to handle the cases differently when the task is an event and more specific than a normal todo task.
 * Event class objects have an attribute from and to which represents the start and end timing of the
 * event respectively. This class handles all the functionalities of an Event object, such as the
 * printing of the task description and the function that returns the data to be stored.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class Event extends Task{
    private String from;
    private String to;
    public Event(String event, String from, String to) {
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
     *
     * @return String that represents information of the Deadlines object.
     */
    @Override
    public String toStore() {
        return " E | " + (isDone ? "1" : "0") +  " | "  + description + " | " + from
                    + " | " + to + "\n";
    }

    @Override
    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      _________________________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, getTag(), getStatusIcon(), getDescription(), from, to);
            } else {
                System.out.printf("      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, getTag(), getStatusIcon(), getDescription(), from, to);
            }
        } else {
            if (num == 1) {
                System.out.print("      _________________________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, getTag(), getStatusIcon(), getDescription(), from, to);
                System.out.print("      _________________________________________________________________________\n");

            } else {
                System.out.printf("      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, getTag(), getStatusIcon(), getDescription(), from, to);
                System.out.print("      _________________________________________________________________________\n");
            }
        }
    }

    @Override
    public void printMatchDesc(int num) {
        System.out.printf("      %d.[%s][%s] %s (from: %s to: %s)\n",
                num, getTag(), getStatusIcon(), getDescription(), from, to);
    }

    @Override
    public void printFullDesc() {
        System.out.printf("         [%s][%s] %s (from: %s to: %s)\n",
                getTag(), getStatusIcon(), getDescription(), from, to);
    }
}
