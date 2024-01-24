import java.util.Scanner;

public class Whisper {
    static String line = "-------------------------------------------------\n";
    static String name = "Whisper";
    static String welcomeMsg = "Hello! I'm " + name + " , your personal chatbot!\n" +
            "What can I do for you?\n";
    static String byeMsg = line + "Bye. Hope to see you soon!\n" + line;
    static Task[] taskList; // store tasks in array
    static int count;

    // Main method
    public static void main(String[] args) {
        System.out.println(line + welcomeMsg + line);

        Scanner sc = new Scanner(System.in);
        taskList = new Task[100];
        count = 0;

        while (true) {
            // read user input
            System.out.println("Enter your input: ");
            String input = sc.nextLine();

            // break if user exists the bot
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(byeMsg);
                break;
                // show user's list
            } else if (input.equalsIgnoreCase("list")) {
                getTasks();
                // store user list and display them back
            } else if (input.toLowerCase().startsWith("mark")) {
                // get the index of task
                int index = getIndex(input);
                printTaskDone(index);
            } else if (input.toLowerCase().startsWith("unmark")) {
                int index = getIndex(input);
                printTaskUndone(index);
            }
            else {
                addTask(input);
            }
        }
        sc.close();

    }

    // insert task into the task list
    public static void addTask(String description) {
        if (count < 100) {
            taskList[count++] = new Task(description);
            System.out.println(line + "Task added: " + description + "\n" + line);
        } else {
            // if more than 100 tasks
            System.out.println("Sorry, list is full. Can't add anymore.");
        }
    }

    // display text when task is marked done
    public static void printTaskDone(int index) {
        // check index bound
        if (index >= 0 && index < count) {
            Task t = taskList[index];
            t.markAsDone();
            System.out.println(line + "Nice! I've marked this task as done:\n" + t.toString() + "\n" + line);
        } else {
            System.out.println("Invalid task number, please try again.");
        }
    }

    // display text when task mark undone
    public static void printTaskUndone(int index) {
        // check index bound
        if (index >= 0 && index < count) {
            Task t = taskList[index];
            t.markAsUndone();
            System.out.println(line + "Nice! I've marked this task as done:\n" + t.toString() + "\n" + line);
        } else {
            System.out.println("Invalid task number, please try again.");
        }
    }

    // display task list
    public static void getTasks() {
        System.out.println(line + "Here are your task list: \n");

        for (int i = 0; i < count; i++) {
            Task currentTask = taskList[i];
            System.out.println((i + 1) + ". " + taskList[i]);
        }
        System.out.println(line);
    }

    // get task id based on command
    public static int getIndex(String input) {
        return Integer.parseInt(input.substring(input.indexOf(" ") + 1).trim()) - 1;
    }
}