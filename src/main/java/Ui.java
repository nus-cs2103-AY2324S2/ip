import java.util.Scanner;

public class Ui {

    protected String botName;
    protected Scanner scanner;

    public Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLoadingError() {
        System.out.printf("""
                ____________________________________________________________
                 Error loading file. Creating new file...
                ____________________________________________________________
                %n""");
    }
    public void showWelcome() {
        System.out.printf("""
                ____________________________________________________________
                 Hello! I'm %s
                 What can I do for you?
                ____________________________________________________________
                %n""", botName);
    }

    public void showTaskList(String[] tasks) {
        System.out.print("""
                ____________________________________________________________
                 Here are the tasks in your list:
                """);
        for (int i = 0; i < tasks.length; i++) {
            System.out.printf(" %d.%s%n", i + 1, tasks[i]);
        }

    }

    public void showTaskMarked(Task task) {
        System.out.printf("""
                ____________________________________________________________
                 Nice! I've marked this task as done:
                   %s%n""", task);
    }

    public void showTaskUnmarked(Task task) {
        System.out.printf("""
                ____________________________________________________________
                 OK, I've marked this task as not done yet:
                   %s%n""", task);
    }
    public void showTaskAdded(Task task, int len) {
        System.out.printf("""
                ____________________________________________________________
                 Got it. I've added this task:
                   %s
                 Now you have %d tasks in the list.%n""", task, len);
    }

    public void showGoodbye() {
        System.out.printf("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                %n""");
    }

}
