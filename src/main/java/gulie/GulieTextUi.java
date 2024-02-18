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
        gulieInterface.print(String.format(" Greetings. I am %s.\n What can I do for you?", NAME));
    }

    public void farewell() {
        gulieInterface.print(" Goodbye.");
        gulieInterface.close();
    }

    public void store(Task task, int size) {
        String line1 = " Understood. I have added this task:\n   " + task;
        String line2 = String.format(" You now have %d tasks in the list", size);
        gulieInterface.print(line1 + '\n' + line2);
    }

    public void delete(Task task, int size) {
        String line1 = " I have removed this task:\n   " + task;
        String line2 = String.format(" You now have %d tasks in the list", size);
        gulieInterface.print(line1 + '\n' + line2);
    }

    public void mark(Task task) {
        gulieInterface.print(" I have marked this task as completed:\n   " + task);
    }

    public void unmark(Task task) {
        gulieInterface.print(" I have marked this task as incomplete:\n   " + task);
    }

    public void list(GulieTasklist tasklist) {
        if (tasklist.size()  == 0) {
            gulieInterface.print("The list is currently empty.");
            return;
        }
        String lines = "";
        for (int i = 0; i < tasklist.size(); i++) {
            lines += '\n' + String.format(" %d. %s", i + 1, tasklist.get(i).toString(DATE_TIME_FORMATTER));
        }
        gulieInterface.print(lines.substring(1));
    }

    /**
     * Displays a list of tasks found with the "find" command. 
     * @param tasklist the tasklist found
     */
    public void find(GulieTasklist tasklist) {
        String lines = " These are the matching tasks in yur list: ";
        for (int i = 0; i < tasklist.size(); i++) {
            lines += '\n' + String.format(" %d. %s", i + 1, tasklist.get(i).toString(DATE_TIME_FORMATTER));
        }
        gulieInterface.print(lines);
    }

    public void error(GulieException ge) {
        gulieInterface.print(" " + ge.getMessage());
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