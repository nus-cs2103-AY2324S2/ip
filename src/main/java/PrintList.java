import java.util.ArrayList;

public class PrintList extends Reply {
    private ArrayList<Task> tasks;

    public PrintList(ArrayList<Task> tasks) {
        super("Here are the tasks in your list:");
        this.tasks = tasks;
    }

    public void displayMessage() {
        String message = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
            String taskLine = (i + 1) + "." + tasks.get(i).toString();
            message = message.concat("\n        " + taskLine);
        }
        Reply replyToUser = new Reply(message);
        replyToUser.displayMessage();
    }
}
