package alpaca.actions;

import java.util.ArrayList;

import alpaca.tasks.Task;
import alpaca.exceptions.ValueNotFound;
import alpaca.exceptions.InvalidInput;

/**
 * Handles the processing of the Unmark command
 **/
public abstract class Unmark extends Template {
    protected static String trigger = "unmark";

    protected static Boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").replaceAll("[0-9]", "").equals(trigger);
    }

    /**
     * Determines if the input is referring to this case and if the inputs are valid
     **/
    public static Boolean run(String input, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (input.toLowerCase().equals(trigger)) throw new ValueNotFound("Unmark needs a index");
        if (!isTriggerPrefix(input, trigger)) return false;
        if (!isTrigger(input, trigger)) throw new InvalidInput("That's not a valid input for Unmark");
        int index = getNumber(input, trigger) - 1;
        if (index < 0 || index >= list.size()) throw new ArrayIndexOutOfBoundsException("An item does not exist at that index");
        Task task = list.get(index);
        task.setUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        return true;
    }
}