import java.util.Scanner;

public class Duke {
    private static final int max_tasks = 100;
    private static final String[] tasks = new String[max_tasks];
    private static int taskcount = 0;

    public static void main(String[] args) {
        String name = "BotChat";

        //Introduction message
        System.out.println("____________________________________________________________\n" +
                           " Hello! I'm " + name + "\n What can I do for you?\n" +
                           "____________________________________________________________\n");

        userInput();
    }

    private static void userInput() {
        //Scanner to scan what the user is inputting
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            //Bye input + close scanner + exit program
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                                   " Bye. Hope to see you again soon!\n" +
                                   "____________________________________________________________\n");
                scanner.close();
                System.exit(0);
            } else if (input.equals("list")) {
                //List tasks
                listTasks(input);
            } else {
                //Add task to list
                addTask(input);
            }
        }
    }

    //Method to list tasks
    private static void listTasks(String input) {
        System.out.println("____________________________________________________________\n" +
                           " Here are your tasks:\n");
        for (int i = 0; i < taskcount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________\n");
    }

    //Method to add tasks to list
    private static void addTask(String input) {
        //Check that taskcount does not exceed maxtask
        if (taskcount < max_tasks) {
            tasks[taskcount] = input;
            taskcount++;
            System.out.println("____________________________________________________________\n" +
                               " Okay! Adding " + input + " to list\n" +
                               "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                               " Ohno :( Your task list is full. Complete some tasks first. \n" +
                               "____________________________________________________________\n");
        }
    }
}
