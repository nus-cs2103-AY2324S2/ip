package essentials;

/**
 * Represents the user interface of the bot.
 */
public class Ui {

    /**
     * Greets the user.
     */
    public void greetUser() {
        System.out.println("Hello! I'm Jimmy\nWhat can I do for you?\n");
    }

    /**
     * Exits the bot.
     */
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Display the number of tasks left after the deletion of a task.
     *
     * @param deletedTask The task that was deleted.
     * @param tasksCount The number of tasks in the list.
     */
    public void showDeletedTask(String deletedTask, int tasksCount) {

        System.out.println("Noted. I've removed this task:");
        System.out.println(deletedTask);
        System.out.println(tasksCount + "\n");
    }
}
