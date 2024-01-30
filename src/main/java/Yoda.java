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

        Parser commandParser = new Parser(yoda);

        // Display the initial greeting message from Yoda.
        yoda.printGreeting();

        // Main loop to keep processing user input until the chatbot stops chatting.
        while (yoda.isChatting()) {
            // Read the next line of input from the user.
            String input = scanner.nextLine();

            try {
                // Use CommandParser to handle the user input
                commandParser.parseAndExecute(input);
            } catch (EmptyDescriptionException | UnknownCommandException | InvalidTaskException | TimeMissingException e) {
                yoda.printMessage(e.getMessage());
            } catch (Exception e) {
                // For any other exceptions, print an error message
                yoda.printMessage("Error occurred: " + e.getMessage());
            }
        }
        // Close the scanner to prevent resource leaks.
        scanner.close();
    }
}
