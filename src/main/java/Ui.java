public class Ui {
    public Ui() {}

    public void showCreateTask(Task task, int taskListSize) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.\n");
    }

    public void showNoTaskFound() {
        System.out.println("You don't have that task!\n");
    }

    public void showMarkTask(Task task) {
        System.out.println("Nice! I've marked this task done:");
        System.out.println(task + "\n");
    }

    public void showUnmarkTask(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task + "\n");
    }

    public void showDeleteTask(Task task, int taskListSize) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskListSize + " tasks in the list.\n");
    }
}
