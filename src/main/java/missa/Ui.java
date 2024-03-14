package missa;

import missa.task.Task;

/**
 * missa.Ui class that deals with interactions with the users.
 */
public class Ui {
    /** An empty line */
    private String emptyLine = "________________________________________\n";

    /** Greeting sentences */
    private String greeting = "Hello from Miss A\n"
            + "What can I do for you?\n"
            + emptyLine
            + "I can record 4 types of tasks now.\n"
            + "ToDos: e.g. todo clean my room\n"
            + "Deadlines: e.g. deadline submit homework /by 2021-4-1 21\n"
            + "Events: e.g. event lecture /from 2021-4-2 14 /to 2021-4-2 16\n"
            + "DoAfter: e.g. return book /after this Sunday\n"
            + emptyLine
            + "Here are the commands that I can understand:)\n"
            + "\"list\": I will display the latest task list.\n"
            + "\"mark/unmark + number\": I will mark/unmark task in the list!\n"
            + "\"delete + number\": I will remove task from the list.\n"
            + "\"find + keyword\": I will search for relevant tasks recorded!\n"
            + "\"bye\": this program will be closed.\n";

    /** Goodbye sentences */
    private String goodBye = "Bye. Have a nice day!\n";

    /**
     * Greets users when first enter the program.
     *
     * @return A string of greeting info.
     */
    public String sayHi() {
        return this.greeting;
    }

    /**
     * Says goodbye to users.
     *
     * @return A string saying goodbye.
     */
    public String goodBye() {
        return this.goodBye;
    }

    /**
     * Shows error message to users.
     *
     * @return A string of error info.
     */
    public String showError(Exception e) {

        return e.getMessage() + "\n";
    }

    /**
     * Replies to delete missa.command.
     *
     * @param taskinfo missa.task.Task to be deleted.
     * @param noOfTasks Number of tasks left.
     * @return A message to show that the task is deleted successfully.
     */
    public String replyDeleteCommand(Task taskinfo, int noOfTasks) {
        return "Noted. I will remove this task:\n"
                + "  " + taskinfo
                + "\n"
                + "Now you have " + noOfTasks + " tasks!\n";
    }

    /**
     * Replies to add tasks missa.command.
     *
     * @param nextTask missa.task.Task to be added.
     * @param noOfTasks Number of tasks in taskList.
     * @return A message to show that the task is added successfully.
     */
    public String replyAddCommand(Task nextTask, int noOfTasks) {
        return "Ok, I will add in this task:\n"
                + "  " + nextTask
                + "\n"
                + "Now there are " + noOfTasks + " tasks in the list.\n";
    }

    /**
     * Replies to unmark tasks missa.command.
     *
     * @return A message to show that the task is unmarked.
     */
    public String replyUnmarkCommand() {
        return "Ok, this task is still in progress :-(\n";
    }

    /**
     * Replies to mark tasks missa.command.
     *
     * @return A message to show that the task is marked.
     */
    public String replyMarkCommand() {
        return "Great! You have completed this task!\n";
    }

    /**
     * Replies to list commands.
     *
     * @param tasks String representation of tasks in taskList.
     * @return String representation of tasks.
     */
    public String loadingTaskList(String tasks) {
        return "Here are your tasks:\n"
                + tasks;
    }

    /**
     * Replies to find commands.
     *
     * @param tasks String representation of matching tasks in taskList.
     * @return String representation of matching tasks.
     */
    public String replyFindCommand(String tasks) {
        return "Yes! Here are the matching tasks in your list:\n"
                + tasks;
    }
}
