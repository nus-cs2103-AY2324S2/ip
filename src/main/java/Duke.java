import java.util.Scanner;
import java.util.Objects;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm PeWPeWPeW");
        Scanner scanner = new Scanner(System.in);

        System.out.println("What can I do for you?");
        String userInput = scanner.nextLine();
        while (!Objects.equals(userInput.toLowerCase(), "bye")) {
            if (Objects.equals(userInput.toLowerCase(), "pewpewpew")) {
                System.out.println("PeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeW");
            }
            else {
                System.out.println(userInput);
            }
            System.out.println("What else can I do for you? (try typing my name 3 times with no space in between)" );
            userInput = scanner.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}