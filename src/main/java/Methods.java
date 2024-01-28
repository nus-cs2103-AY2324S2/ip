import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The FileSaving class will store the methods that will be used by the
 * William.java
 */
public class Methods {
    private static final DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Adds the opening message in the William Chatbot
     */
    public static void openingTitle() {
        String logo = "William";
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?\n");
    }

    /**
     * Returns the split input after getting the user input
     * 
     * @param input Input from the user
     * @return string[] String[] consisting of two variables: [0] == command, [1] ==
     *         additional information
     */
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

    /**
     * Returns the enum command
     * 
     * @param input Input that is a command in String format
     * @return output Output that is a command in Commands format
     * @throws WilliamException If the command does not exist in the Commands class
     */
    public static Commands retrieveCommand(String input) throws WilliamException {
        try {
            return Commands.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new WilliamException("This command " + input + " does not exist, please try again!");
        }
    }

    /**
     * Unmark/mark the task based on the ID
     * 
     * @param input The ID of the task
     * @param tasks The list that contains the task
     */
    public static void markAndUnmark(String input, List<Task> tasks) {
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        tasks.get(actualId).changeIsDone();
        System.out.println(tasks.get(actualId).toString() + "\n");
    }

    /**
     * Print out of all the tasks
     * 
     * @param tasks The list that contains the task
     */
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

    /**
     * Split the method by "/by"
     * 
     * @param input Input that is the additional details
     * @return string[] String[] that contains the name and the date
     * @throws WilliamException If the input does not have the "/by" or missing text
     *                          before/after "/by"
     */
    public static String[] splitBy(String input) throws WilliamException {
        checkAdditionalDetailEmpty(input);
        String[] twoParts = input.split(" /by ", 2);
        if (twoParts.length < 2) {
            throw new WilliamException(
                    "The input does not contain the required '/by' keyword or is missing text before/after '/by' keyword. Please try again!");
        }
        acceptDateAndTime(twoParts[1]);
        return twoParts;
    }

    /**
     * Split the method by "/to" and "/from"
     * 
     * @param input Input that is the additional details
     * @return string[] String[] that contains the name, from and to date
     * @throws WilliamException If the input does not contain "/to", "/from"
     *                          keywords
     *                          and is missing text before/after the keywords
     */
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
        acceptDateAndTime(secondSplit[0]);
        acceptDateAndTime(secondSplit[1]);
        checkWhetherToAndFromValid(secondSplit[0], secondSplit[1]);
        String[] threeParts = { firstSplit[0], secondSplit[0], secondSplit[1] };
        return threeParts;
    }

    /**
     * Add task into the list of tasks
     * 
     * @param task  Input that is the task (could be todo, deadline or event)
     * @param tasks The list that contains the tasks
     */
    public static void addTask(Task task, List<Task> tasks) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * Check whether the additional detail is empty
     * 
     * @param input Input that is the additional detail
     * @throws WilliamException If the description of the additional detail is empty
     */
    public static void checkAdditionalDetailEmpty(String input) throws WilliamException {
        if (input == null) {
            throw new WilliamException("The description of a task should not be empty. Please try again!");
        }
    }

    /**
     * Delete the specified task from the list
     * 
     * @param input The ID of the task
     * @param tasks The list that contains the tasks
     */
    public static void deleteFromList(String input, List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no task to be deleted. Please add some task to the list first!\n");
        } else {
            int idOfItem = Integer.parseInt(input);
            int actualId = idOfItem - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(actualId).toString());
            tasks.remove(actualId);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
        }
    }

    /**
     * Check whether the input matches the date and time format
     * 
     * @param input The date and time of the task
     * @throws WilliamException If the format of the date and time is wrong
     */
    public static void acceptDateAndTime(String input) throws WilliamException {
        try {
            LocalDateTime.parse(input, inputFormat);
        } catch (DateTimeParseException e) {
            throw new WilliamException("The date and time format is invalid. Please try again!");
        }
    }

    /**
     * Check whether the '/from' date is before the '/to' date
     * 
     * @param fromDate The input date '/from'
     * @param toDate   The input date '/to'
     * @throws WilliamException If the '/from' date is not before the '/to' date
     */
    public static void checkWhetherToAndFromValid(String fromDate, String toDate) throws WilliamException {
        LocalDateTime fromDateModified = LocalDateTime.parse(fromDate, inputFormat);
        LocalDateTime toDateModified = LocalDateTime.parse(toDate, inputFormat);

        if (fromDateModified.isBefore(toDateModified) == false) {
            throw new WilliamException(
                    "The '/from' date and time should be before '/to' date and time. Please try again!");
        }
    }

    /**
     * Convert String date to LocalDateTime date
     * 
     * @param date Date in String
     * @return Date in LocalDateTime
     */
    public static LocalDateTime convertStringToDate(String date) {
        LocalDateTime modifiedDate = LocalDateTime.parse(date, inputFormat);
        return modifiedDate;
    }
}
