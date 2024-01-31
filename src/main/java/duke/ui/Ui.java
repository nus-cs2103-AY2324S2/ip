package duke.ui;

import duke.task.TaskList;

public class Ui {
    private final static String LINE = "-----------------------------------------------------------------------";

    public static void greet(String name) {
        System.out.println(LINE);
        System.out.println("Heyoo I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public static void bye(TaskList tasklist) {
        System.out.println(LINE);
        System.out.println("Bye lol see you again!");
        System.out.println(LINE);
        tasklist.saveTasks();
    }

    public static void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }

    public static void listTasks(TaskList tasklist) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list: ");
        tasklist.listTask();
        System.out.println(LINE);
    }

    public static void addTask(TaskList tasklist, String input) {
        System.out.println(LINE);

        boolean added = tasklist.addTask(input);
        int tasksCount = TaskList.tasksCount;

        if (added) {
            System.out.println("Gotcha. I've added this task: ");
            System.out.println("    " + tasklist.getTaskDescription(tasksCount - 1));
            System.out.println("Now you have " + tasksCount + " tasks in the list.");
        }
        System.out.println(LINE);
    }

    public static void mark(TaskList tasklist, String input, boolean status) {
        int num = Integer.parseInt(input) - 1; // duke.task.Task num starts from 0
        tasklist.markTask(num, status);

        System.out.println(LINE);
        System.out.println(status ? "Good job on finishing this task: " : "Aw OK, I've marked this task as not done yet: ");
        System.out.println("    " + tasklist.getTaskDescription(num));
        System.out.println(LINE);
    }

    public static void delete(TaskList tasklist, String input) {
        int num = Integer.parseInt(input) - 1; // duke.task.Task num starts from 0
        String deletedTask = tasklist.getTaskDescription(num);
        tasklist.deleteTask(num);

        System.out.println(LINE);
        System.out.println("Sure, I've removed this task:");
        System.out.println("    " + deletedTask);
        System.out.println("Now you have " + TaskList.tasksCount + " tasks in the list.");
        System.out.println(LINE);
    }
}
