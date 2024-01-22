import java.lang.reflect.Array;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Kervyn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userRequests = new ArrayList<>();
        String chatbotName = "Kervyn";

        System.out.println("\tHello! I'm " + chatbotName);
        System.out.println("\tWhat can I do for you?");

        String userInput;
        do {
            userInput = scanner.nextLine();
            if (!Objects.equals(userInput, "bye") && !Objects.equals(userInput, "list")) {
                System.out.println("\tadded: " + userInput);
                userRequests.add(userInput);
            }

            if (Objects.equals(userInput, "list")) {
                for (int i = 0; i < userRequests.size(); i ++) {
                    System.out.println("\t" + (i + 1) + ". " +  userRequests.get(i));

                }
            }
        } while (!Objects.equals(userInput, "bye"));

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
