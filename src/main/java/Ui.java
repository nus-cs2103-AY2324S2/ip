import java.util.ArrayList;
import java.util.Scanner;

// deals with interactions with the user
public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void sendGreeting() {
        System.out.println("Hello I am DylanBot! \nWhat can I do for you?");
    }

    public void sendExit() {
        System.out.println("Bye! Hope to see you again soon");
    }

    public String takeInput() throws DylanBotException {
        String input = null;
        try {
            input = sc.nextLine();
            if (input.isEmpty()) {
                throw new DylanBotException("HEY no input BETTER SAY SOMETHING");
            }
        } catch (DylanBotException e) {
            System.out.println(e);
        }
        return input;
    }

    public void displayTasks(TaskList tl) {
        ArrayList<Task> tasks = tl.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task curr = tasks.get(i);
            System.out.println("\t" + (i + 1) + ". " + curr.toString());
        }
    }

    public void print(String msg) {
        System.out.println(msg);
    }

    public void displayError(DylanBotException e) {
        System.out.println(e);
    }

    public void displayLoadError() {
        System.out.println("ERROR: Issue loading data from file");
    }

    public void displayIOError() {
        System.out.println("ERROR: Issue with IO");
    }

}
