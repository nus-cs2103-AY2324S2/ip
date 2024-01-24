import java.util.Scanner;

public class Arona {
    private TaskList tasks;
    Arona() {
        tasks = new TaskList();
    }
    private void greet() {
        System.out.println("Welcome, sensei! Arona has been waiting for you.\n");
    }

    private void exit() {
        System.out.println("Thanks for the hard work, Sensei.\n");
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks.");
        } else {
            System.out.println("There's lots of work to do, but I know you can do it!");
        }
        tasks.printElements();
    }

    private void addTask(String str) {
        Task task = new Task(str);
        tasks.addElements(task);
        System.out.println("Noted, I have added this task.");
        System.out.println("    " + task);
        System.out.println("Now, your task list has " + tasks.taskCount() + " tasks");
    }

    private void addDeadline(String str, String by) {
        Deadline deadline = new Deadline(str, by);
        tasks.addElements(deadline);
        System.out.println("Noted, I have added this task with deadline.");
        System.out.println("    " + deadline);
        System.out.println("Now, your task list has " + tasks.taskCount() + " tasks");
    }

    private void addEvent(String str, String start, String end) {
        Event event = new Event(str, start, end);
        tasks.addElements(event);
        System.out.println("Noted, I have added this event.");
        System.out.println("    " + event);
        System.out.println("Now, your task list has " + tasks.taskCount() + " tasks");
    }

    private void markDone(int id) {
        tasks.markIndexAsDone(id);
        System.out.println("Nice, I've marked this task as done!");
        System.out.println("    " + tasks.getTask(id));
    }

    private void markUndone(int id) {
        tasks.markIndexAsUndone(id);
        System.out.println("Okay, I've marked this task as not done.");
        System.out.println("    " + tasks.getTask(id));
    }

    public static void main(String[] args) {
        Arona arona = new Arona();
        Scanner scanner = new Scanner(System.in);
        String command;

        arona.greet();

        while (true) {
            command = scanner.nextLine();
            String[] commandSplit = command.split(" ", 2);

            if (commandSplit[0].toLowerCase().equals("bye")) {
                break;
            } else if (commandSplit[0].toLowerCase().equals("list")) {
                arona.listTasks();
            } else if (commandSplit[0].toLowerCase().equals("mark")) {
                int index = Integer.parseInt(commandSplit[1]) - 1;
                arona.markDone(index);
            } else if (commandSplit[0].toLowerCase().equals("unmark")) {
                int index = Integer.parseInt(commandSplit[1]) - 1;
                arona.markUndone(index);
            } else if (commandSplit[0].toLowerCase().equals("todo")) {
                arona.addTask(commandSplit[1]);
            } else if (commandSplit[0].toLowerCase().equals("deadline")) {
                String[] deadlineSplit = commandSplit[1].split(" /by ");
                arona.addDeadline(deadlineSplit[0], deadlineSplit[1]);
            } else if (commandSplit[0].toLowerCase().equals("event")) {
                String[] eventSplit = commandSplit[1].split(" /from ");
                String[] eventSplitTime = eventSplit[1].split(" /to ");
                arona.addEvent(eventSplit[0], eventSplitTime[0], eventSplitTime[1]);
            }
        }

        arona.exit();
        scanner.close();
    }
}
