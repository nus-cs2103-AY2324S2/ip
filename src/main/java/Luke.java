import java.util.*;

public class Luke {

    public static void main(String[] args) {

        String name = "Luke";

        String[] text = new String[100];

        int noWords = 0;

        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < noWords; i++) {
                    System.out.println((i + 1) + ". " + text[i]);
                }
            } else {
                text[noWords] = input;
                noWords++;
                System.out.println("added: " + input);
            }
            input = scanner.nextLine();
        };
        System.out.println("Bye. Hope to see you again soon!");

    }
}
