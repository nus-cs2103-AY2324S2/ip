import java.util.Scanner;
public class RyanGosling {
    public static String chatBotName = "RyanGosling";

    public static void main(String[] args) {
        MessagePrinter.greeting(chatBotName);
        //We begin listening
        ChatListener botDispatcher = new ChatListener();
        botDispatcher.chatListener();
    }
}
