import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private final Scanner in;
    private final PrintStream out;
    private static final String SEP = "\t__________________________________________";
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void printMessage(String... message) {
        out.println(SEP);
        for(String m : message) {
            System.out.println("\t" + m);
        }
        out.println(SEP);
    }

    public void printWelcomeMessage() {
        String[] s = {"Hello! I'm JOSEPH JOSHTUR!!!", "What can I do for you?"};
        printMessage(s);
    }

    public void printByeMessage() {
        String[] s = {"Bye. Hope to see you again soon!"};
        printMessage(s);
    }

    public String getCommand() {
        String input = in.nextLine();
        while (input.isEmpty()) {
             input = in.nextLine();
        }
        return input.trim();
    }
}
