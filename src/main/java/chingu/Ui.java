package chingu;

import chingu.task.Task;

import java.io.InputStream;
import java.util.Scanner;

/**
 * This class deals with the Ui of the Chingu Program
 */
public class Ui {
    public final Scanner IN;

    private static final String LINE = "_________________________________________________________";

    private static final String ACKNOWLEDGEMENT = "Got it, my friend! I've added this task:\n";

    private static final String LIST_ANNOUNCEMENT = "Here are the tasks in your list:\n";

    public static final String BYE = "BYE, Friend! Hope to see you again soon!";

    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.IN = new Scanner(in);
    }


    public String readCommand() {
        String userCmd = IN.nextLine();
        return userCmd;
    }

    public String showError(String message) {
        return "My dear friend, " + message;
    }

    public String showDateError(String message) {
        return "My dear friend, your date format is WRONG!\n" + message;
    }

    public String showLine() {
        return LINE;
    }


    public String announceAcknowledgement(){
        return ACKNOWLEDGEMENT;
    }

    public String announceListing() {
        return LIST_ANNOUNCEMENT;
    }

    public String announceFinding() {
        return "Here are the matching tasks in your list:\n";
    }

    /**
     * Return a starting line that task is marked as Done.
     *
     * @param task that is marked as Done
     * @return starting line in string form
     */
    public String markedDone(Task task) {
        return "Nice! I've marked this task as done:\n" +
                "\t" + task.toString();
    }

    /**
     * Return a starting line that task is marked as Undone.
     *
     * @param task that is marked as Undone
     * @return starting line in string form
     */
    public String markedUndone(Task task) {
        return "OK, I've marked this task as not done yet:\n" +
                "\t" + task.toString();
    }

    /**
     * Says GoodBye to user to mark the end of the program
     *
     * @return Bye statement in String form
     */
    public String sayGoodBye() {
        return BYE;
    }

}
