package youdon;

/**
 * Represents the user interface (UI) of the Youdon chatbot.
 * Provides methods to interact with the user through the console.
 */
public class Ui {

    /**
     * Constructs a new instance of the Ui class.
     */
    public Ui() {

    }

    /**
     * Returns the farewell message when the user exits the chatbot.
     *
     * @return The farewell message.
     */
    public String getByeMsg() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns an error message to be displayed by the chatbot.
     *
     * @param message The error message to be displayed.
     * @return The formatted error message.
     */
    public String getYoudonErrorMsg(String message) {
        return message;
    }

    /**
     * Returns a formatted string representation of the task list.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The formatted task list.
     */
    public String getTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:").append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns a message confirming the successful marking of a task as done.
     *
     * @param tasks      The list of tasks containing the marked task.
     * @param taskNumber The number of the marked task.
     * @return The confirmation message.
     */
    public String getMarkMsg(TaskList tasks, int taskNumber) {
        return "Nicely done! The task has been marked as done:" + "\n" +
                "  " + tasks.get(taskNumber - 1).toString() + "\n";
    }

    /**
     * Returns a message confirming the successful marking of a task as undone.
     *
     * @param tasks      The list of tasks containing the marked task.
     * @param taskNumber The number of the marked task.
     * @return The confirmation message.
     */
    public String getUnmarkMsg(TaskList tasks, int taskNumber) {
        return "Okies! The task has been marked as undone:" + "\n" +
                "  " + tasks.get(taskNumber - 1).toString() + "\n";
    }

    /**
     * Returns a message confirming the successful deletion of a task.
     *
     * @param task The task that has been deleted.
     * @return The confirmation message.
     */
    public String getDeleteMsg(Task task) {
        return "Alright! The task has been deleted:" + "\n" +
                "  " + task.toString() + "\n";
    }

    /**
     * Returns a message confirming the successful addition of a task to the task list.
     *
     * @param tasks The updated list of tasks.
     * @return The confirmation message.
     */
    public String getTaskAddedMsg(TaskList tasks) {
        int index = tasks.size() - 1;
        return "Alright! Task added:\n  " + tasks.get(index).toString() + "\n" +
                "You now have " + tasks.size() + " task(s) in the list." + "\n";
    }

    /**
     * Returns a message confirming the successful snoozing of a task by 1 day.
     *
     * @param tasks      The list of tasks containing the snoozed task.
     * @param taskNumber The number of the snoozed task.
     * @return The confirmation message.
     */
    public String getSnoozeMsg(TaskList tasks, int taskNumber) {
        return "Okies! The task has been snoozed by 1 day:" + "\n" +
                "  " + tasks.get(taskNumber - 1).toString() + "\n";
    }
}
