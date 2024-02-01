import java.util.List;
import java.util.Scanner;
public class Ui {
    String line = "_______________________________________________________\n";

    Scanner sc;
    public Ui(){
        this.sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(line +
                "Hello! I'm Thames and I'll be your assistant chatbot.\n" +
                "What can I do for you today?\n" +
                line);
    }
    public void bye() {
        this.sc.close();
        System.out.println(line + "Bye. Hope to see you soon!\n" + line);
    }

    public void mark(Task task) {
        System.out.println(line + "Nice! I've marked this task as done:\n " + task + "\n" + line);
    }

    public void unmark(Task task) {
        System.out.println(line + "Ok, I've marked this task as not done yet:\n " + task + "\n" + line);
    }

    public void delete(Task task, int listSize) {
        System.out.println(line +
                "Noted. I've removed this task from the list:\n " + task +
                "\nNow you have " + listSize + " tasks in the list.\n" +
                line);
    }

    public void addTask(Task task, int listSize) {
        System.out.println(line + " added: " +
                task + "\n Now you have " + listSize + " tasks in the list\n" +
                line);
    }

    public void showList(TaskList taskList) {
        System.out.println(line);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i));
        }

        System.out.println(line);
    }

    public void showLoadingError() {
        System.out.println("It seems like you do not have a saved task list. I will be creating an empty one for you.");
    }

    public void showError(String s) {
        System.out.println(line + s + "\n" + line);
    }
    public String readCommand() {
        return sc.nextLine();
    }



}
