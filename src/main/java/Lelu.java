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
            System.out.printf("    %d.%s", i + 1, tasks[i].toString());
        }
        System.out.printf("\n");
    }

    public static void mark(Task[] tasks, int i) {
        tasks[i].markTask();
        System.out.printf("    Great job completing your task!\n      %s\n\n", tasks[i].toString());
    }
    public static void unmark(Task[] tasks, int i) {
        tasks[i].unmarkTask();
        System.out.printf("    Don't forget to complete your task soon...\n      %s\n\n", tasks[i].toString());
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
                Duke.mark(tasks, Integer.parseInt(taskName.split(" ")[1]) - 1);
            } else if (taskName.startsWith("unmark")) {
                Duke.unmark(tasks, Integer.parseInt(taskName.split(" ")[1]) - 1);
            } else {
                Task t = null;
                if(taskName.startsWith("todo")) {
                    t = new ToDo(taskName.substring(5));
                } else if(taskName.startsWith("deadline")) {
                    String[] d = taskName.substring(9).split("/");
                    t = new Deadline(d[0], d[1].substring(3));
                } else if(taskName.startsWith("event")) {
                    String[] e = taskName.substring(6).split("/");
                    t = new Event(e[0], e[1].substring(5), e[2].substring(3));
                } else {
                    t = new Task(taskName);
                }
                tasks[index++] = t;
                System.out.printf("    Ok! I have added your task:\n      %s%n    You have %d task(s) in the " +
                                "list now.\n\n", t.toString(), index);
            }
        }

    }
}
