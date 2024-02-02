package duke.ui;//package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * The Ui class manages user interface interactions and displays messages to the user.
 */
public class Ui {
    /** The name of the chat bot. */
    String botName;

    /**
     * Constructs a Ui object with the specified bot name.
     *
     * @param botName the name of the bot
     */
    public Ui(String botName) {
        this.botName = botName;
    }

    /**
     * Displays a message with horizontal lines above and below it.
     *
     * @param message the message to be displayed
     */
    public static void messageWithHorizontalLines(String message) {
        System.out.println("____________________________________________________________\n" +
                message + "\n" +
                "____________________________________________________________");
    }

    /**
     * Sends a welcome message to the user.
     * Can alter welcomeStr
     */
    public void sendWelcome() {
        String welcomeStr = " Hello! I'm " + botName + "\n What can I do for you?";
        messageWithHorizontalLines(welcomeStr);
    }

    /**
     * Sends a goodbye message to the user.
     * Can alter byeStr
     */
    public void sendGoodbye() {
        String byeStr = "Bye. Hope to see you again soon!";
        messageWithHorizontalLines(byeStr);
    }

    /**
     * Sends this when user input is not unrecognized.
     */
    public void badUserInput() {
        messageWithHorizontalLines(botName + " does not understand you :((");
    }

    /**
     * Sends the list of current tasks
     * Also sends the amount of tasks
     *
     * @param taskList the task lists
     */
    public void showTaskList(ArrayList<Task> taskList) {
        if (taskList.isEmpty()) {
            messageWithHorizontalLines("There are no tasks!");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(" " + (i + 1) + "." + task);
            }
            System.out.println("____________________________________________________________");
        }
    }

    public void addNewTask(Task addedTask, int taskListSize) {
        messageWithHorizontalLines("Got it. I have added " + addedTask + " into Task List.");
    }

    public void deleteTask(Task deletedTask, int taskListSize) {
        messageWithHorizontalLines("Task has been successfully removed:\n"
                                    + " " + deletedTask + "\n"
                                    + "There are " + taskListSize + " in the task list currently.");
    }

    public void invalidTaskIndex() {
        messageWithHorizontalLines("Invalid Task Index!");
    }

    public void markAsDone(Task doneTask) {
        messageWithHorizontalLines("Nice! I've marked this task as done:\n" + "  " + doneTask);
    }

    public void markAsUndone(Task undoneTask) {
        messageWithHorizontalLines("OK, I've marked this task as not done:\n" + "  " + undoneTask);
    }

    /**
     * Sends when user enter invalid index
     */
    public void noIndexDeleteTask() {
        System.out.println("Please provide the task index you want to delete.");
    }

    /**
     * Sends when user enter invalid index
     */
    public void noIndexMarkAsDone() {
        System.out.println("Please provide the task index to mark as done.");
    }

    /**
     * Sends when user enter invalid index
     */
    public void noIndexMarkAsUndone() {
        System.out.println("Please provide the task index to mark as not done.");
    }

    /**
     * Sends when user enter invalid date format
     */
    public void invalidDateInput() {
        System.out.println("Error input: Date format is invalid (Date format: YYYY-MM-DD)");
    }

    public void insufficientTodoDescription() {
        System.out.println("Please provide a description for your Todo task.");
    }

    public void insufficientDeadline() {
        System.out.println("Please provide a deadline for your Deadline task.");
    }

    public void insufficientEventStartTimeEndTime() {
        System.out.println("Please provide both starting time and ending time for your event task.");
    }

    public void insufficientEventStartTime() {
        System.out.println("Please provide a starting time for your event task.");
    }

    public void insufficientEventEndTime() {
        System.out.println("Please provide a ending time for your event task.");
    }

    public void invalidEventStartingTimeAndEndingTime() {
        System.out.println("Error input: Start time should be earlier than end time.");
    }

    public void noMatchingTasks(String keyword) {
        System.out.println("There are no tasks matching with '" + keyword + "'.");
    }

    /**
     * Sends when user forgot to enter a keyword when using searching feature.
     */
    public void noKeywordsForSearching() {
        System.out.println("Please provide a keyword to search.");
    }
}

