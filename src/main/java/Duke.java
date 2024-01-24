import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static String line = "    ____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.printf(
                "%s\n     Hello! I'm Buto\n     What can I do for you?\n%s\n",
                line, line
        );

        String command = sc.next();

        while (!command.equals("bye")) {
            switch (command) {
                case "list" :
                    printTaskList();
                    break;
                case "todo" :
                    addTask(new ToDo(sc.nextLine()));
                    break;
                case "deadline" :
                    String[] splitDeadline = sc.nextLine().split(" /by ");
                    addTask(new Deadline(splitDeadline[0], splitDeadline[1]));
                    break;
                case "event" :
                    String[] splitName = sc.nextLine().split(" /from ");
                    String[] startEnd = splitName[1].split(" /to ");
                    addTask(new Event(splitName[0], startEnd[0], startEnd[1]));
                    break;
                case "mark" :
                    int markIndex = sc.nextInt() - 1;
                    taskList.get(markIndex).mark();
                    printResponse("Nice! I've marked this task as done:", markIndex);
                    break;
                case "unmark" :
                    int unmarkIndex = sc.nextInt() - 1;
                    taskList.get(unmarkIndex).unmark();
                    printResponse("OK, I've marked this task as not done yet:", unmarkIndex);
                    break;
                default :
                    addTask(new Task(command + sc.nextLine()));
            }
            command = sc.next();
        }

        sc.close();

        System.out.printf("%s\n     Bye. Hope to see you again soon!\n%s",
                line, line);
    }

    public static void addTask(Task newTask) {
        taskList.add(newTask);
        System.out.println(line + "\n     Got it. I've added this task:\n       " + newTask.toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.\n" + line);
    }

    public static void printTaskList() {
        System.out.println(line + "\n     Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.printf("     %d.%s\n", i, taskList.get(i - 1).toString());
        }
        System.out.println(line);
    }

    public static void printResponse(String response, int taskIndex) {
        System.out.printf("%s\n     %s\n       %s\n%s\n",
                line, response, taskList.get(taskIndex).toString(), line);
    }
}