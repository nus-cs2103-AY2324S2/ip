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

            String[] tokens = Parser.parse(userIn);
            switch (tokens[0]) {
                case "list":  // list stored tasks
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
                case "todo": {  // saves task as todo
                    Todo todo = new Todo(tokens[1]);
                    al.add(todo);
                    echoMsg = String.format(
                            Config.PLACEHOLDER,
                            "Got it. I've added this task:\n\t\t\t" +
                                    todo +
                                    "\n\t\t" +
                                    "Now you have " +
                                    al.size() +
                                    " task(s) in the list."
                    );
                    break;
                }
                case "deadline": {  // saves task as deadline
                    Deadline deadline = new Deadline(tokens[1], tokens[3]);
                    al.add(deadline);
                    echoMsg = String.format(
                            Config.PLACEHOLDER,
                            "Got it. I've added this task:\n\t\t\t" +
                                    deadline +
                                    "\n\t\t" +
                                    "Now you have " +
                                    al.size() +
                                    " task(s) in the list."
                    );
                    break;
                }
                case "event": {  // saves task as event
                    Event event = new Event(tokens[1], tokens[3], tokens[5]);
                    al.add(event);
                    echoMsg = String.format(
                            Config.PLACEHOLDER,
                            "Got it. I've added this task:\n\t\t\t" +
                                    event +
                                    "\n\t\t" +
                                    "Now you have " +
                                    al.size() +
                                    " task(s) in the list."
                    );
                    break;
                }
                default: {  // no such action
                    echoMsg = String.format(
                            Config.PLACEHOLDER,
                            "no such action, cannot simp for you!");
                    break;
                }
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