import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String bot_name = "Calvin";
        String greeting_msg = " Hello! I'm " + bot_name + "\n"
                + " What can I do for you? \n\n";
        String exit_msg = " Bye. Hope to see you again soon!\n";
        System.out.println(greeting_msg);

        Scanner reader = new Scanner(System.in);
        while (true) {
            String user_input = reader.next();
            if (user_input.equals("bye")) {
                break;
            }
            System.out.println(user_input);
        }
        System.out.println(exit_msg);
    }
}
