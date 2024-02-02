import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static final String LINE = "___________________________________________________________\n";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> listOfStrings = new ArrayList<>();
        boolean hasEnded = false;
        String botName = "Yube";
        int counter = 1;

        greet(botName);
        while (!(hasEnded)) {
            String input = reader.readLine();
            if (input.equals("bye")) {
                bye();
                hasEnded = true;
            } else if (input.equals("list")) {
                printList(listOfStrings);
            } else {
                repeatFunction(input);
                listOfStrings.add(String.format("%s. %s\n", counter, input));
                counter++;
            }
        }
    }

    /**
     * Displays a greeting message
     * 
     * @param botName Name of the bot
     */
    public static void greet(String botName) {
        System.out.println(String.format(
                "%sHello! I'm %s \nWhat can I do for you? \n%s", LINE, botName, LINE));
    }

    /**
     * Displays a farewell message
     */
    public static void bye() {
        System.out.println(String.format(
                "%sBye. Hope to see you again soon! \n%s", LINE, LINE));
    }

    /**
     * Displays a repeated message of the input by the user
     * 
     * @param input User input
     */
    public static void repeatFunction(String input) {
        System.out.println(String.format("%sadded: %s\n%s", LINE, input, LINE));
    }

    /**
     * Displays the list of Strings
     * 
     * @param listOfStrings list of Strings
     */
    public static void printList(ArrayList<String> listOfStrings) {
        StringBuilder finalString = new StringBuilder();
        finalString.append(LINE);
        for (String c : listOfStrings) {
            finalString.append(c);
        }
        finalString.append(LINE);
        System.out.println(finalString.toString());
    }
}
