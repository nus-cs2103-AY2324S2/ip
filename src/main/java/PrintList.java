import java.util.ArrayList;

public class PrintList extends Reply {
    private ArrayList<String> tasks;

    public PrintList(ArrayList<String> tasks) {
        super("Here are the tasks in your list:");
        this.tasks = tasks;
    }

    public void displayMessage() {
        String message = "";
        for (int i = 0; i < tasks.size(); i++) {
            String taskLine = (i + 1) + ". " + tasks.get(i);
            if (i == 0) {
                message = taskLine;
            } else {
                message = message.concat("\n      " + taskLine);
            }
        }
        Reply replyToUser = new Reply(message);
        replyToUser.displayMessage();
    }
}
