import java.util.*;

public class Duke {

    static String logo = "Duke";
    public static void main(String[] args) {

        System.out.println("Hello! I'm " + logo + "\nWhat can I do for you?");

        String input = null;
        do {
            if (input != null) {
                System.out.println(input);
            }
            Scanner scanner = new Scanner (System.in);
            input = scanner.nextLine();
        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");

    }
}
