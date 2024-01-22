import java.util.Objects;
import java.util.Scanner;
public class Kervyn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chatbotName = "Kervyn";

        System.out.println("\tHello! I'm " + chatbotName);
        System.out.println("\tWhat can I do for you?");

        String userInput;
        do {
            userInput = scanner.nextLine();
            if (!Objects.equals(userInput, "bye")) {
                System.out.println("\t" + userInput);
            }

        } while (!Objects.equals(userInput, "bye"));

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
