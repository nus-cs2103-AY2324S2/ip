package alpaca.actions;

import java.util.ArrayList;

import alpaca.tasks.Task;
import alpaca.exceptions.ValueNotFound;
import alpaca.exceptions.InvalidInput;

/**
 * Handles the processing of the Mark command
 **/
public abstract class Mark extends Template {
    protected static String trigger = "mark";

    protected static Boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").replaceAll("[0-9]", "").equals(trigger);
    }

    /**
     * Determines if the input is referring to this case and if the inputs are valid
     **/
    public static Boolean run(String input, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (input.toLowerCase().equals(trigger)) throw new ValueNotFound("Mark needs a index");
        if (!isTriggerPrefix(input, trigger)) return false;
        if (!isTrigger(input, trigger)) throw new InvalidInput("That's not a valid input for Mark");
        int index = getNumber(input, trigger) - 1;
        if (index < 0 || index >= list.size()) throw new ArrayIndexOutOfBoundsException("An item does not exist at that index");
        Task task = list.get(index);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        return true;
    }
}
