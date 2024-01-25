import java.util.*;

public class Eggy {
    public static void main(String[] args) throws Exception {
        String name = "Eggy";
        List<Task> taskList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm " + name + "\uD83E\uDD5A.");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            String[] commandArr = command.split(" ", 2);
            System.out.println("    ____________________________________________________________");
            try {
                validateCommand(commandArr);
                switch (commandArr[0]) {
                    case "list":
                        showList(taskList);
                        break;
                    case "delete":
                        Task task = taskList.remove(Integer.parseInt(commandArr[1]) - 1);
                        System.out.println("     Noted. I've removed this task:");
                        System.out.println("       " + task.toString());
                        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                        break;
                    case "mark":
                        taskList.get(Integer.parseInt(commandArr[1]) - 1).markDone();
                        break;
                    case "unmark":
                        taskList.get(Integer.parseInt(commandArr[1]) - 1).unmarkDone();
                        break;
                    case "todo":
                        Todo newTodo = new Todo(commandArr[1]);
                        addTask(newTodo, taskList);
                        break;
                    case "deadline":
                        String[] deadlineSplit = commandArr[1].split(" /by ");
                        Deadline newDeadline = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                        addTask(newDeadline, taskList);
                        break;
                    case "event":
                        String[] eventSplit = commandArr[1].split(" /from | /to ");
                        Event newEvent = new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
                        addTask(newEvent, taskList);
                        break;
                    default:
                        throw new EggyException("");
                }
            } catch (EggyException e) {
                System.out.println("     " + e.getMessage());
            } finally {
                System.out.println("    ____________________________________________________________");
                command = sc.nextLine();
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }

    public static void validateCommand(String[] commandArr) throws Exception {
        if (commandArr[0].equals("list")) {
            return;
        } else if (commandArr.length < 2 && (commandArr[0].equals("todo") || commandArr[0].equals("deadline")
                || commandArr[0].equals("event"))) {
            throw new IncompleteTaskException(commandArr[0]);
        } else if (commandArr.length < 2 && (commandArr[0].equals("delete") || commandArr[0].equals("mark")
                || commandArr[0].equals("unmark"))) {
            throw new IncompleteCommandException(commandArr[0]);
        } else if (!(commandArr[0].equals("todo") || commandArr[0].equals("deadline")
                || commandArr[0].equals("event") || commandArr[0].equals("delete") || commandArr[0].equals("mark")
                || commandArr[0].equals("unmark"))) {
            throw new InvalidCommandException();
        }
    }

    public static void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task.toString());
        System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void showList(List<Task> taskList) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i).toString());
        }
    }
}
