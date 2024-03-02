package alpaca.actions;

import alpaca.exceptions.InvalidInput;
import alpaca.exceptions.ValueNotFound;
import alpaca.tasks.Task;

import java.util.ArrayList;

/**
 * Handles the processing of the Delete command.
 **/
public abstract class Delete extends Template {
    protected static String trigger = "delete";

    protected static boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "")
                .replaceAll("[0-9]", "").equals(trigger);
    }

    /**
     * Processes delete command.
     *
     * @param input The command inputted
     * @param list  The list to delete tasks from
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
            throw new ValueNotFound("Delete needs a index");
        }
        if (!isTriggerPrefix(input, trigger)) {
            return false;
        }
        if (!isTrigger(input, trigger)) {
            throw new InvalidInput("That's not a valid input for Delete");
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
        list.remove(task);
        result += "Noted. I've removed this task:\n";
        result += task.toString() + "\n";
        result += numOfTasks(list);
        return result;
    }
}
