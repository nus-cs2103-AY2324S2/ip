import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "    ___________________________________________________________\n";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Task> listOfTasks = new ArrayList<>();
        boolean hasEnded = false;
        String botName = "Yube";

        greet(botName);
        while (!(hasEnded)) {
            String input = reader.readLine();
            if (input.equals("bye")) {
                bye();
                hasEnded = true;
            } else if (input.equals("list")) {
                printList(listOfTasks);
            } else if (input.contains("mark")) {
                String[] parts = input.split(" ");
                int index = Integer.parseInt(parts[1]);
                if (input.contains("unmark")) {
                    unmark(listOfTasks.get(index - 1));
                } else {
                    mark(listOfTasks.get(index - 1));
                }
            } else {
                repeatFunction(input);
                listOfTasks.add(new Task(input));
            }
        }

    }

    /**
     * Displays a greeting message.
     * 
     * @param botName Name of the bot.
     */
    public static void greet(String botName) {
        System.out.println(String.format(
                "%s     Hello! I'm %s \n     What can I do for you? \n%s", LINE, botName, LINE));
    }

    /**
     * Marks a task as done.
     * 
     * @param task
     */
    public static void mark(Task task) {
        System.out.println(LINE);
        System.out.println("     Nice! I've marked this task as done:");
        task.setDone();
        System.out.println("  " + task.toString());
        System.out.println(LINE);
    }

    /**
     * Marks a task as not done.
     * 
     * @param task
     */
    public static void unmark(Task task) {
        System.out.println(LINE);
        System.out.println("     OK, I've marked this task as not done yet:");
        task.setNotDone();
        System.out.println("  " + task.toString());
        System.out.println(LINE);
    }

    /**
     * Displays a farewell message.
     */
    public static void bye() {
        System.out.println(String.format(
                "%s     Bye. Hope to see you again soon! \n%s", LINE, LINE));
    }

    /**
     * Displays a repeated message of the input by the user.
     * 
     * @param input User input.
     */
    public static void repeatFunction(String input) {
        System.out.println(String.format("%s     added: %s\n%s", LINE, input, LINE));
    }

    /**
     * Displays the list of Strings.
     * 
     * @param listOfStrings list of Strings.
     */
    public static void printList(ArrayList<Task> listOfTasks) {
        StringBuilder finalString = new StringBuilder();
        finalString.append(LINE);
        for (Task c : listOfTasks) {
            finalString.append(c);
        }
        finalString.append(LINE);
        System.out.println(finalString.toString());
    }
}
