import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotChat {
    private static final String FILEPATH = "./././data/botchat.txt";
    private static boolean terminate = false;
    private static DataStore dataStore;
    private static Pattern markPattern = Pattern.compile("mark \\d+");
    private static Pattern unmarkPattern = Pattern.compile("unmark \\d+");

    public static void addTask(String s) throws Exception {
        if (s.startsWith("todo")) {
            if (s.split("todo ").length < 2) {
                throw new IncompleteCommandException("Todo command incomplete. It should be in the form of " +
                        "todo description.");
            } else {
                dataStore.addToDataStore(new ToDo(s.split("todo ")[1]));
            }
        } else if (s.startsWith("deadline")) {
            String slicedString = s.substring(8); // slice away "deadline "
            String[] stringParts = slicedString.split("/by ");
            if (stringParts.length < 2) {
                throw new IncompleteCommandException("Deadline command incomplete. It should be in the form of " +
                        "deadline description /by datetime.");
            } else {
                dataStore.addToDataStore(new Deadline(stringParts[0], stringParts[1]));
            }
        } else if (s.startsWith("event")) {
            String slicedString = s.substring(5);
            String[] stringParts = slicedString.split("/from |/to ");
            if (stringParts.length < 3) {
                throw new IncompleteCommandException("Event command incomplete. It should be in the form of " +
                        "event description /from datetime /to datetime.");
            } else {
                dataStore.addToDataStore(new Event(stringParts[0], stringParts[1], stringParts[2]));
            }
        } else {
            throw new InvalidCommandException(s);
        }
    }

    public static String response(String s) {
        Matcher markMatcher = markPattern.matcher(s);
        Matcher unmarkMatcher = unmarkPattern.matcher(s);
        if (s.equals("bye")) {
            terminate = true;
            return "Bye. Hope to see you again soon!";
        } else if (s.equals("list")) {
            StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 1; i <= dataStore.getLastIdx(); i++) {
                stringBuilder.append(i);
                stringBuilder.append(". ");
                stringBuilder.append(dataStore.getTaskByIdx(i-1).toString());
                stringBuilder.append("\n ");;
            }
            return stringBuilder.toString();
        } else if (markMatcher.matches()) {
            try {
                return markTaskAsDone(s);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        } else if (unmarkMatcher.matches()) {
            try {
                return unmarkTaskAsDone(s);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        } else if (s.startsWith("delete")) {
            String requestedDeletion = s.substring(7);
            try {
                return deleteTask(requestedDeletion);
            } catch (InvalidTaskNumberException e) {
                return e.toString();
            }
        } else {
            try {
                addTask(s);
                return String.format("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.",
                        dataStore.getTaskByIdx(dataStore.getLastIdx() - 1).toString(),
                        dataStore.getLastIdx());
            } catch (Exception e) {
                return e.toString();
            }
        }
    }

    public static String markTaskAsDone(String s) throws InvalidTaskNumberException {
        String taskNumString = s.split("\\s+")[1];
        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum > dataStore.getLastIdx()) {
            throw new InvalidTaskNumberException(taskNumString);
        }
        dataStore.getTaskByIdx(taskNum - 1).markAsDone();
        return String.format("Nice! I've marked this task as done: \n %s",
                dataStore.getTaskByIdx(taskNum - 1).toString());
    }

    public static String unmarkTaskAsDone(String s) throws InvalidTaskNumberException {
        String taskNumString = s.split("\\s+")[1];
        int taskNum = Integer.parseInt(taskNumString);
        if (taskNum > dataStore.getLastIdx()) {
            throw new InvalidTaskNumberException(taskNumString);
        }
        dataStore.getTaskByIdx(taskNum - 1).markAsUndone();
        return String.format("Nice! I've marked this task as done: \n %s",
                dataStore.getTaskByIdx(taskNum - 1).toString());
    }

    public static String deleteTask(String requestedDeletion) throws InvalidTaskNumberException {
        try {
            int taskNum = Integer.parseInt(requestedDeletion);
            if (taskNum > dataStore.getLastIdx()) {
                throw new InvalidTaskNumberException(requestedDeletion);
            }
            String deletedTaskString = dataStore.getTaskByIdx(taskNum - 1).toString();
            dataStore.removeFromDataStore(taskNum - 1);
            dataStore = new DataStore(FILEPATH);
            return String.format("Noted. I've removed this task: \n %s \n Now you have %d tasks in the list.",
                    deletedTaskString, dataStore.getLastIdx());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(requestedDeletion);
        }
    }

    public static void main(String[] args) {
        dataStore = new DataStore(FILEPATH);

        String greeting = "Hello! I'm BotChat.\n What can I do for you?";
        System.out.println(greeting);

        Scanner userInput = new Scanner(System.in);

        while (!terminate) {
            String command = userInput.nextLine();
            System.out.println(response(command));
        }
    }
}
