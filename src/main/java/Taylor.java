import java.util.Scanner;

public class Taylor {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");

        while(true) {
            Scanner type = new Scanner(System.in);
            String input = type.nextLine();

            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}