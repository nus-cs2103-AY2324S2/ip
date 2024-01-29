import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "__________________________________\n" +
                "Hello! I'm Tim \n" +
                "What can i do for you? \n" +
                "__________________________________\n";

        String exit = "Bye. Hope to see you again soon!\n" +
                "__________________________________";

        System.out.println(logo);

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (!input.isEmpty()) {
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }

            String msg = "__________________________________\n" +
                    input +
                    "\n__________________________________\n";
            System.out.println(msg);
            input = scan.nextLine();
        }

    }
}
