package youdon;

/**
 * Represents the user interface (UI) of the Youdon chatbot.
 * Provides methods to interact with the user through the console.
 */
public class Ui {
    private static final String DIVIDER_LINE = "----------------------------------------------------------------";

    /**
     * Constructs a new instance of the Ui class.
     */
    public Ui() {

    }

    /**
     * Prints the welcome message when the chatbot is started.
     */
    public void printWelcomeMsg() {
        System.out.println(DIVIDER_LINE);
        System.out.println("Hello! I'm Youdon!\nWhat can I do for you?\n");
        System.out.println(DIVIDER_LINE);
    }

    public String getByeMsg() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints the goodbye message when the chatbot is exited.
     */
    public void printByeMsg() {
        System.out.println(DIVIDER_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER_LINE);
    }

    public String getYoudonErrorMsg(String message) {
        return message;
    }

    /**
     * Prints a Youdon-specific error message to the console.
     *
     * @param message The error message to be printed.
     */
    public void printYoudonErrorMsg(String message) {
        System.out.println(DIVIDER_LINE);
        System.out.println(message);
        System.out.println(DIVIDER_LINE);
    }

    public String getTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:").append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Prints the task list to the console.
     *
     * @param tasks The task list to be printed.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println(DIVIDER_LINE);
    }

    public String getMarkMsg(TaskList tasks, int taskNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nicely done! The task has been marked as done:").append("\n");
        sb.append("  ").append(tasks.get(taskNumber - 1).toString()).append("\n");
        return sb.toString();
    }

    /**
     * Prints a message indicating that a task has been marked as done.
     *
     * @param tasks   The task list containing the marked task.
     * @param taskNumber The index of the marked task in the task list.
     */
    public void printMarkMsg(TaskList tasks, int taskNumber) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Nicely done! The task has been marked as done:");
        System.out.println("  " + tasks.get(taskNumber - 1).toString());
        System.out.println(DIVIDER_LINE);
    }

    public String getUnmarkMsg(TaskList tasks, int taskNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Okies! The task has been marked as undone:").append("\n");
        sb.append("  ").append(tasks.get(taskNumber - 1).toString()).append("\n");
        return sb.toString();
    }

    /**
     * Prints a message indicating that a task has been marked as undone.
     *
     * @param tasks   The task list containing the unmarked task.
     * @param taskNumber The index of the unmarked task in the task list.
     */
    public void printUnmarkMsg(TaskList tasks, int taskNumber) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Okies! The task has been marked as undone:");
        System.out.println("  " + tasks.get(taskNumber - 1).toString());
        System.out.println(DIVIDER_LINE);
    }

    public String getDeleteMsg(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Alright! The task has been deleted:").append("\n");
        sb.append("  ").append(task.toString()).append("\n");
        return sb.toString();
    }

    /**
     * Prints a message indicating that a task has been deleted.
     *
     * @param tasks   The task list containing the deleted task.
     * @param taskNumber The index of the deleted task in the task list.
     */
    public void printDeleteMsg(TaskList tasks, int taskNumber) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Alright! The task has been deleted:");
        System.out.println("  " + tasks.get(taskNumber - 1).toString());
        System.out.println(DIVIDER_LINE);
    }

    public String getTaskAddedMsg(TaskList tasks) {
        int index = tasks.size() - 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Alright! Task added:\n  ").append(tasks.get(index).toString()).append("\n");
        sb.append("You now have ").append(tasks.size()).append(" task(s) in the list.").append("\n");
        return sb.toString();
    }

    /**
     * Prints a message indicating that a task has been added.
     *
     * @param tasks The updated task list containing the newly added task.
     */
    public void printTaskAdded(TaskList tasks) {
        // index of most recently added item
        int index = tasks.size() - 1;
        System.out.println(DIVIDER_LINE);
        System.out.println("Alright! Task added:\n  " + tasks.get(index).toString());
        System.out.println("You now have " + (tasks.size()) + " task(s) in the list.");
        System.out.println(DIVIDER_LINE);
    }
}
