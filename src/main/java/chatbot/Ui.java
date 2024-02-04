package chatbot;
import java.util.ArrayList;
public class Ui {
    protected static final String LINE = "________________________________________________________________________________________";
    public Ui() {
    }
    public void greet() {
        this.echo("Hello! I'm Alfred\nWhat can I do for you?");
    }

    public void bye() {
        this.echo("Bye. Hope to see you again soon!");
    }

    public void echo(String input) {
        System.out.println(LINE);
        System.out.println(input);
        System.out.println(LINE);
    }
    public void printList(ArrayList<Task> taskList) {
        if (taskList == null || taskList.size() == 0) {
            this.echo("Sorry Master Bruce. There are no tasks in the list.");
            return;
        }
        System.out.println(LINE);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + ". " + taskList.get(i).toString());
        }
        System.out.println(LINE);
    }
}
