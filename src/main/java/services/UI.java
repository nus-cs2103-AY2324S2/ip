import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public String nextCommand() {
        return this.scanner.nextLine();
    }

    public void printIntroMessage() {
        printHorizontalLine();
        System.out.println("\tHello! I'm RoeBot!");
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
        this.scanner.close();
    }
    public void printMessage(String message) {
        System.out.println("\t" + message);
    }

    public void printHorizontalLine() {
        System.out.println("\t_________________________________________________");
    }
}
