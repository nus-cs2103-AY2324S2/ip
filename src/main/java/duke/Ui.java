package duke;


/**
 * Represents the user interface for the Duke program, providing methods for
 * displaying messages and information to the user.
 */
public class Ui {

    /**
     * Displays the Duke logo and an initial message to the user.
     *
     * @return The initial message to the user.
     */
    public String message() {
        // Displaying Duke logo and initial message
        String logo = " KASSIM ";
        return "YOO I AM " + logo + "\nWhat can I do for you?";
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
    public String displayEmptyErrorMessage() {
        return "yes u have your task, but what your task plan to do? please indicate it.";
    }

    /**
     * Returns a general error message for unexpected inputs.
     *
     * @return The general error message for unexpected inputs.
     */
    public String displayErrorMessage() {
        return "what? please check your input.";
    }

    /**
     * Displays an error message for an unrecognized command.
     * @return The error message for an unrecognized command.
     */
    public String commandError() {
        return "please check ur input!";
    }

    /**
     * Displays information that a task has been marked as done
     *
     * @param task The task that has been marked as done.
     * @return A message indicating the task has been marked as done.
     */
    public String markInfo(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Displays information that a task has been marked as not done.
     *
     * @param task The task that has been marked as not done.
     * @return A message indicating the task has been marked as not done.
     */
    public String unmarkInfo(Task task) {
        return "Ok, I've marked this task as not done yet: \n" + task;
    }

    /**
     * Displays a message for an invalid task number.
     * @return The error message for an invalid task number.
     */
    public String invalidNum() {
        return "Invalid task number.";
    }

    /**
     * Displays the header for the list of tasks.
     * @return A message indicating the list of tasks is shown.
     */
    public String listDetails() {
        return "Here are the tasks in your list: \n";
    }

    /**
     * Prints a header indicating the start of the list of matching tasks.
     * @return A message indicating the list of tasks user are finding.
     */
    public String findListDetails() {
        return "Here are the matching tasks in your list: ";
    }


    /**
     * Displays information about the removal of a task.
     *
     * @param task The task that has been removed.
     * @return A message indicating the task removed.
     */

    public String removeTop(Task task) {
        return "Noted. I've removed this task: \n" + task;
    }

    /**
     * Displays information about the updated task list size after task removal.
     *
     * @param size The updated size of the task list.
     * @return A message indicating the number of tasks left in the list.
     */
    public String removeBottom(int size) {
        return "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays information about the addition of a to-do task.
     *
     * @param newTodo The new to-do task that has been added.
     * @param size    The updated size of the task list.
     * @return A message indicating what todo task is added into the task and the number of tasks left in the list.
     */
    public String todoInfo(Todo newTodo, int size) {
        return "Got it. I've added this task:\n" + newTodo
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays information about the addition of a deadline task.
     *
     * @param newDeadline The new deadline task that has been added.
     * @param size        The updated size of the task list.
     * @return A message indicating what deadline task is added into the task and the number of task left in the list.
     */
    public String deadlineInfo(Deadline newDeadline, int size) {
        return "Got it. I've added this task: \n" + newDeadline
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays information about the addition of an event task.
     *
     * @param newEvent The new event task that had been added.
     * @param size     The updated size of the task list.
     * @return A message indicating what event task is added into the task and the number of task left in the list.
     */
    public String eventInfo(Event newEvent, int size) {
        return "Got it. I've added this task: \n" + newEvent
                + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Displays a final goodbye message when the user exits the Duke program.
     *
     * @return Last message for the user before they exit the program.
     */
    public String finalMessage() {
        return "Bye! Hope to see you again!!";
    }

    /**
     * Prints a blank line.
     * @return A message indicating user enter a blank command.
     */
    public String blank() {
        return "Input cannot be empty";
    }

    /**
     * Prints a separation line to visually separate different sections of output.
     * @return A separation line to separate the section.
     */
    public String separationLine() {
        return "------------------------------------------------------------";
    }
}
