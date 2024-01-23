import java.util.*;

public class Luke {

    static String[] validCommands = {"bye", "list", "unmark", "mark", "todo", "event", "deadline"};

    private static boolean isCommandValid(String command) {
        for (String validCommand: validCommands) {
            if (command.equals(validCommand)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        String name = "Luke";

        Task[] taskList = new Task[100];

        int noTasks = 0;

        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");

        Scanner scanner = new Scanner (System.in);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] splited = input.split(" ");
            String command = splited[0];
            try {
                if (!isCommandValid(command)) {
                    throw new LukeException("Invalid command/task type.");
                }

            } catch (LukeException e) {
                System.out.println(e);
            }
            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < noTasks; i++) {
                        System.out.println((i + 1) + ". " + taskList[i].toString());
                    }
                    break;
                case "mark":
                    try {
                        int markIndex = Integer.valueOf(splited[1]);
                        if (markIndex <= 0 || markIndex > noTasks) {
                            throw new LukeException("Task does not exist. Please give a valid task number.");
                        }
                        Task markTask = taskList[markIndex - 1];
                        markTask.setToDone();
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println(markTask.toString());
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "unmark":
                    try {
                        int unmarkIndex = Integer.valueOf(splited[1]);
                        if (unmarkIndex <= 0 || unmarkIndex > noTasks) {
                            throw new LukeException("Task does not exist. Please give a valid task number.");
                        }
                        Task unmarkTask = taskList[unmarkIndex - 1];
                        unmarkTask.setToNotDone();
                        System.out.println("OK, I've marked this task as not done yet: ");
                        System.out.println(unmarkTask.toString());
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "todo":
                    try {
                        if (input.substring(4).trim().isEmpty()) {
                            throw new LukeException("Invalid command. The description cannot be empty.");
                        }
                        Todo todo = new Todo(input.substring(5));
                        taskList[noTasks] = todo;

                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo.toString());
                        noTasks++;
                        System.out.println("Now you have " + noTasks + " tasks in the list.");
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineSplit = input.split("/");
                        if (deadlineSplit.length < 2
                                || !deadlineSplit[1].substring(0,2).equals("by")) {
                            throw new LukeException("Invalid command. Please follow the format for deadline tasks.");
                        }
                        if (deadlineSplit[0].substring(9).trim().isEmpty()) {
                            throw new LukeException("Invalid command. The description cannot be empty.");
                        }
                        if (deadlineSplit[1].trim().length() <= 2) {
                            throw new LukeException("Invalid command. The deadline cannot be empty.");
                        }
                        String deadlineDescription = deadlineSplit[0].substring(9);
                        String by = deadlineSplit[1].substring(3);

                        Deadline deadline = new Deadline(deadlineDescription, by);
                        taskList[noTasks] = deadline;

                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline.toString());
                        noTasks++;
                        System.out.println("Now you have " + noTasks + " tasks in the list.");
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;
                case "event":
                    try {
                        String[] eventSplit = input.split("/");
                        if (eventSplit.length < 3
                                || !eventSplit[1].substring(0,4).equals("from")
                                || !eventSplit[2].substring(0,2).equals("to")) {
                            throw new LukeException("Invalid command. Please follow the format for event tasks.");
                        }
                        if (eventSplit[0].substring(6).trim().isEmpty()) {
                            throw new LukeException("Invalid command. The description cannot be empty.");
                        }
                        if (eventSplit[1].trim().length() <= 4 ) {
                            throw new LukeException("Invalid command. The from section cannot be empty.");
                        }
                        if (eventSplit[2].trim().length() <= 2 ) {
                            throw new LukeException("Invalid command. The to section cannot be empty.");
                        }
                        String eventDescription = eventSplit[0].substring(6);
                        String from = eventSplit[1].substring(5);
                        String to = eventSplit[2].substring(3);

                        Event event = new Event(eventDescription, from, to);
                        taskList[noTasks] = event;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event.toString());
                        noTasks++;
                        System.out.println("Now you have " + noTasks + " tasks in the list.");
                    } catch (LukeException e) {
                        System.out.println(e);
                    }
                    break;

            }
            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");

    }
}
