
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "CMU_bot";
        System.out.println("Hello! Boss I'm your" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean isExit = true;
        while (isExit) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye. CMU_bot is always here for you!");
                isExit = false;
            } else {
                System.out.println(command);
            }

        }
    }
}
