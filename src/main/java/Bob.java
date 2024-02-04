import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        String greet = " Hello! I'm Bob.\n"
                + " What can I do for you?\n";

        String exit = " Bye. Hope to see you again soon!";

        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }

            System.out.println(" " + input);
        }

        scanner.close();
    }
}
