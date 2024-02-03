import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }
    public void printError(DukeException e) {
        showLine();
        System.out.printf("        %s\n", e.getMessage());
        showLine();
    }

    public void echoText(String text) {
        showLine();
        System.out.printf("      %s\n", text);
        showLine();
    }

    public String readCommand() {
        return sc.nextLine();
    }
    public void printList(ArrayList<Task> list) {
        int count = 1;
        showLine();
        if (list.size() == 0) {
            System.out.println("      Nothing added to list yet!");
        }
        for (Task task : list) {
            System.out.printf("      %d. %s\n", count, task.toString());
            count++;
        }
        showLine();
    }
    public void addTaskRespond(Task task, int size) {
        showLine();
        System.out.println("      Got it! I added:");
        System.out.printf("        %s\n", task.toString());
        System.out.printf("      You now have %d tasks in your list :D\n", size);
        showLine();
    }

    public void endCommands() {
        this.sc.close();
    }
}
