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
            if (userInput.equals("bye")) {
                // bye
                System.out.println(Std_msgs.BYE);
                break;
            } else if (userInput.equals("list")) {
                // unsure if i should extend Items as a Msg
                System.out.println(new Msg(Duke.items.toString()));
                // add new Task
            } else if (userInput.length() > 6 && userInput.substring(0, 6).equals("unmark")) {
                Duke.items.unmark(Integer.parseInt(userInput.substring(7)));
            } else if (userInput.length() > 4 && userInput.substring(0, 4).equals("mark")) {
                Duke.items.mark(Integer.parseInt(userInput.substring(5)));
            } else {
                Duke.items.add(new Task(userInput));
            }
        }
    }
}
