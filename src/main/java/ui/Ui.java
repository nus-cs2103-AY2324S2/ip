package ui;

/**
 * Interacts with the users. Replies to the user when a command is inserted.
 */
public class Ui {
    /**
     * Greets the user when the chatbot is loaded.
     *
     * @return greet message.
     */
    public String greet() {
        return "Hello! I'm wei.\n" + "What can I do for you?";
    }

    /**
     * Says goodbye before terminating the conversation.
     */
    public String exit() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Lists out the tasks saved in the chatbot.
     *
     * @param list The task list.
     */
    public String showList(String list) {
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Confirms that a task have been added.
     *
     * @param taskName Details about the task.
     */
    public String showAddMessage(String taskName) {
        return "Got it. I've added this task:\n" + taskName + "\n";
    }

    /**
     * Confirms that a task have been deleted.
     *
     * @param taskName Details about the task.
     */
    public String showDeleteMessage(String taskName) {
        return "Noted. I've removed this task:\n" + taskName + "\n";
    }

    /**
     * Indicates that a task is completed.
     *
     * @param taskName Details about the task.
     */
    public String showMarkedMessage(String taskName) {
        return "Nice! I've marked this task as done:\n" + taskName;
    }

    /**
     * Indicates that a task is not completed.
     *
     * @param taskName Details about the task.
     */
    public String showUnmarkedMessage(String taskName) {
        return "Noted! I've unmarked this task:\n" + taskName;
    }

    /**
     * Shows number of tasks in the list.
     *
     * @param sizeOfTaskList Number of tasks.
     */
    public String showNumberOfRemainingTasks(int sizeOfTaskList) {
        return "Now you have " + sizeOfTaskList + " tasks in the list.";
    }

    /**
     * Shows the result that matches the searched keyword.
     *
     * @param result Tasks in the result.
     * @return description plus the result.
     */
    public String showSearchResult(String result) {
        return "Here are the matching tasks in your list:\n" + result;
    }

    public String showSetReminderStatus() {
        return "reminder added successfully";
    }

    public String showReminder(String tasks) {
        String show = "Remember to do these tasks:\n";
        return show + tasks;
    }
}
