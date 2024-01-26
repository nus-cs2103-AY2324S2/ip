import java.util.Scanner;
public class Ui {
    // Line separator.
    private static final String line = "    _______________________________________________________________";
    private Scanner scanner;
    private Storage taskList;

    public Ui() {
        this.scanner = new Scanner(System.in);
        this.taskList = new Storage();
    }

    /**
     * Print a separator line.
     */
    public static void printLine() {
        System.out.println(line);
    }

    /**
     * Greeting from chatbot when initialized.
     */
    public void greet() {
        printLine();
        System.out.println(
                "    Hello! I'm Tes\n" +
                "    What can I do for you?"
                );
        printLine();
    }

    /**
     * Scan the next input.
     *
     * @return New input.
     */
    public String nextCommand() {
        return scanner.nextLine();
    }

    /**
     * Say goodbye when close the chatbot.
     */
    public void close() {
        printLine();
        System.out.println(
                "    Bye. Hope to see you again soon!"
                );
        printLine();
    }

    /**
     * Add a task to the task list.
     *
     * @param command Task description of the new task.
     */
    public void addTask(String command) {
        printLine();
        System.out.println(
                "    added: " + command
                );
        printLine();
        this.taskList.storeTask(command);
    }

    /**
     * List the tasks stored.
     */
    public void listTask() {
        printLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            System.out.println("    " + i + "."
                    + this.taskList.getTaskDescription(i - 1));
        }
        printLine();
    }

    /**
     * Mark a task as done.
     * @param index Index of the task to be marked.
     */
    public void mark(int index) {
        this.taskList.mark(index);
        System.out.println("    Nice! I've marked this task as done:\n      "
                + this.taskList.getTaskDescription(index));
        printLine();
    }

    /**
     * Unmark a task.
     *
     * @param index Index of the task to be unmarked.
     */
    public void unmark(int index) {
        this.taskList.unmark(index);
        System.out.println("    OK, I've marked this task as not done yet:\n      "
                + this.taskList.getTaskDescription(index));
        printLine();
    }

}
