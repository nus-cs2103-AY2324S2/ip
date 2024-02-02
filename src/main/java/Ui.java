import java.util.ListIterator;

public class Ui {

    private static final String LINE = "____________________________________________________________";

    public void showLine() {
        System.out.println(Ui.LINE);
    }

    public void newLine() {
        System.out.println();
    }

    public void showWelcome() {
        System.out.println(String.format("Hello! I'm %s. \nWhat can I do for you? \n", "Bond"));
        this.showLine();
        this.newLine();
    }

    public void taskAdded(Task newTask, TaskList taskList) {
        this.showLine();
        System.out.println(String.format(
                "\n\n    Got it. I've added this task:\n      %s \n    Now you have %d tasks in the list.\n",
                newTask.toString(), taskList.numberOfTasks()));
        this.showLine();
        this.newLine();
    }

    public void taskDeleted(Task deletedTask, TaskList taskList) {
        this.showLine();
        System.out.println(String.format(
                "\n\n    Got it. I've removed this task:\n      %s \n    Now you have %d tasks in the list.\n",
                deletedTask.toString(), taskList.numberOfTasks()));
        this.showLine();
        this.newLine();
    }

    public void taskMarked(Task markedTask, TaskList taskList) {
        this.showLine();
        System.out
                .println(String.format(
                        "\n\n    Nice! I've marked this task as done:\n      %s \n",
                        markedTask.toString()));
        this.showLine();
        this.newLine();
    }

    public void taskUnmarked(Task unmarkedTask, TaskList taskList) {
        this.showLine();
        System.out
                .println(String.format(
                        "\n\n    OK, I've marked this task as not done yet:\n      %s \n",
                        unmarkedTask.toString()));
        this.showLine();
        this.newLine();
    }

    public void showList(TaskList taskList) {
        ListIterator<Task> tasks = taskList.getTasks();

        this.showLine();
        System.out.println(String.format("\n\n    Here are the tasks in your list:"));

        while (tasks.hasNext()) {
            System.out.println(String.format("    %d. %s",
                    tasks.nextIndex() + 1, tasks.next().toString()));
        }

        this.showLine();
        this.newLine();
    }

    public void showError(Exception e) {
        this.showLine();
        System.out.println(String.format("\n\n    %s\n", e.getMessage()));
        this.showLine();
        this.newLine();
    }

    public void showGoodbye() {
        this.showLine();
        System.out.println("\n\n    Bye. Hope to see you again soon!\n");
        this.showLine();
        this.newLine();
    }
}
