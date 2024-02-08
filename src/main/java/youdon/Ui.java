package youdon;

public class Ui {

    private final String DIVIDER_LINE = "----------------------------------------------------------------";
    public Ui() {

    }

    public void printWelcomeMsg() {
        System.out.println(DIVIDER_LINE);
        System.out.println("Hello! I'm Youdon!\nWhat can I do for you?\n");
        System.out.println(DIVIDER_LINE);
    }

    public void printByeMsg() {
        System.out.println(DIVIDER_LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER_LINE);
    }

    public void printErrorMsg(String message) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Oh no!" + message);
        System.out.println(DIVIDER_LINE);
    }

    public void printYoudonErrorMsg(String message) {
        System.out.println(DIVIDER_LINE);
        System.out.println(message);
        System.out.println(DIVIDER_LINE);
    }

    public void printTaskList(TaskList taskList) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Here are your tasks:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println(DIVIDER_LINE);
    }

    public void printMarkMsg(TaskList taskList, int taskNumber) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Nicely done! The task has been marked as done:");
        System.out.println("  " + taskList.get(taskNumber - 1).toString());
        System.out.println(DIVIDER_LINE);
    }

    public void printUnmarkMsg(TaskList taskList, int taskNumber) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Okies! The task has been marked as undone:");
        System.out.println("  " + taskList.get(taskNumber - 1).toString());
        System.out.println(DIVIDER_LINE);
    }

    public void printDeleteMsg(TaskList taskList, int taskNumber) {
        System.out.println(DIVIDER_LINE);
        System.out.println("Alright! The task has been deleted:");
        System.out.println("  " + taskList.get(taskNumber - 1).toString());
        System.out.println(DIVIDER_LINE);
    }

    public void printTaskAdded(TaskList taskList) {
        // index of most recently added item
        int index = taskList.size() - 1;
        System.out.println(DIVIDER_LINE);
        System.out.println("Alright! Task added:\n  " + taskList.get(index).toString());
        System.out.println("You now have " + (taskList.size()) + " task(s) in the list.");
        System.out.println(DIVIDER_LINE);
    }


}
