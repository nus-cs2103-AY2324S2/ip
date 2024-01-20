import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] items = new String[100];
        int itemCount = 0;

        System.out.println("Hello! I'm GuanGuanBot");
        System.out.println("What can I do for you?");

        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i=0; i<itemCount; i++) {
                    System.out.printf("%s. %s%n", i+1, items[i]);
                }
            } else {
                items[itemCount] = command;
                itemCount++;
                System.out.println("added: " + command);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
