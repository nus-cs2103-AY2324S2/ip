package alpaca.actions;

import alpaca.exceptions.InvalidInput;
import alpaca.exceptions.ValueNotFound;
import alpaca.tasks.Task;

import java.util.ArrayList;

/**
 * Handles the creation of {@link alpaca.tasks.Event}.
 **/
public abstract class Event extends Template {
    protected static String trigger = "event";

    protected static boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").replaceAll("[0-9]", "").equals(trigger);
    }

    /**
     * Creates a Event task and adds it to the list.
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
    public static boolean run(String input, ArrayList<Task> list)
            throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        if (isTrigger(input, trigger)) {
            throw new ValueNotFound("You need to provide a value for event");
        }
        if (!isTriggerPrefix(input, trigger)) {
            return false;
        }
        Task task = new alpaca.tasks.Event(removePrefix(input, trigger));
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("added: " + task.toString());
        numOfTasks(list);
        return true;
    }
}