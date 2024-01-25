import java.util.Scanner;

public class Eggy {
    public static void main(String[] args) {
        String name = "Eggy";
        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm " + name + "\uD83E\uDD5A.");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();;
        while (!command.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     " + command);
            System.out.println("    ____________________________________________________________");
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }
}
