import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // storing user input
        ArrayList<Task> al = new ArrayList<>();

        // welcome message
        welcome();

        // taking in user input performing actions
        Scanner scanner = new Scanner(System.in);
        String userIn = scanner.nextLine();
        while (!userIn.equals("bye")) {
            String echoMsg;

            String[] tokens = userIn.split(" ");
            switch (tokens[0]) {
                case "list":   // echos stored user inputs
                    StringBuilder strItems = new StringBuilder();
                    for (int i = 0; i < al.size() - 1; i++) {
                        strItems.append(String.format("%d. %s\n\t\t", i + 1, al.get(i)));
                    }
                    strItems.append(String.format("%d. %s", al.size(), al.get(al.size() - 1)));
                    echoMsg = String.format(Config.PLACEHOLDER, strItems);
                    break;
                case "mark": {  // mark task as done
                    int itemIndex = Integer.parseInt(tokens[1]) - 1;
                    al.get(itemIndex).done();

                    echoMsg = String.format(
                            Config.PLACEHOLDER,
                            "Nice! I've marked this task as done:\n\t\t\t" +
                                    al.get(itemIndex));
                    break;
                }
                case "unmark": {  // mark task as undone
                    int itemIndex = Integer.parseInt(tokens[1]) - 1;
                    al.get(itemIndex).undone();

                    echoMsg = String.format(
                            Config.PLACEHOLDER,
                            "OK, I've marked this task as not done yet:\n\t\t\t" +
                                    al.get(itemIndex));
                    break;
                }
                default:   // echo and store the user input
                    al.add(new Task(userIn));
                    echoMsg = String.format(
                            Config.PLACEHOLDER,
                            "added: " + userIn);
                    break;
            }
            System.out.println(echoMsg);

            userIn = scanner.nextLine();
        }

        // goodbye message
        bye();
    }

    private static void welcome() {
        String wcMsg = String.format(
                Config.PLACEHOLDER,
                "Hello! I'm SIMP-LI\n\t\t" +
                        "How can I simp-lify your life?"
        );
        System.out.println(wcMsg);
    }

    private static void bye() {
        String gbMsg = String.format(
                Config.PLACEHOLDER,
                "Bye. Hope to simp for you again soon!"
        );
        System.out.println(gbMsg);
    }
}