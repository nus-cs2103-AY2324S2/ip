import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ragdoll{
    private static String USER = "Master";
    private static List<Task> tasks = new ArrayList<>();
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " /$$$$$$$                            /$$           /$$ /$$\n"
                + "| $$__  $$                          | $$          | $$| $$\n"
                + "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$ | $$| $$\n"
                + "| $$$$$$$/ |____  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$\n"
                + "| $$__  $$  /$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$| $$\n"
                + "| $$  \\ $$ /$$__  $$| $$  | $$| $$  | $$| $$  | $$| $$| $$\n"
                + "| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$| $$\n"
                + "|__/  |__/ \\_______/ \\____  $$ \\_______/ \\______/ |__/|__/\n"
                + "                     /$$  \\ $$\n"
                + "                    |  $$$$$$/\n"
                + "                     \\______/\n";

        System.out.println("Hello! I am your virtual assistant\n" + "\n" + logo);

        System.out.println("How can I assist you today, " + USER + "?");

        System.out.println(LINE);

        while(true) {
            String input = scanner.nextLine().trim();
            System.out.println(LINE);

            String[] parts = input.split(" ", 2);
            Commands command;

            try {
                command = Commands.valueOf(parts[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                command = Commands.ADD;
            }

            switch (command) {
                case BYE:
                    scanner.close();
                    System.out.println("See ya, " + USER + "!");
                    System.out.println(LINE);
                    return;
                case LIST:
                    listTasks();
                    break;
                case MARK:
                    markTask(input);
                    break;
                case UNMARK:
                    unmarkTask(input);
                    break;
                case DELETE:
                    deleteTask(input);
                    break;
                case ADD:
                    addTask(input);
                    break;
            }

            System.out.println(LINE);
        }
    }

    private static void addTask(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            System.out.println("Sorry, " + USER + ", please format your tasks as [task type] [task description]!\n" +
                    "I am only able to handle 3 task types: todo, deadline, and event.");
            return;
        }

        TaskType taskType;
        Task task = null;

        try {
            taskType = TaskType.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry, " + USER + ", please format your tasks as [task type] [task description]!\n" +
                    "I am only able to handle 3 task types: todo, deadline, and event.");
            return;
        }

        switch (taskType) {
            case TODO:
                task = new ToDo(parts[1]);
                break;
            case DEADLINE:
                String[] info = parts[1].split(" /by ", 2);
                if (info.length < 2) {
                    System.out.println("Sorry, " + USER + ", please use 'deadline [task] /by [date]'.");
                    return;
                }
                task = new Deadline(info[0], info[1]);
                break;
            case EVENT:
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts.length < 2) {
                    System.out.println("Sorry, " + USER + ", please use 'event [task] /from [start time] /to [end time]'.");
                    return;
                }
                String[] timeParts = eventParts[1].split(" /to ", 2);
                if (timeParts.length < 2) {
                    System.out.println("Sorry, " + USER + ", please use 'event [task] /from [start time] /to [end time]'.");
                    return;
                }
                task = new Event(eventParts[0], timeParts[0], timeParts[1]);
                break;
        }

        if (task != null) {
            tasks.add(task);
            System.out.println(USER + ", I've added this task:\n  " + task);

            int taskCount = tasks.size();
            if (taskCount <= 1) {
                System.out.println("Now you have " + taskCount + " task in the list, " + USER + "!");
            } else {
                System.out.println("Now you have " + taskCount + " tasks in the list, " + USER + "!");
            }
        }
    }

    public static void deleteTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            int taskCount = tasks.size();

            if (idx < 0 || idx >= taskCount) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                Task removed = tasks.remove(idx);
                taskCount--;

                System.out.println(USER + ", I've removed this task:\n  " + removed);
                if (taskCount <= 1) {
                    System.out.println("Now you have " + taskCount + " task in the list, " + USER + "!");
                } else {
                    System.out.println("Now you have " + taskCount + " tasks in the list, " + USER + "!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as delete [task index].");
        }
    }

    private static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("There is no task yet, " + USER + "!");
        } else {
            System.out.println(USER + ", your task list has the following tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void markTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(5)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                tasks.get(idx).mark();
                System.out.println(USER + "! I've marked this task as done:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as mark [task index].");
        }
    }

    private static void unmarkTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                tasks.get(idx).unmark();
                System.out.println("Ok, " + USER + "! I've undone this task:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as mark [task index].");
        }
    }
}
