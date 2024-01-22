import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        // Initialization
        String name = "MR. WONG";
        say("Hey man. I'm " + name + "\nWhat can I do for you?");

        // Stores user items
        ArrayList<String> userItems = new ArrayList<>();

        // Input/output
        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                say("Bye. Hope to see you again soon!");
            } else if (userInput.equals("list")) {
                say(displayList(userItems));
            } else {
                say("added: " + userInput);
                userItems.add(userInput);
            }
        }
        scanner.close();
    }

    public static void say(String msg) {
        String horizontal = "_________________________________";
        System.out.println(horizontal);
        System.out.println(msg);
        System.out.println(horizontal);
    }

    public static String displayList(ArrayList<String> userItems) {
        String d = "";
        for (int i = 1; i <= userItems.size(); ++i) {
            d += (i + ". " + userItems.get(i-1) + '\n');
        }
        return d;
    }
}