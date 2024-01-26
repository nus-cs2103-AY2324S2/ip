import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotChat {
    private static boolean terminate = false;
    private static Task[] dataStore = new Task[100];
    private static int lastIdx = 0;
    private static Pattern markPattern = Pattern.compile("mark \\d+");
    private static Pattern unmarkPattern = Pattern.compile("unmark \\d+");

    public static void addTask(String s) throws Exception {
        if (s.startsWith("todo")) {
            if (s.split("todo ").length < 2) {
                throw new IncompleteCommandException("Todo command incomplete. It should be in the form of " +
                        "todo description.");
            } else {
                dataStore[lastIdx] = new ToDo(s.split("todo ")[1]);
            }
        } else if (s.startsWith("deadline")) {
            String slicedString = s.substring(8); // slice away "deadline "
            String[] stringParts = slicedString.split("/by ");
            if (stringParts.length < 2) {
                throw new IncompleteCommandException("Deadline command incomplete. It should be in the form of " +
                        "deadline description /by datetime.");
            } else {
                dataStore[lastIdx] = new Deadline(stringParts[0], stringParts[1]);
            }
        } else if (s.startsWith("event")) {
            String slicedString = s.substring(5);
            String[] stringParts = slicedString.split("/from |/to ");
            if (stringParts.length < 3) {
                throw new IncompleteCommandException("Event command incomplete. It should be in the form of " +
                        "event description /from datetime /to datetime.");
            } else {
                dataStore[lastIdx] = new Event(stringParts[0], stringParts[1], stringParts[2]);
            }
        } else {
            throw new InvalidCommandException(s);
        }
        lastIdx++;
    }

    public static String response(String s) {
        Matcher markMatcher = markPattern.matcher(s);
        Matcher unmarkMatcher = unmarkPattern.matcher(s);
        if (s.equals("bye")) {
            terminate = true;
            return "Bye. Hope to see you again soon!";
        } else if (s.equals("list")) {
            StringBuilder stringBuilder = new StringBuilder("Here are the tasks in your list: \n");
            for (int i = 1; i <= lastIdx; i++) {
                stringBuilder.append(i);
                stringBuilder.append(". ");
                stringBuilder.append(dataStore[i-1].toString());
                stringBuilder.append("\n ");;
            }
            return stringBuilder.toString();
        } else if (markMatcher.matches()) {
            int taskNum = Integer.parseInt(s.split("\\s+")[1]);
            dataStore[taskNum - 1].markAsDone();
            return String.format("Nice! I've marked this task as done: \n %s", dataStore[taskNum - 1].toString());
        } else if (unmarkMatcher.matches()) {
            int taskNum = Integer.parseInt(s.split("\\s+")[1]);
            dataStore[taskNum - 1].markAsUndone();
            return String.format("OK, I've marked this task as not done yet: \n %s", dataStore[taskNum - 1].toString());
        } else {
            try {
                addTask(s);
                return String.format("Got it. I've added this task:\n %s \n Now you have %d tasks in the list.",
                        dataStore[lastIdx - 1].toString(), lastIdx);
            } catch (Exception e) {
                return e.toString();
            }
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm BotChat.\n What can I do for you?";
        System.out.println(greeting);

        Scanner userInput = new Scanner(System.in);

        while (!terminate) {
            String command = userInput.nextLine();
            System.out.println(response(command));
        }
    }
}
