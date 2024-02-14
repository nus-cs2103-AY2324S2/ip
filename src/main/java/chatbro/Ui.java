package chatbro;

/**
 * Represents the user interface of ChatBro.
 */
public class Ui {
    public static void printLine() {
        System.out.println("____________________________");
    }
    public static void printWithoutLine(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message with a line above and below it.
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }
    public static void printWelcome() {
        printMessage(" __  __       __\n"
                + " \\ \\/ /__    / /\n"
                + "  \\  / _ \\  /_/ \n"
                + "  /_/\\___/ (_)\n\n"
                + "I'm ChatBro! What can I do for you bro?\n"
                + "Type 'help' to see the list of available commands!");
    }
    public static void printHelp() {
        printMessage("Here are the available commands bro:\n"
                + "1. list (view your list of tasks)\n"
                + "2. bye (saves your task list and ends the program)\n"
                + "(Tasks *will not be saved* if you exit the program without 'bye')\n"
                + "3. mark <task number> (marks a task as done)\n"
                + "4. unmark <task number> (marks a task as undone)\n"
                + "5. delete <task number> (delete a task from your list)\n"
                + "You may add tasks to your list using:\n"
                + "6. todo <description>\n"
                + "7. deadline <description> /by <deadline time>\n"
                + "8. event <description> /from <start time> /to <end time>\n"
                + "Deadline and start/end times must follow the format:\n"
                + "'DD-MM-YYYY HHmm' (HHmm is *optional* and in 24hr format)\n"
                + "Find tasks using:\n"
                + "9. find <keyword> (finds tasks containing keyword in the description)");
    }
    public static void printBye() {
        printMessage("I have saved your tasks. Hasta la vista bro!");
    }

    public static void printFormatError(String format) {
        printMessage("Hey bro, make sure to follow the format:\n" + format);
    }
    public static void printAddMessage(Task task) {
        printMessage("Got it bro. I've added this task:\n" + task.toString()
                + "\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.");
    }
    public static void printDeleteMessage(Task task) {
        printMessage("Noted bro. I've removed this task:\n" + task.toString()
                + "\nNow you have " + TaskManager.getTaskCount() + " tasks in the list.");
    }
    public static void printDoesNotExistError() {
        printMessage("Sorry bro, this task does not exist in your list.");
    }
    public static void printMarkMessage(Task task) {
        printMessage("Got it bro! I've marked this task as done:\n" + task.toString());
    }
    public static void printUnmarkMessage(Task task) {
        printMessage("Got it bro! I've marked this task as undone:\n" + task.toString());
    }
    public static void printList() {
        System.out.println("Here are the tasks in your list bro:");
        printLine();
        for (int i = 1; i <= 100; i++) {
            if (TaskManager.getList().get(i) == null) {
                break;
            }
            System.out.println(i + "." + TaskManager.getList().get(i).toString());
        }
        printLine();
    }
}
