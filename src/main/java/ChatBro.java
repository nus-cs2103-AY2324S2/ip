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
                "Use the available commands: list, blah, bye\n" +
                "or type anything else to store it in your list bro.\n" +
                "_________________________\n");

        boolean isQuit = false;
        String[] storedList = new String[100];
        while (!isQuit) {
            String input = sc.nextLine();
            switch (input) {
                case "list":
                    System.out.println("Here are the items in your list bro:\n" +
                            "_________________________");
                    for (int i = 0; i < 100; i++) {
                        if (storedList[i] == null) {
                            break;
                        }
                        System.out.println(i + 1 + ". " + storedList[i]);
                    }
                    System.out.println("_________________________\n");
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
                default: // Other input will be stored into storedList
                    for (int i = 0; i < 100; i++) {
                        if (storedList[i] == null) {
                            storedList[i] = input;
                            break;
                        }
                    }
                    System.out.println("_________________________\n" +
                            "Ok bro, I've added: " + input + "\n" +
                            "_________________________\n");
                    break;
            }
        }
    }
}
