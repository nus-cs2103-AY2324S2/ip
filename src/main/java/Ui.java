import java.util.Scanner;

public class Ui {

    private static final String PADDING = " ".repeat(4);
    private static void printDivider() {
        System.out.println(PADDING + "_".repeat(50));
    }
    private final Scanner SC;

    public Ui () {
        SC = new Scanner(System.in);
    }

    public void makeResponse(String... arr) {
        printDivider();
        for (String s : arr) {
            System.out.println(PADDING + s);
        }
        printDivider();
    }

    public String getUserInput() {
        return SC.nextLine();
    }

    public void showGreeting() {
        makeResponse("Hello! I'm Earl", "What can I do for you?");
    }

    public void showGoodbye() {
        makeResponse("Goodbye! See you soon.");
    }
}
