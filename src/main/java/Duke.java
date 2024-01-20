import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String message = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------");
        System.out.println("Hello! I'm Bob!");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
        while (!Objects.equals(message, "bye")) {
            message = scanner.nextLine();
            if (!Objects.equals(message, "bye")) {
                System.out.println("------------------------------------------");
                System.out.println(message);
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }
}
