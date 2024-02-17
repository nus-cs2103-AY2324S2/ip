package duke;

/**
 * Handles interactions with the user
 */
public class Ui {
    private String response;
    /**
     * Prints the list of tasks to the console.
     *
     * @param tasks The TaskList containing the tasks to be printed.
     * @param size  The number of tasks to be printed.
     */
    public void printList(TaskList tasks, int size) {
        System.out.println("Here are the tasks in your list!");
        for (int j = 0; j < size; j++) {
            int nr = j + 1;
            System.out.println(nr + "." + tasks.getTask(j).toString() + ".");
        }
    }
    /**
     * Prints a message to the console.
     *
     * @param msg The message to be printed.
     */
    public void printMessage(String msg) {
        System.out.println(msg);
    }
    /**
     * Sets the response generated during the execution of user input.
     *
     * @param response The response to be set.
     */
    public void setResponse(String response) {
        this.response = response;
    }
    /**
     * Retrieves the response generated during the execution of user input.
     *
     * @return The stored response.
     */
    public String getResponse() {
        return response;
    }
}
