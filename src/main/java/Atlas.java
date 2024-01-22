import java.util.Scanner;

public class Atlas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final String horizontalLine = "____________________________________________________________";
        greet();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        exit();

    }

    private static void greet() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }
    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
