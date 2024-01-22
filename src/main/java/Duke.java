import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm AcademicWeapon");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = br.readLine();
        ArrayList<Task> lst = new ArrayList<>();
        while (!input.equals("bye")) {
            String[] separatedInput = input.split(" ");
            switch (separatedInput[0]) {
                case "list":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < lst.size(); i++) {
                        System.out.println(i + 1 + "." + "[" + lst.get(i).getStatusIcon()  + "] " + lst.get(i).getDescription());
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "mark":
                    int indexToMark = Integer.parseInt(separatedInput[1]);
                    Task markTask = lst.get(indexToMark - 1);
                    markTask.markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + markTask.getDescription());
                    System.out.println("____________________________________________________________");
                    break;
                case "unmark":
                    int indexToUnmark = Integer.parseInt(separatedInput[1]);
                    Task unmarkTask = lst.get(indexToUnmark - 1);
                    unmarkTask.markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[ ] " + unmarkTask.getDescription());
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + input);
                    Task toAddTask = new Task(input);
                    lst.add(toAddTask);
                    System.out.println("____________________________________________________________");
            }
            input = br.readLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
        br.close();
    }
}
