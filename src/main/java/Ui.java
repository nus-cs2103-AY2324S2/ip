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
                "    Tes here.\n" +
                "    huh? What you want from me?"
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
                "    Annoying brat (-.-)"
                );
        printLine();
    }

    /**
     * Add a task to the task list.
     *
     * @param command Task description of the new task.
     */
    public void addToDo(String command) {
        this.taskList.storeToDo(command);
        printLine();
        System.out.println("    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have " + this.taskList.getSize() + " in the list.");
        printLine();
    }

    public void addDeadline(String command, String by) {
        this.taskList.storeDeadline(command, by);
        printLine();
        System.out.println("    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have " + this.taskList.getSize() + " in the list.");
        printLine();
    }

    public void addEvent(String command, String from, String to) {
        this.taskList.storeEvent(command, from, to);
        printLine();
        System.out.println("    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have " + this.taskList.getSize() + " in the list.");
        printLine();
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
        printLine();
        System.out.println("    OK, I've marked this task as not done yet:\n      "
                + this.taskList.getTaskDescription(index));
        printLine();
    }

    public void delete(int index) {
        printLine();
        System.out.println("    Noted. I've removed this task:\n      "
                + this.taskList.getTaskDescription(index - 1)
                + "\n    Now you have " + Integer.toString(this.taskList.getSize() - 1) + " in the list.");
        this.taskList.delete(index - 1);
        printLine();
    }

}
