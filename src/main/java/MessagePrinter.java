import java.util.HashMap;

//General helper class to help parse and print messages. Not sure how useful it can be... for now, might remove.
public class MessagePrinter {
    public String message;
    public MessagePrinter(String message) {
        this.message = message;
    }

    public void printStored() {
        System.out.println(this.message);
    }

    //Help check the nature of the message to have more personalised replies
    public static void personalisedPrint(String message) {
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






}
