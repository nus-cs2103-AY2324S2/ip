import java.util.Scanner;
import java.util.ArrayList;

public class Stille {
    public static void main(String[] args) {
        String openingMessage = "Hello! I'm Stille\n" + "What can I do for you?\n";
        String closingMessage = "Bye. Hope to see you again soon!\n";
        String input = "";
        String closeCommand = "bye";
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>(100);

        System.out.println(openingMessage);

        while (true) {
            input = sc.nextLine();
            if (input.equals("")) {
                continue;
            }
            if (input.equals(closeCommand)) {
                break;
            }
            if (input.equals("list")) {
                for (String line : list) {
                    System.out.println(line);
                }
                continue;
            }
            list.add((list.size() + 1) + ". " + input);
            System.out.println("added: " + input);
        }

        System.out.println(closingMessage);



    }
}
