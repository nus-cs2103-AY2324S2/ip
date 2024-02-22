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
    public static boolean check(String input, ArrayList<Task> list)
            throws ValueNotFound, InvalidInput {
        if (isTrigger(input, trigger)) {
            throw new ValueNotFound("You need to provide a value for find");
        }
        if (!isTriggerPrefix(input, trigger)) {
            return false;
        }
        return true;
    }

    public static String run(String input, ArrayList<Task> list) {
        String result = "";
        if (list.size() == 0) {
            return "Sorry, you haven't created any tasks yet :(\n";
        }
        Pattern pattern = Pattern.compile(removePrefix(input, trigger).toLowerCase());
        Matcher matcher;
        boolean isMatching = false;
        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i).toString());
            if (matcher.find()) {
                isMatching = true;
                break;
            }
        }
        if (!isMatching) {
            return "Sorry, there are no matching tasks :(\n";
        }
        result += " Here are matching the tasks in your list:\n";
        for (int i = 0; i < list.size(); i++) {
            matcher = pattern.matcher(list.get(i).toString());
            if (matcher.find()) {
                result += i + 1 + "." + (list.get(i).toString()) + "\n";
            }
        }
        return result;
    }
}
