package duke.utils;

import java.util.Random;

/**
 * The class for handling UI in Duke chatbot.
 * It contains tools such as line styles.
 */
public class Ui {
    public static final String LINE =
            "_________________________________________________________________________\n";

    private static final String[] CATCHPHRASES = {
        "Yee-haw!",
        "So long, partner.",
        "To infinity and beyond!",
        "Reach for the sky!",
        "There's a snake in my boot!"
    };

    /**
     * Prints a greeting to the user
     *
     */
    public static void greet() {
        String greeting = LINE + "   Hello! I'm Woody\n"
                + "   What can I do for you?\n" + LINE;

        System.out.println(greeting);
    }

    /**
     * Prints a goodbye to the user
     *
     */
    public static void goodbye() {
        Random random = new Random();
        System.out.println("   Bye! " + CATCHPHRASES[random.nextInt(CATCHPHRASES.length)]
                + "\n" + LINE);
    }

}
