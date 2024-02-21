package duke;

/**
 * The UI class contains formatting styles for outputs,
 * as well as the logo and greeting text Strings. A UI object
 * is instantiated within the main method of Duke.
 */
public class UI {

    private String greet = "Hello! I'm ELIAS\n"
            + "What can I do for you?";

    private String logo = "______ _ _\n"
            + "|  ____| (_)\n"
            + "| |__  | |_  __ _ ___\n"
            + "|  __| | | |/ _` / __|\n"
            + "| |____| | | (_| \\__ \\\n"
            + "|______|_|_|\\__,_|___/\n";

    private String bye = "Bye. Hope to see you again soon!";

    public String getGreet() {
        return greet;
    }

    public String getLogo() {
        return logo;
    }

    public String getBye() {
        return bye;
    }

    /**
     * Takes in a string and returns a string that is
     * formatted with horizontal rules above and below
     * for aesthetic purposes.
     *
     * @param s string input for formatting.
     * @return a string that is formatted.
     */
    public String format(String s) {
        return "    ____________________________________________________________\n     "
                + s
                + "\n    ____________________________________________________________\n";
    }
}
