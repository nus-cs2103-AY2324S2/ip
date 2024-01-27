package kaiyap;

public class Ui {

    TaskList taskList;

    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    public void sayHello() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm KaiYap.\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
    }

    public void sayBye() {
        System.out.println("\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________");
    }

    public void listInputs() {
        System.out.println("\t____________________________________________________________\n\tHere are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    public void printError(String error) {
        System.out.println("\t____________________________________________________________\n" +
                error +
                "\n\t____________________________________________________________\n");
    }

    public void printTaskRemoved(Task t, int size) {
        System.out.println(
                "\t____________________________________________________________\n" +
                        "\tNoted. I've removed this task:\n" +
                        "\t\t" + t.toString() +
                        "\n\tYou now have " + size + (size == 1 ? " task" : " tasks") + " in the list.\n" +
                        "\t____________________________________________________________"
        );
    }
}
