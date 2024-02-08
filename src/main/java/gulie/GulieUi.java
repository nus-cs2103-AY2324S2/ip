package gulie;

import gulie.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class GulieUi {
    private static final String name = "Güliedistodiez";
    private static final String line = "____________________________________________________________";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH':'mm");

    private Scanner in;
    private PrintStream out;
    public GulieUi(InputStream input, PrintStream output) {
        in = new Scanner(input);
        out = output;
    }


    public void greet() {
        out.println(line);
        out.println(String.format(" Greetings. I am %s.\n What can I do for you?", name));
        out.println(line);
    }

    public void farewell() {
        out.println(line);
        out.println(" Goodbye.");
        out.println(line);
        in.close();
    }

    public void store(Task task, int size) {
        out.println(line);
        out.println(" Understood. I have added this task:\n   " + task);
        out.println(String.format(" You now have %d tasks in the list", size));
        out.println(line);
    }

    public void delete(Task task, int size) {
        out.println(line);
        out.println(" I have removed this task:\n   " + task);
        out.println(String.format(" You now have %d tasks in the list", size));
        out.println(line);
    }

    public void mark(Task task) {
        out.println(line);
        out.println(" I have marked this task as completed:\n   " + task);
        out.println(line);
    }

    public void unmark(Task task) {
        out.println(line);
        out.println(" I have marked this task as incomplete:\n   " + task);
        out.println(line);
    }

    public void list(GulieTasklist tasklist) {
        out.println(line);
        for (int i = 0; i < tasklist.size(); i++) {
            out.println(String.format(" %d. %s", i + 1, tasklist.get(i)));
        }
        out.println(line);
    }

    public void error(GulieException ge) {
        out.println(line);
        out.println(" " + ge.getMessage());
        out.println(line);
    }

    public String getInput() {
        if (in.hasNextLine()) {
            return in.nextLine();
        } else {
            return "bye";
        }
    }
}
