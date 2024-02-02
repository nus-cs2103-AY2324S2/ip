import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static final String LINE = "___________________________________________________________\n";

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean hasEnded = false;
        String botName = "Yube";

        greet(botName);
        while (!(hasEnded)) {
            String input = reader.readLine();
            if (input.equals("bye")) {
                bye();
                hasEnded = true;
            } else {
                repeatFunction(input);
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
        System.out.println(String.format("%s%s\n%s", LINE, input, LINE));
    }
}
