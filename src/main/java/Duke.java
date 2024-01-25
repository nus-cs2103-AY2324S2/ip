import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<Task>();
    public static Integer taskCount = 0;

    public static void main(String[] args) {
        //Greets User
        String lineBreak = "____________________________________________________";
        System.out.println(lineBreak + '\n' +
                "Hello! I'm Pororo" + '\n' +
                "What can I do for you?" + '\n' +
                lineBreak);

        Scanner sc = new Scanner(System.in);
        String user_input = sc.nextLine();

        while (!user_input.equals("bye")) {
            try {
                String[] inputArray = user_input.split(" ");
                switch (inputArray[0]) {
                    case "list":
                        if (taskList.size() == 0) {
                            System.out.println(lineBreak + '\n' +
                                    "There is currently no task in your list, trying adding some!" + '\n' +
                                    lineBreak);
                        } else {
                            System.out.println(lineBreak + '\n' +
                                    "Here are the tasks in your list: ");
                            for (int i = 0; i < taskCount; i++) {
                                System.out.println("" + (i + 1) + ". " + taskList.get(i));
                            }
                            System.out.println(lineBreak);
                        }
                        break;
                }
            } catch (Exception e) {
                System.out.println(lineBreak + '\n' +
                        e.getMessage() + '\n' +
                        lineBreak);

            }
            user_input = sc.nextLine();
        }


        System.out.println(lineBreak + '\n' +
                "Bye. Hope to see you again soon!" + '\n' +
                lineBreak);
    }
}



//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

