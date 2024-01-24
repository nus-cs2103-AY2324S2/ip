import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Coat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> userInputs = new ArrayList<>();

        System.out.printf(Messages.WELCOME);
        while (true) {
            System.out.printf("\n-> ");
            String userInput = scanner.nextLine();

            // switch (userInput) {
            // case ""
            // }

            // Exit condition: say "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.printf(Messages.EXIT);
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                listUserInputs(userInputs);
            } else {
                userInputs.add(userInput);
                System.out.printf("\n(^-^)~~   Added: %s\n", userInput);
            }

        }

        scanner.close();
    }


    private static void listUserInputs(List<String> userInputs) {
        System.out.printf("\n(^-^)~~   Here's your list!\n");
        for (int i = 0; i < userInputs.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, userInputs.get(i));
        }
    }
}
