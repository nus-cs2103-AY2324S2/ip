import java.util.Scanner;

public class Skyler {
    public static void main(String[] args) {
        String chatbotName = "Skyler";
        String line = "------------------------------------------------------------";

        System.out.println("   /\\_/\\");
        System.out.println("  ( o.o ) Hello! I'm " + chatbotName);
        System.out.println("   > ^ < What can I do for you?");
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("You: ");
            String userInput = scanner.nextLine();
            System.out.println(line);

            if (userInput.equals("bye")) {
                System.out.println("Skyler: Bye. Hope to see you again soon!");
                System.out.println(line);
                break; 
            } else {
                System.out.println("Skyler: " + userInput);
                System.out.println(line);
            }
        }

        scanner.close();
    }
}
