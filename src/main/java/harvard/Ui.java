package harvard;

import harvard.tasks.Task;
public class Ui {
    public Ui() {
    }

    public void showWelcome() {
        String initial = "____________________________________________________________\n"
                + "Hello! I'm Harvard\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(initial);
    }

    public void showGoodbye() {
        String exit = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        System.out.println(exit);
    }

    public void printAddTask(Task task, int tasksLeft) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksLeft + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    public void printDeleteTask(Task task, int tasksLeft) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + tasksLeft + " tasks in the list.");
        System.out.println("____________________________________________________________\n");
    }

    public void printMark(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________\n");
    }

    public void printUnmark(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println("____________________________________________________________\n");
    }

    public void printUnrecognisedCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Bro... Idk what that is man.");
        System.out.println("____________________________________________________________");
    }

    public void printTasks(TaskList tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            System.out.println(i + 1 + ". " + tasks.printString(i));
        }
        System.out.println("____________________________________________________________\n");
    }

}
