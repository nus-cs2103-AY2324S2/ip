package guanguan;

/**
 * Represents an Ui class that is responsible for the outputs on the terminal.
 */
public class Ui {
    /**
     * Prints welcome message
     */
    public void welcome() {
        System.out.println("Hello! I'm GuanGuanBot!");
        System.out.println("What can I do for you?");
        line();
    }

    /**
     * Prints goodbye message
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message
     * Wrapper of the System.out.println method
     *
     * @param message message to be printed
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints added task message
     */
    public void addTask() {
        System.out.println("Got it. I've added this task:");
    }

    /**
     * Prints deleted task message
     */
    public void deleteTask() {
        System.out.println("Noted. I've removed this task:");
    }

    /**
     * Prints number of tasks message
     *
     * @param i number of tasks
     */
    public void countTasks(int i) {
        System.out.printf("Now you have %s tasks in the list.%n", i);
    }

    /**
     * Prints list of tasks message
     *
     * @param items list of tasks
     * @throws GgException if index out of bound
     */
    public void tasks(TaskList items) throws GgException {
        if (items.isEmpty()) {
            System.out.println("You have no tasking available.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%s. %s%n", i + 1, items.get(i));
            }
        }
    }

    /**
     * Prints mark task message
     */
    public void markTask() {
        System.out.println("Nice! I've marked this task as done:");
    }

    /**
     * Prints unmark task message
     */
    public void unmarkTask() {
        System.out.println("OK, I've marked this task as not done yet:");
    }

    /**
     * Prints divider line message
     */
    public void line() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints empty line message
     */
    public void emptyLine() {
        System.out.println();
    }

    /**
     * Prints error message
     *
     * @param message error message
     */
    public void error(String message) {
        System.out.printf("[!] %s\n", message);
    }

    public String getTextOutput() {
        return "";
    }
}
