import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Hello! I'm Dukey.");
	    System.out.println("What can I do for you?");

        String input ;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(input);
        }

    }
}
