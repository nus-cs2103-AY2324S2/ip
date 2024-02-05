import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    public static void main(String[] args) {
        String greet = " Hello! I'm Bob.\n"
                + " What can I do for you?\n";

        String exit = " Bye. Hope to see you again soon!";

        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>(100);

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            }

            if (input.equals("list")) {
                int size = list.size();

                for (int count = 0; count < size; count++) {
                    System.out.println(" " + (count + 1) + ". " + list.get(count));
                }
            }

            else {
                list.add(input);
                System.out.println(" added: " + input);
            }
        }

        scanner.close();
    }
}
