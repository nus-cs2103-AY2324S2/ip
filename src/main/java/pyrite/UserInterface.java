package pyrite;

import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Handles user interaction by printing messages to the user and reading user input.
 */
public class UserInterface {
    private static String horizontal_line = "____________________________________________________________";
    private static String farewell = "Bye. Hope to see you again soon!";
    //indentString method adapted from ChatGPT generation
    private static String indentString(String input) {
        // Indent each line using Stream API and joining collector
        String indentedLines = input.lines()
                .map(line -> "\t" + line)
                .collect(Collectors.joining(System.lineSeparator()));

        return indentedLines;
    }
    private Scanner scanner = new Scanner(System.in);

    /**
     * Returns an indented string of the given paragraph.
     *
     * @param what The paragraph to be indented.
     */
    public void say(String what){
        System.out.println(UserInterface.indentString(horizontal_line + "\n" + what + "\n" +  horizontal_line));
    }

    /**
     * Prints the greeting.
     *
     * @param name Name to greet with.
     */
    public void greet(String name) {
        this.say("Hello! I'm " + name + ".\n" + "What can I do for you?");
    }

    /**
     * Retrieves the next command string from the user.
     *
     * @return Command string from the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
