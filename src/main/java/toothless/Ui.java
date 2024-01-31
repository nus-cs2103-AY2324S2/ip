package toothless;

import java.util.Scanner;
import toothless.tasks.*;

public class Ui {

    private String splitLine = "____________________________________________________________";
    private String chatBotName = "Toothless";
    private String greetingString = "Hi! "+ chatBotName +" is " + chatBotName + "!\n"
            + "What can " + chatBotName + " do for human?\n" + splitLine;
    private String exitString = "Bye. Hope to see you again soon!";

    public void showWelcome() {
        System.out.println(greetingString);
    }

    public void showFarewell() {
        System.out.println(exitString);
    }

    public void showLine() {
        System.out.println(splitLine);
    }

    public void showTask(Task task) {
        System.out.println(" ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task);
    }

    public void showTask(Task task, int index) {
        System.out.format("%d. ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task + "\n", index + 1);
    }

    public void showLoadingTasks() {
        System.out.println("Loading previous recorded tasks...\n" + splitLine);
    }

    public void showIncompleteTask(TaskList tasks) {
        System.out.println("You have these unmarked tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i);
            if (!t.isDone()) {
                this.showTask(t);
            }
        }
        this.showLine();
    }

    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
