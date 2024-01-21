import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "BotChat";

        //Introduction message
        System.out.println("____________________________________________________________\n" +
                           " Hello! I'm " + name + "\n What can I do for you?\n" +
                           "____________________________________________________________\n");

        userInput();
    }

    private static void userInput() {
        //Scanner to scan what the user is inputting
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            //Bye input + close scanner + exit program
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                scanner.close();
                System.exit(0);
            } else {
                //Echo input
                System.out.println("____________________________________________________________\n" +
                                   " Okay! Carrying out " + input + "\n" +
                                   "____________________________________________________________\n");
            }
        }
    }
}
