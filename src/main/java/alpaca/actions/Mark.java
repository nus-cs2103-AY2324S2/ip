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

    protected static boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "")
                .replaceAll("[0-9]", "").equals(trigger);
    }

    /**
     * Marks a task on the list
     * 
     * @param input The command inputted
     * @param list  The list of tasks to mark
     * @throws ArrayIndexOutOfBoundsException If an item with that index does not
     *                                        exist
     * @throws ValueNotFound                  If there is additional information
     *                                        following the command
     * @throws InvalidInput                   If the information following the
     *                                        command is not comprehesible
     **/
    public static boolean run(String input, ArrayList<Task> list)
            throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (input.toLowerCase().equals(trigger)) {
            throw new ValueNotFound("Mark needs a index");
        }
        if (!isTriggerPrefix(input, trigger)) {
            return false;
        }
        if (!isTrigger(input, trigger)) {
            throw new InvalidInput("That's not a valid input for Mark");
        }
        int index = getNumber(input, trigger) - 1;
        if (index < 0 || index >= list.size()) {
            throw new ArrayIndexOutOfBoundsException("An item does not exist at that index");
        }
        Task task = list.get(index);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        return true;
    }
}
