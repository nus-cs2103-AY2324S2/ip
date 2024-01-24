import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
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
            if (!userInput.equals("list")) {
                list.add(userInput);
                System.out.println("added: " + userInput);
                System.out.println(divider);
            } else {
                System.out.println(divider);
                for (String item: list) {
                    int index = list.indexOf(item) + 1;
                    System.out.println(index + ": " + item);
                }
                System.out.println(divider);

            }

            userInput = scanner.nextLine();
        }

        //Farewell
        System.out.println(divider);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(divider);
    }
}
