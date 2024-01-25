import java.util.Objects;
import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Should I create a profile about the user by having them answer a few questions?
        // welcome_msg
        System.out.println(Std_msgs.LOGO);
        System.out.println(Std_msgs.WELCOME);
        // await input from user
        String userInput = "";
        while (!Objects.equals(userInput, Commands.BYE.toString())) {
            userInput = sc.nextLine();
            System.out.println(Std_msgs.DIVIDER);
            System.out.println(new Msg(userInput));
        }
        // bye
        System.out.println(Std_msgs.BYE);

    }
}
