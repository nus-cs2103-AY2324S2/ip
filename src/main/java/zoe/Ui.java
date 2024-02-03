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
    public void saysHi() {
        System.out.println("Hello! I'm Zoe");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints closing statement
     */
    public void saysBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints requirement for todo
     */
    public void todoDescription() {
        System.out.println("Sorry! todo requires a description like todo XYZ");
    }

    /**
     * Alerts user that they have keyed in an invalid command, prints list of valid commands
     */
    public void invalidCommand(){
        System.out.println("Sorry! I can't carry this out. I can only perform these currently: " + functions);
    }

    /**
     * Alerts user that the index does not exist, gives user current size
     */
    public void invalidIndex(int i) {
        System.out.println("This task does not exist! " +
                "Input a positive number among tasks in list starting from 1 to " + i);
    }

    public void taskDoesNotExist() {
        System.out.println("Sorry, I can't find the task you have keyed in");
    }

}