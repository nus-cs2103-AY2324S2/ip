package duke.ui;

import java.util.Scanner;

import duke.duke.Parser;
import duke.duke.Storage;
import duke.tasks.TaskList;

/**
 * The class that contains formatted strings for the chat bot.
 *
 * @author Lim Zi Jia
 */
public class Skibidi {
    /** Logo of Skibidi. */
    public static final String LOGO =
            " ____  _  _____ ____ ___ ____ ___ \n"
            + "/ ___|| |/ /_ _| __ )_ _|  _ \\_ _|\n"
            + "\\___ \\| ' / | ||  _ \\| || | | | | \n"
            + " ___) | . \\ | || |_) | || |_| | | \n"
            + "|____/|_|\\_\\___|____/___|____/___|";


    /** Greeting for startup */
    public static final String GREETING = LOGO + "\n" +"Hello! I'm Skibidi!\nWhat can I do for you?";

    /** Says goodbye to the user. */
    public static final String BYE = "Bye! Hope to see you again soon!";

    public String formatOutput(String... strings) {
        StringBuilder output = new StringBuilder();

        for (String s:strings) {
            output.append(s).append("\n");
        }

        return output.toString();
    }
}
