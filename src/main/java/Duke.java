import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________");
        System.out.println("Hello! I'm Waffles");
        System.out.println("What can I do for you?");
        System.out.println("____________________");

        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________");
                break;
            } else {
                echoCommand(command);
            }
        }

        sc.close();

    }

    private static void echoCommand(String command) {
        System.out.println(command);
    }

}
