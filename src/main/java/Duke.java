import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] userInputs = new String[100];
        int Count = 0;

        System.out.println("Hello! I'm Bentley\n" + "What can I do for you?\n");

        while (Count < 100) {
            String userInput = scanner.nextLine();

            userInputs[Count] = userInput;

            Count++;

            if (userInput.equals("Bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("List")) {

                for (int i = 0; i < Count; i++) {
                    System.out.println(userInputs[i]);
                }
            } else {
                System.out.println("added: " + userInput);

            }
        }

        scanner.close();
    }
}