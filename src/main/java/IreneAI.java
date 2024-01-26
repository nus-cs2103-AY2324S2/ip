import java.util.Scanner;

/**
 * The IreneAI Class represents a simple chatbot.
 * For the starter, it greets the user and say goodbye before exiting
 */
public class IreneAI {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String chatbotName = "IreneAI";
        String line = "____________________________________________________________";
        dividingLine(line);
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        dividingLine(line);

        // Echo message
        while (true) {
            System.out.println("You: ");
            String userInput = scanner.nextLine();
            dividingLine(line);

            // Terminating condition
            if (userInput.equalsIgnoreCase("bye")){
                break;
            }
            System.out.println("IreneAI: I heard you said '" + userInput + "'.");
            dividingLine(line);
        }

        // Print the farewell message
        System.out.println(" Bye. Hope to see you again soon!");
        dividingLine(line);
    }

    public static void dividingLine(String line){
        System.out.println(line);
    }
}
