package Actions;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.InvalidInput;
import Exceptions.ValueNotFound;
import Tasks.Task;

public abstract class Template {
    protected static Boolean isTrigger(String input, String trigger) {
        return input.toLowerCase().replaceAll(" ", "").equals(trigger);
    }

    protected static Boolean isTriggerPrefix(String input, String trigger) {
        Pattern pattern = Pattern.compile("^" + trigger + " .+");
        Matcher matcher = pattern.matcher(input.toLowerCase());
        return matcher.find();
    }

    protected static int getNumber(String input, String trigger) throws InvalidInput {
        String number = input.toLowerCase().replaceFirst("^" + trigger, "");
        number = number.replaceAll(" ", "");
        if (number.equals("")) throw new InvalidInput("Please provide an item index for me to work on :)");
        return Integer.parseInt(number);
    }

    protected static String removePrefix(String input, String prefix) {
        return input.replaceFirst("^" + prefix + " ", "");
    }

    protected static Boolean isValidIndex(int index, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException {
        if (index > 0 && index <= list.size()) return true;
        throw new ArrayIndexOutOfBoundsException("That task is not available. Please try that on a valid index.");
    }

    protected static String processParams(String input) {
        Pattern pattern = Pattern.compile("^[^\\/]+");
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find())
            return input;
        String result = matcher.group();
        String tmp = input.replaceFirst("^[^/]+", "");
        if (tmp.equals(""))
            return input;
        tmp = tmp.replaceAll("(/)([^ ]+)", "$2:");
        result = result + "(" + tmp + ")";
        return result;
    }

    protected static void numOfTasks(ArrayList<Task> list) {
        if (list.size() == 0) {
            System.out.println("Now you have no tasks in the list.");
            return;
        } 
        System.out.println("Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
    }

    public static Boolean run(String input, ArrayList<Task> list) throws ArrayIndexOutOfBoundsException, ValueNotFound, InvalidInput {
        throw new ValueNotFound("Don't call template");
    }
}
