import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        String gap = "____________________________________________________________\n";
        System.out.println(logo);
        System.out.println(gap + "Greetings! I am Aegis.\n"
                         + "How can I assist you?\n" + gap);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            if (input.toLowerCase().equals("list")) {
                String taskList = "";
                for (int i = 0; i < tasks.size(); i++) {
                    Task curr = tasks.get(i);
                    taskList += ((i+1) + ". [" + curr.getStatusIcon() + "] " + curr.description
                                + "\n");
                }
                System.out.println(gap + "Here are your tasks:\n" + taskList + gap);
            } else if (input.toLowerCase().contains("mark")) {
                StringTokenizer st = new StringTokenizer(input);
                String command = st.nextToken().toLowerCase();
                int taskNum = Integer.parseInt(st.nextToken()) - 1;
                Task selectedTask = tasks.get(taskNum);
                if (command.equals("mark")) {
                    selectedTask.markDone();
                    System.out.println(gap + "Well done, task marked as completed.\n"
                                      + "[" + selectedTask.getStatusIcon() + "] "
                                      +  selectedTask.description + "\n" + gap);
                } else {
                    selectedTask.markNotDone();
                    System.out.println(gap + "Understood, task marked as uncompleted.\n"
                            + "[" + selectedTask.getStatusIcon() + "] "
                            +  selectedTask.description + "\n" + gap);
                }
            } else {
                tasks.add(new Task(input));
                System.out.println(gap + "Added: " + input + "\n" + gap);
            }
        }
        System.out.println(gap + "Goodbye! Have a pleasant day!\n" + gap);
    }
}
