package dibo;

/**
 * Class to deal with the interaction with the users.
 */
public class Ui {
    private final StringBuilder outputString;

    /**
     * Constructs the Ui class.
     */
    public Ui() {
        this.outputString = new StringBuilder();
    }

    /**
     * Takes in the string representation of the taskList
     * and stores it in the StringBuilder object to be returned later.
     *
     * @param taskList The String representation of the taskList.
     */
    public void showList(String taskList) {
        this.outputString.append(taskList);
    }

    /**
     * Adds the successfully unmarked message into the
     * StringBuilder object to be returned later.
     *
     * @param task The String representation of the task.
     */
    public void showUnmarked(String task) {
        String unmarkedMessage = "OK, I've marked this task as not done yet:\n";
        this.outputString.append(unmarkedMessage);
        this.outputString.append(task);
    }

    /**
     * Adds the successfully marked message into the
     * StringBuilder object to be returned later.
     *
     * @param task The String representation of the task.
     */
    public void showMarked(String task) {
        String markedMessage = "OK, I've marked this task as done:\n";
        this.outputString.append(markedMessage);
        this.outputString.append(task);
    }

    /**
     * Adds the successfully deleted message into the
     * StringBuilder object to be returned later.
     *
     * @param task The String representation of the task.
     * @param size The size of the taskList after the last action performed.
     */
    public void showDeleted(String task, int size) {
        String deletedMessage = "Noted. I'm removing this task:\n";
        this.outputString.append(deletedMessage);
        this.outputString.append(task);
        this.addSeparator();
        this.addTasksLeftMessage(size);
    }

    /**
     * Adds the successfully added message into the
     * StringBuilder object to be returned later.
     *
     * @param task The String representation of the task.
     * @param size The size of the taskList after the last action performed.
     */
    public void showAdded(String task, int size) {
        String addedMessage = "Good news sir! I've added this task:\n";
        this.outputString.append(addedMessage);
        this.outputString.append(task);
        this.addSeparator();
        this.addTasksLeftMessage(size);
    }

    /**
     * Takes in the string representation of the tasks with the specified keyword(s)
     * and stores it in the StringBuilder object to be returned later.
     *
     * @param tasksWithKeyword The string representation of the tasks
     *                         that contains the specified keyword.
     */
    public void showFound(String tasksWithKeyword) {
        String foundMessage = "Good news sir! We've found the tasks in your list:\n";
        this.outputString.append(foundMessage);
        this.outputString.append(tasksWithKeyword);
    }

    /**
     * Adds the goodbye message into the
     * StringBuilder object to be returned later.
     */
    public void sayBye() {
        String goodbyeMessage = "Bye sir! Always happy to assist you :D\nHope to see you again soon!";
        this.outputString.append(goodbyeMessage);
    }

    private void addSeparator() {
        this.outputString.append("\n");
    }

    private void addTasksLeftMessage(int size) {
        this.outputString.append("Now you have ");
        this.outputString.append(size);
        this.outputString.append(size > 1 ? " tasks " : " task ");
        this.outputString.append("in the list.\n");
    }

    /**
     * Returns the output of the command to the user
     * and resets the StringBuilder object.
     */
    public String getOutput() {
        String output = this.outputString.toString();
        this.outputString.setLength(0);
        assert this.outputString.length() == 0 : "The output string should be empty";
        return output;
    }
}
