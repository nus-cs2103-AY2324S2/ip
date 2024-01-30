import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String outro = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";

        System.out.println(intro);

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Message");

        while (true) {
            String message = input.nextLine();
            if (message.equals("bye")) {
                System.out.println(outro);
                break;
            } else {
                System.out.println("____________________________________________________________\n" +
                        message + "\n" +
                        "____________________________________________________________\n");
            }

        }
    }
}
