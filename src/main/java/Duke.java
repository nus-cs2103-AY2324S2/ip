import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greetUser();
        echo();
        exit();
    }
    private static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Judy");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
    }

    private static void echo() {
        Scanner scanner = new Scanner(System.in);
        String  command;

        do {
            System.out.print("\n");
            command = scanner.nextLine();
            if(! command.equals("bye")){
                System.out.println("_________________________________________");
                System.out.println(" " + command);
                System.out.println("_________________________________________");
            }
        } while (!command.equalsIgnoreCase("bye"));

        scanner.close();
    }

    private static void exit() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________");
    }
}
