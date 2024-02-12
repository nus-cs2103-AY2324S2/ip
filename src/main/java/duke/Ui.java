package duke;


/**
 * Represents the user interface for the Duke program, providing methods for
 * displaying messages and information to the user.
 */
public class Ui {

    /**
     * Displays the Duke logo and an initial message to the user.
     *
     * @return
     */
    public String message() {
        // Displaying Duke logo and initial message
        String logo = " KASSIM ";
        System.out.println("YOO I AM " + logo);
        System.out.println("What can I do for you?");
        return logo;
    }

    /**
     * Displays an error message encountered during the Duke program execution.
     *
     * @param e The DukeException containing the error message.
     */
    public void errorEncounter(DukeException e) {
        System.out.println("------------------------------------------------------------");
        System.out.println(e.getMessage());
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Returns a message indicating that a task description is empty.
     *
     * @return The error message for an empty task description.
     */
    public String emptyErrorMessage() {
        return "yes u have your task, but what your task plan to do? please indicate it.";
    }

    /**
     * Returns a general error message for unexpected inputs.
     *
     * @return The general error message for unexpected inputs.
     */
    public String errorMessage() {
        return "what? please check your input.";
    }

    /**
     * i an error message for an unrecognized command.
     */
    public void commandError() {
        System.out.println("SORRY! but are you sure you enter the correct command? please check!");
    }

    /**
     * Displays information that a task has been marked as done
     *
     * @param task The task that has been marked as done.
     */
    public void markInfo(Task task) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Displays information that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     */
    public void unmarkInfo(Task task) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Ok, I've marked this task as not done yet: ");
        System.out.println(task);
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Displays a message for an invalid task number.
     */
    public void invalidNum() {
        System.out.println("Invalid task number.");
    }

    /**
     * Displays the header for the list of tasks.
     */
    public void listDetails() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Here are the tasks in your list: ");
    }

    /**
     * Prints a header indicating the start of the list of matching tasks.
     */
    public void findListDetails() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Here are the matching tasks in your list: ");
    }


    /**
     * Displays information about the removal of a task.
     *
     * @param task The task that has been removed.
     */

    public void removeTop(Task task) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
    }

    /**
     * Displays information about the updated task list size after task removal.
     *
     * @param size The updated size of the task list.
     */
    public void removeBottom(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Displays information about the addition of a to-do task.
     *
     * @param newTodo The new to-do task that has been added.
     * @param size    The updated size of the task list.
     */
    public void todoInfo(Todo newTodo, int size) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(newTodo);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Displays information about the addition of a deadline task.
     *
     * @param newDeadline The new deadline task that has been added.
     * @param size        The updated size of the task list.
     */
    public void deadlineInfo(Deadline newDeadline, int size) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(newDeadline);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Displays information about the addition of an event task.
     *
     * @param newEvent The new event task that had been added.
     * @param size     The updated size of the task list.
     */
    public void eventInfo(Event newEvent, int size) {
        System.out.println("------------------------------------------------------------");
        System.out.println("Got it. I've added this task: ");
        System.out.println(newEvent);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("------------------------------------------------------------");
    }

    /**
     * Displays a final goodbye message when the user exits the Duke program.
     */
    public void finalMessage() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Bye! Hope to see you again!!");
    }

    /**
     * Prints a blank line.
     */
    public void blank() {
        System.out.print(" ");
    }

    /**
     * Prints a separation line to visually separate different sections of output.
     */
    public void separationLine() {
        System.out.println("------------------------------------------------------------");
    }
}
