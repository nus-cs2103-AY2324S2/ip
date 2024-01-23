import java.util.*;

public class Methods {
    // A method for the opening title:
    public static void openingTitle() {
        String logo = "William";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?\n");
    }

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
    public static Commands retrieveCommand(String input) throws WilliamException {
        try {
            return Commands.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new WilliamException("This command " + input + " does not exist, please try again!");
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
        if (tasks.isEmpty()) {
            System.out.println("Your list is empty. Please add some task to the list first!");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
        System.out.println();
    }

    // A method to split by "by":
    public static String[] splitBy(String input) throws WilliamException {
        checkAdditionalDetailEmpty(input);
        String[] twoParts = input.split(" /by ", 2);
        if (twoParts.length < 2) {
            throw new WilliamException(
                    "The input does not contain the required '/by' keyword or is missing text before/after '/by' keyword. Please try again!");
        }
        return twoParts;
    }

    // A method to split by "from" and "to":
    public static String[] splitToAndFrom(String input) throws WilliamException {
        checkAdditionalDetailEmpty(input);
        String[] firstSplit = input.split(" /from ", 2);
        if (firstSplit.length < 2) {
            throw new WilliamException(
                    "The input does not contain the required '/from' keyword or is missing text before/after '/from' keyword. Please try again!");
        }
        String[] secondSplit = firstSplit[1].split(" /to ", 2);
        if (secondSplit.length < 2) {
            throw new WilliamException(
                    "The input does not contain the required '/to' keyword or is missing text before/after '/to' keyword. Please try again!");
        }
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

    // A method to check whether the additional stuff is empty or not (if its empty,
    // throw an exception):
    public static void checkAdditionalDetailEmpty(String input) throws WilliamException {
        if (input == null) {
            throw new WilliamException("The description of a task should not be empty. Please try again!");
        }
    }
}
