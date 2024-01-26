import java.util.ArrayList;

public class Printer {
    public static void printDecorator() {
        System.out.println("    ____________________________________________________________");
    }

    public static void println(String string) {
        System.out.println("     " + string);
    }

    public static void printlnFurtherIndent(String string) {
        System.out.println("       " + string);
    }

    public static void printActionAttach(Action action, Task task, int taskCount) {
        switch (action) {
            case ADD_TODO:
            case ADD_DEADLINE:
            case ADD_EVENT:
                printDecorator();
                Printer.println("Got it. I've added this task:");
                Printer.printlnFurtherIndent(task.toString());
                Printer.println("Now you have " + taskCount + " tasks in the list.");
                printDecorator();
                break;
            case DELETE:
                printDecorator();
                Printer.println("Noted. I've removed this task:");
                Printer.printlnFurtherIndent(task.toString());
                Printer.println("Now you have " + taskCount + " tasks in the list.");
                printDecorator();
                break;
            default:
                throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    public static void printActionAttach(Action action, Task task) {
        switch (action) {
            case MARK:
                printDecorator();
                Printer.println("Nice! I've marked this task as done:");
                Printer.printlnFurtherIndent(task.toString());
                printDecorator();
                break;
            case UNMARK:
                printDecorator();
                Printer.println("OK, I've marked this task as not done yet:");
                Printer.printlnFurtherIndent(task.toString());
                printDecorator();
                break;
            default:
                throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    public static void printActionAttach(Action action) {
        switch (action) {
            case BYE:
                printDecorator();
                Printer.println("Bye. Hope to see you again soon!");
                printDecorator();
                break;
            case GREET:
                printDecorator();
                Printer.println("Hello! I'm Wis.");
                Printer.println("What can I do for you?");
                printDecorator();
                break;
            default:
                throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }

    public static void printActionAttach(Action action, ArrayList<Task> tasks, int taskCount) {
        if (action == Action.LIST) {
            printDecorator();
            Printer.println("Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                Printer.println((i + 1) + "." + tasks.get(i).toString());
            }
            printDecorator();
        } else {
            throw new IllegalArgumentException("Illegal action argument provided.\n");
        }
    }
}


