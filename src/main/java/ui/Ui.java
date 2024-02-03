package ui;

/**
 * Interacts with the users. Replies to the user when a command is inserted.
 */
public class Ui {
    /**
     * Prints a line to separate between sections of the conversation.
     */
    public void showLine() {
        System.out.println("________________________________________");
    }

    /**
     * Greets the user when the chatbot is loaded.
     */
    public void greet() {
        System.out.println("Hello! I'm Wei.\n" + "What can I do for you?");
        showLine();
    }

    /**
     * Says goodbye before terminating the conversation.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Lists out the tasks saved in the chatbot.
     *
     * @param list The task list.
     */
    public void showList(String list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    /**
     * Confirms that a task have been added.
     *
     * @param taskName Details about the task.
     */
    public void showAddMessage(String taskName) {
        System.out.println("Got it. I've added this task:");
        System.out.println(taskName);
    }

    /**
     * Confirms that a task have been deleted.
     *
     * @param taskName Details about the task.
     */
    public void showDeleteMessage(String taskName) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskName);
    }

    /**
     * Indicates that a task is completed.
     *
     * @param taskName Details about the task.
     */
    public void showMarkedMessage(String taskName) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskName);
    }

    /**
     * Indicates that a task is not completed.
     *
     * @param taskName Details about the task.
     */
    public void showUnmarkedMessage(String taskName) {
        System.out.println("Noted! I've unmarked this task:");
        System.out.println(taskName);
    }

    /**
     * Shows number of tasks in the list.
     *
     * @param sizeOfTaskList Number of tasks.
     */
    public void showNumberOfRemainingTasks(int sizeOfTaskList) {
        System.out.println("Now you have " + sizeOfTaskList + " tasks in the list.");
    }

    /**
     * Prints out the error message being raised.
     *
     * @param errorMessage Message of exception raised.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
