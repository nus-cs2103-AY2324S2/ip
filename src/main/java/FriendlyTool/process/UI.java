package FriendlyTool.process;

import FriendlyTool.task.Task;

public class UI {
    public static void initMsg() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm FriendlyTool\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________"
        );
    }

    public static void byeMsg() {
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

    public static void updateTaskMsg(Task task, int size) {
        System.out.println("    ____________________________________________________________\n"
                + "    Completed. I've added this task: \n    "
                + task
                + "\n    Now you have " + size + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    public static void markMsg(Task task) {
        System.out.println("    ____________________________________________________________\n"
                + "    Nice! I've marked this task as done:\n"
                + "      " + task.toString()
                + "\n    ____________________________________________________________\n");
    }
    public static void unmarkMsg(Task task) {
        System.out.println("    ____________________________________________________________\n"
                + "    OK, I've marked this task as not done yet:\n"
                + "      " + task.toString()
                + "\n    ____________________________________________________________\n");
    }

    public static void deleteMsg(String task, int size) {
        System.out.println("    ____________________________________________________________\n"
                + "    Great!, You have completed the task:\n"
                + "      " + task
                + "\n    Now you have " + size + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    public static void createSaveMsg(Boolean isSuccessful) {
        System.out.println("    There was no save data.");
        System.out.println(isSuccessful ? "    New save data file created." : "    Failed to create a new save data");
    }

    public static void loadSaveMsg() {
        System.out.println("    Successfully loaded the save data. ");
    }
    public static void showList(TaskList list) {
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
