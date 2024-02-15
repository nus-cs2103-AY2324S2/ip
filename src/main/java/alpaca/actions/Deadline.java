package alpaca.actions;

import alpaca.exceptions.InvalidInput;
import alpaca.exceptions.ValueNotFound;
import alpaca.tasks.Task;

import java.util.ArrayList;

/**
 * Handles the creation of {@link alpaca.tasks.Deadline}.
 **/
public abstract class Deadline extends Template {
    protected static String trigger = "deadline";

    protected static boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").replaceAll("[0-9]", "").equals(trigger);
    }

    /**
     * Creates a Deadline task and adds it to the list.
     *
     * @param input The command inputted
     * @param list  The list to put the newly created task in
     * @throws ArrayIndexOutOfBoundsException If an item with that index does not
     *                                        exist
     * @throws ValueNotFound                  If there is additional information
     *                                        following the command
     * @throws InvalidInput                   If the information following the
     *                                        command is not comprehesible
     **/
    public static boolean check(String input, ArrayList<Task> list)
            throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (isTrigger(input, trigger)) {
            throw new ValueNotFound("You need to provide a value for deadline");
        }
        if (!isTriggerPrefix(input, trigger)) {
            return false;
        }
        return true;
    }

    public static String run(String input, ArrayList<Task> list) {
        String result = "";
        Task task = new alpaca.tasks.Deadline(removePrefix(input, trigger));
        list.add(task);
        result += "Got it. I've added this task:\n";
        result += "added: " + task.toString() + "\n";
        result += numOfTasks(list);
        return result;
    }
}