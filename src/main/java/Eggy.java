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
                if (command.equals("list")) {
                    showList(taskList);
                } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                    throw new IncompleteTaskException(command);
                } else if (commandArr.length <= 1) {
                    throw new InvalidCommandException();
                } else if (commandArr[0].equals("done")) {
                    taskList.get(Integer.parseInt(commandArr[1]) - 1).markDone();
                } else if (commandArr[0].equals("mark")) {
                    taskList.get(Integer.parseInt(commandArr[1]) - 1).markDone();
                } else if (commandArr[0].equals("unmark")) {
                    taskList.get(Integer.parseInt(commandArr[1]) - 1).unmarkDone();
                } else if (commandArr[0].equals("todo")) {
                    Todo newTodo = new Todo(commandArr[1]);
                    taskList.add(newTodo);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newTodo.toString());
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                } else if (commandArr[0].equals("deadline")) {
                    String[] deadlineSplit = commandArr[1].split(" /by ");
                    Deadline newDeadline = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                    taskList.add(newDeadline);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newDeadline.toString());
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                } else if (commandArr[0].equals("event")) {
                    String[] eventSplit = commandArr[1].split(" /from | /to ");
                    Event newEvent = new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
                    taskList.add(newEvent);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newEvent.toString());
                    System.out.println("     Now you have " + taskList.size() + " tasks in the list.");
                } else {
                    throw new EggyException("");
                }
            } catch (EggyException e) {
                System.out.println("     " + e.getMessage());
            }
            System.out.println("    ____________________________________________________________");
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye\uD83D\uDC4B. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }

    public static void showList(List<Task> taskList) {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i).toString());
        }
    }
}
