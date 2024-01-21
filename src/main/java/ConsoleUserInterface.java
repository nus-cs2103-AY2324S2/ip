import java.util.Objects;
import java.util.Scanner;

public class ConsoleUserInterface {
    private Scanner scanner;
    private String lastInput;

    public ConsoleUserInterface() {
        this.scanner = new Scanner(System.in);
    }

    public void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm MicroManager");
        System.out.println("What can I do for you?");
        printSeparator();
        handleUserInput();
    }

    private void handleUserInput() {
        while (true) {
            this.lastInput = this.scanner.nextLine();
            if (Objects.equals(this.lastInput, "bye")) {
                break;
            } else {
                printOutput(this.lastInput);
            }
        }
    }

    public void printOutput(String string) {
        printSeparator();
        System.out.println(string);
        printSeparator();
    }

    public void exit() {
        printOutput("Bye. Hope to see you again soon!");

        this.scanner.close();
    }

    private void printSeparator() {
        System.out.println("____________________________________________________________");
    }
}
