import java.util.ArrayList;
import java.util.Scanner;

public class Irwyn {
    public static void main(String[] args) {
        String linebreak = "____________________________________________________________\n";
        ArrayList<String> list = new ArrayList<>();
        String start = linebreak
                + "Hello! I'm Irwyn\n"
                + "What can I do for you?\n"
                + linebreak;
        System.out.println(start);

        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();

        while (!userInput.isEmpty()) {
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.equals("list")) {
                System.out.println(linebreak);
                for (int i = 0; i < list.size(); i++) {
                    String listStatement = i + 1 + ". " + list.get(i);
                    System.out.println(listStatement);
                }
                System.out.println(linebreak);
            } else {
                System.out.println(linebreak
                        + "added: " + userInput + "\n"
                        + linebreak);
                list.add(userInput);
            }
            userInput = input.nextLine();
        }
        String end = linebreak
                + "Bye. Hope to see you again soon!\n"
                + linebreak;
        System.out.println(end);
    }
}
