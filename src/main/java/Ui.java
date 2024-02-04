import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
public class Ui {

    public static final String DIVIDER = "____________________________________________________________\n";

    public static final String INTRO = "yoooooooo im sibehupzcoder9000\n" +
            "what you want sia";

    public static final String OUTRO = "toodledoo";

    public final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(m);
        }
    }

    public void showIntro() {
        showToUser(DIVIDER, INTRO, DIVIDER);
    }

    public void showOutro() {
        showToUser(DIVIDER, OUTRO, DIVIDER);
    }

    public void showLoadingError(String e) {
        showToUser(DIVIDER, e, DIVIDER);
    }

    public void showList(ArrayList<Task> list) {
        String message = "Here are the tasks in your list";
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            tasks.append(" ").append(i + 1).append(". ").append(list.get(i).toString()).append("\n");
        }
        showToUser(DIVIDER, message, tasks.toString(), DIVIDER);
    }
}
