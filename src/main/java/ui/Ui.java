package ui;

import java.util.Scanner;

public class Ui {

    private static final String NAME = "Tartar";

    private static final String GREETING = String.format("Hello! I'm %s\n", NAME);
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String QUESTION = "What can I do for you?";

    private static final String BYE = " Bye. Hope to see you again soon!";

    private static final String MARKED = "Nice! I've marked this task as done:";

    private static final String UNMARKED = "OK, I've marked this task as not done yet:";
    private static final String RECOMMENDER = "RECCOMENDATION";

    private Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns a string containing the greeting string.
     *
     * @return a string containing the greeting string.
     */
    public String greeting() {return GREETING;}

    /**
     * Returns a string containing the divider string.
     *
     * @return a string containing the divider string.
     */
    public String divider() {return DIVIDER;}

    /**
     * Returns a string containing the question string.
     *
     * @return a string containing the question string.
     */


     /**
      * Returns a string containing the question string.
      *      
      * @return a string containing the question string.
      */
    public String question() {return QUESTION;}

    /**
     * Returns a string containing the marked string.
     *
     * @return a string containing the marked string.
     */
    public String mark() {return MARKED;}

    /**
     * Returns a string containing the unmarked string.
     *
     * @return a string containing the unmarked string.
     */
    public String unmark() {return UNMARKED;}

    /**
     * Returns the user's input as a string.
     *
     * @return the user's input as a string.
     */
    public String getCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Returns a string containing the bye string.
     *
     * @return a string containing the bye string.
     */
    public static String bye() {
        return BYE;
    }

    /**
     * Returns a string containing the given text wrapped in a divider.
     *
     * @param text the text to be wrapped in a divider
     * @return a string containing the given text wrapped in a divider
     */
    public String dividerWrapper(String text) {
        return divider() + text + "\n" + divider();
    }

    /**
     * Returns a string containing the given text wrapped in a recommender.
     *
     * @param text the text to be wrapped in a recommender
     * @return a string containing the given text wrapped in a recommender
     */
    public String recommenderWrapper(String text) {
        return RECOMMENDER + "\n" + text + "\n" + RECOMMENDER;
    }

    /**
     * Returns a string containing the greeting and question wrapped in a divider.
     *
     * @return a string containing the greeting and question wrapped in a divider
     */
    public String greetingBox() {
        return dividerWrapper(greeting() + question());
    }



}
