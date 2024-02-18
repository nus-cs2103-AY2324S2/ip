package dave;

import java.util.Scanner;

import dave.tasks.Task;

public class Ui {
    /** The user input scanner. */
    private Scanner sc;

    /** Name of the chatbot, Dave. */
    static final String NAME = "Dave";

    /** Horizontal line divider for output. */
    static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    /** Greeting message shown to user. */
    public static final String GREETING = String.format("%s: Nice to meet you,"
            + " I'm the ever-helpful %s!\nHow may I be of service today?\n",
            NAME, NAME);

    /** Farewell message shown to user. */
    public static final String FAREWELL = "\nDave: Thank you for your patronage,"
            + " goodbye and have a nice day!\n";

    /** Message to show upon successful load. */
    public static final String LOAD_SUCCESS = "Dave has found %s existing tasks and loaded them.\n";

    /** Message to show upon loading error. */
    public static final String LOAD_ERROR = "Dave did not find any existing tasks to load.\n";

    /** Message to show upon adding a task. */
    public static final String TASK_ADDED = "Dave added the task:\n  %s\n"
            + "You now have %d task(s).\n";

    /** Message to show upon task deletion. */
    public static final String TASK_DELETED = "Dave removed the task:\n  %s\n"
            + "You now have %d task(s).\n";

    /** Message to show upon marking a task. */
    public static final String TASK_MARKED = "Good job! Dave will mark this task as done:\n  %s";
    
    /** Message to show upon unmarking a task. */
    public static final String TASK_UNMARKED = "Alright, Dave believes you'll get this done eventually:\n  %s";

    /**
     * Creates new UI object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Welcomes the user with a message.
     * 
     * @return The greeting string.
     */
    public String sayHello() {
        return GREETING;
    }

    /**
     * Says bye to the user with a message.
     * 
     * @return The farewell string.
     */
    public String sayBye() {
        return FAREWELL;
    }

    /**
     * Shows that tasks from an existing task list have been loaded in the chatbot
     * program, if any.
     * 
     * @return Feedback to user about successful loading of tasks.
     */
    public String showLoadingSuccess(int noOfTasks) {
        return String.format(LOAD_SUCCESS, noOfTasks);
    }

    /**
     * Shows that there is no existing task list to load tasks from.
     * 
     * @return Feedback to user about no existing tasks to load.
     */
    public String showLoadingError() {
        return String.format(LOAD_ERROR);
    }

    /**
     * Shows error from any user-chatbot interaction.
     * 
     * @param msg The error output.
     * 
     * @return The error message string.
     */
    public String showError(String msg) {
        return msg;
    }

    /**
     * Reads user input.
     * 
     * @return The user input to the chatbot program.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows user feedback when a new task is successfully added into the task list.
     * 
     * @param newTask  Information about the new task added.
     * @param taskList Current task list.
     * 
     * @return Feedback to user about successful task addition.
     */
    public String showTaskAdded(Task newTask, TaskList taskList) {
        return String.format(TASK_ADDED, newTask.toString(), taskList.getNumberOfTasks());
    }

    /**
     * Shows user feedback when a task is successfully deleted from the task list.
     * 
     * @param deletedTask Information about the deleted task.
     * @param taskList    Current task list.
     * 
     * @return Feedback to user about successful task deletion.
     */
    public String showTaskDeleted(Task deletedTask, TaskList taskList) {
        return String.format(TASK_DELETED, deletedTask.toString(), taskList.getNumberOfTasks());
    }

    /**
     * Shows user feedback when a task is successfully marked as done.
     * 
     * @param markedTask The task marked as done.
     * 
     * @return Feedback to user about successful marking of task.
     */
    public String showTaskMarked(Task markedTask) {
        return String.format(TASK_MARKED, markedTask.toString());
    }

    /**
     * Shows user feedback when a task is successfully unmarked as not done.
     * 
     * @param unmarkedTask The task unmarked as not done.
     * 
     * @return Feedback to user about successful unmarking of task.
     */
    public String showTaskUnmarked(Task unmarkedTask) {
        return String.format(TASK_UNMARKED, unmarkedTask.toString());

    }
}
