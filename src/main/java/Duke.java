import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm GuanGuanBot");
        System.out.println("What can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                System.out.println(command);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}