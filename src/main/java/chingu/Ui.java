package chingu;

import chingu.task.Task;

import java.io.InputStream;
import java.util.Scanner;

public class Ui {
    public final Scanner IN;

    private static final String LINE = "_________________________________________________________";

    private static final String LOADING_ERROR = "Your tasks are loaded wrongly " +
            "- Please check your list.txt again, my friend!";

    private static final String ACKNOWLEDGEMENT = "Got it, my friend! I've added this task:\n";

    private static final String LIST_ANNOUNCEMENT = "Here are the tasks in your list:\n";

    public static final String BYE = "Hope to see you again soon!";

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

    public String showLoadingError() {
        return LOADING_ERROR;
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

    public String markedDone(Task task) {
        return "Nice! I've marked this task as done:\n" +
                "\t" + task.toString();
    }

    public String markedUndone(Task task) {
        return "OK, I've marked this task as not done yet:\n" +
                "\t" + task.toString();
    }

    public String sayGoodBye() {
        return BYE;
    }

}
