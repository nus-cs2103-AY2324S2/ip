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
                System.out.println(new Msg(items.toString()));
            } else {
                // add list
                Duke.items.add(userInput);
            }
        }
    }
}
