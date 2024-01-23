import java.io.*;
import java.util.*;
public class Duke {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("================================ \n" +
                "Hello I'm Axolotl! \n" +
                "What can I do for you? \n" +
                "================================ \n");

        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println( "--------------------------------");
                for (int i = 0; i < list.size(); i++) {
                    Task iTask = list.get(i);
                    System.out.println((i+1) + ". " + iTask.getStatusIcon()  + " " + iTask.getTaskName());
                }
                System.out.println( "-------------------------------- \n");
            }
            else if (input.contains("mark")) {
                String[] inputSplit = input.split(" ");
                int taskNo = Integer.parseInt(inputSplit[1]) - 1;
                Task dTask = list.get(taskNo);

                if (inputSplit[0].equals("mark")) {
                    dTask.done();

                    System.out.println("-------------------------------- \n" +
                            "Nice! I've marked task " + taskNo + " as done: \n" +
                            dTask.getStatusIcon() + " " + dTask.getTaskName() + "\n" +
                            "-------------------------------- \n");
                }
                else {
                    dTask.undone();

                    System.out.println("-------------------------------- \n" +
                            "Sure, I've marked task " + taskNo + " as not done yet: \n" +
                            dTask.getStatusIcon() + " " + dTask.getTaskName() + "\n" +
                            "-------------------------------- \n");
                }
            }
            else {
                Task t = new Task(input);
                list.add(t);
                System.out.println( "-------------------------------- \n" +
                                    "added: " + t.getTaskName() + "\n" +
                                    "-------------------------------- \n");
            }
            input = sc.nextLine();
        }

        System.out.println("================================ \n" +
                "Bye. Hope to see you again soon! \n" +
                "================================ \n");
    }
}
