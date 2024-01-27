import java.util.Scanner;

/**
 * Main class for the Yoda chatbot application.
 */
public class Yoda {
    public static void main(String[] args) {
        // Name of the chatbot is set to "Yoda".
        String chatbotName = "Yoda";

        // Create an instance of the YodaUI class with the specified chatbot name.
        YodaUI yoda = new YodaUI(chatbotName);

        // Scanner to read user input from the console.
        Scanner scanner = new Scanner(System.in);

        // Display the initial greeting message from Yoda.
        yoda.printGreeting();

        // Main loop to keep processing user input until the chatbot stops chatting.
        while (yoda.isChatting()) {
            // Read the next line of input from the user.
            String input = scanner.nextLine();

            try {
                // Handle the user's input and perform the appropriate action.
                yoda.handleUserInput(input);
            } catch (EmptyDescriptionException | UnknownCommandException | InvalidTaskException | TimeMissingException e) {
                // Catch specific exceptions and print their messages.
                yoda.printMessage(e.getMessage());
            } catch (Exception e) {
                // Catch any other exceptions and throw a RuntimeException.
                throw new RuntimeException(e);
            }
        }

        // Close the scanner to prevent resource leaks.
        scanner.close();
    }
}
