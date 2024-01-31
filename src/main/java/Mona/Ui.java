package Mona;

/**
 * This class contains all logic regarding Mona's response
 */
public class Ui {

    /**
     * Constructor for Ui
     */
    public Ui() {
        this.greet();
    }

    /**
     * Functionality for Mona's greeting at the start of the program
     */
    public void greet() {
        String introduction = "  ____________________________________________________________\n"
                + "   Hello! I'm Mona\n"
                + "   What can I do for you?\n"
                + "  ____________________________________________________________\n";
        System.out.println(introduction);
    }

    /**
     * Mona's farewell when the user issues the bye command
     */
    public void sayBye() {
        String farewell = "  ____________________________________________________________\n"
                + "   Bye. Hope to see you again soon!\n"
                + "  ____________________________________________________________";
        System.out.println(farewell);
    }

    /**
     * Mona updates the user of the task that was added / removed and the number of tasks that remain
     * @param task task that was removed / added
     * @param numTasks remaining number of tasks
     * @param hasIncreased whether a task was added or removed from the list
     */
    public void showListAfterQuantityChange(Task task, int numTasks, boolean hasIncreased) {
        String action = hasIncreased ? "added" : "removed";
        String response = "  ____________________________________________________________\n"
                + "     Noted. I've " + action + " this task: \n"
                + "     " + task + "\n"
                + "     Now you have " + numTasks + " tasks in the list.\n"
                + "  ____________________________________________________________\n";
        System.out.println(response);
    }

    /**
     * Mona acknowledges that a task has been marked or unmarked
     * @param task the task that was marked or unmarked
     */
    public void showListAfterProgressChange(Task task) {
        String status = task.isDone ? "done" : "not done";
        String response = "  ____________________________________________________________\n"
                + "     Got it! I've marked this task as " + status + ":\n"
                + "     " + task + "\n"
                + "  ____________________________________________________________\n";
        System.out.println(response);
    }

    /**
     * Mona echoes the message when an exception is thrown
     * @param message the message from the exception
     */
    public void showError(String message) {
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + message);
        System.out.println("  ____________________________________________________________");
    }
}
