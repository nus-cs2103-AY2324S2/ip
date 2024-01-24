import java.util.Scanner;
public class Ken {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) throws KenException {
        //greet
        System.out.println("Hi Barbie!");
        System.out.println("I'm Ken!");

        System.out.println("What would you like to beach today?\n");

        //create scanner
        Scanner scanner = new Scanner(System.in);
        String command;

        //response
        do {
            command = scanner.nextLine();

            if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("mark ")) {
                markTask(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("unmark ")) {
                unmarkTask(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("todo ")) {
                addTodoTask(command.substring(5));
            } else if (command.startsWith("deadline ")) {
                addDeadlineTask(command.substring(9));
            } else if (command.startsWith("event ")) {
                addEventTask(command.substring(6));
            } else if (command.startsWith("todo")) {
                System.out.println("do what?\n");
            } else if (command.startsWith("deadline")) {
                System.out.println("oh no! which line died?\n");
            } else if (command.startsWith("event")) {
                System.out.println("where you going?\n");
            } else {
                System.out.println("don't know what that is\n");
            }

        } while (!command.equalsIgnoreCase("bye"));


        //byee
        System.out.println("Beach off!\n");

        scanner.close();
    }

    private static void echoCommand(String command) {
        System.out.println(command);
    }

    private static void addTask(Task task) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = task;
            System.out.println("Got it!");
            System.out.println("added task: " + task);
            System.out.println("Now Barbie has " + taskCount + " tasks in list\n");

        } else {
            System.out.println("Way too many too many tasks for today Barbie!");
            System.out.println("Slow the Slayy\n");

        }
    }

    private static void listTasks() {

        System.out.println("Hold my ice cream,");

        if (tasks.length == 1) {
            System.out.println("actually, wait, i'm taking my ice cream back");
            System.out.println("no tasks yet");
        } else {
            System.out.println("Your tasks for today: \n");

            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks[i]);
            }
        }
    }

    private static void markTask(int index) {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].markAsDone();
            System.out.println("SUBLIME! Task " + index + " completed!\n " + tasks[index - 1].toString());
        } else {
            System.out.println("Barbie has no task " + index);
        }
    }

    private static void unmarkTask(int index) {
        if (index >= 1 && index <= taskCount) {
            tasks[index - 1].unmarkAsDone();
            System.out.println("ookayy, so task " + index + " is not actually done\n " + tasks[index - 1].toString());
            System.out.println("You are not doing task very well :(");

        } else {
            System.out.println("Barbie has no task " + index);
        }
    }

    private static void addTodoTask(String description) throws KenException {
        if (description.isEmpty()) {
            throw new KenException("do what?");
        }
        Todo todo = new Todo(description);
        addTask(todo);
    }

    private static void addDeadlineTask(String description) throws KenException {
        try {
            int indexOfBy = description.indexOf("/by");
            if (indexOfBy != -1) {
                String deadlineDescription = description.substring(0, indexOfBy).trim();
                String by = description.substring(indexOfBy + 3).trim();
                Deadline deadline = new Deadline(deadlineDescription, by);
                addTask(deadline);
            } else {
                System.out.println("That's not how you declare a deadline. p.s. use /by.");
                throw new KenException("Invalid deadline command. By when?.");
            }
        } catch (Exception e) {
            throw new KenException("Invalid deadline command. By when?.");
        }
    }

    private static void addEventTask(String description) throws KenException {
        try {
            int indexOfFrom = description.indexOf("/from");
            int indexOfTo = description.indexOf("/to");

            if (indexOfFrom != -1 && indexOfTo != -1) {
                String eventDescription = description.substring(0, indexOfFrom).trim();
                String from = description.substring(indexOfFrom + 5, indexOfTo).trim();
                String to = description.substring(indexOfTo + 3).trim();
                Event event = new Event(eventDescription, from, to);
                addTask(event);
            } else {
                System.out.println("That's not how you declare an event. p.s. use /from, and /to.");
                throw new KenException("Invalid event command. From when to when?");
            }
        } catch (Exception e) {
            throw new KenException("Invalid event command. From when to when?");
        }
    }
}
