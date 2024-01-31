import java.util.Scanner;
import java.util.Arrays;



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

        final int ArraySize = 100;
        Task[] Tasks = new Task[ArraySize];
        int counter = 0;

        while (true) {

            String message = input.nextLine();
            if (message.equals("bye")) {
                System.out.println(outro);
                break;
            } else if (message.equals("list")) {
                for( int i = 0; i < counter; i++ ){
                    System.out.println("____________________________________________________________\n" +
                            (i + 1) + ". " + "[" + Tasks[i].getStatusIcon() + "]" + Tasks[i].getTask() + "\n" +
                            "____________________________________________________________\n");
                }
            } else if(message.startsWith("mark")) {
                String[] parts = message.split(" ");
                int num = Integer.parseInt(parts[1]);
                Tasks[num - 1].markAsDone();
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done:" + "\n" + "[" + Tasks[num - 1].getStatusIcon() + "]" + Tasks[num - 1].getTask() + "\n" +
                        "____________________________________________________________\n");
            } else if(message.startsWith("unmark")) {
                String[] parts = message.split(" ");
                int num = Integer.parseInt(parts[1]);
                Tasks[num - 1].unmark();
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done:" + "\n" + "[" + Tasks[num - 1].getStatusIcon() + "]" + Tasks[num - 1].getTask() + "\n" +
                        "____________________________________________________________\n");
            }
            else {
                Tasks[counter] = new Task(message);
                counter++;
                System.out.println("____________________________________________________________\n" +
                        "added: " + message + "\n" +
                        "____________________________________________________________\n");
            }

        }
    }
}

