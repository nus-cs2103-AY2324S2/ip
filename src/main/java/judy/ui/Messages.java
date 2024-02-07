package judy.ui;

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
    public static final String ERROR_LOADING_MESSAGE = " Error loading tasks from file";

    public static String printTaskSize(int size){
        return ("\n Now you have " + size + " tasks in the list. ");
    }
}
