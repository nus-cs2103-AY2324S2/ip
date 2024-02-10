package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "-".repeat(60);
    private static final String CHATBOT_NAME = "Cicada";
    private static final String LOGO =  " _____ _               _\n"
                                +       "/  __ (_)             | |\n"
                                +       "| /  \\/_  ___ __ _  __| | __ _\n"
                                +       "| |   | |/ __/ _` |/ _` |/ _` |\n"
                                +       "| \\__/\\ | (_| (_| | (_| | (_| |\n"
                                +       " \\____/_|\\___\\__,_|\\__,_|\\__,_|\n";
    private final Scanner in;
    private final PrintStream out;
    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showLine() {
        out.println(DIVIDER);
    }

    public void showWelcome() {
        out.println("Hello from\n" + LOGO);
        showLine();
        out.println("Hello! I'm " + CHATBOT_NAME + "\nWhat can I do for you?");
        showLine();
    }

    public String getUserCommand() {
        out.println("Type your command and press Enter. Type 'bye' to quit.");
        return in.nextLine();
    }

    public void showGoodbye() {
        showLine();
        out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLoadingError() {
        System.err.println("There is loading error");
    }

    public void showError(String errorMsg) {
        System.err.println(errorMsg);
    }
}
