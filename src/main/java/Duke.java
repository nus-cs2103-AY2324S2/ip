import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static String line = "    ____________________________________________________________";
    public static void main(String[] args) throws EmptyTaskNameException, NoTaskTypeException{
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
                    try {
                        String todoName = sc.nextLine();
                        checkEmptyTask(todoName);
                        addTask(new ToDo(todoName.trim()));
                    } catch (EmptyTaskNameException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline" :
                    try {
                        String[] splitDeadline = sc.nextLine().split(" /by ");
                        checkEmptyTask(splitDeadline[0]);
                        addTask(new Deadline(splitDeadline[0].trim(), splitDeadline[1]));
                    } catch (EmptyTaskNameException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event" :
                    try {
                        String[] splitName = sc.nextLine().split(" /from ");
                        checkEmptyTask(splitName[0]);
                        String[] startEnd = splitName[1].split(" /to ");
                        addTask(new Event(splitName[0].trim(), startEnd[0], startEnd[1]));
                    } catch (EmptyTaskNameException e) {
                        System.out.println(e.getMessage());
                    }
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
                    try {
                        throw new NoTaskTypeException();
                    } catch (NoTaskTypeException e) {
                        System.out.println(e.getMessage());
                    }
            }
            command = sc.next();
        }

        sc.close();

        System.out.printf("%s\n     Bye. Hope to see you again soon!\n%s",
                line, line);
    }

    public static void checkEmptyTask(String taskName) throws EmptyTaskNameException {
        if (taskName.trim().isEmpty()) {
            throw new EmptyTaskNameException();
        }
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