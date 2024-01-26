/**
 * The IreneAI Class represents a simple chatbot.
 * For the starter, it greets the user and say goodbye before exiting
 */
public class IreneAI {
    public static void main(String[] args){
        String chatbotName = "IreneAI";
        String line = "____________________________________________________________";
        dividingLine(line);
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");

        // Print the farewell message
        dividingLine(line);
        System.out.println(" Bye. Hope to see you again soon!");
        dividingLine(line);
    }

    public static void dividingLine(String line){
        System.out.println(line);
    }
}
