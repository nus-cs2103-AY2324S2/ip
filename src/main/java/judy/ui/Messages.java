package judy.ui;

/**
 * The Messages class contains various messages used in the application.
 */
public class Messages {
    public static final String LINE = "-------------------------------------------------------------------";
    public static final String GREET_MESSAGE = " Hello! I'm Judy\n What can I do for you?";
    public static final String MARK_TASK_MESSSAGE = " Nice ! I've marked this task as done: \n";
    public static final String UNMARK_TASK_MESSAGE = " Ok, I've marked this task as not done yet: \n";
    public static final String EMPTY_TASKLIST_MESSAGE = " Your task list is empty. Try add one now! ";
    public static final String LIST_TASKS_MESSAGE = " Here are the tasks in your task list: ";
    public static final String DELETE_TASK_MESSAGE = " Noted. I've removed this task: \n";
    public static final String ADD_TASK_MESSAGE = " Got it. I've added this task: \n";
    public static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    public static final String EMPTY_MATCHING_TASKS_MESSAGE = "Ohno! Can't find a matching task in your list!";
    public static final String LIST_MATCHING_TASKS_MESSAGE = " Here are the matching tasks in your list:";

    /**
     * Generates and returns the help message with instructions on using the application.
     *
     * @return The help message.
     */
    public static String helpMessage() {
        return ("Here are the commands that I understand!\n"
                + "To add a todo, type 'todo <description>'\n"
                + "To add a deadline, type 'deadline <description> /by <yyyy-mm-dd HHmm>' \n"
                + "To add an event, type 'event <description> /from <yyyy-mm-dd HHmm> /to <yyyy-mm-dd HHmm>'\n"
                + "To list out your tasks, type 'list'\n"
                + "To mark a task as done, type 'mark <task number>' \n"
                + "To mark a task as undone, type 'unmark <task number>' \n"
                + "To delete a task, type 'delete <task number>' \n"
                + "To exit the chat, type 'bye'"
            );
    }

    /**
     * Generates and returns a message indicating the updated task size in the task list.
     *
     * @param size The updated size of the task list.
     * @return The message indicating the updated task size.
     */
    public static String printTaskSize(int size) {
        return ("\n Now you have " + size + " tasks in the list. ");
    }
}
