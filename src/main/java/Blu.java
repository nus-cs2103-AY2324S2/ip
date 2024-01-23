import java.util.Scanner;
import exceptions.BluException;

public class Blu {
    private static final String PROMPT = "> ";

    private static String readUserInput(Scanner scanner) {
        System.out.print(PROMPT);
        return scanner.nextLine();
    }
    
    public static void main(String[] args) {
        Chatbot bot = new Chatbot("Blu");
        InputHandler inputHandler = new InputHandler();
        bot.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput = readUserInput(scanner);
        while (!userInput.equals("bye")) {
            if (userInput.isEmpty()) {
                userInput = readUserInput(scanner);
                continue;
            }
            try {
                inputHandler.handleInput(userInput, bot);
            } catch (BluException e) {
                System.out.println(e.getMessage());
            } finally {
                userInput = readUserInput(scanner);
            }
        }
        scanner.close();
        bot.exit();
    }
}