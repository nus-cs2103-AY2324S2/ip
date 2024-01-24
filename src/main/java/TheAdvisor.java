import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TheAdvisor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String intro = "Hello, I am The Advisor. The one and only advisor you will ever need in your investing " +
                "journey. What can I do for you?";
        System.out.println(intro + "\n");

        // An ArrayList that stores the tasks to be done
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String str = br.readLine();
            String[] strings = str.split(" ");
            if (str.equals("bye")) {
                System.out.println("     Goodbye. Thank you for using TheAdvisor chatbox and I hope that my advice has managed" +
                        "to help you in your investing journey!");
                break;
            } else if (str.equals("list")){
                int counter = 1;
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("     " + counter + ". " + taskList.get(i).toString());
                    counter++;
                }
            } else if (strings[0].equals("mark")) {
                // 1-based indexing on input
                int number = Integer.parseInt(strings[1]);
                Task temp = taskList.get(number - 1);
                temp.markDone();
                System.out.println("     Nice! I've marked this task as done:\n" + "       " +
                        temp.toString());
            } else if (strings[0].equals("unmark")) {
                // 1-based indexing on input
                int number = Integer.parseInt(strings[1]);
                Task temp = taskList.get(number - 1);
                temp.unmark();
                System.out.println("     OK, I've marked this task as not done yet:\n" + "       " +
                        temp.toString());
            } else {
                String type = strings[0];
                if (type.equals("todo")) {
                    String task = str.substring(4);
                    ToDos toDos = new ToDos(task);
                    taskList.add(toDos);
                    System.out.println("     Got it. I've added this task:\n" +
                            "       " + toDos.toString() + "\n" +
                            "     Now you have " + taskList.size() +
                            " tasks in the list.");
                } else if (type.equals("deadline")) {
                    String task = str.substring(8);
                    String[] arrTask = task.split(" /by ");
                    Deadline deadline = new Deadline(arrTask[0], arrTask[1]);
                    taskList.add(deadline);
                    System.out.println("     Got it. I've added this task:\n" +
                            "       " + deadline.toString() + "\n" +
                            "     Now you have " + taskList.size() +
                            " tasks in the list.");
                } else if (type.equals("event")) {
                    String task = str.substring(5);
                    String[] arrTask = task.split(" /from ");
                    String[] timings = arrTask[1].split(" /to");
                    Events events = new Events(arrTask[0], timings[0], timings[1]);
                    taskList.add(events);
                    System.out.println("     Got it. I've added this task:\n" +
                            "       " + events.toString() + "\n" +
                            "     Now you have " + taskList.size() +
                            " tasks in the list.");
                }
            }
        }
    }
}
