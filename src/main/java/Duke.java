import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        // Initialise chatbot
        ItemList itemList = new ItemList();
        String name = "Sleepy";
        String welcomeLine = "Hello! I'm " + name;
        String questionLine = "What can I do for you?";
        System.out.println(welcomeLine);
        System.out.println(questionLine);
        // Await next command from user
        Scanner userInput = new Scanner(System.in);
        while (true) {
            String nextUserCommand = userInput.nextLine();
            if (nextUserCommand.equals("bye")) {
                CommandHandler.exit();
                break;
            }
            if (nextUserCommand.equals("list")) {
                itemList.printItems();
                continue;
            }
            Item item = new Item(nextUserCommand);
            itemList.addItem(item);
        }
    }
}
