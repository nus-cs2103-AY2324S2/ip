import java.util.Scanner;

/**
 * Ui class that deals with interactions with the users.
 */
public class Ui {
    /** An empty line */
    private String emptyLine = "____________________________________________________________\n";

    /** Greeting sentences */
    private String greeting = "Hello from Miss A\n"
            + "What can I do for you?\n"
            + emptyLine
            + "I can record 3 types of tasks now.\n"
            + "ToDos: e.g. todo clean my room\n"
            + "Deadlines: e.g. deadline submit homework /by 2021-4-1 21\n"
            + "Events: e.g. event lecture /from 2021-4-2 14 /to 2021-4-2 16\n"
            + emptyLine
            + "Here are the commands that I can understand:)\n"
            + "\"list\": I will display the latest task list.\n"
            + "\"mark/unmark + number\": I will mark/unmark task in the list!\n"
            + "\"delete + number\": I will remove task from the list.\n"
            + "\"bye\": this program will be closed.\n"
            + emptyLine;

    /** Goodbye sentences */
    private String goodBye = emptyLine
            + "Bye. Have a nice day!\n"
            + emptyLine;

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
        return emptyLine + "\n" + e.getMessage() + "\n" + emptyLine;
    }

    /**
     * Replies to delete command.
     *
     * @param taskinfo Task to be deleted.
     * @param noOfTasks Number of tasks left.
     * @return A message to show that the task is deleted successfully.
     */
    public String replyDeleteCommand(Task taskinfo, int noOfTasks) {
        return emptyLine
            + "Noted. I will remove this task:\n"
            + "  " + taskinfo
            + "\n"
            + "Now you have " + noOfTasks + " tasks!\n"
            + emptyLine;
    }

    /**
     * Replies to add tasks command.
     *
     * @param nextTask Task to be added.
     * @param noOfTasks Number of tasks in taskList.
     * @return A message to show that the task is added successfully.
     */
    public String replyAddCommand(Task nextTask, int noOfTasks) {
        return emptyLine
                + "Ok, I will add in this task:\n"
                + "  " + nextTask
                + "\n"
                + "Now there are " + noOfTasks + " tasks in the list.\n"
                + emptyLine;
    }

    /**
     * Replies to unmark tasks command.
     *
     * @return A message to show that the task is unmarked.
     */
    public String replyUnmarkCommand() {
        return emptyLine
                + "Ok, this task is still in progress :-(\n"
                + emptyLine;
    }

    /**
     * Replies to mark tasks command.
     *
     * @return A message to show that the task is marked.
     */
    public String replyMarkCommand() {
        return emptyLine
                + "Great! You have completed this task!\n"
                + emptyLine;
    }

    /**
     * Replies to list command.
     *
     * @param tasks String representation of tasks in taskList.
     * @return String representation of tasks.
     */
    public String loadingTaskList(String tasks) {
        return emptyLine
                + "Here are your tasks:\n"
                + tasks
                + emptyLine;
    }

    /**
     * Gets user input.
     *
     * @return A string of user input.
     */
    public String readInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }
}
