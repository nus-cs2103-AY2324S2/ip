package dibo;

/**
 * The Ui class represents the entity which is responsible for communicating with the user.
 */
public class Ui {
    private final StringBuilder outputString;

    /**
     * Constructs a new Ui object.
     */
    public Ui() {
        this.outputString = new StringBuilder();
    }

    /**
     * Stores the string representation of the taskList
     * to be outputted as response later.
     *
     * @param taskList The String representation of the taskList.
     */
    public void storeList(String taskList) {
        this.outputString.append(taskList);
    }

    /**
     * Stores the successfully unmarked message
     * to be outputted as response later.
     *
     * @param task The String representation of the task.
     */
    public void storeUnmarkedMessage(String task) {
        String unmarkedMessage = "OK, I've marked this task as not done yet:\n";
        this.outputString.append(unmarkedMessage);
        this.outputString.append(task);
    }

    /**
     * Stores the successfully marked message
     * to be outputted as response later.
     *
     * @param task The String representation of the task.
     */
    public void storeMarkedMessage(String task) {
        String markedMessage = "OK, I've marked this task as done:\n";
        this.outputString.append(markedMessage);
        this.outputString.append(task);
    }

    /**
     * Stores the successfully deleted message
     * to be outputted as response later.
     *
     * @param task The String representation of the task.
     * @param size The size of the taskList after the last action performed.
     */
    public void storeDeletedMessage(String task, int size) {
        String deletedMessage = "Noted. I'm removing this task:\n";
        this.outputString.append(deletedMessage);
        this.outputString.append(task);
        this.addSeparator();
        this.addTasksLeftMessage(size);
    }

    /**
     * Stores the successfully added message
     * to be outputted as response later.
     *
     * @param task The String representation of the task.
     * @param size The size of the taskList after the last action performed.
     */
    public void storeAddedMessage(String task, int size) {
        String addedMessage = "Good news sir! I've added this task:\n";
        this.outputString.append(addedMessage);
        this.outputString.append(task);
        this.addSeparator();
        this.addTasksLeftMessage(size);
    }

    /**
     * Stores the task(s) with the specified keyword(s)
     * to be outputted as response later.
     *
     * @param tasksWithKeywords The string representation of the tasks
     *                         that contains the specified keyword.
     */
    public void storeFoundMessage(String tasksWithKeywords) {
        String foundMessage = "Good news sir! We've found the tasks in your list:\n";
        this.outputString.append(foundMessage);
        this.outputString.append(tasksWithKeywords);
    }

    /**
     * Adds the goodbye message to be outputted as response later.
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
