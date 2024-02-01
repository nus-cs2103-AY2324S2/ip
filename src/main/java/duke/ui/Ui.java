package duke.ui;

import duke.task.TaskList;

import java.io.PrintStream;

public class Ui {

    private static PrintStream out;
    private final static String LINE = "-----------------------------------------------------------------------";

    public static void setOutputStream(PrintStream out) {
        Ui.out = out;
    }

    public static void greet(String name) {
        out.println(LINE);
        out.println("Heyoo I'm " + name + ".");
        out.println("What can I do for you?");
        out.println(LINE);
    }

    public static void bye(TaskList tasklist) {
        out.println(LINE);
        out.println("Bye lol see you again!");
        out.println(LINE);
        tasklist.saveTasks();
    }

    public static void echo(String input) {
        out.println(LINE);
        out.println(input);
        out.println(LINE);
    }

    public static void listTasks(TaskList tasklist) {
        out.println(LINE);
        out.println("Here are the tasks in your list: ");
        tasklist.listTask();
        out.println(LINE);
    }

    public static void addTask(TaskList tasklist, String input) {
        out.println(LINE);

        boolean added = tasklist.addTask(input);
        int tasksCount = TaskList.tasksCount;

        if (added) {
            out.println("Gotcha. I've added this task: ");
            out.println("    " + tasklist.getTaskDescription(tasksCount - 1));
            out.println("Now you have " + tasksCount + " tasks in the list.");
        }
        out.println(LINE);
    }

    public static void mark(TaskList tasklist, String input, boolean status) {
        int num = Integer.parseInt(input) - 1; // duke.task.Task num starts from 0
        tasklist.markTask(num, status);

        out.println(LINE);
        out.println(status ? "Good job on finishing this task: " : "Aw OK, I've marked this task as not done yet: ");
        out.println("    " + tasklist.getTaskDescription(num));
        out.println(LINE);
    }

    public static void delete(TaskList tasklist, String input) {
        int num = Integer.parseInt(input) - 1; // duke.task.Task num starts from 0
        String deletedTask = tasklist.getTaskDescription(num);
        tasklist.deleteTask(num);

        out.println(LINE);
        out.println("Sure, I've removed this task:");
        out.println("    " + deletedTask);
        out.println("Now you have " + TaskList.tasksCount + " tasks in the list.");
        out.println(LINE);
    }
}
