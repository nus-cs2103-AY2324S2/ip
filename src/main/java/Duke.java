import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Welcome!! I'm Belle <3.");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        boolean exit = false;
        String input = "";

        while (!exit) {
            input = sc.next();
            if (input.equals("bye")) {
                exit = true;
                break;
            }
            System.out.println("--------------------------");
            System.out.println(input);
            System.out.println("--------------------------");
        }

        System.out.println("--------------------------");
        System.out.println("Till next time!! Goodbye.");
        System.out.println("--------------------------");

    }
}
