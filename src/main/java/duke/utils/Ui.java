package duke.utils;

import java.util.Random;

public class Ui {
    public static final String LINE =
            "_________________________________________________________________________\n";

    private static final String[] catchphrases = {
            "Yee-haw!",
            "So long, partner.",
            "To infinity and beyond!",
            "Reach for the sky!",
            "There's a snake in my boot!"
    };

    public static void greet() {
        String greeting = LINE + "   Hello! I'm Woody\n"
                + "   What can I do for you?\n" + LINE;

        System.out.println(greeting);
    }

    public static void goodbye() {
        Random random = new Random();
        System.out.println("   Bye! " + catchphrases[random.nextInt(catchphrases.length)]
                + "\n" + LINE);
    }

}
