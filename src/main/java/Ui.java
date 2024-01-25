import java.lang.annotation.Target;
import java.util.Scanner;

public class Ui {

    private static final String NAME = "Tartar";

    private static final String GREETING = String.format("Hello! I'm %s\n", NAME);
    private static final String DIVIDER = "____________________________________________________________\n";
    private static final String QUESTION = "What can I do for you?";

    private static final String BYE = " Bye. Hope to see you again soon!";

    private static final String MARKED = "Nice! I've marked this task as done:";

    private static final String UNMARKED = "OK, I've marked this task as not done yet:";

    private Scanner sc;

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

    public String dividerWrapper(String text) {
        return divider() + text + "\n" + divider();
    }

    public String greetingBox() {
        return dividerWrapper(greeting() + question());
    }



}
