import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String message = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("------------------------------------------");
        System.out.println("Hello! I'm Bob!");
        System.out.println("What can I do for you?");
        System.out.println("------------------------------------------");
        while (!Objects.equals(message, "bye")) {
            message = scanner.nextLine();
            if (!Objects.equals(message, "bye") && !Objects.equals(message, "list")) {
                System.out.println("------------------------------------------");
                list.add(message);
                System.out.println("added: " + message);
                System.out.println("------------------------------------------");
            } else if (Objects.equals(message, "list")) {
                System.out.println("------------------------------------------");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i + 1 + ". " + list.get(i));
                }
                System.out.println("------------------------------------------");
            }
        }
        System.out.println("------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }
}
