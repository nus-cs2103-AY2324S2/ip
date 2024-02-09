package anita;

/**
 * The Ui class handles methods that provide interactive messages for the user.
 */
public class Ui {

    /**
     * Prints the start-up message when launching the task bot.
     */
    public void greet() {
        String logo = "$$\\      $$\\  $$$$$$\\  $$\\   $$\\       $$\\      $$\\ $$$$$$\\ $$\\   $$\\ \n"
                + "$$$\\    $$$ |$$  __$$\\ $$ |  $$ |      $$ | $\\  $$ |\\_$$  _|$$$\\  $$ | \n"
                + "$$$$\\  $$$$ |$$ /  $$ |\\$$\\ $$  |      $$ |$$$\\ $$ |  $$ |  $$$$\\ $$ | \n"
                + "$$\\$$\\$$ $$ |$$$$$$$$ | \\$$$$  /       $$ $$ $$\\$$ |  $$ |  $$ $$\\$$ | \n"
                + "$$ \\$$$  $$ |$$  __$$ | $$  $$<        $$$$  _$$$$ |  $$ |  $$ \\$$$$ | \n"
                + "$$ |\\$  /$$ |$$ |  $$ |$$  /\\$$\\       $$$  / \\$$$ |  $$ |  $$ |\\$$$ | \n"
                + "$$ | \\_/ $$ |$$ |  $$ |$$ /  $$ |      $$  /   \\$$ |$$$$$$\\ $$ | \\$$ | \n"
                + "\\__|     \\__|\\__|  \\__|\\__|  \\__|      \\__/     \\__|\\______|\\__|  \\__| \n";
        System.out.println("Hello from\n" + logo);
        line();
        System.out.println("Hello! I'm Anita MaxWynn");
        System.out.println("What can I do for you?");
        line();
    }

    /**
     * Prints the bye message when closing the task bot.
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the default line spacer between tasks.
     */
    public void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the add task message with the details of the task.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        System.out.println("Yer task has been added: \n  " + task);
    }

    /**
     * Prints the task complete message with the details of the task.
     *
     * @param task The complete task.
     */
    public void setDoneMessage(Task task) {
        System.out.println("Cha Ching! Task Completed.");
        System.out.println(task);
    }

    /**
     * Prints the task incomplete message with the details of the task.
     *
     * @param task The incomplete task.
     */
    public void setNotDoneMessage(Task task) {
        System.out.println("$$$ Task Incomplete :(");
        System.out.println(task);
    }

    /**
     * Prints the deleted task message with the details of the deleted task.
     *
     * @param task The deleted task.
     */
    public void removeTaskMessage(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + task);
    }

    /**
     * Prints the task in the specified format.
     *
     * @param index The index of the task in the list.
     * @param task The task to be printed.
     */
    public void listTaskMessage(int index, Task task) {
        System.out.println(index + ". " + task);
    }

    /**
     * Prints the message showing the number of remaining tasks.
     *
     * @param size The number of remaining tasks.
     */
    public void getNumberOfTasksMessage(int size) {
        System.out.println("Now you have " + size + " task(s) in the list.");
    }
}
