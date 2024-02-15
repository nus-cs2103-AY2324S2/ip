package alpaca.actions;

import alpaca.exceptions.InvalidInput;
import alpaca.tasks.Task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Template {
    protected static boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").equals(trigger);
    }

    protected static boolean isTriggerPrefix(String input, String trigger) {
        Pattern pattern = Pattern.compile("^" + trigger + " .+");
        Matcher matcher = pattern.matcher(input.toLowerCase());
        return matcher.find();
    }

    protected static int getNumber(String input, String trigger) throws InvalidInput {
        String number = input.toLowerCase().replaceFirst("^" + trigger, "");
        number = number.replaceAll(" ", "");
        if (number.equals("")) {
            throw new InvalidInput("Please provide an item index for me to work on :)");
        }
        return Integer.parseInt(number);
    }

    protected static String removePrefix(String input, String prefix) {
        return input.replaceFirst("^(?i)" + prefix + " ", "");
    }

    protected static boolean isValidIndex(int index, ArrayList<Task> list)
            throws ArrayIndexOutOfBoundsException {
        if (index > 0 && index <= list.size()) {
            return true;
        }
        throw new ArrayIndexOutOfBoundsException(
                "That task is not available. Please try that on a valid index.");
    }

    protected static String numOfTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            return "Now you have no tasks in the list.";
        }
        return "Now you have " + list.size() + " task" 
                + (list.size() == 1 ? "" : "s") + " in the list.";
    }
}
