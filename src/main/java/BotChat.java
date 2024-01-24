import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BotChat {
    static boolean terminate = false;
    static Task[] dataStore = new Task[100];
    static int lastIdx = 0;
    static Pattern markPattern = Pattern.compile("mark \\d+");
    static Pattern unmarkPattern = Pattern.compile("unmark \\d+");

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
                stringBuilder.append(". [");
                if (dataStore[i-1].getDoneStatus()) {
                    stringBuilder.append("X");
                } else {
                    stringBuilder.append(" ");
                }
                stringBuilder.append("] ");
                stringBuilder.append(dataStore[i-1].toString());
                stringBuilder.append("\n ");;
            }
            return stringBuilder.toString();
        } else if (markMatcher.matches()) {
            int taskNum = Integer.parseInt(s.split("\\s+")[1]);
            dataStore[taskNum - 1].markAsDone();
            StringBuilder stringBuilder = new StringBuilder("Nice! I've marked this task as done: \n [X] ");
            stringBuilder.append(dataStore[taskNum - 1].toString());
            return stringBuilder.toString();
        } else if (unmarkMatcher.matches()) {
            int taskNum = Integer.parseInt(s.split("\\s+")[1]);
            dataStore[taskNum - 1].markAsUndone();
            StringBuilder stringBuilder = new StringBuilder("OK, I've marked this task as not done yet: \n [ ] ");
            stringBuilder.append(dataStore[taskNum - 1].toString());
            return stringBuilder.toString();
        } else {
            dataStore[lastIdx] = new Task(s);
            lastIdx++;
            return "added: " + s;
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
