import java.util.Scanner;

public class Duke {
    private static final int max_tasks = 100;
    private static final Task[] tasks = new Task[max_tasks];
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
            } else if (input.startsWith("mark")) {
                //mark tasks as complete
                markTask(input);
            }else if (input.startsWith("unmark")) {
                //unmark tasks
                unmarkTask(input);
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
            Task task = new Task(input);
            tasks[taskcount] = task;
            taskcount++;
            System.out.println("____________________________________________________________\n" +
                               " Okay! Adding " + task + " to your list\n" +
                               "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                               " Ohno :( Your task list is full. Complete some tasks first. \n" +
                               "____________________________________________________________\n");
        }
    }

    //Method to mark tasks
    private static void markTask(String input) {
        int taskindex = Integer.parseInt(input.substring(5)) - 1;
        if (taskindex >= 0 && taskindex < taskcount) {
            tasks[taskindex].mark();
            System.out.println("____________________________________________________________\n" +
                               " Nice! This task has been marked as done:\n" +
                               "   " + tasks[taskindex] + "\n" +
                               "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                               " Invalid task index inputted. Please try again.\n" +
                               "____________________________________________________________\n");
        }
    }

    //Method to unmark tasks
    private static void unmarkTask(String input) {
        int taskindex = Integer.parseInt(input.substring(7)) - 1;
        if (taskindex >= 0 && taskindex < taskcount) {
            tasks[taskindex].unmark();
            System.out.println("____________________________________________________________\n" +
                    " Okay. This task has been unmarked. \n" +
                    "   " + tasks[taskindex] + "\n" +
                    "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                    " Invalid task index inputted. Please try again.\n" +
                    "____________________________________________________________\n");
        }
    }
}
