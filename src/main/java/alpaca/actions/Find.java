package alpaca.actions;

import alpaca.exceptions.InvalidInput;
import alpaca.exceptions.ValueNotFound;
import alpaca.tasks.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handles the processing of the Delete command.
 **/
public abstract class Find extends Template {
    protected static String trigger = "find";

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
    public static boolean run(String input, ArrayList<Task> list)
            throws ValueNotFound, InvalidInput {
        if (isTrigger(input, trigger)) {
            throw new ValueNotFound("You need to provide a value for find");
        }
        if (!isTriggerPrefix(input, trigger)) {
            return false;
        }
        if (list.size() == 0) {
            System.out.println("Sorry, you haven't created any tasks yet :(");
            return true;
        }
        Pattern pattern = Pattern.compile(removePrefix(input, trigger).toLowerCase());
        Matcher matcher;
        boolean oneMatch = false;
        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i).toString());
            if (matcher.find()) {
                oneMatch = true;
                break;
            }
        }
        if (!oneMatch) {
            System.out.println("Sorry, there are no matching tasks :(");
            return true;
        }
        System.out.println(" Here are matching the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i).toString());
            if (matcher.find()) {
                System.out.println(i + 1 + "." + (list.get(i).toString()));
            }
        }
        return true;
    }
}
