package alpaca.actions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import alpaca.tasks.Task;
import alpaca.exceptions.ValueNotFound;
import alpaca.exceptions.InvalidInput;

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
        if (number.equals(""))
            throw new InvalidInput("Please provide an item index for me to work on :)");
        return Integer.parseInt(number);
    }

    protected static String removePrefix(String input, String prefix) {
        return input.replaceFirst("^(?i)" + prefix + " ", "");
    }

    protected static boolean isValidIndex(int index, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException {
        if (index > 0 && index <= list.size())
            return true;
        throw new ArrayIndexOutOfBoundsException("That task is not available. Please try that on a valid index.");
    }

    protected static void numOfTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("Now you have no tasks in the list.");
            return;
        }
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
    }

    public static boolean run(String input, ArrayList<Task> list)
            throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        throw new ValueNotFound("Don't call template");
    }
}
