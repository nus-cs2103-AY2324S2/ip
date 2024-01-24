import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm GHBot");
        System.out.println("What can I do for you?");
        List<String> lst = new ArrayList<>();

        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (str.equalsIgnoreCase("list")) {
                for (int i = 0; i < lst.size(); i++) {
                    System.out.println(i + 1 + ". " + lst.get(i));
                }
            } else {
                lst.add(str);
                System.out.println("added: " + str);
            }
        }
    }
}
