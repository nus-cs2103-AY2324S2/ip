package youdon;

/**
 * Represents the user interface (UI) of the Youdon chatbot.
 * Provides methods to interact with the user through the console.
 */
public class Ui {

    /**
     * Constructs a new instance of the Ui class.
     */
    public Ui() {

    }

    public String getByeMsg() {
        return "Bye. Hope to see you again soon!\n";
    }

    public String getYoudonErrorMsg(String message) {
        return message;
    }

    public String getTaskList(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are your tasks:").append("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    public String getMarkMsg(TaskList tasks, int taskNumber) {
        return "Nicely done! The task has been marked as done:" + "\n" +
                "  " + tasks.get(taskNumber - 1).toString() + "\n";
    }

    public String getUnmarkMsg(TaskList tasks, int taskNumber) {
        return "Okies! The task has been marked as undone:" + "\n" +
                "  " + tasks.get(taskNumber - 1).toString() + "\n";
    }

    public String getDeleteMsg(Task task) {
        return "Alright! The task has been deleted:" + "\n" +
                "  " + task.toString() + "\n";
    }

    public String getTaskAddedMsg(TaskList tasks) {
        int index = tasks.size() - 1;
        return "Alright! Task added:\n  " + tasks.get(index).toString() + "\n" +
                "You now have " + tasks.size() + " task(s) in the list." + "\n";
    }

    public String getSnoozeMsg(TaskList tasks, int taskNumber) {
        return "Okies! The task has been snoozed by 1 day:" + "\n" +
                "  " + tasks.get(taskNumber - 1).toString() + "\n";
    }
}
