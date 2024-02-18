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
     * Returns a String containing the welcome message.
     *
     * @param name The name of the chatbot.
     */
    public static String printWelcomeMessage(String name) {
        String welcomeMessage = "hello! i'm " + name + "!!!\n"
                + "i'm here to manage your task list!\n"
                + "what can i do for you?";
        return welcomeMessage;
    }

    /**
     * Returns a String containing the exit message.
     */
    public static String printByeMessage() {
        return "bye!! come visit me again! :D";
    }

    /**
     * Returns a String containing the list of tasks.
     *
     * @param tasks The TaskList object containing the user's task list.
     */
    public static String printListTasks(TaskList tasks) {
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
        return listMessage;
    }

    /**
     * Returns a String containing the list of tasks containing the specified keyword.
     *
     * @param tasks The TaskList object containing the task list to be searched.
     * @param keyword The keyword to be found.
     */
    public static String printFindTask(TaskList tasks, String keyword) {
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
        return listMessage;
    }

    /**
     * Returns a String containing the message for when a task has successfully been marked.
     *
     * @param tasks The TaskList object containing the user's task list.
     * @param taskNum The number of the task that has been marked.
     */
    public static String printMarkedTask(TaskList tasks, int taskNum) {
        String markedMessage = "good job!!! i've marked this task as done:\n"
                + "   " + tasks.printTask(taskNum);
        return markedMessage;
    }

    /**
     * Returns a String containing the message for when a task has successfully been unmarked.
     *
     * @param tasks The TaskList object containing the user's task list.
     * @param taskNum The number of the task that has been unmarked.
     */
    public static String printUnmarkedTask(TaskList tasks, int taskNum) {
        String unmarkedMessage = "okay! i've unmarked this task:\n"
                + "   " + tasks.printTask(taskNum);
        return unmarkedMessage;
    }

    /**
     * Returns a String containing the message for when a task has successfully been added.
     *
     * @param taskMessage The message to be printed.
     * @param totalNumOfTasks The total number of tasks in the task list after the new task has been added.
     */
    public static String printAddedTask(String taskMessage, int totalNumOfTasks) {
        String addedTaskMessage = "got it!! i've added this task:\n"
                + "   " + taskMessage + "\n"
                + "you now have " + totalNumOfTasks + " tasks in the task list!";
        return addedTaskMessage;
    }

    /**
     * Returns a String containing the message for when a task has successfully been deleted.
     *
     * @param taskMessage The message to be printed.
     * @param remainingNumOfTasks The remaining number of tasks in the task list after the task has been deleted.
     */
    public static String printDeletedTask(String taskMessage, int remainingNumOfTasks) {
        String deletedTaskMessage = "got it!! i've deleted this task:\n"
                + "   " + taskMessage + "\n"
                + "you now have " + remainingNumOfTasks + " tasks left in the task list!";
        return deletedTaskMessage;
    }

    /**
     * Returns a String containing the message for when the user inputs an unknown command.
     *
     * @throws DukeException For an unknown command.
     */
    public static String printUnknownCommand() throws DukeException {
        String exceptionMessage = "hm? i don't understand what that means :(\n"
                + "you can try any of these commands instead!!\n"
                + "list\n"
                + "mark\n"
                + "unmark\n"
                + "delete\n"
                + "todo\n"
                + "deadline\n"
                + "event\n"
                + "find\n"
                + "bye\n";
        throw new DukeException(exceptionMessage);
    }

    /**
     * Returns a String containing the specified error message.
     *
     * @param message The error message to be printed.
     */
    public static String printErrorMessage(String message) {
        return message;
    }
}
