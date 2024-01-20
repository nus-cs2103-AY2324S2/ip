import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//Delegate printing to this class
public class MessagePrinter {
    public String message;
    public MessagePrinter(String message) {
        this.message = message;
    }

    public void printStored() {
        System.out.println(this.message);
    }

    //Help check the nature of the message to have more personalised replies
    public static void personalisedReply(String message) {
        MessagePrinter.printLine();
        HashMap<String, String> responseMap = new HashMap<>();
        responseMap.put("byee", "You meant bye?");
        responseMap.put("byeee", "You meant bye?");
        responseMap.put("hi", "Hi again!");
        if (responseMap.containsKey(message)) {
            System.out.println(responseMap.get(message));
        } else {
            System.out.println(message);
        }
        MessagePrinter.printLine();
    }
    public static void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    public static void greeting(String chatBotName) {
        MessagePrinter.printLine();
        System.out.println("Hello! I'm " + chatBotName + "\nWhat can I do for you?");
        MessagePrinter.printLine();
    }

    public static void bye() {
        MessagePrinter.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        MessagePrinter.printLine();
    }

    public static void commandPrint(String command) {
        MessagePrinter.printLine();
        System.out.println(command);
        MessagePrinter.printLine();
    }

    public static void commandListPrint(ArrayList<Task> list) {
        MessagePrinter.printLine();
        for (int i = 0; i < list.size(); i += 1) {
            System.out.print(i+1 + ". ");
            System.out.println(list.get(i));
        }
        MessagePrinter.printLine();
    }

    public static void markActionPrint(String action, Task task) {
        MessagePrinter.printLine();
        if (action.equals("mark")) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(task);
        MessagePrinter.printLine();

    }

}
