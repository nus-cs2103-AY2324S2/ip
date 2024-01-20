import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Artemis");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            System.out.println();
            String str = sc.nextLine();
            if (!str.equals("bye")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     " + str);
                System.out.println("    ____________________________________________________________");
            } else {
                break;
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }
}
