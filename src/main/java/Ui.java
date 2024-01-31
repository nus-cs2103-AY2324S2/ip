import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);
    public Ui() {
    }
    public void showWelcome() {
        System.out.println(
                "Hello! I'm Bob\n" +
                "What can I do for you?");
    }
    public void readCommand() {
        String command = scanner.nextLine();
    }
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
