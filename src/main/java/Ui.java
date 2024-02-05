import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    protected Scanner in;
    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.in = new Scanner(in);
    }

    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Taro\n" + "What can I do for you?\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye bye!");
    }

    public String readInput() {
        String input = in.nextLine();
        while (shouldIgnore(input)) {
            input = in.nextLine();
        }
        return input;
    }

    public void exit() {
        System.out.println("Bye bye!");
        System.exit(0);
    }
}
