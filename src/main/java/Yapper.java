import java.util.Scanner;
public class Yapper {
    public static void main(String[] args) {
        String chatbotName = "Yapper";
        Scanner scanner = new Scanner(System.in);
        System.out.println(" Hello! I'm " + chatbotName + ". I talk a lot, hence my name, but I will be sure to keep you company.");
        System.out.println(" What can I do for you?");

        while (true) {
            System.out.println("User: ");
            String userInput = scanner.nextLine();
            System.out.println(chatbotName + ": " + userInput);

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
        }
        scanner.close();
    }
}

