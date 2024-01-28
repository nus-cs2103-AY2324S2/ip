import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Charlie {
    public static void main(String[] args) {

        List<String> stringList = new ArrayList<>();

        System.out.println("Hello, I'm Charlie");
        System.out.println("What can I do for you?");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < stringList.size(); i++) {
                    System.out.println((i + 1) + ". " + stringList.get(i));
                }
            } else {
                System.out.println("added: " + input);
                stringList.add(input);
            }
        }
    }
}
