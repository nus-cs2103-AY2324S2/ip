import java.util.Scanner;

public class Asher {
    private static Task[] tasks = new Task[100];
    private static int count = 0;

    private static void greet() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echoCommand(String command) {
        System.out.println("added:" + " " + command);
    }

    private static void addTask(String task) {
        Task newTask = new Task(task);
        if (count < tasks.length) {
            tasks[count] = newTask;
            count++;
        } else {
            System.out.println("Task List is full, unable to add more.");
        }
    }

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "." + tasks[i].getStatusIcon() + tasks[i]);
        }
    }

    private static int getTaskNumber(String task) {
        String[] word = task.split(" ");
        if (word.length == 2) {
            int number = Integer.parseInt(word[1]) - 1;
            if (number >= 0 && number < count) {
                return number;
            }
        }
        return -1;
    }

    private static void markTaskDone(String task) {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks[taskNumber].markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + "[X]" + " " + tasks[taskNumber]);
        } else {
            System.out.println("Invalid Task!");
        }
    }

    private static void markTaskUndone(String task) {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks[taskNumber].markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + "[X]" + " " + tasks[taskNumber]);
        } else {
            System.out.println("Invalid Task!");
        }
    }

    public static void processCommand(String command) {
        String[] word = command.split(" ");

        if (word[0].equals("bye")) {
            Asher.exit();
        } else if (word[0].equals("list")) {
            displayTasks();
        } else if (word[0].equals("mark")) {
            markTaskDone(command);
        } else if (word[0].equals("unmark")) {
            markTaskUndone(command);
        } else {
            addTask(command);
            echoCommand(command);
        }
    }

    public static void main(String[] args) {
        Asher.greet();
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            command = scanner.nextLine();
            Asher.processCommand(command);

        } while (!command.equals("bye"));
    }
}
