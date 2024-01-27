import javax.swing.*;
import java.util.Scanner;

public class Ragdoll{
    private static String user = "Master";
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static String line = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " /$$$$$$$                            /$$           /$$ /$$\n"
                + "| $$__  $$                          | $$          | $$| $$\n"
                + "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$ | $$| $$\n"
                + "| $$$$$$$/ |____  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$\n"
                + "| $$__  $$  /$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$| $$\n"
                + "| $$  \\ $$ /$$__  $$| $$  | $$| $$  | $$| $$  | $$| $$| $$\n"
                + "| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$| $$\n"
                + "|__/  |__/ \\_______/ \\____  $$ \\_______/ \\______/ |__/|__/\n"
                + "                     /$$  \\ $$                            \n"
                + "                    |  $$$$$$/                            \n"
                + "                     \\______/                             \n";
        System.out.println("Hello! I am your virtual assistant \n" + "\n" + logo);

        System.out.println("How can I assist you today, " + user + "?");

        System.out.println(line);

        while(true) {
            String input = scanner.nextLine().trim();
            System.out.println(line);

            if ("bye".equalsIgnoreCase(input)) {
                break;
            } else if ("list".equalsIgnoreCase(input)) {
                listTasks();
            } else if (input.startsWith("mark ")) {
                int idx = Integer.parseInt(input.substring(5)) - 1;
                if (tasks[idx] == null) {
                    System.out.println("No task numbered " + (idx + 1) + ", " + user + "!");
                } else {
                    tasks[idx].markTask();
                }
            } else if (input.startsWith("unmark")) {
                int idx = Integer.parseInt(input.substring(7)) - 1;
                if (tasks[idx] == null) {
                    System.out.println("No task numbered " + (idx + 1) + ", " + user + "!");
                } else {
                    tasks[idx].unmark();
                }
            } else {
                addTask(input);
            }

            System.out.println(line);

        }

        scanner.close();
        System.out.println("See ya, " + user + "!");
        System.out.println(line);
    }

    private static class Task{
        private String description;
        private boolean isDone;

        private Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markTask() {
            this.isDone = true;
            System.out.println(user + "! I've marked this task as done: ");
            System.out.println(this.toString());
        }

        public void unmark() {
            this.isDone = false;
            System.out.println("Ok, " + user + "! I've undone this task: ");
            System.out.println(this.toString());
        }

        public String toString() {
            String status = isDone ? "[X]" : "[ ]";
            return status + " " + description;
        }
    }

    private static void addTask(String description) {
        if (taskCount < MAX_TASKS) {
            Task task = new Task(description);
            tasks[taskCount] = task;
            taskCount++;
            System.out.println("added: " + description);
        } else {
            System.out.println("Task list is full!");
        }
    }

    private static void listTasks() {
        if (taskCount == 0) {
            System.out.println("There is no task yet, Master!");
        } else {
            System.out.println("Your task list has the following tasks, Master: ");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
    }
}
