import java.util.Scanner;
import java.util.*;
public class Zoe {
    public static void main(String[] args) {
        ArrayList<String> addedCommands = new ArrayList<String>();
        System.out.println("Hello! I'm Zoe");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (int i = 0; i < addedCommands.size(); i++) {
                    System.out.println(String.format
                            ("%d. %s", i + 1, addedCommands.get(i)));
                }
            } else {
                addedCommands.add(command);
                System.out.println("added: " + command);
            }

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
