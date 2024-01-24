import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "--------------------------------------------------";

        Scanner scanner = new Scanner(System.in);

        System.out.println(divider);
        System.out.println("Hello! I'm TheGiantPeach\nWhat can I do for you?");
        System.out.println(divider);

        while (true) {
            String command = scanner.nextLine();

            System.out.println(divider);

            if (Objects.equals(command, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(command);
            System.out.println(divider);
        }

        System.out.println(divider);
    }
}
