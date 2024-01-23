import java.util.*;

public class Methods {
    // A method to split the input to get:
    // text[0] == command
    // text[1] == additional stuff
    public static String[] retrieveTexts(String input) {
        String[] resultOfSplit = new String[2];
        int indexOfFirstSpace = input.indexOf(" ");
        if (indexOfFirstSpace == -1) {
            resultOfSplit[0] = input;
            resultOfSplit[1] = null;
        } else {
            resultOfSplit[0] = input.substring(0, indexOfFirstSpace);
            resultOfSplit[1] = input.substring(indexOfFirstSpace + 1);
        }
        return resultOfSplit;
    }

    // A method to retrieve the enum commands:
    public static Commands retrieveCommand(String input) {
        try {
            return Commands.valueOf(input);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    // A method to mark/unmark the tasks:
    public static void markAndUnmark(String input, List<Task> tasks) {
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        tasks.get(actualId).changeIsDone();
        System.out.println(tasks.get(actualId).toString() + "\n");
    }

    // A method to print out all of the tasks:
    public static void printList(List<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString() + "\n");
                break;
            }
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    // A method to split by "by":
    public static String[] splitBy(String input) {
        String[] twoParts = input.split(" /by ", 2);
        return twoParts;
    }

    // A method to split by "from" and "to":
    public static String[] splitToAndFrom(String input) {
        String[] firstSplit = input.split(" /from ", 2);
        String[] secondSplit = firstSplit[1].split(" /to ", 2);
        String[] threeParts = { firstSplit[0], secondSplit[0], secondSplit[1] };
        return threeParts;
    }

    // A method to add the tasks (regardless of todo, deadline, etc) to list of
    // tasks:
    public static void addTask(Task task, List<Task> tasks) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }
}
