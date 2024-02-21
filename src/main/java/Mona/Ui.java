package mona;

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
    public String greet() {
        String introduction = "  ______________________________________\n"
                + "   YEAAAARM! I'm Mona\n"
                + "   How can I help you?\n"
                + "  ______________________________________\n";
        return introduction;
    }

    /**
     * Mona's farewell when the user issues the bye command
     */
    public String sayBye() {
        String farewell = "  ______________________________________\n"
                + "   YEAAAARM! Good job today!\n"
                + "   See you soon!\n"
                + "  ______________________________________\n";
        return farewell;
    }

    /**
     * Mona updates the user of the task that was added / removed and the number of tasks that remain
     * @param task task that was removed / added
     * @param numTasks remaining number of tasks
     * @param hasIncreased whether a task was added or removed from the list
     */
    public String showListAfterQuantityChange(Task task, int numTasks, boolean hasIncreased) {
        String action = hasIncreased ? "added" : "removed";
        String response = "  ______________________________________\n"
                + "     YEAAAAARM! I've " + action + " this task: \n"
                + "     " + task + "\n"
                + "     Only " + numTasks + " tasks remain!\n"
                + "  ______________________________________\n";
        return response;
    }

    /**
     * Mona acknowledges that a task has been marked or unmarked
     * @param task the task that was marked or unmarked
     */
    public String showListAfterProgressChange(Task task) {
        String status = task.isDone ? "done" : "not done";
        String response = "  ______________________________________\n"
                + "     YEAAAARM! I've marked this task as " + status + ":\n"
                + "     " + task + "\n"
                + "  ______________________________________\n";
        return response;
    }

    /**
     * Mona updates a task
     * @param task the task that was updated
     */
    public String showListAfterUpdate(Task task) {
        String response = "  ______________________________________\n"
                + "     YEAAAARM! I've updated this task:\n"
                + "     " + task + "\n"
                + "  ______________________________________\n";
        return response;
    }

    /**
     * Mona echoes the message when an exception is thrown
     * @param message the message from the exception
     */
    public String showError(String message) {
        String response = "  ______________________________________\n"
                + "    " + message
                + "  ______________________________________\n";
        return response;
    }
}
