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

    public String greeting() {return GREETING;}
    public String divider() {return DIVIDER;}
    public String question() {return QUESTION;}

    public String mark() {return MARKED;}

    public String unmark() {return UNMARKED;}

    public String getCommand() {
        return sc.nextLine().trim();
    }
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
