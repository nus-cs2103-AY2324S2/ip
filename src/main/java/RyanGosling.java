import java.util.Scanner;
public class RyanGosling {
    public static String chatBotName = "RyanGosling";

    public static void chatListener(Scanner sc) {
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                MessagePrinter.bye();
                return;
            }
            MessagePrinter.personalisedPrint(command);
        }

    }

    public static void main(String[] args) {
        MessagePrinter.greeting(chatBotName);
        //We begin listening
        Scanner sc = new Scanner(System.in);
        RyanGosling.chatListener(sc);
    }
}
