package gulie;

import gulie.task.Task;

import java.time.format.DateTimeFormatter;

/**
 * A user interface for Gulie.
 */
public class GulieTextUi {
    private static final String NAME = "Güliedistodiez";
    private static final String LINE = "____________________________________________________________";

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH':'mm");

    private GulieInterface gulieInterface;

    /**
     * A constructor for GulieUi
     * @param input
     * @param output
     */
    public GulieTextUi(GulieInterface gulieInterface) {
        this.gulieInterface = gulieInterface;
    }


    public void greet() {
        gulieInterface.print(LINE);
        gulieInterface.print(String.format(" Greetings. I am %s.\n What can I do for you?", NAME));
        gulieInterface.print(LINE);
    }

    public void farewell() {
        gulieInterface.print(LINE);
        gulieInterface.print(" Goodbye.");
        gulieInterface.print(LINE);
        gulieInterface.close();
    }

    public void store(Task task, int size) {
        gulieInterface.print(LINE);
        gulieInterface.print(" Understood. I have added this task:\n   " + task);
        gulieInterface.print(String.format(" You now have %d tasks in the list", size));
        gulieInterface.print(LINE);
    }

    public void delete(Task task, int size) {
        gulieInterface.print(LINE);
        gulieInterface.print(" I have removed this task:\n   " + task);
        gulieInterface.print(String.format(" You now have %d tasks in the list", size));
        gulieInterface.print(LINE);
    }

    public void mark(Task task) {
        gulieInterface.print(LINE);
        gulieInterface.print(" I have marked this task as completed:\n   " + task);
        gulieInterface.print(LINE);
    }

    public void unmark(Task task) {
        gulieInterface.print(LINE);
        gulieInterface.print(" I have marked this task as incomplete:\n   " + task);
        gulieInterface.print(LINE);
    }

    public void list(GulieTasklist tasklist) {
        gulieInterface.print(LINE);
        for (int i = 0; i < tasklist.size(); i++) {
            gulieInterface.print(String.format(" %d. %s", i + 1, tasklist.get(i).toString(DATE_TIME_FORMATTER)));
        }
        gulieInterface.print(LINE);
    }

    /**
     * Displays a list of tasks found with the "find" command. 
     * @param tasklist the tasklist found
     */
    public void find(GulieTasklist tasklist) {
        gulieInterface.print(LINE);
        gulieInterface.print(" These are the matching tasks in yur list: ");
        for (int i = 0; i < tasklist.size(); i++) {
            gulieInterface.print(String.format(" %d. %s", i + 1, tasklist.get(i).toString(DATE_TIME_FORMATTER)));
        }
        gulieInterface.print(LINE);
    }

    public void error(GulieException ge) {
        gulieInterface.print(LINE);
        gulieInterface.print(" " + ge.getMessage());
        gulieInterface.print(LINE);
    }

    /**
     * Gets the next input from the user.
     * @return
     */
    public String getInput() {
        if (gulieInterface.isOpen()) {
            return gulieInterface.getString();
        } else {
            return "bye";
        }
    }
}