import java.util.Scanner;
public class Duke {
    private static Items items = new Items();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Should I create a profile about the user by having them answer a few questions?
        // welcome_msg
        System.out.println(Std_msgs.LOGO);
        System.out.println(Std_msgs.WELCOME);
        // await input from user
        String userInput = "";
        while (true) {
            userInput = sc.nextLine();
            if (userInput.startsWith("bye")) {
                // bye
                System.out.println(Std_msgs.BYE);
                break;
            } else if (userInput.startsWith("list")) {
                // unsure if i should extend Items as a Msg
                System.out.println(new Msg(Duke.items.toString()));
                // add new Task
            } else if (userInput.startsWith("unmark")) {
                Duke.items.unmark(Integer.parseInt(userInput.substring(7)));
            } else if (userInput.startsWith("mark")) {
                Duke.items.mark(Integer.parseInt(userInput.substring(5)));
            } else if (userInput.startsWith("todo")) {
                Duke.items.add(new Todo(userInput.substring(6)));
            } else if (userInput.startsWith("deadline")) {
                String[] inputs = userInput.split("/by", 2);
                Duke.items.add(new Deadline(inputs[0], inputs[1]));
            } else if (userInput.startsWith("event")) {
                String[] inputs = userInput.split("/", 3); // 0 has description, 1 has from, 2 has to
                Duke.items.add(
                        new Event(inputs[0],
                                inputs[1].substring(4),
                                inputs[2].substring(2)));
            } else {
                Duke.items.add(new Task(userInput));
            }
        }
    }
}
