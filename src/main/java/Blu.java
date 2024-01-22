import java.util.Scanner;

public class Blu {
    private static final String PROMPT = "> ";
    
    public static void main(String[] args) {
        Chatbot bot = new Chatbot("Blu");
        InputHandler inputHandler = new InputHandler();
        bot.greet();

        Scanner scanner = new Scanner(System.in);
        System.out.print(PROMPT);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            inputHandler.handleInput(userInput, bot);
            System.out.print(PROMPT);
            userInput = scanner.nextLine();
        }
        scanner.close();
        bot.exit();
    }
}