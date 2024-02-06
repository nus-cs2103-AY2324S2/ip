package dibo;

/**
 * Class to deal with the interaction with users.
 */
public class Ui {
    private StringBuilder outputString;

    /**
     * Constructs the Ui class.
     */
    public Ui() {
        this.outputString = new StringBuilder();
    }

    /**
     * Takes in the string representation of the taskList and reports it to the user.
     *
     * @param taskList The String representation of the taskList.
     */
    public void showList(String taskList) {
        this.outputString.append(taskList);
    }

    /**
     * Sends the message to show that the task has been successfully unmarked.
     *
     * @param task The String representation of the task.
     */
    public void showUnmarked(String task) {
        this.outputString.append("OK, I've marked this task as not done yet:\n");
        this.outputString.append(task);
    }

    /**
     * Sends the message to show that the task has been successfully marked.
     *
     * @param task The String representation of the task.
     */
    public void showMarked(String task) {
        this.outputString.append("OK, I've marked this task as done:\n");
        this.outputString.append(task);
    }

    /**
     * Sends the message to show that the task has been successfully deleted.
     *
     * @param task The String representation of the task.
     * @param size The size of the taskList after the last action performed.
     */
    public void showDeleted(String task, int size) {
        this.outputString.append("Noted. I'm removing this task:\n");
        this.outputString.append(task);
        this.addSeparator();
        this.outputString.append("Now you have ");
        this.outputString.append(size);
        this.outputString.append(size > 1 ? " tasks " : " task ");
        this.outputString.append("left in the list.\n");
    }

    /**
     * Sends the message to show that the task has been successfully added.
     *
     * @param task The String representation of the task.
     * @param size The size of the taskList after the last action performed.
     */
    public void showAdded(String task, int size) {
        this.outputString.append("Good news sir! I've added this task:\n");
        this.outputString.append(task);
        this.addSeparator();
        this.outputString.append("Now you have ");
        this.outputString.append(size);
        this.outputString.append(size > 1 ? " tasks " : " task ");
        this.outputString.append("in the list.\n");
    }

    /**
     * Sends the message to show that the task has been found.
     *
     * @param tasksWithKeyword The string representation of the tasks that contains the specified keyword.
     */
    public void showFound(String tasksWithKeyword) {
        this.outputString.append("Good news sir! We've found the tasks in your list:\n");
        this.outputString.append(tasksWithKeyword);
    }


    /**
     * Sends the error message.
     *
     * @param errorMessage The string representation of the error.
     */
    public void showError(String errorMessage) {
        this.outputString.append(errorMessage);
    }

    /**
     * Sends a goodbye message to the user.
     */
    public void sayBye() {
        this.outputString.append("Bye sir! Always happy to assist you :D\n");
        this.outputString.append("Hope to see you again soon!");
    }
    private void addSeparator() {
        this.outputString.append("\n");
    }

    /**
     * Returns the output of the command to the user.
     */
    public String getOutput() {
        String output = this.outputString.toString();
        this.outputString.setLength(0);
        return output;
    }
}
