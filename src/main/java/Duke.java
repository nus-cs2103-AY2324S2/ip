import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Greetings
        String logo = "Tommy";
        String divider = "____________________________";
        System.out.println(divider);
        System.out.println("Hello! I'm " + logo);
        System.out.println("What can I do for you?");
        System.out.println(divider);

        //Echo
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(divider);
            System.out.println(userInput);
            System.out.println(divider);

            userInput = scanner.nextLine();
        }

        //Farewell
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
