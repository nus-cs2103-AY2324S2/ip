package youdon;

/**
 * Represents the user interface (UI) of the Youdon chatbot.
 * Provides methods to interact with the user through the console.
 */
public class Ui {
    private static final String DIVIDER_LINE = "----------------------------------------------------------------";

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
        StringBuilder sb = new StringBuilder();
        sb.append("Nicely done! The task has been marked as done:").append("\n");
        sb.append("  ").append(tasks.get(taskNumber - 1).toString()).append("\n");
        return sb.toString();
    }

    public String getUnmarkMsg(TaskList tasks, int taskNumber) {
        StringBuilder sb = new StringBuilder();
        sb.append("Okies! The task has been marked as undone:").append("\n");
        sb.append("  ").append(tasks.get(taskNumber - 1).toString()).append("\n");
        return sb.toString();
    }

    public String getDeleteMsg(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append("Alright! The task has been deleted:").append("\n");
        sb.append("  ").append(task.toString()).append("\n");
        return sb.toString();
    }

    public String getTaskAddedMsg(TaskList tasks) {
        int index = tasks.size() - 1;
        StringBuilder sb = new StringBuilder();
        sb.append("Alright! Task added:\n  ").append(tasks.get(index).toString()).append("\n");
        sb.append("You now have ").append(tasks.size()).append(" task(s) in the list.").append("\n");
        return sb.toString();
    }
}
