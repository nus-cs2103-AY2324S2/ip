import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KAI\n" + "What can i do for you?\n");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(input);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
