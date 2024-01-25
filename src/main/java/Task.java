import java.util.Scanner;
public class Task {
    private String taskName;
    private static String taskList[] = new String[100];

    private static boolean done[] = new boolean[100];
    private static int counter = 0;

    public Task() {


    }

    public Task(String taskName) {
        this.taskName = taskName;
        if (this.taskName.equals("list")) {

        }

        else if (this.taskName.equals("mark")) {

        }

        else if (this.taskName.equals("unmark")) {

        }
        else {
            Task.taskList[counter] = this.taskName;
            Task.counter++;
        }
    }

    public void display() {

        System.out.println("----------------------------------------------------------");
        for (int i = 0; i < Task.counter; i++) {
            if (Task.done[i]) {
                System.out.println(i + 1 + ". " + "[X] " + Task.taskList[i]);
            }
            else {
                System.out.println(i + 1 + ". " + "[ ] " + Task.taskList[i]);
            }
        }
        System.out.println("----------------------------------------------------------");
    }


    public void mark() {

        Scanner s = new Scanner(System.in);
        System.out.println("Which one should I mark? Write the label number :] ");
        int label = s.nextInt();
        Task.done[label-1] = true;
        System.out.println("----------------------------------------------------------");
        System.out.println("Nice! I've marked this task as done: \n"
        + "[X] " + Task.taskList[label-1]);
        System.out.println("----------------------------------------------------------");
    }

    public void unmark() {
        Scanner s = new Scanner(System.in);
        System.out.println("Which one should I mark? Write the label number :] ");
        int label = s.nextInt();
        Task.done[label-1] = false;
        System.out.println("----------------------------------------------------------");
        System.out.println("Ok, I've marked this task as not done yet: \n"
                + "[ ] " + Task.taskList[label-1]);
        System.out.println("----------------------------------------------------------");
    }
    public void add() {
        String s = "----------------------------------------------------------\n" +
                "Added: " + this.taskName

                + "\n----------------------------------------------------------";
        System.out.println(s);
    }
}
