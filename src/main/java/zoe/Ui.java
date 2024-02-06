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
     * Prints opening statement
     */
    public String saysHi() {
        return "Hello I am Zoe! What can I do for you today?";
    }

    /**
     * Prints closing statement
     */
    public String saysBye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints requirement for todo
     */
    public String todoDescription() {
        return "Sorry! todo requires a description like todo XYZ";
    }

    /**
     * Alerts user that they have keyed in an invalid command, prints list of valid commands
     */
    public String invalidCommand(){
        return "Sorry! I can't carry this out. I can only perform these currently: " + functions;
    }

    /**
     * Alerts user that the index does not exist, gives user current size
     */
    public String invalidIndex(int size) {
        return "This task does not exist! " +
                "Input a positive number among tasks in list starting from 1 to " + size;
    }

    public String taskDoesNotExist(int size) {
        return String.format
                ("Sorry, I can't find the task you have keyed in\nThere are only %d tasks in your list", size);
    }

    public String addedTask(Task t, int size) {
        return String.format
                ("I've added this task:\n%s\nNow there are %d tasks in your list :D", t.getStatus(), size);
    }

    public String markedTask(Task t) {
        return String.format("Nice! I've marked this task as done:\n%s", t.getStatus());
    }
    public String unmarkedTask(Task t) {
        return String.format("Ok, I've marked this task as not done yet:\n%s", t.getStatus());
    }

    public String deletedTask(Task t, int size) {
        return String.format
                ("Noted! this task gon like pentagon:\n%s\nThere are %d tasks left in the list", t.getStatus(), size);
    }

    public String invalidDate() {
        return "Sorry, right now I only accept deadline dates in the form yyyy-mm-dd";
    }
}