package gulie;

import gulie.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A user interface for Gulie.
 */
public class GulieUi {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH':'mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");

    private GulieInterface gulieInterface;

    /**
     * A constructor for GulieUi
     * @param gulieInterface An interface to interact with Gulie.
     */
    public GulieUi(GulieInterface gulieInterface) {
        this.gulieInterface = gulieInterface;
    }


    public void printGreet() {
        gulieInterface.print(String.format(" Greetings. I am %s.\n What can I do for you?", Gulie.NAME));
    }

    public void printFarewell() {
        gulieInterface.print(" Goodbye.");
        gulieInterface.close();
    }

    public void printStore(Task task, int size) {
        String line1 = " Understood. I have added this task:\n   " + task.toString(DATE_TIME_FORMATTER);
        String line2 = String.format(" You now have %d task%s in the list", size, size == 1 ? "" : "s");
        gulieInterface.print(line1 + '\n' + line2);
    }

    public void printDelete(Task task, int size) {
        String line1 = " I have removed this task:\n   " + task;
        String line2 = String.format(" You now have %d task%s in the list", size, size == 1 ? "" : "s");
        gulieInterface.print(line1 + '\n' + line2);
    }

    public void printMark(Task task) {
        gulieInterface.print(" I have marked this task as completed:\n   " + task);
    }

    public void printUnmark(Task task) {
        gulieInterface.print(" I have marked this task as incomplete:\n   " + task);
    }

    public void printList(GulieTasklist tasklist) {
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
    public void printFind(GulieTasklist tasklist, GulieTasklist findlist) {
        String lines = " These are the matching tasks in your list: ";
        for (int i = 0; i < findlist.size(); i++) {
            Task task = findlist.get(i);
            lines += '\n' + String.format(" %d. %s", tasklist.indexOf(task) + 1, task.toString(DATE_TIME_FORMATTER));
        }
        gulieInterface.print(lines);
    }

    /**
     * Displays a list of tasks on the specified date.
     * @param date
     * @param tasklist
     */
    public void printSchedule(LocalDate date, GulieTasklist tasklist) {
        String lines = " These are the tasks you have on " + date.format(DATE_FORMATTER);
        for (int i = 0; i < tasklist.size(); i++) {
            lines += '\n' + String.format(" %s", tasklist.get(i).toString(DATE_TIME_FORMATTER));
        }
        gulieInterface.print(lines);
    }

    /**
     * Prints an error.
     * @param ge
     */
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