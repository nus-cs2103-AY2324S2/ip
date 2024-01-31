package Mona;
public class Ui {
    public Ui() {
        this.greet();
    }
    public void greet() {
        String introduction = "  ____________________________________________________________\n"
                + "   Hello! I'm Mona\n"
                + "   What can I do for you?\n"
                + "  ____________________________________________________________\n";
        System.out.println(introduction);
    }
    public void sayBye() {
        String farewell = "  ____________________________________________________________\n"
                + "   Bye. Hope to see you again soon!\n"
                + "  ____________________________________________________________";
        System.out.println(farewell);
    }
    public void showListAfterQuantityChange(Task task, int numTasks, boolean hasIncreased) {
        String action = hasIncreased ? "added" : "removed";
        String response = "  ____________________________________________________________\n"
                + "     Noted. I've " + action + " this task: \n"
                + "     " + task + "\n"
                + "     Now you have " + numTasks + " tasks in the list.\n"
                + "  ____________________________________________________________\n";
        System.out.println(response);
    }
    public void showListAfterProgressChange(Task task) {
        String status = task.isDone ? "done" : "not done";
        String response = "  ____________________________________________________________\n"
                + "     Got it! I've marked this task as " + status + ":\n"
                + "     " + task + "\n"
                + "  ____________________________________________________________\n";
        System.out.println(response);
    }
    public void showError(String message) {
        System.out.println("  ____________________________________________________________");
        System.out.println("    " + message);
        System.out.println("  ____________________________________________________________");
    }
}
