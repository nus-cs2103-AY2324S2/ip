package utils;

import tasks.Task;


/**
 * The Response class handles response messages returned to the User.
 */
public class Response {
    private static final String CHATBOT_NAME = "Chitty";
    private static final String GREETING_MESSAGE = String.format("Greetings! I am %s, your digital assistant. Speed 1 "
            + "Tera-Hertz, Memory 1 Zeta-byte. State your query.\n", CHATBOT_NAME);
    private static final String GOODBYE_MESSAGE = "No one can destroy me! Hope to see you again soon!\n";
    private static final String ADD_TASK = "I am Chitty, the ultimate machine! I've added this task:\n";
    private static final String TASK_LENGTH = "I am programmed to serve and protect. "
           + "Now you have %d tasks in the list.\n";
    private static final String MARK_TASK = "Happy Diwali Folks! I've marked this task as done:\n";
    private static final String UNMARK_TASK = "I am a superhuman robot, I am not a human being. "
            + "I've marked this task as not done yet:\n";
    private static final String DELETE_TASK = "The black sheep! I've removed this task:\n";
    private static final String LIST_TASKS = "I am Chitty, a one-of-a-kind creation! "
            + "Here are the tasks in your list:\n";
    private static final String FOUND_TASKS = "Command mode activated. Here are the matching tasks in your list:\n";
    private static final String FOUND_NO_TASKS = "Task Incomplete. There are no tasks that match your query!\n";
    private static final String EMPTY_LIST = "My memory is virtually infinite, and my capabilities are boundless."
            + "Your task list is empty!\n";
    private static final String INVALID_INPUT = "Task Incomplete. Invalid input, "
            + "please double check your input values!\n";
    private static final String INVALID_COMMAND = "Task Incomplete. Invalid command, "
            + "type 'help' to view available commands \n";
    private static final String HELP = "I am Chitty, a masterpiece of artificial intelligence!\n\n"
            + "Here are the available commands along with their descriptions and usage:\n\n"
            + "- `todo [description]`: Add a new to-do task.\n"
            + "   Example: `todo Borrow book`\n\n"
            + "- `deadline [description] /by [deadline]`: Add a new task with a deadline.\n"
            + "   Example: `deadline Return book /by Sunday`\n\n"
            + "- `event [description] /from [start time] /to [end time]`: Add a new event task.\n"
            + "   Example: `event Project meeting /from Mon 2pm /to 4pm`\n\n"
            + "- `mark [task number]`: Mark a task as completed.\n"
            + "   Example: `mark 1`\n\n"
            + "- `unmark [task number]`: Unmark a completed task.\n"
            + "   Example: `unmark 1`\n\n"
            + "- `delete [task number]`: Delete a task.\n"
            + "   Example: `delete 1`\n\n"
            + "- `list`: List all tasks.\n"
            + "   Example: `list`\n\n"
            + "- `find [query]`: Find a task by searching for a keyword.\n"
            + "   Example: `find project`\n\n"
            + "- `bye`: Exit the application.\n"
            + "   Example: `bye`\n\n"
            + "- `help`: Display this help message.\n"
            + "   Example: `help`\n\n"
            + "Please note:\n"
            + "- Replace [description] with a brief description of the task.\n"
            + "- Replace [deadline] with the deadline for the task (e.g., a date or time).\n"
            + "- Replace [start time] and [end time] with the start and end times for the event task.\n"
            + "- Replace [task number] with the corresponding number of the task you want to modify or delete.\n"
            + "- Replace [query] with the keyword you want to search for in your tasks.\n\n"
            + "My circuits are designed to outperform any human brain.";

    /**
     * Returns a greeting message.
     */
    public static String getGreetingResponse() {
        return GREETING_MESSAGE;
    }

    /**
     * Returns a goodbye message.
     */
    public static String getGoodByeResponse() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns a message indicating that the user input is invalid.
     */
    public static String getInvalidInputResponse() {
        return INVALID_INPUT;
    }

    /**
     * Returns a specific message indicating why the user input is invalid.
     *
     * @param message The specific message about invalid input.
     */
    public static String getInvalidInputResponse(String message) {
        return message;
    }

    /**
     * Returns a message indicating that the user command is invalid.
     */
    public static String getInvalidCommandResponse() {
        return INVALID_COMMAND;
    }

    /**
     * Returns a message indicating that a task has been added.
     *
     * @param task   The task that has been added.
     * @param length The number of tasks in the list after adding the task.
     */
    public static String getAddTaskResponse(Task task, int length) {
        return ADD_TASK + task + "\n" + String.format(TASK_LENGTH, length);
    }

    /**
     * Returns a message indicating that a task has been marked as completed.
     *
     * @param task The task that has been marked as completed.
     */
    public static String getMarkTaskResponse(Task task) {
        return MARK_TASK + task + "\n";
    }

    /**
     * Returns a message indicating that a task has been unmarked.
     *
     * @param task The task that has been unmarked.
     */
    public static String getUnmarkTaskResponse(Task task) {
        return UNMARK_TASK + task + "\n";
    }

    /**
     * Returns a message indicating that a task has been deleted.
     *
     * @param task   The task that has been deleted.
     * @param length The number of tasks in the list after deleting the task.
     */
    public static String getDeleteTaskResponse(Task task, int length) {
        return DELETE_TASK + task + "\n" + String.format(TASK_LENGTH, length);
    }

    /**
     * Returns a message describing the list of tasks.
     *
     * @param taskList The task list.
     */
    public static String getListTasksResponse(TaskList taskList) {
        return LIST_TASKS + taskList;
    }

    /**
     * Returns a message stating task list is empty
     */
    public static String getEmptyListResponse() {
        return EMPTY_LIST;
    }

    /**
     * Returns a message describing the list of found tasks in the filtered task list.
     *
     * @param filteredTaskList The filtered task list containing tasks to get.
     */
    public static String getFoundTasksResponse(TaskList filteredTaskList) {
        String foundString = filteredTaskList.getLength() > 0 ? FOUND_TASKS : FOUND_NO_TASKS;
        return foundString + filteredTaskList;
    }

    /**
     * Retrieves the help message.
     *
     * @return The help message.
     */
    public static String getHelpMessage() {
        return HELP;
    }
}
