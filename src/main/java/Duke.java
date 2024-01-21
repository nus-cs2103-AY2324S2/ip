import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println(
                "Hello! I'm Nico\n" +
                "What can I do for you?");

        Scanner userInput = new Scanner(System.in);
        while (true) {
            String command = userInput.nextLine();
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            } else {
                System.out.println(command);
            }
        }
    }
}
