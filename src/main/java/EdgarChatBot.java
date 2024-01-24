import java.util.Scanner;

public class EdgarChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Edgar.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        scanner.nextLine();

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        scanner.close();
    }
}

