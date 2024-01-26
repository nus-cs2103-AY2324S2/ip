import java.util.Scanner;
public class Ui {
    private static final String line = "    _______________________________________________________________\n";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(line +
                "    Hello! I'm Tes\n" +
                "    What can I do for you?\n" +
                line);
    }

    public String nextCommand() {
        return scanner.nextLine();
    }

    public void close() {
        System.out.println(line
                + "    Bye. Hope to see you again soon!\n"
                + line);
    }

    public void respond(String command) {
        System.out.println(line
                + "    " + command + "\n"
                + line);
    }
}
