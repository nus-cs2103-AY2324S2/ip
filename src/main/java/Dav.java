import java.util.Scanner;

public class Dav {
    public static void main(String[] args) {
        greetUser();
        startChat();
        exit();
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println(" What's up! I'm Dav");
        System.out.println(" How may I help you?");
        System.out.println("____________________________________________________________");
    }

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            userInput = getUserInput(scanner);
            echoUserInput(userInput);

        } while (!userInput.equalsIgnoreCase("bye"));
    }

    public static String getUserInput(Scanner scanner) {
        System.out.print("    ");
        return scanner.nextLine();
    }

    public static void echoUserInput(String input) {
        System.out.println(" " + input);
        System.out.println("____________________________________________________________");
    }

    public static void exit() {
        System.out.println(" Goodbye.");
        System.out.println("____________________________________________________________");
    }
}
