import java.util.Scanner;

public class Arona {
    private TaskList tasks;
    Arona() {
        tasks = new TaskList();
    }

    private void greet() {
        System.out.println("Welcome, sensei! Arona has been waiting for you.");
    }

    private void exit() {
        System.out.println("Thanks for the hard work, Sensei.");
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Sensei! There are no tasks. " +
                    "Take care of your health too. It's gotta take priority!");
        } else {
            System.out.println("Sensei! Pick a task. I'll back you up!");
        }
        tasks.printElements();
    }

    private void addTask(String str) {
        Task task = new Task(str);
        tasks.addElements(task);
        System.out.println("Noted, I have added this task");
        System.out.println("    " + task);
        System.out.println("Now, your task list has " + tasks.taskCount() + " task"
                + (tasks.taskCount() == 1 ? "" : "s") + ".");
    }

    private void addDeadline(String str, String by) {
        Deadline deadline = new Deadline(str, by);
        tasks.addElements(deadline);
        System.out.println("Noted, I have added this deadline");
        System.out.println("    " + deadline);
        System.out.println("Now, your task list has " + tasks.taskCount() + " task"
                + (tasks.taskCount() == 1 ? "" : "s") + ".");
    }

    private void addEvent(String str, String start, String end) {
        Event event = new Event(str, start, end);
        tasks.addElements(event);
        System.out.println("Noted, I have added this event");
        System.out.println("    " + event);
        System.out.println("Now, your task list has " + tasks.taskCount() + " task"
                + (tasks.taskCount() == 1 ? "" : "s") + ".");
    }

    private void markDone(int id) {
        tasks.markIndexAsDone(id);
        System.out.println("Nice, I've marked this task as done!");
        System.out.println("    " + tasks.getTask(id));
    }

    private void markUndone(int id) {
        tasks.markIndexAsUndone(id);
        System.out.println("Okay, I've marked this task as not done");
        System.out.println("    " + tasks.getTask(id));
    }

    private boolean processCommand(String command) throws AronaIncompleteCommandException, AronaInvalidIndexException, AronaInvalidCommandException {
        String[] commandSplit = command.split(" ", 2);

        if (commandSplit[0].equalsIgnoreCase("bye")) {
            return false;
        } else if (commandSplit[0].equalsIgnoreCase("list")) {
            listTasks();
        } else if (commandSplit[0].equalsIgnoreCase("mark")) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (Integer.parseInt(commandSplit[1]) <= 0 || Integer.parseInt(commandSplit[1]) > tasks.taskCount()) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.taskCount()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            markDone(index);
        } else if (commandSplit[0].equalsIgnoreCase("unmark")) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("index number");
            }

            if (Integer.parseInt(commandSplit[1]) <= 0 || Integer.parseInt(commandSplit[1]) > tasks.taskCount()) {
                throw new AronaInvalidIndexException(Integer.toString(tasks.taskCount()));
            }

            int index = Integer.parseInt(commandSplit[1]) - 1;
            markUndone(index);
        } else if (commandSplit[0].equalsIgnoreCase("todo")) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            addTask(commandSplit[1]);
        } else if (commandSplit[0].equalsIgnoreCase("deadline")) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            String[] deadlineSplit = commandSplit[1].split(" /by ");

            if (deadlineSplit.length == 1 || deadlineSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("deadline time");
            }

            addDeadline(deadlineSplit[0], deadlineSplit[1]);
        } else if (commandSplit[0].equalsIgnoreCase("event")) {
            if (commandSplit.length == 1 || commandSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("task description");
            }

            String[] eventSplit = commandSplit[1].split(" /from ");

            if (eventSplit.length == 1 || eventSplit[1].equals("")) {
                throw new AronaIncompleteCommandException("start time");
            }

            String[] eventSplitTime = eventSplit[1].split(" /to ");

            if (eventSplitTime.length == 1 || eventSplitTime[1].equals("")) {
                throw new AronaIncompleteCommandException("end time");
            }

            addEvent(eventSplit[0], eventSplitTime[0], eventSplitTime[1]);
        } else {
            throw new AronaInvalidCommandException("");
        }
        return true;
    }

    public static void main(String[] args) {
        Arona arona = new Arona();
        Scanner scanner = new Scanner(System.in);
        String command;

        arona.greet();

        while (true) {
            command = scanner.nextLine();
            try {
                if (!arona.processCommand(command))
                    break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        arona.exit();
        scanner.close();
    }
}
