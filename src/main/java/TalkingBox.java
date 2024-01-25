import java.util.Scanner;
import java.util.ArrayList;

public class TalkingBox {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String NAME = "Talking Box";
        ArrayList<Task> taskList = new ArrayList<>();
        enum Tasks {
            list,
            mark,
            unmark,
            delete,
            todo,
            deadline,
            event;
        }


        System.out.println("Hello, I am the " + NAME);
        System.out.println("What tasks do you have?");

        String taskType = in.next();
        String input = in.nextLine().trim();
        while (!(taskType.equals("bye"))) {
            boolean error = false;
            try {
                boolean taskError = false;
                if (taskType.equals("list")) {
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println((i + 1) + ": " + taskList.get(i).toString());
                    }
                } else {
                    for (Tasks t : Tasks.values()) {
                        taskError = taskError || (t.name().equals(taskType));
                    }
                    if (!taskError) {
                        throw new UnknownInputException("Sorry, I don't know what that command means");
                    }
                } if (taskError && input.isEmpty()) {
                    throw new EmptyTaskException("Please provide a task name");
                }
            } catch (EmptyTaskException | UnknownInputException e) {
                System.out.println(e);
                error = true;
            }
            if (!error) {
                 switch(taskType) {
                     case "mark":
                         int entry = Integer.parseInt(input) - 1;
                         System.out.println("Task marked as done. Good job!");
                         taskList.get(entry).mark();
                         System.out.println(taskList.get(entry).toString());
                         break;
                    case "unmark":
                        int e = Integer.parseInt(input) - 1;
                        System.out.println("Alright! Task marked as not done");
                        taskList.get(e).unmark();
                        System.out.println(taskList.get(e).toString());
                        break;
                    case "delete":
                        int r = Integer.parseInt(input) - 1;
                        System.out.println("Removing task from list");
                        System.out.println(taskList.get(r).toString());
                        taskList.remove(r);
                        System.out.println("current number of tasks: " + taskList.size());
                        break;
                    case "todo":
                        taskList.add(new ToDo(input));
                        System.out.println("added task: ");
                        System.out.println(taskList.getLast().toString());
                        System.out.println("current number of tasks: " + taskList.size());
                        break;
                    case "deadline":
                        String[] d = input.split("/");
                        taskList.add(new Deadline(d[0], d[1]));
                        System.out.println("added task: ");
                        System.out.println(taskList.getLast().toString());
                        System.out.println("current number of tasks: " + taskList.size());
                        break;
                    case "event":
                        String[] v = input.split("/");
                        taskList.add(new Event(v[0], v[1], v[2]));
                        System.out.println("added task: ");
                        System.out.println(taskList.getLast().toString());
                        System.out.println("current number of tasks: " + taskList.size());
                }
            }
            taskType = in.next();
            input = in.nextLine().trim();
        }
        System.out.println("Goodbye!");
    }
}