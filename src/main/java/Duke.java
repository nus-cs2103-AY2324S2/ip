import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:%n%s";
    private static final String MARK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:%n%s";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.";
    private static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:%s";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNKNOWN_COMMAND_MESSAGE = "The command '%s' is unknown. Please try again!";
    private static final String MISSING_ARGUMENT_MESSAGE = "The command you entered has missing arguments. Please try again!";
    public static ArrayList<Task> taskList = new ArrayList<>();
    private static boolean isOpen = true;

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);

        while (isOpen) {
            String userInput = sc.nextLine();
            Parser p = new Parser(userInput);
            Parser.Command command = p.getCommand();
            String arguments = p.getArgument();

            try {

                switch (command) {
                    case bye:
                        sayGoodbye();
                        isOpen = false;
                        break;
                    case list:
                        listTasks();
                        break;
                    case todo:
                        addToDoTask(arguments);
                        break;
                    case mark:
                        markTask(arguments);
                        break;
                    case unmark:
                        unmarkTask(arguments);
                        break;
                    case deadline:
                        addDeadline(arguments);
                        break;
                    case event:
                        addEvent(arguments);
                        break;
                    case invalid:
                        throw new DukeUnknownCommandException(String.format(UNKNOWN_COMMAND_MESSAGE, p.getUnknownCommand()));
                    default:
                        break;

                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

        }

        sc.close();

    }

    private static void listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%n%d.%s", i + 1, task));
        }
        String output = String.format(LIST_TASK_MESSAGE, sb);
        System.out.println(output);
    }

    public static void markTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsDone();
        String output = String.format(MARK_DONE_MESSAGE, t);
        System.out.println(output);
    }

    public static void unmarkTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsUndone();
        String output = String.format(MARK_UNDONE_MESSAGE, t);
        System.out.println(output);
    }

    public static void addToDoTask(String taskToAdd) {
        if (taskToAdd.isBlank()) {
            throw new DukeIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        Task toDo = new ToDo(taskToAdd);
        taskList.add(toDo);
        String output = String.format(ADD_TASK_MESSAGE, toDo, taskList.size());
        System.out.println(output);
    }

    public static void addDeadline(String deadlineToAdd) {
        if (deadlineToAdd.isBlank()) {
            throw new DukeIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        String[] deadlineArgs = deadlineToAdd.split(" /by ");
        Task deadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        taskList.add(deadline);
        String output = String.format(ADD_TASK_MESSAGE, deadline, taskList.size());
        System.out.println(output);
    }

    public static void addEvent(String eventToAdd) {
        if (eventToAdd.isBlank()) {
            throw new DukeIllegalArgumentException(MISSING_ARGUMENT_MESSAGE);
        }
        String[] eventArgs = eventToAdd.split(" /from ");
        String[] eventTime = eventArgs[1].split(" /to ");
        String startTime = eventTime[0];
        String endTime = eventTime[1];
        Task event = new Event(eventArgs[0], startTime, endTime);
        taskList.add(event);
        String output = String.format(ADD_TASK_MESSAGE, event, taskList.size());
        System.out.println(output);
    }

    public static void greet() {
        String output = String.format("Hello! I'm Waffles!%nWhat can I do for you?");
        System.out.println(output);
    }

    public static void sayGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

}
