import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        String line = "_____________________________________________\n";
        String greeting = "Hello! I'm Donald.\nWhat can I do for you?\n";
        System.out.println(line + greeting + line);

        Scanner sc = new Scanner(System.in);
        String input;

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            System.out.println(line + input + "\n" + line);
        }

        String closing = "Bye. Hope to see you again soon!\n";
        System.out.println(line + closing + line);
    }
}
