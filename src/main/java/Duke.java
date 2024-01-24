import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Yippee";

        //greeting
        System.out.println("    ____________________________________________________________");
        System.out.printf("    Hello! I'm %s\n", name);
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        //scan for input
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        //echo until user inputs bye
        while(!command.toLowerCase().equals("bye")) {
            echoText(command);
            command = sc.nextLine();
        }

        //exit
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon wooo!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echoText(String text) {
        System.out.println("    ____________________________________________________________");
        System.out.printf("    %s\n", text);
        System.out.println("    ____________________________________________________________");

    }
}
