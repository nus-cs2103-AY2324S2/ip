import java.util.Scanner;

public class Duke {
    private static final int max_tasks = 100;
    private static final Task[] tasks = new Task[max_tasks];
    private static int taskCount = 0;

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
            } else if (input.startsWith("event")){
                //Add event task to list
                addEventTask(input);
            } else if (input.startsWith("deadline")) {
                //Add deadline task to list
                addDeadlineTask(input);
            } else if (input.startsWith("todo")) {
                //Add tod0 task to list
                addTodoTask(input);
            } else {
                System.out.println("____________________________________________________________\n" +
                                   " Sorry, I do not understand that command. Please try again.\n" +
                                   "____________________________________________________________\n");
            }
        }
    }

    //Method to list tasks
    private static void listTasks(String input) {
        System.out.println("____________________________________________________________\n" +
                           " Here are your tasks:\n");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________\n");
    }

    //Method to add tasks to list
    private static void addTask(Task task) {
        //Check that taskCount does not exceed maxtask
        if (taskCount < max_tasks) {
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("____________________________________________________________\n" +
                               " Okay! Added to your list: \n"
                                       + "   " + task
                                       + "\n Now you have " + taskCount + " tasks in your list\n" +
                               "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                               " Ohno :( Your task list is full. Complete some tasks first. \n" +
                               "____________________________________________________________\n");
        }
    }

    //Method to mark tasks
    private static void markTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].mark();
            System.out.println("____________________________________________________________\n" +
                               " Nice! This task has been marked as done:\n" +
                               "   " + tasks[taskIndex] + "\n" +
                               "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                               " Invalid task index inputted. Please try again.\n" +
                               "____________________________________________________________\n");
        }
    }

    //Method to unmark tasks
    private static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < taskCount) {
            tasks[taskIndex].unmark();
            System.out.println("____________________________________________________________\n" +
                               " Okay. This task has been unmarked. \n" +
                               "   " + tasks[taskIndex] + "\n" +
                               "____________________________________________________________\n");
        } else {
            System.out.println("____________________________________________________________\n" +
                               " Invalid task index inputted. Please try again.\n" +
                               "____________________________________________________________\n");
        }
    }

    //Method to add event task
    private static void addEventTask(String input) {
        String[] parts = input.split(" ", 4);
        if (parts.length == 4) {
            String description = parts[1];

            String[] time = parts[3].split("/");
            String from = time[0];
            String to = time[1];

            Task task = new Event(description, from, to);
            addTask(task);
        } else {
            System.out.println("____________________________________________________________\n" +
                               " Invalid format of Event task. Please try again with the correct format.\n" +
                               " event (event name) /from (start) /to (end)\n" +
                               "____________________________________________________________\n");
        }
    }

    //Method to add deadline task
    private static void addDeadlineTask(String input) {

    }

    //Method to add tod0 task
    private static void addTodoTask(String input) {

    }
}
