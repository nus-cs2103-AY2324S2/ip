package fishstock;

import java.util.ArrayList;
import java.util.Scanner;

import static fishstock.FishStock.*;

class Ui {
    protected void run(ArrayList<Task> list) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        String input;

        System.out.println(line + "Hello, I'm FishStock.\nI might help if I feel like it.");

        while (true) {
            System.out.println(line);
            input = sc.nextLine();
            System.out.print(line);

            if ("bye".equals(input)) {
                break;
            }

            // list all Tasks
            if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }

            // mark Task as done
            } else if (startsWith("mark", input)) {
                Task task = getTaskFromIndex("mark", input, list);
                if (task != null) {
                    task.markAsDone();
                    System.out.println("Did you actually finish this? \uD83E\uDD14:\n" +
                            "  " + task);
                }

            // mark Task as undone
            } else if (startsWith("unmark", input)) {
                Task task = getTaskFromIndex("unmark", input, list);
                if (task != null) {
                    task.markAsUndone();
                    System.out.println("I knew you didn't finish it! \uD83D\uDE0F:\n" +
                            "  " + task);
                }

            } else if (startsWith("delete", input)) {
                Task task = getTaskFromIndex("delete", input, list);
                if (task != null) {
                    list.remove(task);
                    System.out.println("This task has been removed:\n  " + task +
                            "\n" + "Now you have " + list.size() +
                            " task(s) in total.");
                }

            // try to add as a task
            } else if (!addTask(list, input)) {
                System.out.println("OH NOSE! Wakaranai... :(");
            }
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
