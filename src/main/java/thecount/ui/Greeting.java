package thecount.ui;

/**
 * A specialized reply class for greeting users.
 */
public class Greeting extends Reply {

    // ASCII art adapted from https://www.asciiart.eu/television/sesame-street
    private static final String ASCII_ART =
            "          .wwwwwwww.\n"
                    + "        .w\"  \"WW\"  \"w.\n"
                    + "       .\"   /\\  /\\   \".\n"
                    + "      |\\     o  o     /|\n"
                    + "      \\|   ___\\/___  |/\n"
                    + "       / \\ \\_v__v_/ / \\      Ah-Ah-Ah!\n"
                    + "      / | \\________/ | \\\n"
                    + "      >  \\   WWWW   /  <\n"
                    + "      \\   \\   \"\"   /   /\n"
                    + "       \\   \\      /   /";

    /**
     * Constructs a Greeting object.
     */
    public Greeting() {
        super("Greetings. I am the Count.\n"
                + "They call me the Count,\n"
                + "because I LOVE to count... things.");
        displayAsciiArt();
    }

    /**
     * Displays the ASCII art representing the Count.
     */
    private void displayAsciiArt() {
        System.out.println(ASCII_ART);
    }
}
