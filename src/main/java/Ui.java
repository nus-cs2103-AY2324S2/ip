import java.util.Scanner;

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

    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
