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
                System.out.println("Here are the tasks in your list: " + "\n" +
                        "____________________________________________________________\n");
                for( int i = 0; i < counter; i++ ) {
                        System.out.println((i + 1) + ". " + Tasks[i].toString() + "\n");
                    }
                System.out.println("____________________________________________________________\n");

            } else if(message.startsWith("mark")) {
                String[] parts = message.split(" ");
                int num = Integer.parseInt(parts[1]);
                Tasks[num - 1].markAsDone();
                System.out.println("____________________________________________________________\n" +
                        "Nice! I've marked this task as done:" + "\n" + Tasks[num - 1].toString() + "\n" +
                        "____________________________________________________________\n");
            } else if(message.startsWith("unmark")) {
                String[] parts = message.split(" ");
                int num = Integer.parseInt(parts[1]);
                Tasks[num - 1].unmark();
                System.out.println("____________________________________________________________\n" +
                        "OK, I've marked this task as not done yet:" + "\n" + Tasks[num - 1].toString() + "\n" +
                        "____________________________________________________________\n");
            } else if(message.startsWith("todo")){
                String subString = message.substring(5);
                Tasks[counter] = new Todo(subString);
                counter++;
                System.out.println("____________________________________________________________\n" +
                        "Got it.I've added this task:" + "\n" + Tasks[counter - 1].toString() + "\n" +
                        "Now you have " + counter + " tasks in the list." + "\n" +
                        "____________________________________________________________\n");
            } else if(message.startsWith("deadline")) {
                String subString = message.substring(9);
                String[] parts = subString.split("/by");
                Tasks[counter] = new Deadline(parts[0], parts[1]);
                counter++;
                System.out.println("____________________________________________________________\n" +
                        "Got it.I've added this task:" + "\n" + Tasks[counter - 1].toString() + "\n" +
                        "Now you have " + counter + " tasks in the list." + "\n" +
                        "____________________________________________________________\n");
            }else {
                String subString = message.substring(6);
                String[] parts = subString.split("/from");
                String[] dateParts = parts[1].split("/to");
                Tasks[counter] = new Event(parts[0], dateParts[0], dateParts[1]);
                counter++;
                System.out.println("____________________________________________________________\n" +
                        "Got it.I've added this task:" + "\n" + Tasks[counter - 1].toString() + "\n" +
                        "Now you have " + counter + " tasks in the list." + "\n" +
                        "____________________________________________________________\n");
            }

        }
    }
}

