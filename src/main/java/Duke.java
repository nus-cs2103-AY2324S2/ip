import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println("Welcome!! I'm Belle <3.");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------");

        boolean exit = false;
        String input = "";
        ArrayList<String> list = new ArrayList<>();

        while (!exit) {
            input = sc.next();
            if (input.equals("bye")) {
                exit = true;
                break;
            }
            if (input.equals("list")) {
                System.out.println("--------------------------");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.valueOf(i+1) + ". " + list.get(i));
                }
                System.out.println("--------------------------");
                continue;
            }
            System.out.println("--------------------------");
            System.out.println("added: " + input);
            System.out.println("--------------------------");
            list.add(input);

        }

        System.out.println("--------------------------");
        System.out.println("Till next time!! Goodbye.");
        System.out.println("--------------------------");

    }
}
