package chatbot.ui;

import chatbot.exception.DukeException;
import chatbot.task.TaskList;

/**
 * Encapsulates the data and behaviour of user interface interactions.
 *
 * @author Huang Zhuoyan, Celeste
 * @version CS2103T AY24/25 Semester 1, G07
 */
public class Ui {
    private static final int LINE_LENGTH = 60;

    /**
     * Creates a line, in the form of a String.
     *
     * @return The line.
     */
    public static String createLine() {
        String line = "";
        for (int i = 0; i < LINE_LENGTH; i++) {
            line += "_";
        }
        return line;
    }

    /**
     * Prints a line.
     */
    public static void printLine() {
        for (int i = 0; i < LINE_LENGTH; i++) {
            System.out.print("_");
        }
        System.out.print("\n");
    }

    /**
     * Prints a message bound by 2 lines.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        Ui.printLine();
        System.out.println(message);
        Ui.printLine();
    }

    /**
     * Prints the welcome message.
     *
     * @param name The name of the chatbot.
     */
    public static void printWelcomeMessage(String name) {
        String welcomeMessage = "hello! i'm " + name + "!!!\n"
                + "i'm here to manage your task list!\n"
                + "what can i do for you?";
        Ui.printMessage(welcomeMessage);
    }

    /**
     * Prints the exit message.
     */
    public static void printByeMessage() {
        Ui.printMessage("bye!! come visit me again! :D");
    }

    /**
     * Prints the list of tasks.
     *
     * @param tasks The TaskList object containing the user's task list.
     */
    public static void printListTasks(TaskList tasks) {
        String listMessage = "alright! here is your task list:\n";
        if (tasks.getNumOfTasks() == 0) {
            listMessage += ".\n"
                    + ".\n"
                    + ".\n"
                    + ".\n"
                    + ".\n"
                    + ".\n"
                    + "SURPRISE!! nothing at all! what a good life!";
        }
        for (int i = 0; i < tasks.getNumOfTasks(); i++) {
            int index = i + 1;
            String taskMessage = tasks.printTask(index);
            if (i == tasks.getNumOfTasks() - 1) {
                listMessage += index + ". " + taskMessage;
            } else {
                listMessage += index + ". " + taskMessage + "\n";
            }
        }
        Ui.printMessage(listMessage);
    }

    /**
     * Prints the list of tasks containing the specified keyword.
     *
     * @param tasks The TaskList object containing the task list to be searched.
     * @param keyword The keyword to be found.
     */
    public static void printFindTask(TaskList tasks, String keyword) {
        String listMessage = "";
        String copiedKeyword = keyword.substring(1); //"keyword" is read with a space character at index 0
        if (tasks.getNumOfTasks() == 0) {
            listMessage += "no tasks with keyword \"" + copiedKeyword + "\" have been found!";
        } else {
            listMessage += "alright! here are the matching tasks in your task list:\n";
            for (int i = 0; i < tasks.getNumOfTasks(); i++) {
                int index = i + 1;
                String taskMessage = tasks.printTask(index);
                if (i == tasks.getNumOfTasks() - 1) {
                    listMessage += index + ". " + taskMessage;
                } else {
                    listMessage += index + ". " + taskMessage + "\n";
                }
            }
        }
        Ui.printMessage(listMessage);
    }

    /**
     * Prints the message for when a task has successfully been marked.
     *
     * @param tasks The TaskList object containing the user's task list.
     * @param taskNum The number of the task that has been marked.
     */
    public static void printMarkedTask(TaskList tasks, int taskNum) {
        String markedMessage = "good job!!! i've marked this task as done:\n"
                + "   " + tasks.printTask(taskNum);
        Ui.printMessage(markedMessage);
    }

    /**
     * Prints the message for when a task has successfully been unmarked.
     *
     * @param tasks The TaskList object containing the user's task list.
     * @param taskNum The number of the task that has been unmarked.
     */
    public static void printUnmarkedTask(TaskList tasks, int taskNum) {
        String unmarkedMessage = "okay! i've unmarked this task:\n"
                + "   " + tasks.printTask(taskNum);
        Ui.printMessage(unmarkedMessage);
    }

    /**
     * Prints the message for when a task has successfully been added.
     *
     * @param taskMessage The message to be printed.
     * @param totalNumOfTasks The total number of tasks in the task list after the new task has been added.
     */
    public static void printAddedTask(String taskMessage, int totalNumOfTasks) {
        String addedTaskMessage = "got it!! i've added this task:\n"
                + "   " + taskMessage + "\n"
                + "you now have " + totalNumOfTasks + " tasks in the task list!";
        Ui.printLine();
        System.out.println(addedTaskMessage);
        Ui.printLine();
    }

    /**
     * Prints the message for when a task has successfully been deleted.
     *
     * @param taskMessage The message to be printed.
     * @param remainingNumOfTasks The remaining number of tasks in the task list after the task has been deleted.
     */
    public static void printDeletedTask(String taskMessage, int remainingNumOfTasks) {
        String deletedTaskMessage = "got it!! i've deleted this task:\n"
                + "   " + taskMessage + "\n"
                + "you now have " + remainingNumOfTasks + " tasks left in the task list!";
        Ui.printLine();
        System.out.println(deletedTaskMessage);
        Ui.printLine();
    }

    /**
     * Prints the message for when the user inputs an unknown command.
     *
     * @throws DukeException For an unknown command.
     */
    public static void printUnknownCommand() throws DukeException {
        String exceptionMessage = Ui.createLine() + "\n"
                + "hm? i don't understand what that means :(\n"
                + "you can try any of these commands instead!!\n"
                + "list\n"
                + "mark\n"
                + "unmark\n"
                + "delete\n"
                + "todo\n"
                + "deadline\n"
                + "event\n"
                + "find\n"
                + "bye\n"
                + Ui.createLine();
        throw new DukeException(exceptionMessage);
    }

    /**
     * Prints the specified error message.
     *
     * @param message The error message to be printed.
     */
    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
