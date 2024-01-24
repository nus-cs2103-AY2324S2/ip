import java.util.Scanner;

public class Coat {
    public static void main(String[] args) {
        System.out.printf(Messages.WELCOME);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.printf("-> ");
            String userInput = scanner.nextLine();

            // Exit condition: say "bye"
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.printf(Messages.EXIT);
                break;
            }

            // Level-1: Echo user input
            System.out.printf(">>> %s\n", userInput);
        }


        scanner.close();
    }
}
