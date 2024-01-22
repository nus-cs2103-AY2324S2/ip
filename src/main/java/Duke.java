import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "MR. WONG";
        say("Hey man. I'm " + name + "\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("bye")) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                say("Bye. Hope to see you again soon!");
            } else {
                say(userInput);
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
}