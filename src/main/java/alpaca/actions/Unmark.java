package alpaca.actions;

import alpaca.exceptions.InvalidInput;
import alpaca.exceptions.ValueNotFound;
import alpaca.tasks.Task;

import java.util.ArrayList;

/**
 * Handles the processing of the Unmark command.
 **/
public abstract class Unmark extends Template {
    protected static String trigger = "unmark";

    protected static boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "")
                .replaceAll("[0-9]", "").equals(trigger);
    }

    /**
     * Unmarks a task on the list.
     *
     * @param input The command inputted
     * @param list  The list of tasks to unmark
     * @throws ArrayIndexOutOfBoundsException If an item with that index does not
     *                                        exist
     * @throws ValueNotFound                  If there is additional information
     *                                        following the command
     * @throws InvalidInput                   If the information following the
     *                                        command is not comprehesible
     **/
    public static boolean check(String input, ArrayList<Task> list)
            throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (input.toLowerCase().equals(trigger)) {
            throw new ValueNotFound("Unmark needs a index");
        }
        if (!isTriggerPrefix(input, trigger)) {
            return false;
        }
        if (!isTrigger(input, trigger)) {
            throw new InvalidInput("That's not a valid input for Unmark");
        }
        int index = getNumber(input, trigger) - 1;
        if (index < 0 || index >= list.size()) {
            throw new ArrayIndexOutOfBoundsException("An item does not exist at that index");
        }
        return true;
    }

    public static String run(String input, ArrayList<Task> list) throws InvalidInput{
        String result = "";
        int index = getNumber(input, trigger) - 1;
        Task task = list.get(index);
        task.setUndone();
        result += "OK, I've marked this task as not done yet:\n";
        result += task.toString();
        return result;
    }
}