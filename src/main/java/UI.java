import java.util.Scanner;

public class UI {
    public void initMsg() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm FriendlyTool\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________"
        );
    }

    public void byeMsg() {
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

    public void updateTaskMsg(Task task, int size) {
        System.out.println("    ____________________________________________________________\n"
                + "    Completed. I've added this task: \n    "
                + task
                + "\n    Now you have " + size + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    public void markMsg(Task task) {
        System.out.println("    ____________________________________________________________\n"
                + "    OK, I've marked this task as not done yet:\n"
                + "      " + task.toString()
                + "\n    ____________________________________________________________\n");
    }

    public void showList(TaskList list) {
        System.out.println("    ____________________________________________________________\n"
                + "    Here are the tasks in your list:");
        for (int i = 1; i < list.size() + 1; i++) {
            Task task = list.get(i-1);
            if (task.isDone()) {
                System.out.println("    " + i + "." + task);
            } else {
                System.out.println("    " + i + "." + task);
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
