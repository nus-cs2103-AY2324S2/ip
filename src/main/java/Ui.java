import java.util.Scanner;

public class Ui {

    public static final String SEPERATOR = "------------------------------------------------";
    public static final String INDENT = "    ";
    public static final String INDENT_SEPERATOR = INDENT + SEPERATOR;

    private Scanner scanner;

    private String input;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____\n"
                + "|  _ \\   ___   ___\n"
                + "| |_| | / _ \\ / _ \\\n"
                + "| |_| | | __/ | __/\n"
                + "|____/  \\___| \\___|\n";

        String msg = SEPERATOR
                + "\nHello! I'm Bee!\n"
                + "What can I do for you?\n"
                + SEPERATOR;

        System.out.println(logo + "\n" + msg);
    }

    public static void showGoodbyeMessage() {
        System.out.println(INDENT_SEPERATOR
                + "\n"
                + INDENT
                + "Bye. Hope to see you again soon!\n"
                + INDENT_SEPERATOR);
    }

    public String getInput() {
        return this.input;
    }

    public String readCommand() {
        this.input = this.scanner.nextLine();
        return this.input;
    }

}
