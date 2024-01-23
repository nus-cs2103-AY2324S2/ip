import java.util.Objects;
import java.util.Scanner;

public class CoDriver {
    public static void main(String[] args) {
        greeting();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (Objects.equals(command, "bye")) {
                break;
            }
            printSepLine();
            System.out.println(command);
            printSepLine();
        }
        goodbye();
    }

    private static void printSepLine() {
        System.out.println("------------------------------------------------");
    }

    private static void greeting() {
        printSepLine();
        System.out.println("Hello! I'm CoDriver, your everyday AI companion!");
        System.out.println("What can I do for you?");
        printSepLine();
    }

    private static void goodbye() {
        printSepLine();
        System.out.println("Bye. Hope to see you again soon!");
        printSepLine();
    }
}
