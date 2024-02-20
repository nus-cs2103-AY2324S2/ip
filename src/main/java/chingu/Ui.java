package chingu;

import chingu.task.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    public final Scanner IN;

    private final PrintStream OUT;

    private static final String LINE = "_________________________________________________________";

    private static final String LOADING_ERROR = "Your tasks are loaded wrongly " +
            "- Please check your list.txt again, my friend!";

    private static final String LIST_ANNOUNCEMENT = "Here are the tasks in your list:";

    private static final String BYE = "Hope to see you again soon!";

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.IN = new Scanner(in);
        this.OUT = out;
    }


    public String readCommand() {
        String userCmd = IN.nextLine();
        return userCmd;
    }

    public String showError(String msg) {
        return "My dear friend, " + msg;
    }

    public String showLoadingError() {
        return LOADING_ERROR;
    }

    public String showLine() {
        return LINE;
    }

    public String announceListing() {
        return LIST_ANNOUNCEMENT;
    }

    public String announceFinding() {
        return "Here are the matching tasks in your list:";
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
