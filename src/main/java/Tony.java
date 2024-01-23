import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tony {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        greeting();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                line();
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + ". " + list.get(i) + "\n");
                }
                line();
                input = scanner.nextLine();
            } else {
                list.add(input);
                line();
                System.out.println("Added: " + input);
                line();
                input = scanner.nextLine();
            }
        }
        goodbye();
    }

    private static void greeting() {
        String greeting = "_______________________\n"
                + "Hello! I'm Tony!\n"
                + "What can I do for you?\n"
                + "_________________________\n";
        System.out.println(greeting);
    }
    private static void goodbye() {
        String goodbye = "Bye. Hope to see you again soon!\n";
        line();
        System.out.println(goodbye);
        line();
        System.exit(0);
    }
    private static void line() {
        System.out.println("_______________________\n");
    }
}
