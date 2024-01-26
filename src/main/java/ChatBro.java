import java.util.Scanner;

public class ChatBro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("_________________________\n" +
                " __  __       __\n" +
                " \\ \\/ /__    / /\n" +
                "  \\  / _ \\  /_/ \n" +
                "  /_/\\___/ (_)\n\n" +
                "I'm ChatBro!\n" +
                "What can I do for you bro?\n" +
                "Available commands: list, blah, bye\n" +
                "_________________________\n");

        boolean isQuit = false;
        while (!isQuit) {
            String input = sc.next();
            switch (input) {
                case "list":
                    System.out.println("This is the list reply bro.\n");
                    break;
                case "blah":
                    System.out.println("This is the blah reply bro.\n");
                    break;
                case "bye":
                    isQuit = true;
                    System.out.println("_________________________\n" +
                            "Bye bro! Hope to see you again soon!\n" +
                            "_________________________\n");
                    break;
                default:
                    System.out.println("I don't understand that command bro.\n");
                    break;
            }
        }
    }
}
