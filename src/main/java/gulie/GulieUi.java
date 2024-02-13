package gulie;

import gulie.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * A user interface for Gulie.
 */
public class GulieUi {
    private static final String NAME = "Güliedistodiez";
    private static final String LINE = "____________________________________________________________";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH':'mm");

    private Scanner in;
    private PrintStream out;

    /**
     * A constructor for GulieUi
     * @param input
     * @param output
     */
    public GulieUi(InputStream input, PrintStream output) {
        in = new Scanner(input);
        out = output;
    }


    public void greet() {
        out.println(LINE);
        out.println(String.format(" Greetings. I am %s.\n What can I do for you?", NAME));
        out.println(LINE);
    }

    public void farewell() {
        out.println(LINE);
        out.println(" Goodbye.");
        out.println(LINE);
        in.close();
    }

    public void store(Task task, int size) {
        out.println(LINE);
        out.println(" Understood. I have added this task:\n   " + task);
        out.println(String.format(" You now have %d tasks in the list", size));
        out.println(LINE);
    }

    public void delete(Task task, int size) {
        out.println(LINE);
        out.println(" I have removed this task:\n   " + task);
        out.println(String.format(" You now have %d tasks in the list", size));
        out.println(LINE);
    }

    public void mark(Task task) {
        out.println(LINE);
        out.println(" I have marked this task as completed:\n   " + task);
        out.println(LINE);
    }

    public void unmark(Task task) {
        out.println(LINE);
        out.println(" I have marked this task as incomplete:\n   " + task);
        out.println(LINE);
    }

    public void list(GulieTasklist tasklist) {
        out.println(LINE);
        for (int i = 0; i < tasklist.size(); i++) {
            out.println(String.format(" %d. %s", i + 1, tasklist.get(i).toString(DATE_TIME_FORMATTER)));
        }
        out.println(LINE);
    }

    /**
     * Displays a list of tasks found with the "find" command. 
     * @param tasklist the tasklist found
     */
    public void find(GulieTasklist tasklist) {
        out.println(LINE);
        out.println(" These are the matching tasks in yur list: ");
        for (int i = 0; i < tasklist.size(); i++) {
            out.println(String.format(" %d. %s", i + 1, tasklist.get(i).toString(DATE_TIME_FORMATTER)));
        }
        out.println(LINE);
    }

    public void error(GulieException ge) {
        out.println(LINE);
        out.println(" " + ge.getMessage());
        out.println(LINE);
    }

    /**
     * Gets the next input from the user.
     * @return
     */
    public String getInput() {
        if (in.hasNextLine()) {
            return in.nextLine();
        } else {
            return "bye";
        }
    }
}
