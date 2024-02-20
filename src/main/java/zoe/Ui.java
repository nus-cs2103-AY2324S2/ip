package zoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Prints statements to interact with user
 */
public class Ui {
    protected ArrayList<String> functions = new ArrayList<String>();

    public Ui() {
        functions.addAll(List.of("list", "mark", "unmark", "todo", "deadline", "event", "delete", "find"));
    }

    /**
     * Makes Zoe say hi when a user runs the programme
     */
    public String saysHi() {
        return "Hello I am Zoe! What can I do for you today?";
    }

    /**
     * Makes Zoe say bye when a user closes a programme
     */
    public String saysBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Alerts user of the required todo format which requires a description
     */
    public String todoDescription() {
        return "Sorry! todo requires a description like todo XYZ";
    }

    /**
     * Alerts user that they have keyed in an invalid command, prints list of valid commands
     */
    public String invalidCommand() {
        return String.format
                ("Sorry! Invalid command or incorrect input. I can only perform these currently: %s " +
                        "\nIf you are trying to call task related commands, refer to the website :D", functions);
    }

    /**
     * Alerts user that the index does not exist, gives user current size
     */
    public String invalidIndex(int size) {
        return String.format
                ("This task does not exist! Input a positive number among tasks in list starting from 1 to %d", size);
    }

    /**
     * Alerts user that the index does not exist, this is specifically for finding a task
     */
    public String taskDoesNotExist(int size) {
        return String.format
                ("Sorry, I can't find the task you have keyed in\nThere are only %d tasks in your list", size);
    }

    /**
     * Alerts user on successful addition
     */
    public String addedTask(Task t, int size) {
        return String.format
                ("I've added this task:\n%s\nNow there are %d tasks in your list :D", t.getStatus(), size);
    }

    /**
     * Alerts user on successful mark of a task
     */
    public String markedTask(Task t) {
        return String.format("Nice! I've marked this task as done:\n%s", t.getStatus());
    }

    /**
     * Alerts user on successful unmarking of a task
     */
    public String unmarkedTask(Task t) {
        return String.format("Ok, I've marked this task as not done yet:\n%s", t.getStatus());
    }

    /**
     * Alerts user on successful deletion of a task
     */
    public String deletedTask(Task t, int size) {
        return String.format
                ("Noted! this task gon like pentagon:\n%s\nThere are %d tasks left in the list", t.getStatus(), size);
    }

    /**
     * Alerts user in the event they keyed in an invalid date
     */
    public String invalidDate() {
        return "Sorry, I only accept deadline dates in the form yyyy-mm-dd";
    }

    /**
     * Alerts user in the event they keyed in an invalid event format
     */
    public String eventFormat(){
        return "Invalid event format. Please key it in as follows:\n" +
                "event <description> /from <start> /to <end>\nThere are no requirements for from or to.";
    }

    /**
     * Alerts user in the event they keyed in an invalid deadline format
     */
    public String deadlineFormat(){
        return "Invalid deadline format. Please key it in as follows:\n" +
                "deadline <description> /by yyyy-mm-dd\nI only accept that specific date format.";
    }
}