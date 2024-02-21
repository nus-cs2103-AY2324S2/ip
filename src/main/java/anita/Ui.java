package anita;

/**
 * The Ui class handles methods that provide interactive messages for the user.
 */
public class Ui {

    /**
     * Returns the start-up message when launching the task bot.
     *
     * @return The start-up message.
     */
    public String greet() {
        String logo = "$$\\      $$\\  $$$$$$\\  $$\\   $$\\       $$\\      $$\\ $$$$$$\\ $$\\   $$\\ \n"
                + "$$$\\    $$$ |$$  __$$\\ $$ |  $$ |      $$ | $\\  $$ |\\_$$  _|$$$\\  $$ | \n"
                + "$$$$\\  $$$$ |$$ /  $$ |\\$$\\ $$  |      $$ |$$$\\ $$ |  $$ |  $$$$\\ $$ | \n"
                + "$$\\$$\\$$ $$ |$$$$$$$$ | \\$$$$  /       $$ $$ $$\\$$ |  $$ |  $$ $$\\$$ | \n"
                + "$$ \\$$$  $$ |$$  __$$ | $$  $$<        $$$$  _$$$$ |  $$ |  $$ \\$$$$ | \n"
                + "$$ |\\$  /$$ |$$ |  $$ |$$  /\\$$\\       $$$  / \\$$$ |  $$ |  $$ |\\$$$ | \n"
                + "$$ | \\_/ $$ |$$ |  $$ |$$ /  $$ |      $$  /   \\$$ |$$$$$$\\ $$ | \\$$ | \n"
                + "\\__|     \\__|\\__|  \\__|\\__|  \\__|      \\__/     \\__|\\______|\\__|  \\__| \n";
        logo += "\n";
        logo += "Hello! I'm Anita MaxWynn\n";
        logo += "What can I do for you?\n";
        logo += line();
        return logo;
    }

    /**
     * Returns the bye message when closing the task bot.
     *
     * @return The bye message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns the default line spacer between tasks.
     *
     * @return The default line spacer.
     */
    public String line() {
        return "___________________________________________________________________________\n";
    }

    /**
     * Returns the add task message with the details of the task.
     *
     * @param task The task to be added.
     * @return The add task message.
     */
    public String addTask(Task task) {
        return "Yer task has been added: \n  " + task + "\n";
    }

    /**
     * Returns the task complete message with the details of the task.
     *
     * @param task The complete task.
     * @return The task complete message.
     */
    public String setDoneMessage(Task task) {
        return "Cha Ching! Task Completed. \n" + task + "\n";
    }

    /**
     * Returns the task incomplete message with the details of the task.
     *
     * @param task The incomplete task.
     * @return The task incomplete message.
     */
    public String setNotDoneMessage(Task task) {
        return "$$$ Task Incomplete :( \n" + task + "\n";
    }

    /**
     * Returns the deleted task message with the details of the deleted task.
     *
     * @param task The deleted task.
     * @return The deleted task message.
     */
    public String removeTaskMessage(Task task) {
        return "Noted. I've removed this task: \n" + "  " + task + "\n";
    }

    /**
     * Returns the task in the specified format.
     *
     * @param index The index of the task in the list.
     * @param task The task to be printed.
     * @return String of the task in the specified format.
     */
    public String listTaskMessage(int index, Task task) {
        return index + ". " + task + "\n";
    }

    /**
     * Prints the message showing the number of remaining tasks.
     *
     * @param size The number of remaining tasks.
     * @return Message on remaining tasks.
     */
    public String getNumberOfTasksMessage(int size) {
        return "Now you have " + size + " task(s) in the list.\n";
    }
}
