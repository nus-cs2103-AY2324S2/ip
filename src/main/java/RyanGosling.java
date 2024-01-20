import java.util.Scanner;
public class RyanGosling {
    public static String chatBotName = "RyanGosling";

    public static void main(String[] args) {
        MessagePrinter.greeting(chatBotName);
        //We begin listening
        Scanner sc = new Scanner(System.in);
        ChatListener botDispatcher = new ChatListener();
        botDispatcher.chatListener();
    }
}
