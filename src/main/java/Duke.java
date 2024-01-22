import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greeting = "____________________________________________________________\n" +
                " Hello! I'm steve\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String goodbye = "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n";

        System.out.println(greeting);

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String cmd = scanner.nextLine(); // Get first input

        while (!Objects.equals(cmd, "bye")) {
            String border = "____________________________________________________________";

            System.out.println(border + '\n' + cmd + '\n' + border);

            cmd = scanner.nextLine();
        }
        System.out.println(goodbye);

    }
}
