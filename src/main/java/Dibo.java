import java.util.Scanner;
public class Dibo {
    private static String name = "Dibo the Gift Chatbot";
    public static void main(String[] args) {
        // Greeting the user
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");

        // Getting the command
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        // Responding
        while (!command.equals("bye")) {
            System.out.println(command);
            command = sc.nextLine();
        }

        // Exiting
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
