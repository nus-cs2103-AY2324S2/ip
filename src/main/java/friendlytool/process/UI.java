package friendlytool.process;

import friendlytool.task.Task;

/**
 * UI for the program. Provides instructions to users.
 */
public class UI {
    /**
     * Prints a welcome message.
     */
    public static void printInitMsg() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm FriendlyTool\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________"
        );
    }

    /**
     * Prints a bye message.
     */
    public static void printByeMsg() {
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

    /**
     * Prints task updated message.
     *
     * @param task task updated.
     * @param size number of elements in the list.
     */
    public static void printUpdateTaskMsg(Task task, int size) {
        System.out.println("    ____________________________________________________________\n"
                + "    Completed. I've added this task: \n    "
                + task
                + "\n    Now you have " + size + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Prints that a task is marked as done.
     *
     * @param task task given.
     */
    public static void printMarkMsg(Task task) {
        System.out.println("    ____________________________________________________________\n"
                + "    Nice! I've marked this task as done:\n"
                + "      " + task.toString()
                + "\n    ____________________________________________________________\n");
    }


    /**
     * Prints that a task is unmarked.
     *
     * @param task task given.
     */
    public static void printUnmarkMsg(Task task) {
        System.out.println("    ____________________________________________________________\n"
                + "    OK, I've marked this task as not done yet:\n"
                + "      " + task.toString()
                + "\n    ____________________________________________________________\n");
    }

    /**
     * Print that the task is deleted.
     *
     * @param task task deleted.
     * @param size number of elements in the list.
     */
    public static void printDeleteMsg(String task, int size) {
        System.out.println("    ____________________________________________________________\n"
                + "    Great!, You have completed the task:\n"
                + "      " + task
                + "\n    Now you have " + size + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Prints whether the save data is created or not.
     *
     * @param isSuccessful save data creation is successful or not
     */
    public static void printCreateSaveMsg(Boolean isSuccessful) {
        System.out.println("    There was no save data.");
        System.out.println(isSuccessful ? "    New save data file created." : "    Failed to create a new save data");
    }

    /**
     * Prints that save data is loaded.
     */
    public static void printLoadSaveMsg() {
        System.out.println("    Successfully loaded the save data. ");
    }


    /**
     * Prints the whole list with index.
     *
     * @param list list to be printed.
     */
    public static void showList(TaskList list) {
        System.out.println("    ____________________________________________________________\n"
                + "    Here are the tasks in your list:");
        for (int i = 1; i < list.size() + 1; i++) {
            Task task = list.get(i - 1);
            if (task.isDone()) {
                System.out.println("    " + i + "." + task);
            } else {
                System.out.println("    " + i + "." + task);
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Prints that there are matching tasks.
     */
    public static void printMatchingMsg() {
        System.out.println("____________________________________________________________\n"
                + "These are the matching tasks in your list:");
    }

    /**
     * Prints out the found task.
     *
     * @param i    current index
     * @param task task found
     */
    public static void printFindTaskMsg(int i, Task task) {
        System.out.println("    " + i + "." + task);
    }

    /**
     * Prints out a dotted line.
     */
    public static void printLine() {
        System.out.println("____________________________________________________________\n");
    }
}
