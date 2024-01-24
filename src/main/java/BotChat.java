import java.util.Scanner;

public class BotChat {
    static boolean terminate = false;

    public static String response(String s) {
        if (s.equals("bye")) {
            terminate = true;
            return "Bye. Hope to see you again soon!";
        } else {
            return s;
        }
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm BotChat.\n What can I do for you?\n Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Scanner userInput = new Scanner(System.in);

        while (!terminate) {
            String command = userInput.nextLine();
            System.out.println(response(command));
        }
    }
}
