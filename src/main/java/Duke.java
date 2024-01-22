import java.util.Scanner;

public class Duke {

    public static void greet() {
        String greet = "    Hi! I am your favourite friend, Lelu :) \n    What can I do for you?\n";
        System.out.println(greet);
    }
    public static void exit() {
        String exit = "    Ok bye, you shall be missed :(";
        System.out.println(exit);
    }
    public static void list(Task[] tasks) {
        for (int i = 0; i < 100; i++) {
            if (tasks[i] == null) {
                System.out.println("\n");
                break;
            }
            System.out.println("    " + (i + 1) + "." + tasks[i].toString());
        }
    }

    public static void mark(Task[] tasks, int i) {
        tasks[i].markTask();
        System.out.println("    Great job completing your task!\n      " + tasks[i].toString());
    }
    public static void unmark(Task[] tasks, int i) {
        tasks[i].unmarkTask();
        System.out.println("    Don't forget to complete your task soon...\n      " + tasks[i].toString());
    }
    public static void main(String[] args) {
        Duke.greet();

        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];

        int index = 0;

        while (true) {
            String taskName = sc.nextLine();

            if (taskName.equals("bye")) {
                Duke.exit();
                break;
            } else if (taskName.equals("list")) {
                Duke.list(tasks);
            } else if (taskName.startsWith("mark")) {
                Duke.mark(tasks, Integer.parseInt(taskName.split("\\s+")[1]) - 1);
            } else if (taskName.startsWith("unmark")) {
                Duke.unmark(tasks, Integer.parseInt(taskName.split("\\s+")[1]) - 1);
            } else {
                tasks[index++] = new Task(taskName);
                System.out.println("    added: " + taskName + "\n");
            }
        }

    }
}
