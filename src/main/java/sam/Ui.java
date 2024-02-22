package sam;

import java.util.Scanner;
public class Ui {
    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public void greet() {
        String logo =
                "  ______\n" +
                " |           /  \\ \n" +
                " |______    /____\\     / \\    / \\\n" +
                "        |  /      \\   /   \\  /   \\\n" +
                "  ______| /        \\ /     \\/     \\\n";
        String greetMessage = "Hello! I'm Sam\n" +
                "How can I help you?";
        System.out.println(logo);
        System.out.println(greetMessage);
    }

    public void showError(String err) {
        System.err.println(err);
    }

    public void showLoadingError() {
        System.err.println("Error reading tasks from file.");
    }
}
